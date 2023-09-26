package ma.zs.generator.project.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Qada
 */
@Entity
public class GeneratorHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private ProjectTemplate projectTemplate;


    public GeneratorHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProjectTemplate getProjectTemplate() {
        return projectTemplate;
    }

    public void setProjectTemplate(ProjectTemplate projectTemplate) {
        this.projectTemplate = projectTemplate;
    }


}
