package ma.zs.generator.project.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * @author Qada
 */
@JsonInclude(Include.NON_NULL)
public class Node {

    private String label;
    private String data;
    private String expandedIcon;
    private String collapsedIcon;
    private String icon;
    private List<Node> children;

    public Node() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Node [label=" + label + ", data=" + data + ", expandedIcon=" + expandedIcon + ", collapsedIcon="
                + collapsedIcon + ", icon=" + icon + ", children=" + children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExpandedIcon() {
        return expandedIcon;
    }

    public void setExpandedIcon(String expandedIcon) {
        this.expandedIcon = expandedIcon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCollapsedIcon() {
        return collapsedIcon;
    }

    public void setCollapsedIcon(String collapsedIcon) {
        this.collapsedIcon = collapsedIcon;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }


}
