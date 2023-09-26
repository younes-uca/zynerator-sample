package ma.zs.generator.engine.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.util.Objects;

/**
 * @author Ouiam
 */
public class Field {
    private String name;
    private Type type;

    private Pojo typeAsPojo;
    private boolean boolFrameWork;
    private boolean simple;
    private boolean bool;
    private boolean date;
    private boolean dateTime;
    private boolean localDate;
    private boolean time;
    private boolean nombre;
    private boolean list;
    private boolean generic;
    private boolean id;
    private boolean reference;
    private boolean label;
    private boolean order;
    private boolean notIncluded;
    private boolean eligibleForStackOverFlow;
    private boolean password;
    private boolean findByInclusion = false;
    private boolean deleteByInclusion = false;
    private boolean hidePlusButton = false;
    private boolean hidePlusButtonInCreate = false;
    private boolean hidePlusButtonInEdit = false;
    @JsonProperty(access = Access.WRITE_ONLY)
    private Pojo pojo;
    private Boolean comboBox = true;
    private String mappedBy;
    private String formatedName;
    private int index;
    private int indexMod;
    private String formatedTabPanelName;
    private String formatedUrl;
    private boolean large;
    private String i18nLabel;
    private String i18nPlaceHolder;

    private String name18nEdit;
    private String name18nEditKey;
    private String name18nEditValue;

    private String name18nView;
    private String name18nViewKey;
    private String name18nViewValue;

    private String name18nCreate;
    private String name18nCreateKey;
    private String name18nCreateValue;
    private String name18nPlaceHolderCreate;
    private String name18nPlaceHolderCreateKey;
    private String name18nPlaceHolderCreateValue;
    private String name18nPlaceHolderFilterCreate;
    private String name18nPlaceHolderFilterCreateKey;
    private String name18nPlaceHolderFilterCreateValue;
    private String name18nMinCreate;
    private String name18nMinCreateKey;
    private String name18nMinCreateValue;
    private String name18nMaxCreate;
    private String name18nMaxCreateKey;
    private String name18nMaxCreateValue;

    private String name18nList;
    private String name18nListKey;
    private String name18nListValue;

    private String tabPanelI18nCreate;
    private String tabPanelI18nCreateKey;
    private String tabPanelI18nCreateValue;

    private String tabPanelI18nEdit;
    private String tabPanelI18nEditKey;
    private String tabPanelI18nEditValue;

    private String tabPanelI18nView;
    private String tabPanelI18nViewKey;
    private String tabPanelI18nViewValue;

    private boolean requierd;
    private boolean integerNumber;
    private boolean longNumber;
    private boolean doubleNumber;
    private boolean bigDecimalNumber;
    private String requierdCss;
    private String requierdMessage;
    private String extraInfo;

    private boolean associationComplex;
    private boolean association;
    private boolean genericIssuedOfAssociation;
    private boolean fakeAssociation;
    private boolean createAndListPageInOneTab = true;
    private Field fieldOfAssociation;
    private Field fieldOrignAssociation;
    private int nombreOfAttributes;
    private int columnStyle;
    private boolean notVisibleInCreatePage;
    private boolean notVisibleInListPage;
    private boolean notVisibleInEditPage;
    private boolean notVisibleInViewPage;
    private boolean pureString;
    private boolean string;
    private boolean largeString;
    private boolean uploadOne;
    private boolean uploadMultiple;
    private boolean searchOne = true;
    private boolean tag = true;

    public boolean isGenericIssuedOfAssociation() {
        return genericIssuedOfAssociation;
    }

    public void setGenericIssuedOfAssociation(boolean genericIssuedOfAssociation) {
        this.genericIssuedOfAssociation = genericIssuedOfAssociation;
    }

    public Field getFieldOrignAssociation() {
        return fieldOrignAssociation;
    }

    public void setFieldOrignAssociation(Field fieldOrignAssociation) {
        this.fieldOrignAssociation = fieldOrignAssociation;
    }

    public String getName18nMinCreate() {
        return name18nMinCreate;
    }

    public void setName18nMinCreate(String name18nMinCreate) {
        this.name18nMinCreate = name18nMinCreate;
    }

