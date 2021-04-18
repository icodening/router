package cn.icodening.router.core;

import java.net.URI;
import java.util.Map;

/**
 * @author icodening
 * @date 2021.04.17
 */
public interface IServiceInstance {

    default String getInstanceId() {
        return null;
    }

    String getServiceId();

    String getHost();

    int getPort();

    boolean isSecure();

    URI getUri();

    Map<String, String> getMetadata();

    default String getScheme() {
        return null;
    }
}
