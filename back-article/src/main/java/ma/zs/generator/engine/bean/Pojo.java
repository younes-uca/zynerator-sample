package ma.zs.generator.engine.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.util.*;

/**
 * @author Zouani
 */
public class Pojo {

    private String name;
    private String icon;
    private String iconSuperMenu;
    private String formatedName;
    private String formatedNameLowerCase;
    private String formatedUrl;
    private String formatedDataBase;
    private String orderByClause;
    private Field id;
    private Field reference;
    private Field label;
    private Field order;
    private Field password;
    private Field referenceOrId;
    private Field labelOrReferenceOrId;
    private Field labelOrReference;
    private RoleConfig role;
    private List<Field> fields;
    private boolean config;
    private boolean association;
    private boolean associationComplex;
    private boolean refInterne;
    private boolean state;
    private boolean actor;
    private boolean archivable;
    private boolean mustValidate;
    private String globaleFilter;
    private List<DomainProcess> domainProcesses;


    public List<DomainProcess> getDomainProcesses() {
        if (domainProcesses == null) {
            domainProcesses = new ArrayList<>();
        }
        return domainProcesses;
    }

    public void setDomainProcesses(List<DomainProcess> domainProcesses) {
        this.domainProcesses = domainProcesses;
    }


    private SubModule subModule;
    private PackaginBack packagingBack;

    private boolean requiered;
    private boolean configuration;

    @JsonIgnore
    private Set<Field> fieldsRequiered;
    @JsonIgnore
    private Map<Field, Field> fieldsAssociation;
    @JsonIgnore
    private Set<Field> fieldsAssociationComplexe;

    @JsonIgnore
    private Set<Field> fieldsGenericIncludingInnerTypeInListField;
    @JsonIgnore
    private Set<Field> fieldsGenericIncludingInnerTypeInListFieldWithCondition;

    @JsonIgnore
    private Set<Type> types;
    @JsonIgnore
    private Set<Type> typesIncludingInnerTypeInListField;
    @JsonProperty(access = Access.READ_ONLY)
    private List<Field> fieldsGeneric;
    @JsonProperty(access = Access.READ_ONLY)
    private List<Field> fieldsSimple;
    @JsonProperty(access = Access.READ_ONLY)
    private List<Field> fieldsList;
    @JsonIgnore
    private List<Field> fieldsSimpleNumberOrDate;
    @JsonIgnore
    private List<Field> fieldsSimpleStringOrBoolean;
    @JsonIgnore
    private Set<Field> fieldsGeneriqueAndListAndGenericInList;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasBigDecimal;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasList;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasGeneric;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean idString;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasDate;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasTime;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasDateTime;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasLocalDate;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasBoolean;
    @JsonProperty(access = Access.READ_ONLY)
    private boolean hasFindByInclusion = false;


    private int totalFields;
    private int nombreElementInColumn;
    private int columnStyle;

    @JsonIgnore
    private Set<Field> fieldsAssociationInListofList;
    @JsonIgnore
    private Set<Pojo> dependencies;
    @JsonIgnore
    private Set<Pojo> dependenciesImportation;
    @JsonIgnore
    private Set<Pojo> dependenciesImportationGeneric;
    @JsonIgnore
    private Set<Pojo> dependenciesImportationList;
    @JsonIgnore
    private Set<Pojo> viewServices;
    private boolean importData = true;
    private boolean importable;
    private boolean exportable = false;
    private String msName;
    private String msUrl;
    private boolean msSkip;
    private boolean ignoreFront;
    private String msPackaging;
    private String currentMs;
    private boolean msExterne;
    private boolean inMsDependency;
    private List<String> roles;

    private boolean enhanced;
    private boolean initialisation;
    private boolean steps;
    private boolean noSeq;
    private boolean history;
    private List<InitialisationBlock> initialisationBlocks;
    private String blockInitialisationAsString;
    private String blockInitialisationAsStringCollectedFromYaml;
    private boolean baseEntity;
    private boolean baseEntityNoTable;
    private boolean subEntity;
    private String attributeVisibility = "private";
    private Pojo superEntity;

