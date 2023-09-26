package ma.zs.generator.engine.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MoiseGui
 */
public class PojoAsType {

    private String name;

    private List<Field> fields;
    @JsonIgnore
    private Set<Type> types;
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



    public PojoAsType() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PojoAsType(String name, List<Field> fields) {
        super();
        this.name = name;
        this.fields = fields;
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


    public Set<Type> getTypes() {
        if (this.types == null)
            this.types = new HashSet();
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
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
}
