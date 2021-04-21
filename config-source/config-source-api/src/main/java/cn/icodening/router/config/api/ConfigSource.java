package cn.icodening.router.config.api;

import cn.icodening.router.core.IRouterRule;
import cn.icodening.router.core.model.ApplicationRouterRules;

import java.util.List;

/**
 * 配置数据源
 *
 * @author icodening
 * @date 2021.04.18
 */
public interface ConfigSource {

    /**
     * 获取指定应用指定服务名的所有规则
     *
     * @param applicationName 应用名
     * @param serviceId       目标服务名
     * @return 指定服务名下的所有规则
     */
    List<IRouterRule> getRules(String applicationName, String serviceId);


    List<ApplicationRouterRules> query();


    /**
     * 获取整个应用下的规则信息
     *
     * @param applicationName 应用名
     * @return 应用下所有服务对应的路由规则
     */
    ApplicationRouterRules getRules(String applicationName);

    /**
     * 给应用添加一条路由规则
     *
     * @param applicationName 应用名
     * @param serviceId       目标服务名
     * @param routerRule      路由规则
     */
    void addRouterRule(String applicationName, String serviceId, IRouterRule routerRule);


    /**
     * 移除指定应用下，目标服务下的一个路由规则
     *
     * @param applicationName 应用名
     * @param serviceId       服务名
     * @param id              规则id
     */
    void removeRouterRule(String applicationName, String serviceId, String id);

    /**
     * 移除指定应用下，目标服务的所有规则
     *
     * @param applicationName 应用
     * @param serviceId       目标服务
     */
    void removeRouterRule(String applicationName, String serviceId);

    /**
     * 移除指定应用下的所有规则
     *
     * @param applicationName 应用名
     */
    void removeRouterRule(String applicationName);
}
