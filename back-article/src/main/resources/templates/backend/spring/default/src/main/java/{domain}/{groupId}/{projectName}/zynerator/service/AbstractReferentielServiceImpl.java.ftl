package ${config.domain}.${config.groupId}.${config.projectName}.zynerator.service;

import ${config.domain}.${config.groupId}.${config.projectName}.zynerator.audit.AuditBusinessObjectEnhanced;
import ${config.domain}.${config.groupId}.${config.projectName}.zynerator.criteria.BaseCriteria;
import ${config.domain}.${config.groupId}.${config.projectName}.zynerator.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends AuditBusinessObjectEnhanced, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
