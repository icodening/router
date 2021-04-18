package cn.icodening.router.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icodening
 * @date 2021.04.18
 */
public class ThreadLocalContext {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL =
            ThreadLocal.withInitial(HashMap::new);

    private ThreadLocalContext() {
    }

    @SuppressWarnings("unckecked")
    public static <T> T getObject(String name) {
        Map<String, ?> map = THREAD_LOCAL.get();
        Object value = map.get(name);
        return (T) value;
    }

    public static void setObject(String name, Object value) {
        Map<String, Object> map = THREAD_LOCAL.get();
        map.put(name, value);
    }

    public static void removeAll() {
        THREAD_LOCAL.remove();
    }
}
