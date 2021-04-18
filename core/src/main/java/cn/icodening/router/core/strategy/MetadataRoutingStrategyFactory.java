package cn.icodening.router.core.strategy;

import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IRoutingStrategy;

/**
 * @author icodening
 * @date 2021.04.17
 */
public class MetadataRoutingStrategyFactory extends AbstractRoutingStrategyFactory {

    @Override
    protected IRoutingStrategy createRoutingStrategy(IDiscoveryClient discoveryClient) {
        return  new MetadataRoutingStrategy(discoveryClient);
    }
}
