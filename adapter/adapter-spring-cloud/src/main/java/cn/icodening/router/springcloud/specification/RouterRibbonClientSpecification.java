package cn.icodening.router.springcloud.specification;

import org.springframework.cloud.netflix.ribbon.RibbonClientSpecification;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class RouterRibbonClientSpecification extends RibbonClientSpecification {

    private static final String DEFAULT_NAME = "default.routerRibbonClientSpecification";

    public RouterRibbonClientSpecification() {
        this(DEFAULT_NAME, new Class[]{LoadBalancerDelegatePostProcessor.class});
    }

    public RouterRibbonClientSpecification(Class<?>[] classes) {
        this(DEFAULT_NAME, classes);
    }

    public RouterRibbonClientSpecification(String name, Class<?>[] configuration) {
        super(name, configuration);
    }
}
