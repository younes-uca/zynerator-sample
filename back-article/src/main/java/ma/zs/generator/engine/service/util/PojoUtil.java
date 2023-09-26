/**
 *
 */
package ma.zs.generator.engine.service.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ouiam
 */
public class PojoUtil {

    private static List<String> primitifTypes = Arrays.asList("String","boolean", "Boolean", "String", "int", "Integer", "float", "Float", "double",
            "Double", "long", "Long", "BigDecimal", "Date","LocalDateTime");
    private static List<String> numberOrDateTypes = Arrays.asList("int", "Integer", "float", "Float", "double", "Double", "long", "Long",
            "BigDecimal", "Date","LocalDateTime");

    private static List<String> numberTypes = Arrays.asList("int", "Integer", "float", "Float", "double", "long", "Long",
            "Double", "BigDecimal");
    private static String date = "Date";
    private static String time = "Time";
    private static String localDateTime = "LocalDateTime";
    private static String localDate= "LocalDate";
    private static String stringType= "String";
    private static List<String> boolTypes = Arrays.asList("boolean", "Boolean");
    private static List<String> intTypes = Arrays.asList("int", "Integer");
    private static List<String> longTypes = Arrays.asList("long", "Long");
    private static List<String> doubleTypes = Arrays.asList("double", "Double");
    private static List<String> bigDecimalTypes = Arrays.asList("BigDecimal");

    public static boolean isNumberOrDate(String typeName) {
        for (String type : numberOrDateTypes) {
            if (typeName.contains(type) || type.contains(typeName))
                return true;
        }
        return false;
    }

    public static boolean isPrimitif(String typeName) {
        for (String type : primitifTypes) {
            if (typeName.contains(type) || type.contains(typeName))
                return true;
        }
        return false;
    }

    public static boolean isList(String typeName) {
        if (typeName == null) return false;
        List<String> collectionTypes = Arrays.asList("List", "list", "ArrayList", "Collection");
        for (String collectionType : collectionTypes) {
            if (typeName.contains(collectionType) || typeName.contains(collectionType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(String typeName) {

        for (String type : numberTypes) {
            if (typeName.contains(type) || type.contains(typeName))
                return true;
        }
        return false;
    }

    public static boolean isDate(String typeTypeTrimed) {
        return date.equals(typeTypeTrimed);
    }
    public static boolean isTime(String typeTypeTrimed) {
        return time.equals(typeTypeTrimed);
    }

    public static boolean isLocalDateTime(String typeTypeTrimed) {
        return localDateTime.equals(typeTypeTrimed);
    }

    public static boolean isLocalDate(String typeTypeTrimed) {
        return localDate.equals(typeTypeTrimed);
    }
    public static boolean isString(String typeTypeTrimed) {
        return stringType.equals(typeTypeTrimed);
    }

    public static boolean isBool(String typeTypeTrimed) {
        for (String type : boolTypes) {
            if (typeTypeTrimed.contains(type) || type.contains(typeTypeTrimed))
                return true;
        }
        return false;
    }

    public static boolean isIntNumber(String typeTypeTrimed) {
        for (String type : intTypes) {
            if (typeTypeTrimed.contains(type) || type.contains(typeTypeTrimed))
                return true;
        }
        return false;
    }

    public static boolean isLongNumber(String typeTypeTrimed) {
        for (String type : longTypes) {
            if (typeTypeTrimed.contains(type) || type.contains(typeTypeTrimed))
                return true;
        }
        return false;
    }
    public static boolean isDoubleNumber(String typeTypeTrimed) {
        for (String type : doubleTypes) {
            if (typeTypeTrimed.contains(type) || type.contains(typeTypeTrimed))
                return true;
        }
        return false;
    }
    public static boolean isBigDecimalNumber(String typeTypeTrimed) {
        for (String type : bigDecimalTypes) {
            if (typeTypeTrimed.contains(type) || type.contains(typeTypeTrimed))
                return true;
        }
        return false;
    }
}
