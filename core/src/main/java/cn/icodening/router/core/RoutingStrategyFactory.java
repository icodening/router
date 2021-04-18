package cn.icodening.router.core;

/**
 * @author icodening
 * @date 2021.04.17
 */
public interface RoutingStrategyFactory {

    IRoutingStrategy getRoutingStrategy(IDiscoveryClient discoveryClient);
}