    public String getName18nMinCreateKey() {
        return name18nMinCreateKey;
    }

    public void setName18nMinCreateKey(String name18nMinCreateKey) {
        this.name18nMinCreateKey = name18nMinCreateKey;
    }

    public String getName18nMinCreateValue() {
        return name18nMinCreateValue;
    }

    public void setName18nMinCreateValue(String name18nMinCreateValue) {
        this.name18nMinCreateValue = name18nMinCreateValue;
    }

    public String getName18nMaxCreate() {
        return name18nMaxCreate;
    }

    public void setName18nMaxCreate(String name18nMaxCreate) {
        this.name18nMaxCreate = name18nMaxCreate;
    }

    public String getName18nMaxCreateKey() {
        return name18nMaxCreateKey;
    }

    public void setName18nMaxCreateKey(String name18nMaxCreateKey) {
        this.name18nMaxCreateKey = name18nMaxCreateKey;
    }

    public String getName18nMaxCreateValue() {
        return name18nMaxCreateValue;
    }

    public void setName18nMaxCreateValue(String name18nMaxCreateValue) {
        this.name18nMaxCreateValue = name18nMaxCreateValue;
    }

    public String getName18nPlaceHolderCreate() {
        return name18nPlaceHolderCreate;
    }

    public void setName18nPlaceHolderCreate(String name18nPlaceHolderCreate) {
        this.name18nPlaceHolderCreate = name18nPlaceHolderCreate;
    }

    public String getName18nPlaceHolderCreateKey() {
        return name18nPlaceHolderCreateKey;
    }

    public void setName18nPlaceHolderCreateKey(String name18nPlaceHolderCreateKey) {
        this.name18nPlaceHolderCreateKey = name18nPlaceHolderCreateKey;
    }

    public String getName18nPlaceHolderCreateValue() {
        return name18nPlaceHolderCreateValue;
    }

    public void setName18nPlaceHolderCreateValue(String name18nPlaceHolderCreateValue) {
        this.name18nPlaceHolderCreateValue = name18nPlaceHolderCreateValue;
    }

    public String getName18nPlaceHolderFilterCreate() {
        return name18nPlaceHolderFilterCreate;
    }

    public void setName18nPlaceHolderFilterCreate(String name18nPlaceHolderFilterCreate) {
        this.name18nPlaceHolderFilterCreate = name18nPlaceHolderFilterCreate;
    }

    public String getName18nPlaceHolderFilterCreateKey() {
        return name18nPlaceHolderFilterCreateKey;
    }

    public void setName18nPlaceHolderFilterCreateKey(String name18nPlaceHolderFilterCreateKey) {
        this.name18nPlaceHolderFilterCreateKey = name18nPlaceHolderFilterCreateKey;
    }

    public String getName18nPlaceHolderFilterCreateValue() {
        return name18nPlaceHolderFilterCreateValue;
    }

