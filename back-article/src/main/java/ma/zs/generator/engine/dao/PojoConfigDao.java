/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.zs.generator.engine.dao;

import ma.zs.generator.engine.bean.PojoConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author mac
 */
@Repository
public interface PojoConfigDao extends JpaRepository<PojoConfig, Long> {
}
