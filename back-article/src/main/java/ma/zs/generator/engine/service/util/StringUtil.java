package ma.zs.generator.engine.service.util;


public class StringUtil {

    public static boolean isEmpty(String... values) {
        boolean result = false;
        for (String value : values) {
            result = result || isEmpty(value);
        }
        return result;
    }
    public static boolean isNotEmpty(String... values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static boolean isEmpty(Object value) {
        return value == null || isEmpty(value.toString());
    }

    public static boolean isNotEmpty(Object value) {
        return value != null && isNotEmpty(value.toString());
    }

    public static String lowerCaseFirstLetter(String name) {
        if (isEmpty(name)) {
            return "";
        } else {
            return (name.charAt(0) + "").toLowerCase() + name.substring(1, name.length());
        }
    }


    public static String formatWithSpace(String string) {
        String resultat = formatWithSeparator(string, " ");
        return (resultat.substring(0, 1).toUpperCase()) + resultat.substring(1);
    }

    public static String formatWithSpaceLowerCase(String string) {
        String resultat = formatWithSeparator(string, " ");
        return (resultat.substring(0, 1).toLowerCase()) + resultat.substring(1);
    }


    public static String formatDb(String string) {
        return formatWithSeparator(string, "_");
    }

    public static String formatUrl(String string) {
        return formatWithSeparator(string, "-");
    }


    private static String formatWithSeparator(String string, String separator) {
        String resultat = "";
        if (isNotEmpty(string)) {
            char[] chars = string.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char myChar = chars[i];
                if (i == 0) {
                    resultat += (myChar + "").toLowerCase();
                } else if (myChar >= 'A' && myChar <= 'Z') {
                    resultat += separator + (myChar + "").toLowerCase();
                } else {
                    resultat += myChar;
                }
            }
        }
        return resultat;
    }


    public static String formatWithSpaceAndEliminateTail(String value, String tailOrHead) {
        String format = formatWithSpace(value);
        String res = "";
        if (format.toLowerCase().endsWith(tailOrHead.toLowerCase()) || format.toLowerCase().startsWith(tailOrHead.toLowerCase())) {
            res = format.replace(tailOrHead, "");
        }
        if (res.startsWith(" "))
            return res.substring(1);
        else if (res.endsWith(" "))
            return res.substring(0, res.length());
        else return res;
    }

    public static String upperCaseFirst(String name) {
        if (isNotEmpty(name)) {
            char[] charArray = name.toCharArray();
            return (charArray[0] + "").toUpperCase() + name.substring(1);
        }
        return "";
    }
}
