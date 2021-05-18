package cn.icodening.router.springcloud.specification;

import cn.icodening.router.springcloud.SpringCloudDiscoveryClient;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.client.discovery.DiscoveryClient;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class LoadBalancerDelegatePostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ILoadBalancer.class.isAssignableFrom(bean.getClass())) {
            DiscoveryClient discoveryClient = beanFactory.getBean(DiscoveryClient.class);
            IRule rule = beanFactory.getBean(IRule.class);
            LoadBalancerDelegate loadBalancerDelegate = new LoadBalancerDelegate((ILoadBalancer) bean, new SpringCloudDiscoveryClient(discoveryClient));
            rule.setLoadBalancer(loadBalancerDelegate);
            return loadBalancerDelegate;
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