    public void setName18nPlaceHolderFilterCreateValue(String name18nPlaceHolderFilterCreateValue) {
        this.name18nPlaceHolderFilterCreateValue = name18nPlaceHolderFilterCreateValue;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public boolean isSearchOne() {
        return searchOne;
    }

    public void setSearchOne(boolean searchOne) {
        this.searchOne = searchOne;
    }

    public boolean isString() {
        return string;
    }

    public void setString(boolean string) {
        this.string = string;
    }

    public boolean isLargeString() {
        return largeString;
    }

    public void setLargeString(boolean largeString) {
        this.largeString = largeString;
    }

    public boolean isPureString() {
        return pureString;
    }

    public void setPureString(boolean pureString) {
        this.pureString = pureString;
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

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    public String getName18nView() {
        return name18nView;
    }

    public void setName18nView(String name18nView) {
        this.name18nView = name18nView;
    }

    public String getName18nViewKey() {
        return name18nViewKey;
    }

    public void setName18nViewKey(String name18nViewKey) {
        this.name18nViewKey = name18nViewKey;
    }

    public String getName18nViewValue() {
        return name18nViewValue;
    }

    public void setName18nViewValue(String name18nViewValue) {
        this.name18nViewValue = name18nViewValue;
    }

    public String getName18nEditKey() {
        return name18nEditKey;
    }

    public void setName18nEditKey(String name18nEditKey) {
        this.name18nEditKey = name18nEditKey;
    }

    public String getName18nCreateKey() {
        return name18nCreateKey;
    }

    public void setName18nCreateKey(String name18nCreateKey) {
        this.name18nCreateKey = name18nCreateKey;
    }

    public String getName18nListKey() {
        return name18nListKey;
    }

    public void setName18nListKey(String name18nListKey) {
        this.name18nListKey = name18nListKey;
    }

    public String getTabPanelI18nCreateKey() {
        return tabPanelI18nCreateKey;
    }

    public void setTabPanelI18nCreateKey(String tabPanelI18nCreateKey) {
        this.tabPanelI18nCreateKey = tabPanelI18nCreateKey;
    }

    public String getTabPanelI18nEditKey() {
        return tabPanelI18nEditKey;
    }

    public void setTabPanelI18nEditKey(String tabPanelI18nEditKey) {
        this.tabPanelI18nEditKey = tabPanelI18nEditKey;
    }

    public String getTabPanelI18nViewKey() {
        return tabPanelI18nViewKey;
    }

    public void setTabPanelI18nViewKey(String tabPanelI18nViewKey) {
        this.tabPanelI18nViewKey = tabPanelI18nViewKey;
    }

    public String getName18nEditValue() {
        return name18nEditValue;
    }

    public void setName18nEditValue(String name18nEditValue) {
        this.name18nEditValue = name18nEditValue;
    }

    public String getName18nCreateValue() {
        return name18nCreateValue;
    }

    public void setName18nCreateValue(String name18nCreateValue) {
        this.name18nCreateValue = name18nCreateValue;
    }

    public String getName18nListValue() {
        return name18nListValue;
    }

    public void setName18nListValue(String name18nListValue) {
        this.name18nListValue = name18nListValue;
    }

    public String getTabPanelI18nCreate() {
        return tabPanelI18nCreate;
    }

    public void setTabPanelI18nCreate(String tabPanelI18nCreate) {
        this.tabPanelI18nCreate = tabPanelI18nCreate;
    }

    public String getTabPanelI18nCreateValue() {
        return tabPanelI18nCreateValue;
    }

    public void setTabPanelI18nCreateValue(String tabPanelI18nCreateValue) {
        this.tabPanelI18nCreateValue = tabPanelI18nCreateValue;
    }

    public String getTabPanelI18nEdit() {
        return tabPanelI18nEdit;
    }

    public void setTabPanelI18nEdit(String tabPanelI18nEdit) {
        this.tabPanelI18nEdit = tabPanelI18nEdit;
    }

    public String getTabPanelI18nEditValue() {
        return tabPanelI18nEditValue;
    }

    public void setTabPanelI18nEditValue(String tabPanelI18nEditValue) {
        this.tabPanelI18nEditValue = tabPanelI18nEditValue;
    }

    public String getTabPanelI18nView() {
        return tabPanelI18nView;
    }

    public void setTabPanelI18nView(String tabPanelI18nView) {
        this.tabPanelI18nView = tabPanelI18nView;
    }

    public String getTabPanelI18nViewValue() {
        return tabPanelI18nViewValue;
    }

    public void setTabPanelI18nViewValue(String tabPanelI18nViewValue) {
        this.tabPanelI18nViewValue = tabPanelI18nViewValue;
    }

    public String getName18nEdit() {
        return name18nEdit;
    }

    public void setName18nEdit(String name18nEdit) {
        this.name18nEdit = name18nEdit;
    }

    public String getName18nCreate() {
        return name18nCreate;
    }

    public void setName18nCreate(String name18nCreate) {
        this.name18nCreate = name18nCreate;
    }

    public String getName18nList() {
        return name18nList;
    }

    public void setName18nList(String name18nList) {
        this.name18nList = name18nList;
    }

    public boolean isNotVisibleInCreatePage() {
        return notVisibleInCreatePage;
    }

    public void setNotVisibleInCreatePage(boolean notVisibleInCreatePage) {
        this.notVisibleInCreatePage = notVisibleInCreatePage;
    }

    public boolean isNotVisibleInListPage() {
        return notVisibleInListPage;
    }

    public void setNotVisibleInListPage(boolean notVisibleInListPage) {
        this.notVisibleInListPage = notVisibleInListPage;
    }

    public boolean isNotVisibleInEditPage() {
        return notVisibleInEditPage;
    }

    public void setNotVisibleInEditPage(boolean notVisibleInEditPage) {
        this.notVisibleInEditPage = notVisibleInEditPage;
    }

    public boolean isNotVisibleInViewPage() {
        return notVisibleInViewPage;
    }

    public void setNotVisibleInViewPage(boolean notVisibleInViewPage) {
        this.notVisibleInViewPage = notVisibleInViewPage;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public int getColumnStyle() {
        return columnStyle;
    }

    public void setColumnStyle(int columnStyle) {
        this.columnStyle = columnStyle;
    }

    public boolean isCreateAndListPageInOneTab() {
        return createAndListPageInOneTab;
    }

    public void setCreateAndListPageInOneTab(boolean createAndListPageInOneTab) {
        this.createAndListPageInOneTab = createAndListPageInOneTab;
    }

    public int getNombreOfAttributes() {
        return nombreOfAttributes;
    }

    public void setNombreOfAttributes(int nombreOfAttributes) {
        this.nombreOfAttributes = nombreOfAttributes;
    }

    public boolean isFakeAssociation() {
        return fakeAssociation;
    }

    public void setFakeAssociation(boolean fakeAssociation) {
        this.fakeAssociation = fakeAssociation;
    }

    public Field getFieldOfAssociation() {
        return fieldOfAssociation;
    }

    public void setFieldOfAssociation(Field fieldOfAssociation) {
        this.fieldOfAssociation = fieldOfAssociation;
    }

    public boolean isAssociationComplex() {
        return associationComplex;
    }

    public void setAssociationComplex(boolean associationComplex) {
        this.associationComplex = associationComplex;
    }

    public boolean isAssociation() {
        return association;
    }

    public void setAssociation(boolean association) {
        this.association = association;
    }

    public boolean isRequierd() {
        return requierd;
    }

    public void setRequierd(boolean requierd) {
        this.requierd = requierd;
    }

    public String getRequierdCss() {
        return requierdCss;
    }

    public void setRequierdCss(String requierdCss) {
        this.requierdCss = requierdCss;
    }

    public String getRequierdMessage() {
        return requierdMessage;
    }

    public void setRequierdMessage(String requierdMessage) {
        this.requierdMessage = requierdMessage;
    }

    public boolean isBoolFrameWork() {
        return boolFrameWork;
    }

    public void setBoolFrameWork(boolean boolFrameWork) {
        this.boolFrameWork = boolFrameWork;
    }

    public boolean isNotIncluded() {
        return notIncluded;
    }

    public boolean isPassword() {
        return password;
    }

    public boolean isOrder() {
        return order;
    }

    public boolean isNombre() {
        return nombre;
    }

    public void setNombre(boolean nombre) {
        this.nombre = nombre;
    }

    public boolean isLarge() {
        return large;
    }

    public void setLarge(boolean large) {
        this.large = large;
    }

    public String getI18nLabel() {
        return i18nLabel;
    }

    public void setI18nLabel(String i18nLabel) {
        this.i18nLabel = i18nLabel;
    }

    public String getI18nPlaceHolder() {
        return i18nPlaceHolder;
    }

    public void setI18nPlaceHolder(String i18nPlaceHolder) {
        this.i18nPlaceHolder = i18nPlaceHolder;
    }

    public String getFormatedUrl() {
        return formatedUrl;
    }

    public void setFormatedUrl(String formatedUrl) {
        this.formatedUrl = formatedUrl;
    }

    public String getFormatedTabPanelName() {
        return formatedTabPanelName;
    }

    public void setFormatedTabPanelName(String formatedTabPanelName) {
        this.formatedTabPanelName = formatedTabPanelName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndexMod() {
        return indexMod;
    }

    public void setIndexMod(int indexMod) {
        this.indexMod = indexMod;
    }

    public String getFormatedName() {
        return formatedName;
    }

    public void setFormatedName(String formatedName) {
        this.formatedName = formatedName;
    }

    public Field(String name, String typeName) {
        super();
        this.name = name;
        this.type = new Type(typeName);
    }

    public Field(String name, Type type) {
        super();
        this.name = name;
        this.type = type;
    }

    public Field() {
        super();
        // TODO Auto-generated constructor stub
    }


    public boolean isLabel() {
        return label;
    }

    public void setLabel(boolean label) {
        this.label = label;
    }

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }

    public Pojo getPojo() {
        return pojo;
    }

    public void setPojo(Pojo pojo) {
        this.pojo = pojo;
    }

    public Boolean getComboBox() {
        return comboBox;
    }

    public void setComboBox(Boolean comboBox) {
        this.comboBox = comboBox;
    }

    public String getMappedBy() {
        return mappedBy;
    }

    public void setMappedBy(String mappedBy) {
        this.mappedBy = mappedBy;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isReference() {
        return reference;
    }

    public void setReference(boolean reference) {
        this.reference = reference;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Objects.equals(name, field.name) && Objects.equals(type.getName(), field.type.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    public Pojo getTypeAsPojo() {
        if (this.typeAsPojo == null) return new Pojo();
        return typeAsPojo;
    }

    public void setTypeAsPojo(Pojo typeAsPojo) {
        this.typeAsPojo = typeAsPojo;
    }


    public void setOrder(boolean order) {
        this.order = order;
    }

    public boolean getOrder() {
        return order;
    }

    public void setNotIncluded(boolean notIncluded) {
        this.notIncluded = notIncluded;
    }

    public boolean getNotIncluded() {
        return notIncluded;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }

    public boolean getPassword() {
        return password;
    }

    public boolean isFindByInclusion() {
        return findByInclusion;
    }

    public void setFindByInclusion(boolean findByInclusion) {
        this.findByInclusion = findByInclusion;
    }

    public boolean isDeleteByInclusion() {
        return deleteByInclusion;
    }

    public void setDeleteByInclusion(boolean deleteByInclusion) {
        this.deleteByInclusion = deleteByInclusion;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    public boolean isDateTime() {
        return dateTime;
    }

    public void setDateTime(boolean dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isIntegerNumber() {
        return integerNumber;
    }

    public void setIntegerNumber(boolean integerNumber) {
        this.integerNumber = integerNumber;
    }

    public boolean isLongNumber() {
        return longNumber;
    }

    public void setLongNumber(boolean longNumber) {
        this.longNumber = longNumber;
    }

    public boolean isDoubleNumber() {
        return doubleNumber;
    }

    public void setDoubleNumber(boolean doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    public boolean isBigDecimalNumber() {
        return bigDecimalNumber;
    }

    public void setBigDecimalNumber(boolean bigDecimalNumber) {
        this.bigDecimalNumber = bigDecimalNumber;
    }

    public boolean isEligibleForStackOverFlow() {
        return eligibleForStackOverFlow;
    }

    public void setEligibleForStackOverFlow(boolean eligibleForStackOverFlow) {
        this.eligibleForStackOverFlow = eligibleForStackOverFlow;
    }

    public boolean isLocalDate() {
        return localDate;
    }

    public void setLocalDate(boolean localDate) {
        this.localDate = localDate;
    }

    public boolean isHidePlusButton() {
        return hidePlusButton;
    }

    public void setHidePlusButton(boolean hidePlusButton) {
        this.hidePlusButton = hidePlusButton;
    }

    public boolean isHidePlusButtonInCreate() {
        return hidePlusButtonInCreate;
    }

    public void setHidePlusButtonInCreate(boolean hidePlusButtonInCreate) {
        this.hidePlusButtonInCreate = hidePlusButtonInCreate;
    }

    public boolean isHidePlusButtonInEdit() {
        return hidePlusButtonInEdit;
    }

    public void setHidePlusButtonInEdit(boolean hidePlusButtonInEdit) {
        this.hidePlusButtonInEdit = hidePlusButtonInEdit;
    }
}
