package ma.zs.generator.engine.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import ma.zs.generator.engine.bean.ConfigurationMs;
import ma.zs.generator.engine.bean.Field;
import ma.zs.generator.engine.bean.Pojo;
import ma.zs.generator.engine.bean.PojoConfig;
import ma.zs.generator.engine.service.facade.PojoService;
import ma.zs.generator.engine.service.facade.YamlTextPojoReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author Ouiam & Zouani
 */
@Service
public class YamlTextPojoReaderImpl implements YamlTextPojoReader {
    @Autowired
    PojoService pojoService;

    public List<Pojo> convert(String yamlAsText) throws IOException {
        Map<String, Map<String, String>> yamlPojos = parseYaml(yamlAsText);
        List<Pojo> pojos = linkPojoToField(yamlPojos);
        List<ConfigurationMs> configurationMs = adaptConfigurationElement(pojos);
        String currentMs = getCurrentMs(pojos);
        adaptInitialisation(pojos);
        eleminateConfigAndAdaptNonConfigPojo(configurationMs, currentMs, pojos);
        attachConfigMsToNonConfigPojo(pojos, configurationMs, currentMs);
        pojoService.prepare(pojos);
        return pojos;
    }

    private void adaptInitialisation(List<Pojo> pojos) {
        for (int i = 0; i < pojos.size(); i++) {
            Pojo pojo = pojos.get(i);
            if (pojo.getName().equals("$INITIALISATION")) {
                List<Field> fields = pojo.getFields();
                for (Field field : fields) {
                    Pojo myPojoToBeInitialised = findByName(field.getName(), pojos);
                    if (myPojoToBeInitialised != null) {
                        myPojoToBeInitialised.setInitialisation(true);
                        myPojoToBeInitialised.setBlockInitialisationAsStringCollectedFromYaml(field.getType().getName());
                        constructInitialisationValues(myPojoToBeInitialised);
                    }
                }
            }
        }
    }


    private void constructInitialisationValues(Pojo pojo) {
        String extracted = pojo.getBlockInitialisationAsStringCollectedFromYaml();
        String res = "";
        if (extracted != null && !extracted.isEmpty()) {
            String[] splitFirstLevels = extracted.split(";");
            for (int i = 0; i < splitFirstLevels.length; i++) {
                String splitFirstLevel = splitFirstLevels[i];
                String[] splitSecondLevels = splitFirstLevel.replace("{", "").replace("}", "").split(",");
                res += "\"{";
                for (int j = 0; j < splitSecondLevels.length; j++) {
                    String splitSecondLevel = splitSecondLevels[j];
                    String[] keyValue = splitSecondLevel.split("=");
                    res += "\\\"" + keyValue[0].replaceAll(" ", "") + "\\\" : \\\"" + keyValue[1] + "\\\"";
                    if (j < splitSecondLevels.length - 1) {
                        res += ",";
                    }
                }
                res += "\"}";
                if (i < splitFirstLevels.length - 1) {
                    res += ",";
                }
            }
            pojo.setBlockInitialisationAsString(res);
        }
    }

    private Pojo findByName(String name, List<Pojo> pojos) {
        return pojos.stream().filter(e -> e.getName().split("_")[0].equals(name)).findFirst().orElse(null);
    }

    private void attachConfigMsToNonConfigPojo(List<Pojo> pojos, List<ConfigurationMs> configurationMss, String currentMs) {
        if (configurationMss != null) {
            pojos.forEach(p -> {
                // p.setConfigurationMss(configurationMss);
                // p.setCurrentMs(currentMs);
            });
        }
    }

    private void eleminateConfigAndAdaptNonConfigPojo(List<ConfigurationMs> configurationMs, String currentMs, List<Pojo> pojos) {
        for (int i = 0; i < pojos.size(); i++) {
            Pojo pojo = pojos.get(i);
            if (pojo.getName().startsWith("$")) {
                pojos.remove(i);
                i--;
            } else {
                String msName = extractMsName(pojo, PojoConfig.getMs());
                if (msName != null) {
                    ConfigurationMs configurationMsObject = findByMsName(msName, configurationMs);
                    if (configurationMsObject != null) {
                        pojo.setMsName(msName);
                        pojo.setMsUrl(configurationMsObject.getUrl());
                        pojo.setMsPackaging(configurationMsObject.getPackaging());
                        pojo.setMsSkip(configurationMsObject.isSkip());
                        if (currentMs != null && currentMs.equals(msName)) {
                            pojo.setMsExterne(false);
                        } else if (currentMs != null && !currentMs.equals(msName)) {
                            pojo.setMsExterne(true);
                        }
                    }
                }

            }
        }

    }

