package cn.icodening.router.core.strategy;

import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IRouterRule;
import cn.icodening.router.core.IServiceInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤出元数据中 key value一致的服务实例
 *
 * @author icodening
 * @date 2021.04.15
 */
public class MetadataRoutingStrategy extends AbstractRoutingStrategy {

    public static final String NAME = "meta";

    public MetadataRoutingStrategy(IDiscoveryClient discoveryClient) {
        super(NAME, discoveryClient);
    }

    @Override
    public List<IServiceInstance> route(String routingFactorValue, IRouterRule routerRule, List<IServiceInstance> origin) {
        List<IServiceInstance> ret = new ArrayList<>();
        List<IServiceInstance> instances = getDiscoveryClient().getInstances(routerRule.getTargetService());
        for (IServiceInstance instance : instances) {
            String meta = instance.getMetadata().get(routerRule.getRoutingFactorKey());
            if (meta != null && meta.equals(routingFactorValue)) {
                ret.add(instance);
            }
        }
        return ret;
    }
}
