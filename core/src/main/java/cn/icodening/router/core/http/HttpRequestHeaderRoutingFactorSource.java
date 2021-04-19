package cn.icodening.router.core.http;

import cn.icodening.router.core.IRequestRoutingFactorSource;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class HttpRequestHeaderRoutingFactorSource implements IRequestRoutingFactorSource<StandardHttpRequest> {

    @Override
    public String name() {
        return "HttpHeader";
    }

    @Override
    public String getRoutingFactor(String key, StandardHttpRequest request) {
        return request.header(key);
    }
}
