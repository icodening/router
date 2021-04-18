package cn.icodening.router.core;

import java.util.List;

/**
 * @author icodening
 * @date 2021.04.15
 */
public interface IRoutingStrategy {

    /**
     * 获取路由策略名字
     *
     * @return 当前路由策略的名字
     */
    String name();

    /**
     * 路由
     *
     * @param routingFactorValue 从请求中获取到的路由因子值
     * @param routerRule         路由规则
     * @param origin             原始服务列表
     * @return 路由后的服务列表
     */
    List<IServiceInstance> route(String routingFactorValue, IRouterRule routerRule, List<IServiceInstance> origin);
}
