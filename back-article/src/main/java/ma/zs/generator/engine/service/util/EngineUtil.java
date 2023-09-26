/**
 *
 */
package ma.zs.generator.engine.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ouiam and Qada
 *
 */
public class EngineUtil {

    public static boolean isI18n(String fileName) {
        if (fileName.contains("i18n"))
            return true;
        else return false;
    }
    public static boolean isTemplate(String fileName) {
        if (fileName.endsWith(".ftl"))
            return true;
        else return false;
    }

    public static boolean isTemplateForMany(String fileName) {
        if (fileName.contains(".all."))
            return true;
        else
            return false;
    }

    public static boolean isComponent(String fileName) {
        if (fileName.contains(".cpn."))
            return true;
        else return false;
    }
    public static boolean isPermissionRole(String fileName) {
        return (fileName.startsWith("permission"));
    }
    public static boolean isAuthorities(String fileName) {
        if (fileName.contains("Authorities"))
            return true;
        else return false;
    }

    public static String getSuffixOrName(String fileName) {
        return (fileName.split("\\."))[0];
    }

    public static String getExtension(String fileName) {
        Pattern pattern;
        if (fileName.contains(".all.")) {
            if (!fileName.contains(".cpn."))
                pattern = Pattern.compile("all.(.*?).ftl", Pattern.DOTALL);
            else
                pattern = Pattern.compile("cpn.(.*?).ftl", Pattern.DOTALL);

        } else if (fileName.contains(".Authorities.")) {
            pattern = Pattern.compile(getSuffixOrName(fileName) + ".(.*?).Authorities.ftl", Pattern.DOTALL);

        }else
            pattern = Pattern.compile(getSuffixOrName(fileName) + ".(.*?).ftl", Pattern.DOTALL);

        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()) {
            return matcher.group(1);

        }
        return null;

    }

    public static String getFolder(String fileName) {
        if (fileName.contains(".all.") && fileName.contains(".cpn.")) {
            Pattern pattern = Pattern.compile("all.(.+).cpn", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(fileName);
            while (matcher.find()) {
                return matcher.group(1);
            }
            return null;

        }
        return null;
    }

    public static boolean inFolder(String fileName) {
        if (getFolder(fileName) == null)
            return false;
        else
            return true;
    }

    public static String getPlaceHolder(String fileName) {
        if (fileName.contains("{") && fileName.contains("}")) {
            Pattern pattern = Pattern.compile("\\{(.+)\\}", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(fileName);
            while (matcher.find()) {
                return matcher.group(1);
            }
            return null;

        }
        return null;
    }

    public static boolean isPlaceHolder(String fileName) {
        if (getPlaceHolder(fileName) == null)
            return false;
        else
            return true;
    }

    public static boolean isOptional(String fileName) {
        if (getCondition(fileName) == null)
            return false;
        else
            return true;
    }

    public static String getNameOfOptionel(String fileName) {
        return fileName.substring(0, fileName.indexOf('['));

    }

    public static String cutCondition(String fileName) {
        return fileName.replace("[" + getCondition(fileName) + "]", "");

    }

    public static String getCondition(String fileName) {
        Pattern pattern = Pattern.compile("\\[(.+)\\]", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static boolean isRoleRouting(String name) {
        return name.startsWith("rl-rt");
    }

    public static boolean isRest(String name) {
        return name.startsWith("permission.role");
    }
    public static boolean isRestNest(String name) {
        return name.startsWith("nestpermission");
    }

    public static boolean isServiceImpl(String name) {
        return name.startsWith("ServiceImpl") && name.contains("all") && !name.contains("AbstractServiceImpl");
    }
    public static boolean isServiceImplNestJs(String name) {
        return name.startsWith("NestServiceImpl") && name.contains("all") && !name.contains("AbstractServiceImpl");
    }
    public static boolean isService(String name) {
        return name.startsWith("Service") && name.contains("all") && !name.startsWith("ServiceImpl") && !name.contains("AbstractService") ;
    }
    public static boolean isServiceFront(String name) {
        return name.startsWith(".all.service.ts") ;
    }

    public static boolean isLogin(String name) {
        return name.startsWith("login-role");
    }
    public static boolean isRegister(String name) {
        return name.startsWith("register-role");
    }

    public static boolean isRoleRoutingSubModule(String name) {
        return name.startsWith("sub-module");
    }

    public static boolean isProcess(String name) {
        return name.startsWith("process");
    }

    public static boolean isExterne(String name) {
        return name.startsWith("Externe");
    }
}
