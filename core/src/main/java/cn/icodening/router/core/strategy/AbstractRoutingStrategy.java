package cn.icodening.router.core.strategy;

import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IRoutingStrategy;

/**
 * @author icodening
 * @date 2021.04.15
 */
public abstract class AbstractRoutingStrategy
        implements IRoutingStrategy {

    private final IDiscoveryClient discoveryClient;

    protected AbstractRoutingStrategy(IDiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    public IDiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }
}
