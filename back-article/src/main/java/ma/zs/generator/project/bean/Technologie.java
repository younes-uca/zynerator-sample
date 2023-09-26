package ma.zs.generator.project.bean;

import ma.zs.generator.project.enumeration.CATEGORY;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * @author Qada
 */
@Entity
public class Technologie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "spring";

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private ProjectTemplate defaultTemplate;
    @OneToMany(mappedBy = "technologie")
    private List<ProjectTemplate> templates;

    @Enumerated
    private CATEGORY category = CATEGORY.BACKEND;


    public Technologie() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectTemplate getDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(ProjectTemplate defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public List<ProjectTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<ProjectTemplate> templates) {
        this.templates = templates;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }


}