    private ConfigurationMs findByMsName(String msName, List<ConfigurationMs> configurationMs) {
        if (configurationMs != null) {
            for (ConfigurationMs configuration : configurationMs) {
                if (configuration != null && msName.equals(configuration.getName())) {
                    return configuration;
                }
            }
            return null;
            //return configurationMs.stream().filter(c -> c.getName().equals(msName)).findFirst().orElse(null);
        }
        return null;
    }

    private String extractMsName(Pojo pojo, String tag) {
        String[] pojoTags = pojo.getName().split("_");
        for (String pojoTag : pojoTags) {
            if (pojoTag.startsWith(tag)) {
                String value = pojoTag.substring(tag.length() + 1, pojoTag.length() - 1);
                return value;
            }
        }
        return null;
    }

    private List<ConfigurationMs> adaptConfigurationElement(List<Pojo> pojos) throws JsonProcessingException {
        for (Pojo pojo : pojos) {
            if (pojo.getName().equals("$CONFIG")) {
                List<Field> fields = pojo.getFields();
                for (Field field : fields) {
                    if (field.getName().equals("ms")) {
                        String[] split = field.getType().getName().split(";");
                        return constructConfigurationMs(split);
                    }
                }
            }
        }
        return null;
    }

    private String getCurrentMs(List<Pojo> pojos) throws JsonProcessingException {
        for (Pojo pojo : pojos) {
            if (pojo.getName().startsWith("$")) {
                List<Field> fields = pojo.getFields();
                for (Field field : fields) {
                    if (field.getName().equals("currentMs")) {
                        return field.getType().getName();
                    }
                }
            }
        }
        return null;
    }

    private List<ConfigurationMs> constructConfigurationMs(String[] configs) {
        List<ConfigurationMs> result = new ArrayList<>();
        for (String config : configs) {
            String[] attributs = config.replace(" ", "").replace("{", "").replace("}", "").split(",");
            ConfigurationMs configurationMs = new ConfigurationMs();
            for (String attribut : attributs) {
                String[] params = attribut.split("=");
                if (params[0].equals("name")) {
                    configurationMs.setName(params[1]);
                } else if (params[0].equals("packaging")) {
                    configurationMs.setPackaging(params[1]);
                } else if (params[0].equals("url")) {
                    configurationMs.setUrl(params[1]);
                } else if (params[0].equals("skip")) {
                    configurationMs.setSkip(Boolean.parseBoolean(params[1]));
                }
            }
            result.add(configurationMs);
        }
        return result;
    }


    private Map<String, Map<String, String>> parseYaml(String yamlAsText) {
        Yaml yaml = new Yaml();
        // key: pojoName , Map : fieldName, fieldType
        Map<String, Map<String, String>> yamlPojos = (Map<String, Map<String, String>>) yaml.load(yamlAsText);
        return yamlPojos;
    }

    private List<Pojo> linkPojoToField(Map<String, Map<String, String>> yamlPojos) {
        if (yamlPojos == null)
            yamlPojos = new HashMap<>();
        final Map<String, List<Field>> pojoField = yamlPojos.entrySet().stream()
                .collect(toMap(this::pojoName, this::fields));
        return pojoField.entrySet().stream().map(e -> new Pojo(e.getKey(), e.getValue())).collect(toList());
    }

    private String pojoName(Entry<String, Map<String, String>> yamlOuterMapEntry) {
        return yamlOuterMapEntry.getKey();
    }

    private List<Field> fields(Entry<String, Map<String, String>> yamlOuterMapEntry) {
        Map<String, String> yamlFields = yamlOuterMapEntry.getValue();
        if (yamlFields == null)
            return new ArrayList<>();
        return yamlFields.entrySet().stream().map(e -> new Field(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
