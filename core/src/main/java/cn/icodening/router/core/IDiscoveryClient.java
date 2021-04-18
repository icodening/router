package cn.icodening.router.core;

import java.util.List;

/**
 * @author icodening
 * @date 2021.04.17
 */
public interface IDiscoveryClient {

    List<IServiceInstance> getInstances(String serviceId);

    List<String> getServices();

}
