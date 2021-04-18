package cn.icodening.router.core.strategy;

import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IRouterRule;
import cn.icodening.router.core.IServiceInstance;

import java.util.List;

/**
 * 普通路由策略，只会寻找目标服务
 *
 * @author icodening
 * @date 2021.04.17
 */
public class NormalRoutingStrategy extends AbstractRoutingStrategy {

    public NormalRoutingStrategy(IDiscoveryClient discoveryClient) {
        super(discoveryClient);
    }

    @Override
    public List<IServiceInstance> route(String routingFactorValue, IRouterRule routerRule, List<IServiceInstance> origin) {
        IDiscoveryClient discoveryClient = getDiscoveryClient();
        return discoveryClient.getInstances(routerRule.getTargetService());
    }
}
