package cn.icodening.router.springcloud.specification;

import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IRouterRule;
import cn.icodening.router.core.IServiceInstance;
import cn.icodening.router.core.util.ThreadLocalContext;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class LoadBalancerDelegate implements ILoadBalancer {

    private final ILoadBalancer baseLoadBalancer;

    private final IDiscoveryClient discoveryClient;

    public LoadBalancerDelegate(ILoadBalancer baseLoadBalancer, IDiscoveryClient discoveryClient) {
        this.baseLoadBalancer = baseLoadBalancer;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public List<Server> getAllServers() {
        IRouterRule rule = ThreadLocalContext.getObject("routerRule");
        if (rule == null) {
            return baseLoadBalancer.getAllServers();
        }
        String targetService = rule.getTargetService();
        List<Server> servers = new ArrayList<>();
        List<IServiceInstance> instances = discoveryClient.getInstances(targetService);
        for (IServiceInstance instance : instances) {
            servers.add(buildServer(instance));
        }
        return servers;
    }

    @Override
    public void addServers(List<Server> newServers) {
        baseLoadBalancer.addServers(newServers);
    }

    @Override
    public Server chooseServer(Object key) {
        return baseLoadBalancer.chooseServer(key);
    }

    @Override
    public void markServerDown(Server server) {
        baseLoadBalancer.markServerDown(server);
    }

    @Override
    public List<Server> getServerList(boolean availableOnly) {
        return baseLoadBalancer.getServerList(availableOnly);
    }

    @Override
    public List<Server> getReachableServers() {
        IRouterRule rule = ThreadLocalContext.getObject("routerRule");
        if (rule == null) {
            return baseLoadBalancer.getReachableServers();
        }
        String targetService = rule.getTargetService();
        List<Server> servers = new ArrayList<>();
        List<IServiceInstance> instances = discoveryClient.getInstances(targetService);
        for (IServiceInstance instance : instances) {
            servers.add(buildServer(instance));
        }
        return servers;
    }

    private Server buildServer(IServiceInstance instance) {
        InstanceInfo.Builder builder = InstanceInfo.Builder.newBuilder();
        Map<String, String> metadata = instance.getMetadata();
        builder.setHostName(instance.getHost());
        builder.setInstanceId(instance.getInstanceId());
        builder.setMetadata(metadata);
        builder.setVIPAddress(instance.getHost());
        builder.setAppName(instance.getServiceId());
        builder.setPort(instance.getPort());
        InstanceInfo build = builder.build();
        return new DiscoveryEnabledServer(build, false);
    }
}
