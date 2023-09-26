package ma.zs.generator.engine.bean;

import java.util.List;
import java.util.Objects;

public class PackaginBack {
    private String folder;
    private String name;
    private boolean shared;
    private String className;
    private List<Pojo> pojos;

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Pojo> getPojos() {
        return pojos;
    }

    public void setPojos(List<Pojo> pojos) {
        this.pojos = pojos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackaginBack subModule = (PackaginBack) o;
        return Objects.equals(folder, subModule.folder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(folder);
    }
}
