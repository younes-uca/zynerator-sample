package ma.zs.generator.project.config;

/**
 * @author Qada
 */
public class FrontendConfig {
    public String userName = System.getProperty("user.name");
    public int maxFields = 10;
    private Boolean searchInEdit = true;

    public Boolean getSearchInEdit() {
        return searchInEdit;
    }

    public void setSearchInEdit(Boolean searchInEdit) {
        this.searchInEdit = searchInEdit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMaxFields() {
        return maxFields;
    }

    public void setMaxFields(int maxFields) {
        this.maxFields = maxFields;
    }
}
