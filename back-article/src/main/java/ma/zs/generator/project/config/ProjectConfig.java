package ma.zs.generator.project.config;


import java.util.UUID;

/**
 * @author Qada
 */
public class ProjectConfig {

    public String projectName = "generated";
    public String groupId = "zs";
    public String config = "config";
    public String domain = "ma";
    public String bean = "bean";
    public String dao = "dao";
    public String service = "service";
    public String serviceFacade = "facade";
    public String serviceImpl = "impl";
    public String serviceUtil = "util";
    public String ws = "ws";
    public String restFacade = "facade";
    public String dto = "dto";
    public String roles = "roles";
    public String converter = "converter";
    public String common = "common";
    public String jwt = "jwt";
    public String userService = "UserService";
    public String securityParams = "SecurityParams";
    public String security = "security";
    public boolean swagger = true;
    public boolean flyWayDb = true;
    public String mainClass = ((projectName.charAt(0) + "").toUpperCase()) + projectName.substring(1) + "Application";
    private BackendConfig backend;
    private FrontendConfig frontend;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public boolean isSwagger() {
        return swagger;
    }

    public boolean isFlyWayDb() {
        return flyWayDb;
    }


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public ProjectConfig() {
        super();
        this.backend = new BackendConfig();
        this.frontend = new FrontendConfig();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        mainClass = ((projectName.charAt(0) + "").toUpperCase()) + projectName.substring(1) + "Application";
    }

    public String getGroupId() {
        return groupId;
    }

    public String getUserService() {
        return userService;
    }

    public void setUserService(String userService) {
        this.userService = userService;
    }

    public String getSecurityParams() {
        return securityParams;
    }

    public void setSecurityParams(String securityParams) {
        this.securityParams = securityParams;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public BackendConfig getBackend() {
        return backend;
    }

    public void setBackend(BackendConfig backend) {
        this.backend = backend;
    }

    public FrontendConfig getFrontend() {
        return frontend;
    }

    public void setFrontend(FrontendConfig frontend) {
        this.frontend = frontend;
    }


    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getDao() {
        return dao;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceFacade() {
        return serviceFacade;
    }

    public void setServiceFacade(String serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getServiceUtil() {
        return serviceUtil;
    }

    public void setServiceUtil(String serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getRestFacade() {
        return restFacade;
    }

    public void setRestFacade(String restFacade) {
        this.restFacade = restFacade;
    }

    public String getDto() {
        return dto;
    }

    public void setDto(String dto) {
        this.dto = dto;
    }

    public String getConverter() {
        return converter;
    }

    public void setConverter(String converter) {
        this.converter = converter;
    }


    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public boolean getSwagger() {
        return swagger;
    }

    public void setSwagger(boolean swagger) {
        this.swagger = swagger;
    }

    public boolean getFlyWayDb() {
        return flyWayDb;
    }

    public void setFlyWayDb(boolean flyWayDb) {
        this.flyWayDb = flyWayDb;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "ProjectConfig{" +
                "projectName='" + projectName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }

    public String getSecret(){
        return UUID.randomUUID().toString();
    }
}
