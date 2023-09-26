package ma.zs.generator.engine.bean;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationMs{
    private String name;
    private String packaging;
    private String url;
    private String currentMs;
    private boolean skip=false;
    private List<Pojo> dependencies;

    public ConfigurationMs(String name, String packaging, String url,  boolean skip) {
        this.name = name;
        this.packaging = packaging;
        this.url = url;
        this.skip = skip;
    }

    public ConfigurationMs() {
    }

    public List<Pojo> getDependencies() {
        if (dependencies == null) {
            dependencies= new ArrayList<>();
        }
        return dependencies;
    }

    public void setDependencies(List<Pojo> dependencies) {
        this.dependencies = dependencies;
    }

    public String getCurrentMs() {
        return currentMs;
    }

    public void setCurrentMs(String currentMs) {
        this.currentMs = currentMs;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}