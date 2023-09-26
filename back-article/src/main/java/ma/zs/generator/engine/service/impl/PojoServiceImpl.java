package ma.zs.generator.engine.service.impl;

import ma.zs.generator.GeneratorApplication;
import ma.zs.generator.engine.bean.*;
import ma.zs.generator.engine.service.facade.PojoService;
import ma.zs.generator.engine.service.util.PojoUtil;
import ma.zs.generator.engine.service.util.StringUtil;
import ma.zs.generator.project.config.FrontendConfig;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zouani
 */
@Service
public class PojoServiceImpl implements PojoService {

    private FrontendConfig frontendConfig = new FrontendConfig();
    private List<String> boolFramworkFields = Arrays.asList("enabled", "credentialsNonExpired",
            "enabled", "accountNonExpired", "accountNonLocked", "passwordChanged");


    public List<Pojo> validatePojos(List<Pojo> pojos) {
        fillPojoLists(pojos);
        List<Pojo> prepare = prepare(pojos);
        fillPojoTypes(pojos);
        fillMappedBy(pojos);
        prepareTypesAndFields(pojos);
        prepareAssociationSimpleAndComplexe(pojos);
        prepareDependencies(pojos, true);
        prepareDependenciesImportation(pojos);
        prepareViewServices(pojos);
        prepareAllGenericsIncludingTypeInListWithCondition(pojos);
        prepareStackOverFlowForGeniricField(pojos);
        diagnos(pojos);
        prepareInheritancePojo(pojos);
        return prepare;
    }

