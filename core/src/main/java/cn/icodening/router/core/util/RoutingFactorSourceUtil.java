package cn.icodening.router.core.util;

import cn.icodening.router.core.IRequestRoutingFactorSource;
import cn.icodening.router.core.Request;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class RoutingFactorSourceUtil {

    private static final Map<String, IRequestRoutingFactorSource<Request>> ROUTING_FACTOR_SOURCE_MAP = new ConcurrentHashMap<>();

    static {
        ServiceLoader<IRequestRoutingFactorSource> load = ServiceLoader.load(IRequestRoutingFactorSource.class);
        for (IRequestRoutingFactorSource factorSource : load) {
            String name = factorSource.name();
            ROUTING_FACTOR_SOURCE_MAP.put(name, factorSource);
        }
    }

    private RoutingFactorSourceUtil() {
    }

    public static IRequestRoutingFactorSource<Request> getRoutingFactorSource(String name) {
        return ROUTING_FACTOR_SOURCE_MAP.get(name);
    }
}
