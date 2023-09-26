package ma.zs.generator.project.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Qada
 */
@Entity
public class ProjectTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "default";
    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne()
    private Technologie technologie;
    private String description;
    private String path = "C/";
    @JsonProperty(access = Access.READ_ONLY)
    @Transient
    private List<Node> tree;


    public ProjectTemplate() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Node> getTree() {
        return tree;
    }

    public void setTree(List<Node> tree) {
        this.tree = tree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Technologie getTechnologie() {
        return technologie;
    }

    public void setTechnologie(Technologie technologie) {
        this.technologie = technologie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
