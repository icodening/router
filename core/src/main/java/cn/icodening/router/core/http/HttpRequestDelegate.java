package cn.icodening.router.core.http;

/**
 * @author icodening
 * @date 2021.04.17
 */
public abstract class HttpRequestDelegate<T> implements StandardHttpRequest {

    private final T request;

    public HttpRequestDelegate(T request) {
        this.request = request;
    }

    protected T getRequest() {
        return request;
    }
}
