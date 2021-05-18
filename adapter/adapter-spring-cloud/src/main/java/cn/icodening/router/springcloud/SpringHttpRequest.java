package cn.icodening.router.springcloud;

import cn.icodening.router.core.http.HttpRequestDelegate;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class SpringHttpRequest extends HttpRequestDelegate<HttpRequest> {
    public SpringHttpRequest(HttpRequest request) {
        super(request);
    }

    @Override
    public String method() {
        return getRequest().getMethodValue();
    }

    @Override
    public String uri() {
        return getRequest().getURI().getQuery();
    }

    @Override
    public String header(String name) {
        return getRequest().getHeaders().getFirst(name);
    }

    @Override
    public void header(String name, String value) {
        getRequest().getHeaders().add(name, value);
    }

    @Override
    public String contentType() {
        MediaType contentType = getRequest().getHeaders().getContentType();
        if (contentType == null) {
            return null;
        }
        return contentType.getType();
    }

    @Override
    public byte[] body() {
        return new byte[0];
    }

    @Override
    public void body(byte[] body) {

    }
}
