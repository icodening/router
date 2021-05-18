package cn.icodening.router.springcloud;

import cn.icodening.router.core.model.ServiceInstanceDelegate;
import org.springframework.cloud.client.ServiceInstance;

import java.net.URI;
import java.util.Map;

/**
 * @author icodening
 * @date 2021.04.17
 */
public class SpringCloudInstance extends ServiceInstanceDelegate<ServiceInstance> {

    public SpringCloudInstance(ServiceInstance instance) {
        super(instance);
    }

    @Override
    public String getServiceId() {
        return getInstance().getServiceId();
    }

    @Override
    public String getHost() {
        return getInstance().getHost();
    }

    @Override
    public int getPort() {
        return getInstance().getPort();
    }

    @Override
    public boolean isSecure() {
        return getInstance().isSecure();
    }

    @Override
    public URI getUri() {
        return getInstance().getUri();
    }

    @Override
    public Map<String, String> getMetadata() {
        return getInstance().getMetadata();
    }
}