    private boolean uploadOne;
    private boolean uploadMultiple;
    private boolean cacheable;
    private Field tagField;
    private boolean hasTag;
    private boolean hasTagInGenericField;

    private Field fieldOfAssociation;

    public boolean isHasTagInGenericField() {
        return hasTagInGenericField;
    }

    public void setHasTagInGenericField(boolean hasTagInGenericField) {
        this.hasTagInGenericField = hasTagInGenericField;
    }

    public Field getTagField() {
        return tagField;
    }

    public void setTagField(Field tagField) {
        this.tagField = tagField;
    }

    public boolean isHasTag() {
        return hasTag;
    }

    public void setHasTag(boolean hasTag) {
        this.hasTag = hasTag;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public Field getLabelOrReference() {
        return labelOrReference;
    }

    public void setLabelOrReference(Field labelOrReference) {
        this.labelOrReference = labelOrReference;
    }

    public Field getFieldOfAssociation() {
        return fieldOfAssociation;
    }

    public void setFieldOfAssociation(Field fieldOfAssociation) {
        this.fieldOfAssociation = fieldOfAssociation;
    }

    public boolean isUploadOne() {
        return uploadOne;
    }

    public void setUploadOne(boolean uploadOne) {
        this.uploadOne = uploadOne;
    }

    public boolean isUploadMultiple() {
        return uploadMultiple;
    }

    public void setUploadMultiple(boolean uploadMultiple) {
        this.uploadMultiple = uploadMultiple;
    }

    public boolean isHasTime() {
        return hasTime;
    }

    public void setHasTime(boolean hasTime) {
        this.hasTime = hasTime;
    }

    public String getBlockInitialisationAsStringCollectedFromYaml() {
        return blockInitialisationAsStringCollectedFromYaml;
    }

    public void setBlockInitialisationAsStringCollectedFromYaml(String blockInitialisationAsStringCollectedFromYaml) {
        this.blockInitialisationAsStringCollectedFromYaml = blockInitialisationAsStringCollectedFromYaml;
    }

    public String getBlockInitialisationAsString() {
        return blockInitialisationAsString;
    }

    public void setBlockInitialisationAsString(String blockInitialisationAsString) {
        this.blockInitialisationAsString = blockInitialisationAsString;
    }

    public List<InitialisationBlock> getInitialisationBlocks() {
        return initialisationBlocks;
    }

    public void setInitialisationBlocks(List<InitialisationBlock> initialisationBlocks) {
        this.initialisationBlocks = initialisationBlocks;
    }

    public boolean isBaseEntityNoTable() {
        return baseEntityNoTable;
    }

    public void setBaseEntityNoTable(boolean baseEntityNoTable) {
        this.baseEntityNoTable = baseEntityNoTable;
    }

    public String getAttributeVisibility() {
        return attributeVisibility;
    }

    public void setAttributeVisibility(String attributeVisibility) {
        this.attributeVisibility = attributeVisibility;
    }

    public Pojo getSuperEntity() {
        return superEntity;
    }

    public void setSuperEntity(Pojo superEntity) {
        this.superEntity = superEntity;
    }

    public boolean isSubEntity() {
        return subEntity;
    }

    public void setSubEntity(boolean subEntity) {
        this.subEntity = subEntity;
    }

    public boolean isBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(boolean baseEntity) {
        this.baseEntity = baseEntity;
    }

    public boolean isInitialisation() {
        return initialisation;
    }

    public void setInitialisation(boolean initialisation) {
        this.initialisation = initialisation;
    }

    public boolean isIgnoreFront() {
        return ignoreFront;
    }

    public void setIgnoreFront(boolean ignoreFront) {
        this.ignoreFront = ignoreFront;
    }

    public boolean isEnhanced() {
        return enhanced;
    }

    public void setEnhanced(boolean enhanced) {
        this.enhanced = enhanced;
    }

    public boolean isInMsDependency() {
        return inMsDependency;
    }

    public void setInMsDependency(boolean inMsDependency) {
        this.inMsDependency = inMsDependency;
    }

    public boolean isMsSkip() {
        return msSkip;
    }

    public void setMsSkip(boolean msSkip) {
        this.msSkip = msSkip;
    }

    public String getCurrentMs() {
        return currentMs;
    }

    public void setCurrentMs(String currentMs) {
        this.currentMs = currentMs;
    }

    public String getMsPackaging() {
        return msPackaging;
    }

    public void setMsPackaging(String msPackaging) {
        this.msPackaging = msPackaging;
    }

    public boolean isConfiguration() {
        return configuration;
    }

    public void setConfiguration(boolean configuration) {
        this.configuration = configuration;
    }

    public List<String> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getMsName() {
        return msName;
    }

    public void setMsName(String msName) {
        this.msName = msName;
    }

    public String getMsUrl() {
        return msUrl;
    }

    public void setMsUrl(String msUrl) {
        this.msUrl = msUrl;
    }

    public boolean isMsExterne() {
        return msExterne;
    }

    public void setMsExterne(boolean msExterne) {
        this.msExterne = msExterne;
    }

    public boolean isHasFindByInclusion() {
        return hasFindByInclusion;
    }

    public void setHasFindByInclusion(boolean hasFindByInclusion) {
        this.hasFindByInclusion = hasFindByInclusion;
    }

    public boolean isExportable() {
        return exportable;
    }

    public void setExportable(boolean exportable) {
        this.exportable = exportable;
    }

    public boolean isImportData() {
        return importData;
    }

    public void setImportData(boolean importData) {
        this.importData = importData;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getFormatedNameLowerCase() {
        return formatedNameLowerCase;
    }

    public void setFormatedNameLowerCase(String formatedNameLowerCase) {
        this.formatedNameLowerCase = formatedNameLowerCase;
    }

    public boolean isMustValidate() {
        return mustValidate;
    }

    public SubModule getSubModule() {
        if (subModule == null)
            subModule = new SubModule();
        return subModule;
    }

    public void setSubModule(SubModule subModule) {
        this.subModule = subModule;
    }

    public boolean isHasBoolean() {
        return hasBoolean;
    }

    public void setHasBoolean(boolean hasBoolean) {
        this.hasBoolean = hasBoolean;
    }

    public Set<Pojo> getViewServices() {
        if (viewServices == null) {
            viewServices = new HashSet<>();
        }
        return viewServices;
    }

    public void setViewServices(Set<Pojo> viewServices) {
        this.viewServices = viewServices;
    }

    public Set<Pojo> getDependencies() {
        if (dependencies == null) {
            dependencies = new HashSet<>();
        }
        return dependencies;
    }

    public void setDependencies(Set<Pojo> dependencies) {
        this.dependencies = dependencies;
    }

    public Set<Pojo> getDependenciesImportation() {
        if (dependenciesImportation == null) {
            dependenciesImportation = new HashSet<>();
        }
        return dependenciesImportation;
    }
    public Set<Pojo> getDependenciesImportationList() {
        if (dependenciesImportationList == null) {
            dependenciesImportationList = new HashSet<>();
        }
        return dependenciesImportationList;
    }

    public void setDependenciesImportationList(Set<Pojo> dependenciesImportationList) {
        this.dependenciesImportationList = dependenciesImportationList;
    }
    public Set<Pojo> getDependenciesImportationGeneric() {
        if (dependenciesImportationGeneric == null) {
            dependenciesImportationGeneric = new HashSet<>();
        }
        return dependenciesImportationGeneric;
    }

    public void setDependenciesImportationGeneric(Set<Pojo> dependenciesImportationGeneric) {
        this.dependenciesImportationGeneric = dependenciesImportationGeneric;
    }
    public void setDependenciesImportation(Set<Pojo> dependenciesImportation) {
        this.dependenciesImportation = dependenciesImportation;
    }

    public Set<Field> getFieldsAssociationInListofList() {
        if (fieldsAssociationInListofList == null) {
            fieldsAssociationInListofList = new HashSet<>();
        }
        return fieldsAssociationInListofList;
    }

    public void setFieldsAssociationInListofList(Set<Field> fieldsAssociationInListofList) {
        this.fieldsAssociationInListofList = fieldsAssociationInListofList;
    }

    public Map<Field, Field> getFieldsAssociation() {
        if (fieldsAssociation == null) {
            fieldsAssociation = new HashMap<>();
        }
        return fieldsAssociation;
    }

    public void setFieldsAssociation(Map<Field, Field> fieldsAssociation) {
        this.fieldsAssociation = fieldsAssociation;
    }

    public Set<Field> getFieldsAssociationComplexe() {
        if (fieldsAssociationComplexe == null) {
            fieldsAssociationComplexe = new HashSet<>();
        }
        return fieldsAssociationComplexe;
    }

    public void setFieldsAssociationComplexe(Set<Field> fieldsAssociationComplexe) {
        this.fieldsAssociationComplexe = fieldsAssociationComplexe;
    }

    public boolean isAssociationComplex() {
        return associationComplex;
    }

    public void setAssociationComplex(boolean associationComplex) {
        this.associationComplex = associationComplex;
    }

    public Set<Field> getFieldsRequiered() {
        if (fieldsRequiered == null) {
            fieldsRequiered = new HashSet<>();
        }
        return fieldsRequiered;
    }

    public void setFieldsRequiered(Set<Field> fieldsRequiered) {
        this.fieldsRequiered = fieldsRequiered;
    }

    public boolean isRequiered() {
        return requiered;
    }

    public void setRequiered(boolean requiered) {
        this.requiered = requiered;
    }

    public Field getOrder() {
        return order;
    }

    public void setOrder(Field order) {
        this.order = order;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isArchivable() {
        return archivable;
    }

    public void setArchivable(boolean archivable) {
        this.archivable = archivable;
    }

    public boolean isActor() {
        return actor;
    }

    public void setActor(boolean actor) {
        this.actor = actor;
    }

    public boolean isAssociation() {
        return association;
    }

    public void setAssociation(boolean association) {
        this.association = association;
    }

    public boolean isRefInterne() {
        return refInterne;
    }

    public void setRefInterne(boolean refInterne) {
        this.refInterne = refInterne;
    }

    public String getGlobaleFilter() {
        return globaleFilter;
    }

    public void setGlobaleFilter(String globaleFilter) {
        this.globaleFilter = globaleFilter;
    }

    public boolean isConfig() {
        return config;
    }

    public void setConfig(boolean config) {
        this.config = config;
    }

    public int getColumnStyle() {
        return columnStyle;
    }

    public void setColumnStyle(int columnStyle) {
        this.columnStyle = columnStyle;
    }

    public int getTotalFields() {
        return totalFields;
    }

    public void setTotalFields(int totalFields) {
        this.totalFields = totalFields;
    }

    public int getNombreElementInColumn() {
        return nombreElementInColumn;
    }

    public void setNombreElementInColumn(int nombreElementInColumn) {
        this.nombreElementInColumn = nombreElementInColumn;
    }

    public Set<Field> getFieldsGeneriqueAndListAndGenericInList() {
        if (fieldsGeneriqueAndListAndGenericInList == null) {
            fieldsGeneriqueAndListAndGenericInList = new HashSet<>();
        }
        return fieldsGeneriqueAndListAndGenericInList;
    }

    public void setFieldsGeneriqueAndListAndGenericInList(Set<Field> fieldsGeneriqueAndListAndGenericInList) {
        this.fieldsGeneriqueAndListAndGenericInList = fieldsGeneriqueAndListAndGenericInList;
    }

    public String getFormatedUrl() {
        return formatedUrl;
    }

    public void setFormatedUrl(String formatedUrl) {
        this.formatedUrl = formatedUrl;
    }

    private List<Permission> permissions;


    public Pojo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Pojo(String name, List<Field> fields) {
        super();
        this.name = name;
        this.fields = fields;
    }


    public Pojo(String pojoName, String idName, String idType) {
        this.name = pojoName;
        this.id = new Field(idName, idType);
    }

    public Pojo(String pojoName, String idName, String idType, String referenceName, String referenceType) {
        this.id = new Field(idName, idType);
        this.reference = new Field(referenceName, referenceType);
        this.name = pojoName;
    }

    public String getFormatedName() {
        return formatedName;
    }

    public void setFormatedName(String formatedName) {
        this.formatedName = formatedName;
    }

    public Field getLabel() {
        return label;
    }

    public void setLabel(Field label) {
        this.label = label;
    }

    public Field getReferenceOrId() {
        return referenceOrId;
    }

    public void setReferenceOrId(Field referenceOrId) {
        this.referenceOrId = referenceOrId;
    }

    public Field getLabelOrReferenceOrId() {
        return labelOrReferenceOrId;
    }

    public void setLabelOrReferenceOrId(Field labelOrReferenceOrId) {
        this.labelOrReferenceOrId = labelOrReferenceOrId;
    }

    public List<Field> getFieldsGeneric() {
        if (fieldsGeneric == null) {
            fieldsGeneric = new ArrayList();
        }
        return fieldsGeneric;
    }

    public void setFieldsGeneric(List<Field> fieldsGeneric) {
        this.fieldsGeneric = fieldsGeneric;
    }

    public List<Field> getFieldsList() {
        if (fieldsList == null) {
            fieldsList = new ArrayList();
        }
        return fieldsList;
    }

    public void setFieldsList(List<Field> fieldsList) {
        this.fieldsList = fieldsList;
    }

    public Field getId() {
        return id;
    }

    public void setId(Field id) {
        this.id = id;
    }

    public Field getReference() {
        return reference;
    }

    public void setReference(Field reference) {
        this.reference = reference;
    }

    public List<Field> getFieldsSimple() {
        if (fieldsSimple == null) {
            fieldsSimple = new ArrayList();
        }
        return fieldsSimple;
    }

    public void setFieldsSimple(List<Field> fieldsSimple) {
        this.fieldsSimple = fieldsSimple;
    }


    public List<Field> getFields() {
        if (fields == null) {
            fields = new ArrayList();
        }
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }


    public String getName() {
        return name;
    }

    public void setName(String pojoName) {
        this.name = pojoName;
    }

    public List<Field> getFieldsSimpleNumberOrDate() {
        if (fieldsSimpleNumberOrDate == null) {
            fieldsSimpleNumberOrDate = new ArrayList();
        }
        return fieldsSimpleNumberOrDate;
    }

    public void setFieldsSimpleNumberOrDate(List<Field> fieldsSimpleNumberOrDate) {
        this.fieldsSimpleNumberOrDate = fieldsSimpleNumberOrDate;
    }

    public List<Field> getFieldsSimpleStringOrBoolean() {
        if (fieldsSimpleStringOrBoolean == null) {
            fieldsSimpleStringOrBoolean = new ArrayList();
        }
        return fieldsSimpleStringOrBoolean;
    }

    public void setFieldsSimpleStringOrBoolean(List<Field> fieldsSimpleStringOrBoolean) {
        this.fieldsSimpleStringOrBoolean = fieldsSimpleStringOrBoolean;

    }


    public boolean isHasBigDecimal() {
        return hasBigDecimal;
    }


    public void setHasBigDecimal(boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
    }


    public boolean isHasList() {
        return hasList;
    }


    public void setHasList(boolean hasList) {
        this.hasList = hasList;
    }


    public boolean isIdString() {
        return idString;
    }


    public void setIdString(boolean idString) {
        this.idString = idString;
    }


    public boolean isHasDate() {
        return hasDate;
    }


    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }

    public boolean isHasDateTime() {
        return hasDateTime;
    }

    public void setHasDateTime(boolean hasDateTime) {
        this.hasDateTime = hasDateTime;
    }

    public RoleConfig getRole() {
        return role;
    }

    public void setRole(RoleConfig role) {
        this.role = role;
    }

    public Set<Type> getTypes() {
        if (this.types == null)
            this.types = new HashSet();
        return types;
    }

    public String getFormatedDataBase() {
        return formatedDataBase;
    }

    public void setFormatedDataBase(String formatedDataBase) {
        this.formatedDataBase = formatedDataBase;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Type> getTypesIncludingInnerTypeInListField() {
        if (typesIncludingInnerTypeInListField == null) {
            typesIncludingInnerTypeInListField = new HashSet<>();
        }
        return typesIncludingInnerTypeInListField;
    }

    public void setTypesIncludingInnerTypeInListField(Set<Type> typesIncludingInnerTypeInListField) {
        this.typesIncludingInnerTypeInListField = typesIncludingInnerTypeInListField;
    }

    public Set<Field> getFieldsGenericIncludingInnerTypeInListField() {
        if (fieldsGenericIncludingInnerTypeInListField == null) {
            fieldsGenericIncludingInnerTypeInListField = new HashSet<>();
        }
        return fieldsGenericIncludingInnerTypeInListField;
    }

    public void setFieldsGenericIncludingInnerTypeInListField(Set<Field> fieldsGenericIncludingInnerTypeInListField) {
        this.fieldsGenericIncludingInnerTypeInListField = fieldsGenericIncludingInnerTypeInListField;
    }

    public Set<Field> getFieldsGenericIncludingInnerTypeInListFieldWithCondition() {
        if (fieldsGenericIncludingInnerTypeInListFieldWithCondition == null) {
            fieldsGenericIncludingInnerTypeInListFieldWithCondition = new HashSet<>();
        }
        return fieldsGenericIncludingInnerTypeInListFieldWithCondition;
    }

    public void setFieldsGenericIncludingInnerTypeInListFieldWithCondition(Set<Field> fieldsGenericIncludingInnerTypeInListFieldWithCondition) {
        this.fieldsGenericIncludingInnerTypeInListFieldWithCondition = fieldsGenericIncludingInnerTypeInListFieldWithCondition;
    }

    public void setPassword(Field password) {
        this.password = password;
    }

    public Field getPassword() {
        return password;
    }


    public void setMustValidate(boolean mustValidate) {
        this.mustValidate = mustValidate;
    }

    public boolean getMustValidate() {
        return mustValidate;
    }

    public boolean isHasGeneric() {
        return hasGeneric;
    }

    public void setHasGeneric(boolean hasGeneric) {
        this.hasGeneric = hasGeneric;
    }

    public boolean isHasLocalDate() {
        return hasLocalDate;
    }

    public boolean isImportable() {
        return importable;
    }

    public void setImportable(boolean importable) {
        this.importable = importable;
    }

    public void setHasLocalDate(boolean hasLocalDate) {
        this.hasLocalDate = hasLocalDate;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "name='" + name + '\'' +
                ", fieldsSimple=" + fieldsSimple + "\n" +
                ", fieldsGeneric=" + fieldsGeneric + "\n" +
                ", fieldsList=" + fieldsList + "\n" +
                '}';
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconSuperMenu() {
        return iconSuperMenu;
    }

    public void setIconSuperMenu(String iconSuperMenu) {
        this.iconSuperMenu = iconSuperMenu;
    }

    public PackaginBack getPackagingBack() {
        if (packagingBack == null)
            packagingBack = new PackaginBack();
        return packagingBack;
    }

    public void setPackagingBack(PackaginBack packagingBack) {
        this.packagingBack = packagingBack;
    }

    public boolean isNoSeq() {
        return noSeq;
    }

    public void setNoSeq(boolean noSeq) {
        this.noSeq = noSeq;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }
}
