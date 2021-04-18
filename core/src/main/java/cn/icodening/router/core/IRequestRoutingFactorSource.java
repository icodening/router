package cn.icodening.router.core;

/**
 * 请求中路由因子来源
 *
 * @author icodening
 * @date 2021.04.16
 */
@FunctionalInterface
public interface IRequestRoutingFactorSource<REQ> {

    /**
     * 从请求中提取路由因子值
     *
     * @param key     键
     * @param request 请求
     * @return 路由因子对应的值
     */
    String getRoutingFactor(String key, REQ request);
}
