package ma.zs.generator.project.service.util;


public class StringUtil {

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static String format(String string) {
        String resultat="";
        if(isNotEmpty(string)){
            char[] chars = string.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char myChar = chars[i];
                if(myChar >= 'A' && myChar <= 'Z'){
                    resultat+=" "+myChar;
                }else{
                    resultat+=myChar;
                }
            }
            resultat=(resultat.substring(0,1).toUpperCase())+resultat.substring(1,resultat.length()-1);
        }
        return resultat;
    }


}
