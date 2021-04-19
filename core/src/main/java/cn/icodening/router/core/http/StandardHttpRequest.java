package cn.icodening.router.core.http;

import cn.icodening.router.core.Request;

/**
 * @author icodening
 * @date 2021.04.17
 */
public interface StandardHttpRequest extends Request {

    String method();

    String uri();

    String header(String name);

    void header(String name, String value);

    String contentType();
}
