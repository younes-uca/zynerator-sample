package ma.zs.generator.project.dao;

import ma.zs.generator.project.bean.Technologie;
import ma.zs.generator.project.enumeration.CATEGORY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Qada
 */
@Repository
public interface TechnologieDao extends JpaRepository<Technologie, Long> {

    Technologie findByName(String name);

    List<Technologie> findByCategory(CATEGORY category);
}
