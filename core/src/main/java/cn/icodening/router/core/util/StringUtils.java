package cn.icodening.router.core.util;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class StringUtils {
    private StringUtils() {
    }

    public static boolean isBlank(String string) {
        return string == null || "".equals(string.trim());
    }
}
