package ma.zs.generator.project.service.facade;

import ma.zs.generator.project.bean.Technologie;
import ma.zs.generator.project.enumeration.CATEGORY;

import java.util.List;

/**
 * @author Qada
 */
public interface TechnologieService {

    Technologie findByName(String name);

    int save(Technologie technologie);

    List<Technologie> findByCategory(CATEGORY category);

    List<Technologie> findAll();

    int deleteByName(String name);


}