    private void prepareAllGenericsIncludingTypeInListWithCondition(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            Set<Field> fieldsGenericIncludingInnerTypeInListField = pojo.getFieldsGenericIncludingInnerTypeInListField();
            for (Field fieldGeneric : fieldsGenericIncludingInnerTypeInListField) {
                if (fieldGeneric.getTypeAsPojo().isHasTag()) {
                    pojo.setHasTagInGenericField(true);
                }
                if (fieldGeneric.getTypeAsPojo().isIgnoreFront() == false && fieldGeneric.getTypeAsPojo().getSubModule().getName().equals(pojo.getSubModule().getName())) {
                    pojo.getFieldsGenericIncludingInnerTypeInListFieldWithCondition().add(fieldGeneric);
                }
            }
        }
    }

    private void diagnos(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            for (Field field : pojo.getFields()) {
                if (field.isGeneric() || field.isList()) {
                    Pojo typeAsPojo = field.getTypeAsPojo();
                    if (typeAsPojo == null || typeAsPojo.getLabelOrReferenceOrId() == null)
                        System.out.println("************* PROBBBBB ***** ::: field = " + field + " is generic but no type as pojo founded");
                }
            }
        }
    }

    private void prepareInheritancePojo(List<Pojo> pojos) {
        String visibility = "protected";
        pojos.stream().filter(p -> p.isSubEntity()).forEach(p -> p.setAttributeVisibility(visibility));
        pojos.stream().filter(p -> p.getSuperEntity() != null && p.getSuperEntity().getName() != null)
                .forEach(p -> p.setSuperEntity(findPojoByName(p.getSuperEntity().getName(), pojos)));
    }

    private void prepareDependenciesImportation(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            for (Field field : pojo.getFields()) {
                if (!field.getTypeAsPojo().equals(pojo)) {
                    if ((field.isGeneric() && pojo.isImportData() && field.getTypeAsPojo().isImportData())) {
                        pojo.getDependenciesImportation().add(field.getTypeAsPojo());
                        pojo.getDependenciesImportationGeneric().add(field.getTypeAsPojo());
                    }
                }
                if (!field.getTypeAsPojo().equals(pojo)) {
                    if ((field.isList() && pojo.isImportData() && field.getTypeAsPojo().isImportData())) {
                        pojo.getDependenciesImportation().add(field.getTypeAsPojo());
                        pojo.getDependenciesImportationList().add(field.getTypeAsPojo());
                    }
                }
            }
        }
    }

    private void prepareStackOverFlowForGeniricField(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            for (Field field : pojo.getFields()) {
                if (field.isGeneric()) {
                    for (Field innerField : field.getTypeAsPojo().getFields()) {
                        if (innerField.isList() && !innerField.getTypeAsPojo().equals(field.getTypeAsPojo())) {
                            field.setEligibleForStackOverFlow(true);
                        }
                    }
                }
            }
        }
    }

    private void prepareViewServices(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            for (Field field : pojo.getFields()) {
                if ((field.isList() || field.isGeneric()) && !field.getTypeAsPojo().equals(pojo)) {
                    pojo.getViewServices().add(field.getTypeAsPojo());
                    if (field.isList()) {
                        for (Field innerField : field.getTypeAsPojo().getFields()) {
                            if (innerField.isGeneric() && !innerField.getTypeAsPojo().equals(pojo)) {
                                pojo.getViewServices().add(innerField.getTypeAsPojo());
                            }
                            if (innerField.getFieldOfAssociation() != null && innerField.getFieldOfAssociation().getTypeAsPojo() != null && innerField.getFieldOfAssociation().getTypeAsPojo() != null && !innerField.getTypeAsPojo().equals(pojo) && innerField.isList()) {
                                pojo.getViewServices().add(innerField.getFieldOfAssociation().getTypeAsPojo());
                            }
                        }
                    }

                }
            }
        }
    }

    private void prepareDependencies(List<Pojo> pojos, boolean includeListFields) {

        for (Pojo pojo : pojos) {
            for (Field field : pojo.getFields()) {
                if (field.isGeneric() && !field.getTypeAsPojo().equals(pojo)) {
                    pojo.getDependencies().add(field.getTypeAsPojo());
                }
                if (field.isList() && !field.getTypeAsPojo().equals(pojo)) {
                    if (includeListFields) {
                        pojo.getDependencies().add(field.getTypeAsPojo());
                    }
                    for (Field myFieldGenericInList : field.getTypeAsPojo().getFieldsGeneric()) {
                        if (!myFieldGenericInList.getTypeAsPojo().equals(pojo))
                            pojo.getDependencies().add(myFieldGenericInList.getTypeAsPojo());
                    }
                    for (Field myListFieldInList : field.getTypeAsPojo().getFields()) {
                        if (!myListFieldInList.getTypeAsPojo().equals(pojo))
                            pojo.getDependencies().add(myListFieldInList.getTypeAsPojo());
                    }

                    if (field.isAssociationComplex() || field.isFakeAssociation()) {
                        for (Field innerField : field.getTypeAsPojo().getFields()) {
                            if (!innerField.getTypeAsPojo().equals(pojo)) {
                                pojo.getDependencies().add(innerField.getTypeAsPojo());
                            }
                            if (innerField.getFieldOfAssociation() != null && !innerField.getFieldOfAssociation().getTypeAsPojo().equals(pojo)) {
                                pojo.getDependencies().add(innerField.getFieldOfAssociation().getTypeAsPojo());
                            }
                        }
                    }
                }
            }
        }
    }



    private void fillMappedBy(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            List<Field> fields = pojo.getFields();
            fields.forEach(field -> {
                if (field.isList()) {
                    if (StringUtil.isEmpty(field.getMappedBy())) {
                        field.setMappedBy(findByType(pojo.getName(), field.getTypeAsPojo()).getName());
                    }
                }

            });

        }

    }

    private Field findByType(String name, Pojo typeAsPojo) {
        List<Field> fields = typeAsPojo.getFields();
        return fields.stream().filter(f -> f.getType().getName().equals(name)).findFirst().orElse(new Field());
    }

    public List<Permission> validatePojosForPermissions(List<Permission> permissions) {
        for (Permission permission : permissions) {
            prepareRefAndIdAndLabel(permission.getPojo());
        }
        return permissions;
    }

    @Override
    public List<Permission> constructPermissions(RoleConfig r, List<Pojo> pojos) {
        List<Permission> permissions = new ArrayList<>();
        for (Pojo pojo : pojos) {
            permissions.add(new Permission(pojo.getName() + ".edit", pojo));
            permissions.add(new Permission(pojo.getName() + ".list", pojo));
            permissions.add(new Permission(pojo.getName() + ".view", pojo));
            permissions.add(new Permission(pojo.getName() + ".add", pojo));
            permissions.add(new Permission(pojo.getName() + ".delete", pojo));
        }
        r.setPermissions(permissions);
        return permissions;
    }

    @Override
    public void prepareSubModulesAndPackage(List<Pojo> pojos, RoleConfig r) {
        prepareSubModules(pojos, r);
        // preparePackageBacks(pojos,r);
        System.out.println("end prepare");
    }

    @Override
    public void prepareSubModules(List<Pojo> pojos, RoleConfig r) {
        for (Pojo pojo : pojos) {
            if (pojo.getSubModule().getFolder() == null) {
                pojo.getSubModule().setFolder("zynerator");
                extractSubModuleFolder(pojo);
            }
        }
        Map<String, List<Pojo>> subModuleMap = pojos.stream().collect(Collectors.groupingBy(e -> e.getSubModule().getFolder()));
        for (String subModuleName : subModuleMap.keySet()) {
            SubModule subModule = findSubModuleByFolder(subModuleName, pojos);
            List<Pojo> pojosOfSubModule = subModuleMap.get(subModuleName);
            subModule.setPojos(pojosOfSubModule);
            if (subModuleExistInRole(subModule, r))
                r.getSubModules().add(subModule);
        }
        System.out.println("r = " + r);
    }

    @Override
    public void preparePackageBacks(List<Pojo> pojos, RoleConfig r) {
        for (Pojo pojo : pojos) {
            if (pojo.getPackagingBack().getFolder() == null) {
                pojo.getPackagingBack().setFolder("zynerator");
                extractPackaginBackFolder(pojo);
            }
        }
        Map<String, List<Pojo>> packagingBackMap = pojos.stream().collect(Collectors.groupingBy(e -> e.getPackagingBack().getFolder()));
        for (String packagingBackName : packagingBackMap.keySet()) {
            PackaginBack packaginBack = findPackagingBackByFolder(packagingBackName, pojos);
            List<Pojo> pojosOfPackagingBack = packagingBackMap.get(packagingBackName);
            packaginBack.setPojos(pojosOfPackagingBack);
            if (packaginBackExistInRole(packaginBack, r))
                r.getPackaginBacks().add(packaginBack);
        }
        System.out.println("r = " + r);
    }

    private boolean subModuleExistInRole(SubModule subModule, RoleConfig r) {
        for (Pojo pojo : subModule.getPojos()) {
            if (pojo.getRoles().contains(r.getName()) && pojo.getSubModule().getName().equals(subModule.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean packaginBackExistInRole(PackaginBack packaginBack, RoleConfig r) {
        for (Pojo pojo : packaginBack.getPojos()) {
            if (pojo.getPackagingBack() == null || pojo.getPackagingBack().getName() == null) {
                System.out.println("pojo = " + pojo);
            }
            if (pojo.getRoles().contains(r.getName()) && packaginBack.getName() != null && packaginBack.getName().equals(pojo.getPackagingBack().getName())) {
                return true;
            }
        }
        return false;
    }

    private SubModule findSubModuleByFolder(String elementKey, List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            if (pojo.getSubModule().getFolder() != null && pojo.getSubModule().getFolder().equals(elementKey))
                return pojo.getSubModule();
        }
        return null;
    }

    private PackaginBack findPackagingBackByFolder(String elementKey, List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            if (pojo.getPackagingBack().getFolder() != null && pojo.getPackagingBack().getFolder().equals(elementKey))
                return pojo.getPackagingBack();
        }
        return null;
    }

    public List<Pojo> prepare(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            preparePojoName(pojo);
            prepareFieldFormating(pojo);
            prepareRefAndIdAndLabel(pojo);
            findFieldSimple(pojo);// here must be checked
            findFieldCompositeList(pojo);
            findFieldGeneric(pojo);
            setFieldsSimpleMinMaxAndSimple(pojo);
            setNestedPojo(pojo, pojos);
            prepareAllTypesIncludingTypeInList(pojo);
            preparePojoBoolean(pojo);
            prepareFormatedName(pojo);
            prepareNmberField(pojo);
            prepareOrderByClause(pojo);
        }
        return pojos;
    }


    private void prepareOrderByClause(Pojo pojo) {
        String orderClause = "";
        if (pojo.isArchivable()) {
            if (pojo.getLabel() != null && pojo.getReference() != null) {
                orderClause = " ORDER BY item." + pojo.getLabel().getName() + " ASC,  item." + pojo.getReference().getName() + " ASC ";
            } else if (pojo.getLabel() != null && pojo.getReference() == null) {
                orderClause = " ORDER BY item." + pojo.getLabel().getName() + " ASC ";
            } else if (pojo.getLabel() == null && pojo.getReference() != null) {
                orderClause = " ORDER BY item." + pojo.getReference().getName() + " ASC ";
            }
        } else if (pojo.getOrder() != null) {
            orderClause = "ORDER BY item." + pojo.getOrder().getName() + " ASC ";
        }
        pojo.setOrderByClause(orderClause);

    }

    private void prepareFieldFormating(Pojo pojo) {
        List<Field> fields = pojo.getFields();
        for (Field field : fields) {
            String name = field.getType().getSimpleName().trim();
            field.getType().setSimpleName(name);
            field.getType().setName(name);
        }
    }

    private void prepareAssociationSimpleAndComplexe(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {//pojo=EnsEtForm
            for (Field field : pojo.getFields()) {
                field.setColumnStyle(pojo.getColumnStyle());
                if (field.isList() && field.getPojo() != null) {// && field.getPojo().isAssociation()) {
                    //field=ensignementEtformationPays, ensignments, formations
                    field.getPojo().setAssociation(true); // hi zdetha
                    List<Field> fieldsOfListType = field.getPojo().getFields();//fieldsOfListType= enjeuxIrdEnseignement ...
                    if (fieldsOfListType.size() >= 7) {
                        field.setCreateAndListPageInOneTab(false);
                        field.setColumnStyle(4);
                    } else {
                        field.setCreateAndListPageInOneTab(true);
                        field.setColumnStyle(6);
                    }
                    if (fieldsOfListType.size() == 3) {
                        Field featchFieldNonIdNonPojo = featchFieldNonIdNonPojo(pojo, fieldsOfListType);
                        if (featchFieldNonIdNonPojo != null) {
                            pojo.getFieldsAssociation().put(field, featchFieldNonIdNonPojo);
                            field.setAssociation(true);
                            pojo.setAssociation(true);
                            field.setAssociationComplex(false);
                            field.setFakeAssociation(false);
                            field.setFieldOfAssociation(featchFieldNonIdNonPojo);
                            field.getTypeAsPojo().setIgnoreFront(true);
                        } else {
                            field.setFakeAssociation(true);
                            field.setAssociationComplex(false);
                            field.setAssociation(false);
                        }

                    } else if (fieldsOfListType.size() > 3) {
                        pojo.getFieldsAssociationComplexe().add(field);
                        field.setAssociationComplex(true);
                        field.setAssociation(false);
                        field.setFakeAssociation(false);
                    }
                } else if (field.isGeneric() && field.getTypeAsPojo() != null) {
                    if (field.getTypeAsPojo().isIgnoreFront() == true || !field.getTypeAsPojo().getSubModule().getName().equals(pojo.getSubModule().getName())) {
                        field.setHidePlusButton(true);
                    } else {
                        field.setColumnStyle(field.getColumnStyle() - 1);
                    }
                }
            }
        }
    }

    private Field featchFieldNonIdNonPojo(Pojo pojo, List<Field> fieldsOfListType) {
        return fieldsOfListType.stream().filter(e -> e.isId() != true && !pojo.getName().equals(e.getTypeAsPojo().getName()) && e.isGeneric()).findFirst().orElse(null);
    }

    private void prepareNmberField(Pojo pojo) {
        List<String> numbers = Arrays.asList("bigdecimal", "long", "integer", "int", "double", "float");
        for (Field field : pojo.getFieldsSimple()) {
            String resultat = numbers.stream().filter(e -> field.getType().getSimpleName().trim().equalsIgnoreCase(e)).findFirst().orElse(null);
            if (resultat != null) {
                field.setNombre(true);
            }
        }
    }

    private void prepareGlobalSearchFields(Pojo pojo) {
        String result = "";
        List<Field> fields = pojo.getFields();
        for (Field e : fields) {
            if (!e.isList() && !e.isLarge() && !e.getName().equals(pojo.getId().getName())) {
                if (e.isSimple()) {
                    result += "'" + e.getName() + "', ";
                } else if (e.isGeneric() || e.isList()) {
                    if (e.getTypeAsPojo() == null || e.getTypeAsPojo().getLabelOrReferenceOrId() == null) {
                        System.out.println("---------------------- Error in entity  " + pojo.getName() + " in field " + e.getName());
                    }
                    result += "'" + e.getName() + e.getTypeAsPojo().getLabelOrReferenceOrId().getName() + "', ";
                }
            }
        }
        pojo.setGlobaleFilter(result.substring(0, result.length() - 2));
    }

    private void preparePojoName(Pojo pojo) {
        String[] pojoName = pojo.getName().split("_");
        if (pojo.getName().contains(PojoConfig.getConfigEnd())) {
            pojo.setConfig(true);
        }
        if (pojo.getName().contains(PojoConfig.getRefInterne())) {
            pojo.setRefInterne(true);
        }
        if (pojo.getName().contains(PojoConfig.getState())) {
            pojo.setState(true);
        }
        if (pojo.getName().contains(PojoConfig.getActor())) {
            pojo.setActor(true);
            addActorAttribute(pojo);
        }
        if (pojo.getName().contains(PojoConfig.getArchivable())) {
            pojo.setArchivable(true);
            addArchiveAttribute(pojo);
        }


        if (pojo.getName().contains(PojoConfig.getMustValidate())) {
            pojo.setMustValidate(true);
            addValidationAttribute(pojo);
        }
        if (pojo.getName().contains(PojoConfig.getImportData())) {
            pojo.setImportData(true);
        }
        if (pojo.getName().contains(PojoConfig.getImportable())) {
            pojo.setImportable(true);
        }
        if (pojo.getName().contains(PojoConfig.getExportable())) {
            pojo.setExportable(true);
        }
        if (pojo.getName().contains(PojoConfig.getIgnoreFront())) {
            pojo.setIgnoreFront(true);
        }
        if (pojo.getName().contains(PojoConfig.getReferentiel())) {
            pojo.setEnhanced(true);
        }
        if (pojo.getName().contains(PojoConfig.getCacheable())) {
            pojo.setCacheable(true);
        }
        if (pojo.getName().contains(PojoConfig.getInitialisation())) {
            pojo.setInitialisation(true);
        }
        if (pojo.getName().contains(PojoConfig.getNoSeq())) {
            pojo.setNoSeq(true);
        }
        if (pojo.getName().contains(PojoConfig.getHistory())) {
            pojo.setHistory(true);
        }
        if (pojo.getName().contains(PojoConfig.getBaseEntity())) {
            pojo.setBaseEntity(true);
            pojo.setBaseEntityNoTable(true);
        }
        if (pojo.getName().contains(PojoConfig.getSubEntity())) {
            pojo.setSubEntity(true);
            String superEntityName = extractSuperEntity(pojoName, PojoConfig.getSubEntity());
            if (pojo.getSuperEntity() == null) {
                pojo.setSuperEntity(new Pojo());
            }
            pojo.getSuperEntity().setName(superEntityName);
        }

        if (pojo.getName().contains(PojoConfig.getRoles())) {
            List<String> roles = extractRoles(pojoName, PojoConfig.getRoles());
            pojo.setRoles(roles);
        }


        if (pojo.getName().contains(PojoConfig.getProcess())) {
            String[] processAsArray = findStringStartWith(pojoName, PojoConfig.getProcess());
            pojo.setDomainProcesses(convertToProcess(processAsArray));
            constructProcessAndRoles(pojo);
        }

        if (pojo.getName().contains(PojoConfig.getSubModuleFolder())) {
            if (pojo.getName().contains(PojoConfig.getSubModuleShared())) {
                pojo.getSubModule().setShared(true);
            }
            extractSubModuleFolder(pojo);
            pojo.getSubModule().setName(pojo.getSubModule().getFolder());
            pojo.getSubModule().setLink(pojo.getSubModule().getFolder());
            pojo.getSubModule().setRoutting(pojo.getSubModule().getFolder());
        }
        if (pojo.getName().contains(PojoConfig.getPackagingBackFolder())) {
            extractPackaginBackFolder(pojo);
            pojo.getPackagingBack().setName(pojo.getPackagingBack().getFolder());
        }
        pojo.setName(pojoName[0]);
    }



    private List<DomainProcess> convertToProcess(String[] processAsArray) {
        List<DomainProcess> res = new ArrayList<>();
        if (processAsArray != null) {
            for (String value : processAsArray) {
                res.add(new DomainProcess(value));
            }
        }
        return res;
    }
    private void constructProcessAndRoles(Pojo pojo) {
        if (pojo.getDomainProcesses() != null) {
            for (DomainProcess domainProcess : pojo.getDomainProcesses()) {
                if (domainProcess.getName().contains("[")) {
                    String[] split = domainProcess.getName().split("\\[");
                    domainProcess.setName(split[0]);
                    String[] roles = split[1].replace("]", "").split(",");
                    domainProcess.setRoles(Arrays.asList(roles));
                } else {
                    domainProcess.setRoles(pojo.getRoles());
                }
            }
        }
    }
    private List<String> extractRoles(String[] pojoName, String startWith) {
        String processWithout_ = startWith.substring(1, startWith.length());// to eliminate _ from _Process
        List<String> result = new ArrayList<>();
        for (String value : pojoName) {
            if (value.startsWith(processWithout_)) {
                String[] split = value.replace(processWithout_, "").replace("(", "").replace(")", "").split(",");
                if (split != null) {
                    return Arrays.asList(split);
                }
            }
        }
        return result;
    }

    private String extractSuperEntity(String[] pojoName, String startWith) {
        String processWithout_ = startWith.substring(1, startWith.length());// to eliminate _ from _Process
        String result = null;
        for (String value : pojoName) {
            if (value.startsWith(processWithout_)) {
                result = value.replace(processWithout_, "").replace("(", "").replace(")", "");
            }
        }
        return result;
    }

    private String[] findStringStartWith(String[] pojoName, String startWith) {
        String processWithout_ = startWith.substring(1, startWith.length());// to eliminate _ from _Process
        for (String value : pojoName) {
            if (value.startsWith(processWithout_)) {
                return value.replace(processWithout_, "").replace("(", "").replace(")", "").split(";");
            }
        }
        return null;
    }

    private void extractSubModuleFolder(Pojo pojo) {
        String[] pojoTags = pojo.getName().split("_");
        String subModuleFolderWithout_ = PojoConfig.getSubModuleFolder().substring(1);
        for (String pojoTag : pojoTags) {
            if (pojoTag.startsWith(subModuleFolderWithout_)) {
                String value = pojoTag.substring(subModuleFolderWithout_.length() + 1, pojoTag.length() - 1);
                // String[] split = value.split(",");
                pojo.getSubModule().setFolder(value);
                pojo.getSubModule().setClassName(StringUtil.upperCaseFirst(value));
                return;
            }
        }
    }

    private void extractPackaginBackFolder(Pojo pojo) {
        String[] pojoTags = pojo.getName().split("_");
        String packaginBackFolderWithout_ = PojoConfig.getPackagingBackFolder().substring(1);
        for (String pojoTag : pojoTags) {
            if (pojoTag.startsWith(packaginBackFolderWithout_)) {
                String value = pojoTag.substring(packaginBackFolderWithout_.length() + 1, pojoTag.length() - 1);
                // String[] split = value.split(",");
                pojo.getPackagingBack().setFolder(value);
                pojo.getPackagingBack().setClassName(StringUtil.upperCaseFirst(value));
                return;
            }
        }
    }

    private String extractValueInTag(Pojo pojo, String elementToSearch) {
        String pojoTag = extractTagFromPojoName(pojo, elementToSearch);
        if (pojoTag != null) return pojoTag;
        else return StringUtil.formatWithSpace(pojo.getName());
    }

    private static String extractTagFromPojoName(Pojo pojo, String elementToSearch) {
        String[] pojoTags = pojo.getName().split("_");
        String elementWithout_ = elementToSearch.substring(1);
        for (String pojoTag : pojoTags) {
            if (pojoTag.startsWith(elementWithout_)) {
                return pojoTag.substring(elementWithout_.length() + 1, pojoTag.length() - 1);
            }
        }
        return null;
    }

    private static String extractTagWithBracesFromPojoName(Pojo pojo, String elementToSearch) {
        String[] pojoTags = pojo.getName().split("_");
        String elementWithout_ = elementToSearch.substring(1);
        for (String pojoTag : pojoTags) {
            if (pojoTag.startsWith(elementWithout_)) {
                return pojoTag.replace(elementWithout_, "").replace("(", "").replace(")", "");
            }
        }
        return null;
    }

    private void addArchiveAttribute(Pojo pojo) {
        pojo.getFields().add(new Field("archive", "Boolean"));
        pojo.getFields().add(new Field("dateArchivage", "Date"));
        pojo.getFields().add(new Field("dateCreation", "Date NOT-IN-CREATE_NOT-IN-EDIT"));

    }

    private void addValidationAttribute(Pojo pojo) {
        pojo.getFields().add(new Field("admin", "Boolean NOT-IN-CREATE_NOT-IN-EDIT"));
        pojo.getFields().add(new Field("username", "String NOT-IN-CREATE_NOT-IN-EDIT"));
        pojo.getFields().add(new Field("visible", "Boolean"));
    }

    private void addActorAttribute(Pojo pojo) {
        pojo.getFields().add(new Field("credentialsNonExpired", "Boolean"));
        pojo.getFields().add(new Field("enabled", "Boolean"));
        pojo.getFields().add(new Field("accountNonExpired", "Boolean"));
        pojo.getFields().add(new Field("accountNonLocked", "Boolean"));
        pojo.getFields().add(new Field("passwordChanged", "Boolean"));
        pojo.getFields().add(new Field("username", "String"));
        pojo.getFields().add(new Field("password", "String"));
    }




    private void prepareFormatedName(Pojo pojo) {
        List<Field> fieldLists = pojo.getFields();
        Set<Field> fieldsGenericIncludingInnerTypeInListField = pojo.getFieldsGenericIncludingInnerTypeInListField();
        Set<Field> fieldsGeneriqueAndListAndGenericInList = pojo.getFieldsGeneriqueAndListAndGenericInList();
        List<Field> fieldsGeneric = pojo.getFieldsGeneric();

        pojo.setFormatedName(StringUtil.formatWithSpace(pojo.getName()));
        pojo.setFormatedNameLowerCase(StringUtil.formatWithSpaceLowerCase(pojo.getName()));

        String formatDb = StringUtil.formatDb(pojo.getName());
        pojo.setFormatedDataBase(formatDb);

        fieldLists.forEach(f -> f.setFormatedName(StringUtil.formatWithSpace(f.getName())));

        pojo.setFormatedUrl(StringUtil.formatUrl(pojo.getName()));
        fieldLists.forEach(f -> f.setFormatedUrl(StringUtil.formatUrl(f.getName())));

        fieldsGenericIncludingInnerTypeInListField.forEach(f -> f.setFormatedUrl(StringUtil.formatUrl(f.getName())));
        fieldsGeneriqueAndListAndGenericInList.forEach(f -> f.setFormatedUrl(StringUtil.formatUrl(f.getName())));
        fieldsGeneric.forEach(f -> f.setFormatedUrl(StringUtil.formatUrl(f.getName())));

        fieldLists.forEach(e -> e.setFormatedTabPanelName(StringUtil.formatWithSpaceAndEliminateTail(e.getName(), pojo.getName())));

    }

    private List<Pojo> prepareTypesAndFields(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            prepareAllTypesIncludingTypeInList(pojo);
            prepareAllGenericsIncludingTypeInList(pojo);
            //prepareGeneriqueAndListAndGenericInList(pojo);
            prepareIndex(pojo);
            prepareGlobalSearchFields(pojo);
        }
        return pojos;
    }


    private void prepareIndex(Pojo pojo) {
        if (pojo.getTotalFields() <= 7) {
            pojo.setNombreElementInColumn(2);
        } else if (pojo.getTotalFields() <= 10) {
            pojo.setNombreElementInColumn(3);
        } else {
            pojo.setNombreElementInColumn(4);
        }
        pojo.setColumnStyle(12 / pojo.getNombreElementInColumn());
        for (int i = 0; i < pojo.getTotalFields(); i++) {
            Field field = pojo.getFields().get(i);
            field.setIndex(i + 1);
            field.setIndexMod(field.getIndex() % pojo.getNombreElementInColumn());
        }

    }

    private void prepareGeneriqueAndListAndGenericInList(Pojo pojo) {
        List<Field> fieldLists = pojo.getFields().stream().filter(e -> e.isList()).collect(Collectors.toList());
        pojo.getFieldsGeneriqueAndListAndGenericInList().addAll(pojo.getFieldsGeneric());
        pojo.getFieldsGeneriqueAndListAndGenericInList().addAll(fieldLists);
        if (fieldLists != null) {
            for (int i = 0; i < fieldLists.size(); i++) {
                Field field = fieldLists.get(i);
                List<Field> genericFields = field.getTypeAsPojo().getFieldsGeneric();
                for (Field e : genericFields) {
                    if (!pojo.getName().equals(e.getTypeAsPojo().getName())) {

                    }
                }

            }
        }
        if (fieldLists != null) {
            fieldLists.forEach(field -> {
                List<Field> genericFields = field.getTypeAsPojo().getFieldsGeneric().stream()
                        .filter(e -> !e.getTypeAsPojo().getName().equals(pojo.getName()))
                        .collect(Collectors.toList());
                pojo.getFieldsGeneriqueAndListAndGenericInList().addAll(genericFields);
            });
        }
    }

    private void prepareAllGenericsIncludingTypeInList(Pojo pojo) {
        List<Field> fieldLists = pojo.getFields().stream().filter(e -> e.isList()).collect(Collectors.toList());
        pojo.getFieldsGenericIncludingInnerTypeInListField().addAll(pojo.getFieldsGeneric());
        if (fieldLists != null) {
            for (int i = 0; i < fieldLists.size(); i++) {
                Field field = fieldLists.get(i);
                List<Field> genericFields = field.getTypeAsPojo().getFieldsGeneric();
                for (Field e : genericFields) {
                    if (!pojo.getName().equals(e.getTypeAsPojo().getName())) {

                    }
                }

            }
        }
        if (fieldLists != null) {
            fieldLists.forEach(field -> {
                if (!field.isAssociation()) {
                    List<Field> genericFields = field.getTypeAsPojo().getFieldsGeneric().stream()
                            .filter(e -> !pojo.getName().equals(e.getTypeAsPojo().getName()))
                            .collect(Collectors.toList());
                    pojo.getFieldsGenericIncludingInnerTypeInListField().addAll(genericFields);
                } else {
                    field.getFieldOfAssociation().setGenericIssuedOfAssociation(true);
                    field.getFieldOfAssociation().setFieldOrignAssociation(field);
                    pojo.getFieldsGenericIncludingInnerTypeInListField().add(field.getFieldOfAssociation());
                }

            });
        }
    }

    private void prepareAllTypesIncludingTypeInList(Pojo pojo) {
        List<Field> fieldLists = pojo.getFields().stream().filter(e -> e.isList()).collect(Collectors.toList());
        pojo.getTypesIncludingInnerTypeInListField().addAll(pojo.getTypes());
        if (fieldLists != null) {
            fieldLists.forEach(field -> {
                if (field.getTypeAsPojo() != null && field.getTypeAsPojo().getFieldsGeneric() != null) {
                    field.getTypeAsPojo().getFieldsGeneric().stream()
                            .filter(e -> !pojo.getName().equals(e.getTypeAsPojo().getName()))
                            .forEach(e ->
                                    {
                                        if (!existTypeByName(e.getName(), new HashSet<>(pojo.getTypesIncludingInnerTypeInListField())))
                                            pojo.getTypesIncludingInnerTypeInListField().add(new Type(e.getName()));
                                    }
                            );
                }

            });
        }
    }

    private boolean existTypeByName(String name, Set<Type> mySet) {
        for (Type type : mySet) {
            if (type.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    private List<Pojo> fillPojoLists(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            List<Field> fields = pojo.getFields();
            fields.parallelStream().forEach(field -> {
                if (field.isGeneric() && notExist(new ArrayList<>(pojo.getFieldsGeneric()), field)) {
                    pojo.setHasGeneric(true);
                    field.setTypeAsPojo(findPojoByName(field.getType().getName(), pojos));
                    pojo.getFieldsGeneric().add(field);
                } else if (field.isList() && notExist(new ArrayList<>(pojo.getFieldsList()), field)) {
                    if (StringUtil.isEmpty(field.getMappedBy())) {
                        field.setMappedBy(pojo.getName().toLowerCase());
                    }
                    pojo.setHasList(true);
                    Pojo myPojo = findPojoByName(field.getType().getName(), pojos);
                    field.setTypeAsPojo(myPojo);
                    pojo.getFieldsList().add(field);
                } else if (field.isSimple() && notExist(new ArrayList<>(pojo.getFieldsSimple()), field)) {
                    pojo.getFieldsSimple().add(field);
                }
            });
        }
        return pojos;
    }

    private List<Pojo> fillPojoTypes(List<Pojo> pojos) {
        for (Pojo pojo : pojos) {
            List<Field> fields = pojo.getFields();
            fields.parallelStream().forEach(field -> {
                if (field.isList()) {
                    pojo.setHasList(true);
                    field.setTypeAsPojo(findPojoByName(field.getType().getName(), pojos));
                } else if (field.isGeneric()) {
                    field.setTypeAsPojo(findPojoByName(field.getType().getName(), pojos));
                }
            });
        }
        return pojos;
    }

    private boolean notExist(List<Field> fields, Field field) {
        for (Field myField : fields) {
            if (myField.getName().equals(field.getName())) {
                return true;
            }
        }
        return false;
    }

    private Pojo findPojoByName(String pojoName, List<Pojo> pojos) {
        return pojos.stream().filter(p -> p.getName().equals(pojoName)).findFirst().orElse(null);
    }

    private void setNestedPojo(Pojo pojo, List<Pojo> pojos) {
        List<Field> fieldGenerics = pojo.getFieldsGeneric();
        List<Field> fieldLists = pojo.getFields().stream().filter(e -> e.isList()).collect(Collectors.toList());
        if (fieldGenerics != null) {
            fieldGenerics.forEach(field -> {
                Pojo nestedPojo = findPojoByName(field.getType().getSimpleName(), pojos);
                pojo.getTypes().add(field.getType());
                field.setPojo(nestedPojo);
            });
        }
        if (fieldLists != null) {
            fieldLists.forEach(field -> {
                Pojo nestedPojo = findPojoByName(field.getType().getSimpleName(), pojos);
                pojo.getTypes().add(field.getType());
                field.setPojo(nestedPojo);
            });
        }
    }

    private void preparePojoBoolean(Pojo pojo) {
        List<Field> simpleFields = pojo.getFieldsSimple();
        if (pojo.getId() != null && pojo.getId().getType() != null && pojo.getId().getType().getSimpleName() != null && pojo.getId().getType().getSimpleName().equals("String"))
            pojo.setIdString(true);
        if (pojo.getFieldsList().size() > 0)
            pojo.setHasList(true);
    }

    private void setFieldsSimpleMinMaxAndSimple(Pojo pojo) {
        List<Field> fields = pojo.getFieldsSimple();
        List<Field> fieldsSimpleMinMax = new ArrayList<>();
        List<Field> fieldsSimpleStringBolean = new ArrayList<>();
        for (Field field : fields) {
            if (PojoUtil.isNumberOrDate(field.getType().getSimpleName())) {
                fieldsSimpleMinMax.add(field);
            } else {
                fieldsSimpleStringBolean.add(field);
            }
        }
        if (!fieldsSimpleMinMax.isEmpty()) {
            pojo.setFieldsSimpleNumberOrDate(fieldsSimpleMinMax);
        }
        if (!fieldsSimpleStringBolean.isEmpty()) {
            pojo.setFieldsSimpleStringOrBoolean(fieldsSimpleStringBolean);
        }

    }


    private void findFieldSimple(Pojo pojo) {
        List<Field> fields = pojo.getFields();
        for (Field field : fields) {

            String simpleName = field.getType().getSimpleName();
            String typeTypeTrimed = simpleName.trim();
            if (PojoUtil.isPrimitif(typeTypeTrimed)) {
                field.setSimple(true);
                field.setGeneric(false);
                field.setList(false);
                if (PojoUtil.isNumber(typeTypeTrimed)) {
                    field.setNombre(true);
                }
                if (PojoUtil.isIntNumber(typeTypeTrimed)) {
                    field.setIntegerNumber(true);
                } else if (PojoUtil.isLongNumber(typeTypeTrimed)) {
                    field.setLongNumber(true);
                } else if (PojoUtil.isDoubleNumber(typeTypeTrimed)) {
                    field.setDoubleNumber(true);
                } else if (PojoUtil.isBigDecimalNumber(typeTypeTrimed)) {
                    field.setBigDecimalNumber(true);
                    pojo.setHasBigDecimal(true);
                } else if (PojoUtil.isDate(typeTypeTrimed)) {
                    field.setDate(true);
                    pojo.setHasDate(true);
                } else if (PojoUtil.isLocalDateTime(typeTypeTrimed)) {
                    field.setDateTime(true);
                    pojo.setHasDateTime(true);
                    pojo.setHasDate(true);
                } else if (PojoUtil.isLocalDate(typeTypeTrimed)) {
                    field.setLocalDate(true);
                    pojo.setHasLocalDate(true);
                    pojo.setHasDate(true);
                } else if (PojoUtil.isTime(typeTypeTrimed)) {
                    field.setTime(true);
                    pojo.setHasTime(true);
                    pojo.setHasDate(true);
                } else if (PojoUtil.isBool(typeTypeTrimed)) {
                    field.setBool(true);
                    pojo.setHasBoolean(true);
                } else if (PojoUtil.isString(typeTypeTrimed)) {
                    field.setString(true);
                    field.setPureString(true);
                }
                if (boolFramworkFields.contains(field.getName())) {
                    field.setBoolFrameWork(true);
                }

                if (field.getExtraInfo() != null && field.getExtraInfo().contains(PojoConfig.getUploadOne())) {
                    field.setUploadOne(true);
                    field.setPureString(false);
                    pojo.setUploadOne(true);
                }
                if (field.getExtraInfo() != null && field.getExtraInfo().contains(PojoConfig.getUploadMultiple())) {
                    field.setUploadMultiple(true);
                    field.setPureString(false);
                    pojo.setUploadMultiple(true);
                }
                pojo.getFieldsSimple().add(field);

            } else {
                field.setSimple(false);
                if (field.getExtraInfo() != null && field.getExtraInfo().contains(PojoConfig.getSearchMultiple())) {
                    field.setSearchOne(false);
                }
            }
            if (field.isSimple()) {
                String[] extraInfo = simpleName.split(" ");
                if (extraInfo.length == 2) {
                    field.setExtraInfo(extraInfo[1]);
                    if (field.getExtraInfo().contains(PojoConfig.getRequired())) {
                        field.setRequierd(true);
                    }
                }

            }
        }
    }

    private void findFieldGeneric(Pojo pojo) {
        List<Field> fields = pojo.getFields();
        for (Field field : fields) {
            if (!field.isSimple() && !field.isList()) {
                field.setGeneric(true);
                String[] split = field.getType().getSimpleName().split(" ");
                field.getType().setSimpleName(split[0]);
                field.getType().setName(split[0]);
                String extra = "";
                for (int i = 1; i < split.length; i++) {
                    extra += split[i];
                }
                field.setExtraInfo(extra);

                if (field.getExtraInfo().contains(PojoConfig.getRequired())) {
                    field.setRequierd(true);
                }


                // added by MoiseGui
                field.setPojo(pojo);
                field.setList(false);
                pojo.getFieldsGeneric().add(field);
            }
        }
    }

    private void findFieldCompositeList(Pojo pojo) {
        List<Field> fields = pojo.getFields();
        for (Field field : fields) {
            if (field.getExtraInfo() != null && field.getExtraInfo().contains(PojoConfig.getRequired())) {
                field.setRequierd(true);
            }
            if (PojoUtil.isList(field.getExtraInfo())) {
                field.setList(true);
                field.setGeneric(false);
                pojo.getFieldsList().add(field);
            }
        }

    }


    private void prepareRefAndIdAndLabel(Pojo pojo) {
        for (Field field : pojo.getFields()) {
            String type = field.getType().getSimpleName();
            String name = field.getName();
            if (type.contains(" ")) {
                String[] typesWithIdOrRef = type.split(" ");
                type = typesWithIdOrRef[0];
                field.getType().setSimpleName(type);
                field.getType().setName(type);
                Field myField = new Field(name, type);
                if (typesWithIdOrRef.length == 2) {
                    field.setExtraInfo(typesWithIdOrRef[1]);

                    if (typesWithIdOrRef[1].contains(PojoConfig.getFindByInDao())) {
                        myField.setFindByInclusion(true);
                        field.setFindByInclusion(true);
                        pojo.setHasFindByInclusion(true);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getDeleteByInDao())) {
                        myField.setDeleteByInclusion(true);
                        field.setDeleteByInclusion(true);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getIdDefaultName())) {
                        myField.setId(true);
                        field.setId(true);
                        pojo.setId(myField);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getTag())) {
                        myField.setTag(true);
                        field.setTag(true);
                        pojo.setTagField(field);
                        pojo.setHasTag(true);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getReference())) {
                        myField.setReference(true);
                        field.setReference(true);
                        pojo.setReference(myField);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getLabel())) {
                        myField.setLabel(true);
                        field.setLabel(true);
                        pojo.setLabel(myField);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getOrder())) {
                        myField.setOrder(true);
                        field.setOrder(true);
                        pojo.setOrder(myField);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getLarge())) {
                        myField.setLarge(true);
                        field.setLarge(true);
                        field.setPureString(false);
                        myField.setPureString(false);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getNotIncluded())) {
                        myField.setNotIncluded(true);
                        field.setNotIncluded(true);
                    }
                    if (field.getName().equalsIgnoreCase(PojoConfig.getPassword())) {
                        myField.setPassword(true);
                        field.setPassword(true);
                        pojo.setPassword(myField);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getNotVisibleInCreatePage())) {
                        myField.setNotVisibleInCreatePage(true);
                        field.setNotVisibleInCreatePage(true);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getNotVisibleInEditPage())) {
                        myField.setNotVisibleInEditPage(true);
                        field.setNotVisibleInEditPage(true);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getNotVisibleInViewPage())) {
                        myField.setNotVisibleInViewPage(true);
                        field.setNotVisibleInViewPage(true);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getNotVisibleInListPage())) {
                        myField.setNotVisibleInListPage(true);
                        field.setNotVisibleInListPage(true);
                    }
                    if (typesWithIdOrRef[1].contains(PojoConfig.getRequired())) {
                        myField.setRequierd(true);
                        field.setRequierd(true);
                        pojo.setRequiered(true);
                        pojo.getFieldsRequiered().add(field);
                    }

                } else if (typesWithIdOrRef.length == 3) {

                    String extra = typesWithIdOrRef[1] + typesWithIdOrRef[2];
                    myField.setExtraInfo(extra);
                    field.setExtraInfo(extra);

                    if (typesWithIdOrRef[2].contains(PojoConfig.getRequired())) {
                        myField.setRequierd(true);
                        field.setRequierd(true);
                        pojo.setRequiered(true);
                        pojo.getFieldsRequiered().add(field);
                    }
                }
            }

        }
        pojo.setReferenceOrId(pojo.getId());
        if (pojo.getReference() != null) {
            pojo.setReferenceOrId(pojo.getReference());
            pojo.setLabelOrReference(pojo.getReference());
        }
        pojo.setLabelOrReferenceOrId(pojo.getReferenceOrId());
        if (pojo.getLabel() != null) {
            pojo.setLabelOrReferenceOrId(pojo.getLabel());
            pojo.setLabelOrReference(pojo.getLabel());
        }


    }
}
