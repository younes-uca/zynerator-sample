package ma.zs.generator.project.bean;

import java.util.List;

/**
 * @author Qada
 */
public class GeneratedProject {

    private String name;
    private byte[] zip;
    private List<Node> tree;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getZip() {
        return zip;
    }

    public void setZip(byte[] zip) {
        this.zip = zip;
    }

    public List<Node> getTree() {
        return tree;
    }

    public void setTree(List<Node> tree) {
        this.tree = tree;
    }


}
