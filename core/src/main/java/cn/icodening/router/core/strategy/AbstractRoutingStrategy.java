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

    private final String name;

    protected AbstractRoutingStrategy(String name, IDiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    public IDiscoveryClient getDiscoveryClient() {
        return discoveryClient;
    }
}
