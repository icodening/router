//package cn.icodening.router;
//
//import cn.icodening.router.config.api.ConfigSource;
//import cn.icodening.router.config.nacos.NacosConfigSourceFactory;
//import cn.icodening.router.springcloud.SpringCloudRouterInterceptor;
//import cn.icodening.router.springcloud.specification.RouterRibbonClientSpecification;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.client.RestTemplateCustomizer;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
///**
// * @author icodening
// * @date 2021.04.19
// */
//@Configuration
//@EnableConfigurationProperties(RouterConfigurationProperties.class)
//public class RouterAutoConfiguration {
//
//    @Bean
//    @ConditionalOnClass(LoadBalancerInterceptor.class)
//    public ClientHttpRequestInterceptor ribbonRouterInterceptor() {
//        NacosConfigSourceFactory nacosConfigSourceFactory = new NacosConfigSourceFactory();
//        Properties properties = new Properties();
//        properties.setProperty("serverAddr", "127.0.0.1:8848");
//        ConfigSource configSource = nacosConfigSourceFactory.getConfigSource(properties);
//        return new SpringCloudRouterInterceptor(configSource);
//    }
//
//
//    @Bean
//    @ConditionalOnClass(LoadBalancerInterceptor.class)
//    public RestTemplateCustomizer restTemplateCustomizer(LoadBalancerInterceptor loadBalancerInterceptor,
//                                                         ClientHttpRequestInterceptor ribbonRouterInterceptor) {
//        return restTemplate -> {
//            List<ClientHttpRequestInterceptor> list = new ArrayList<>(
//                    restTemplate.getInterceptors());
//            list.add(ribbonRouterInterceptor);
//            list.add(loadBalancerInterceptor);
//            restTemplate.setInterceptors(list);
//        };
//    }
//
//    @Bean
//    public RouterRibbonClientSpecification routerRibbonClientSpecification() {
//        return new RouterRibbonClientSpecification();
//    }
//}
