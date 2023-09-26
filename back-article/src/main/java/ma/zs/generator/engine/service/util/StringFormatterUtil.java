package ma.zs.generator.engine.service.util;

import java.util.List;

/**
 * @author YOUNES
 */
public class StringFormatterUtil {

    public static String upperCaseTheFirstLetter(String param) {
        return ((param.charAt(0) + "").toUpperCase()) + param.substring(1);
    }


    public static List<String> upperCaseTheFirstLetter(List<String> params) {
        for (String param : params) {
            param = upperCaseTheFirstLetter(param);
        }
        return params;
    }

    public static String lowerCaseTheFirstLetter(String param) {
        if (param != null && param.length() > 1) {
            return (param.charAt(0) + "").toLowerCase() + param.substring(1);
        }
        return "";
    }


    public static List<String> lowerCaseTheFirstLetter(List<String> params) {
        for (String param : params) {
            param = lowerCaseTheFirstLetter(param);
        }
        return params;
    }

    public static String formateParamToHtml(String param) {
        String[] r = param.split("(?=\\p{Upper})");
        String resultat = "";
        for (int i = 0; i < r.length; i++) {
            if (i == 0) {
                resultat += upperCaseTheFirstLetter(r[0]);
            } else {
                resultat += " " + r[i];
            }
        }
        return resultat;
    }

}
