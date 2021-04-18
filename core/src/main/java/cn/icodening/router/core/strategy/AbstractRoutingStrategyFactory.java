package cn.icodening.router.core.strategy;

import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IRoutingStrategy;
import cn.icodening.router.core.RoutingStrategyFactory;

/**
 * @author icodening
 * @date 2021.04.17
 */
public abstract class AbstractRoutingStrategyFactory
        implements RoutingStrategyFactory {

    private final Object MONITOR = new Object();

    private IRoutingStrategy routingStrategy;

    @Override
    public IRoutingStrategy getRoutingStrategy(IDiscoveryClient discoveryClient) {
        if (routingStrategy != null) {
            return routingStrategy;
        }
        synchronized (MONITOR) {
            if (routingStrategy == null) {
                this.routingStrategy = createRoutingStrategy(discoveryClient);
            }
        }
        return routingStrategy;
    }

    protected abstract IRoutingStrategy createRoutingStrategy(IDiscoveryClient discoveryClient);
}
