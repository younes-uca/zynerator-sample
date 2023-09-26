package ma.zs.generator.engine.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author gouss
 */
@Entity
public class PojoConfig implements Serializable {

    private static String reference = "REF";
    private static String label = "LABEL";
    private static String large = "LARGE";
    private static String order = "ORDER";
    private static String notIncluded = "NOT_INCLUDED";
    private static String password = "password";
    private static String configEnd = "_CONFIG";
    private static String createUpdateConfounded = "_CU-IN-ONE-PAGE";
    private static String refInterne = "_REF-INTERNE";
    private static String actor = "_ACTOR";
    private static String menu = "_MENU";
    private static String icon = "_ICON";
    private static String process = "_PROCESS";
    private static String roles = "_ROLES";
    private static String ms = "MS";
    private static String ignoreFront = "IGNORE-FRONT";
    private static String baseEntity = "BASE-ENTITY";
    private static String subEntity = "EXTENDS";
    private static String subModuleFolder = "_SUB-MODULE";
    private static String packagingBackFolder = "_BACK-PAKAGE";
    private static String subModuleShared = "_SUB-SHARED";
    private static String mustValidate = "_MUST-VALIDATE";
    private static String state = "_STATE";
    private static String archivable = "_ARCH";
    private static String required = "REQ";
    private static String uploadMultiple = "UPLOAD-MULT";
    private static String tag = "TAG";
    private static String searchMultiple = "SEARCH-MULT";
    private static String uploadOne = "UPLOAD-ONE";
    private static String findByInDao = "FIND";
    private static String deleteByInDao = "DELETE";

    private static String importable = "IMPORTABLE";
    private static String importData = "IMPORT";
    private static String exportable = "EXPORT";
    private static String referentiel = "REF";
    private static String cacheable = "CACHEABLE";
    private static String calendar = "CALENDAR";
    private static String calendarLabel = "CALENDAR-LABEL";
    private static String calendarStart = "CALENDAR-START";
    private static String calendarEnd = "CALENDAR-END";
    private static String initialisation = "_INIT";
    private static String steps = "WITH-STEP";
    private static String noSeq = "NO-SEQ";
    private static String history = "HISTORY";
    private static String step = "STEP";

    private static String notVisibleInCreatePage = "NOT-IN-CREATE";
    private static String notVisibleInListPage = "NOT-IN-LIST";
    private static String notVisibleInEditPage = "NOT-IN-EDIT";
    private static String notVisibleInViewPage = "NOT-IN-VIEW";

    private static String idDefaultName = "ID";
    @Id
    private Long id;

    public static String getInitialisation() {
        return initialisation;
    }

    public static String getSteps() {
        return steps;
    }
    public static String getNoSeq() {
        return noSeq;
    }

    public static String getStep() {
        return step;
    }

    public static String getNotVisibleInCreatePage() {
        return notVisibleInCreatePage;
    }

    public static String getNotVisibleInListPage() {
        return notVisibleInListPage;
    }

    public static String getNotVisibleInEditPage() {
        return notVisibleInEditPage;
    }

    public static String getNotVisibleInViewPage() {
        return notVisibleInViewPage;
    }

    public static String getIcon() {
        return icon;
    }

    public static String getMenu() {
        return menu;
    }

    public static String getRequired() {
        return required;
    }

    public static String getActor() {
        return actor;
    }

    public static String getState() {
        return state;
    }


    public static String getRefInterne() {
        return refInterne;
    }

    public static String getArchivable() {
        return archivable;
    }

    public static String getReference() {
        return reference;
    }

    public static String getLarge() {
        return large;
    }

    public static String getConfigEnd() {
        return configEnd;
    }

    public static String getCreateUpdateConfounded() {
        return createUpdateConfounded;
    }

    public static String getOrder() {
        return order;
    }

    public static void setOrder(String order) {
        PojoConfig.order = order;
    }

    public static String getNotIncluded() {
        return notIncluded;
    }

    public static void setNotIncluded(String notIncluded) {
        PojoConfig.notIncluded = notIncluded;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        PojoConfig.password = password;
    }

    public static String getSubModuleFolder() {
        return subModuleFolder;
    }

    public static String getPackagingBackFolder() {
        return packagingBackFolder;
    }

    public static String getSubModuleShared() {
        return subModuleShared;
    }

    public static String getMustValidate() {
        return mustValidate;
    }

    public static String getMs() {
        return ms;
    }

    public static String getIgnoreFront() {
        return ignoreFront;
    }

    public static String getBaseEntity() {
        return baseEntity;
    }

    public static String getSubEntity() {
        return subEntity;
    }

    public static String getUploadOne() {
        return uploadOne;
    }

    public static String getSearchMultiple() {
        return searchMultiple;
    }

    public static String getUploadMultiple() {
        return uploadMultiple;
    }

    public static String getTag() {
        return tag;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static String getIdDefaultName() {
        return idDefaultName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getLabel() {
        return label;
    }

    public static void setLabel(String label) {
        PojoConfig.label = label;
    }

    public static String getImportData() {
        return importData;
    }

    public static String getImportable() {
        return importable;
    }

    public static String getExportable() {
        return exportable;
    }

    public static String getReferentiel() {
        return referentiel;
    }

    public static String getCacheable() {
        return cacheable;
    }

    public static String getCalendar() {
        return calendar;
    }

    public static String getCalendarLabel() {
        return calendarLabel;
    }

    public static String getCalendarStart() {
        return calendarStart;
    }

    public static String getCalendarEnd() {
        return calendarEnd;
    }

    public static String getFindByInDao() {
        return findByInDao;
    }

    public static String getProcess() {
        return process;
    }

    public static String getDeleteByInDao() {
        return deleteByInDao;
    }

    public static String getRoles() {
        return roles;
    }

    public static String getHistory() {
        return history;
    }
}
