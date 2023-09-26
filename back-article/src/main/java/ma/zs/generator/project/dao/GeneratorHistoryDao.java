package ma.zs.generator.project.dao;

import ma.zs.generator.project.bean.GeneratorHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Qada
 */
@Repository
public interface GeneratorHistoryDao extends JpaRepository<GeneratorHistory, Long> {
    @Query("SELECT COUNT(*) FROM GeneratorHistory")
    Long countAll();

    Long countByDate(Date date);

    Long countByDateBetween(Date date1, Date date2);

    Long countByProjectTemplateTechnologieName(String name);

//	Long countByProjectTemplateNameAndProjectTemplateTechnologieName(String templateName,String technologieName);
//	Long countByProjectTemplateNameAndProjectTemplateTechnologieNameAndDate(String templateName,String technologieName,Date date);
//	Long countByProjectTemplateTechnologieName(String technolgieName);
////	  @Query("SELECT COUNT(*) FROM GeneratorHistory h where h.projectTemplate.name = :name")
////	Long hseb(@Param("name") String name);
//	
}
