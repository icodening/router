package cn.icodening.router.springcloud;

import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author icodening
 * @date 2021.04.17
 */
public class SpringCloudDiscoveryClient implements IDiscoveryClient {

    private final DiscoveryClient discoveryClient;

    public SpringCloudDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    @Override
    public List<IServiceInstance> getInstances(String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        ArrayList<IServiceInstance> iServiceInstances = new ArrayList<>();
        if (instances == null || instances.isEmpty()) {
            return Collections.emptyList();
        }
        for (ServiceInstance instance : instances) {
            iServiceInstances.add(new SpringCloudInstance(instance));
        }
        return iServiceInstances;
    }

    @Override
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
}
