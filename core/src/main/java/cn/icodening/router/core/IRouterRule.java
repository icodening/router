package cn.icodening.router.core;

/**
 * 路由规则
 *
 * @author icodening
 * @date 2021.04.16
 */
public interface IRouterRule extends Priority, Unique {

    /**
     * 获取路由的目标服务名
     *
     * @return 目标服务名
     */
    String getTargetService();

    /**
     * 获取目标服务的资源路径
     *
     * @return 目标服务的URI
     */
    String getUri();

    /**
     * 路由策略
     *
     * @return 路由策略名
     */
    String getRoutingStrategy();

    /**
     * 路由因子键
     *
     * @return 路由因子键
     */
    String getRoutingFactorKey();


    /**
     * 路由因子值
     *
     * @return 路由因子值数组
     */
    String getRoutingFactorValue();

    /**
     * 路由因子来源
     *
     * @return 路由因子来源方式  header、jsonBody、xmlBody ...
     */
    String getRoutingFactorSource();
}
