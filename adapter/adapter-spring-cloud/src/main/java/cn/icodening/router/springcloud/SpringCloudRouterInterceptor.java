package cn.icodening.router.springcloud;

import cn.icodening.router.config.api.ConfigSource;
import cn.icodening.router.core.IDiscoveryClient;
import cn.icodening.router.core.IRequestRoutingFactorSource;
import cn.icodening.router.core.IRouterRule;
import cn.icodening.router.core.Request;
import cn.icodening.router.core.http.HttpRequestDelegate;
import cn.icodening.router.core.util.RoutingFactorSourceUtil;
import cn.icodening.router.core.util.StringUtils;
import cn.icodening.router.core.util.ThreadLocalContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * @author icodening
 * @date 2021.04.18
 */
public class SpringCloudRouterInterceptor implements ClientHttpRequestInterceptor {

    private final ConfigSource configSource;

    public SpringCloudRouterInterceptor(ConfigSource configSource) {
        this.configSource = configSource;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final URI originalUri = request.getURI();
        String serviceName = originalUri.getHost();
//        //1.根据服务获取对应的路由规则
//        List<IRouterRule> rules = configSource.getRules(serviceName);
//        //2.按照优先级排序
//        Collections.sort(rules);
//        //3.寻找合适的规则
//        for (IRouterRule rule : rules) {
//            String routingFactorSourceName = rule.getRoutingFactorSource();
//            IRequestRoutingFactorSource<Request> routingFactorSource = RoutingFactorSourceUtil.getRoutingFactorSource(routingFactorSourceName);
//            String routingFactorKey = rule.getRoutingFactorKey();
//            String routingFactorValueFromRequest = routingFactorSource.getRoutingFactor(routingFactorKey, new SpringHttpRequest(request));
//            if (StringUtils.isBlank(routingFactorValueFromRequest)) {
//                continue;
//            }
//            if (routingFactorValueFromRequest.equals(rule.getRoutingFactorValue())) {
//                //选中该规则
//                ThreadLocalContext.setObject("routerRule", rule);
//            }
//        }
        return execution.execute(request, body);
    }
}
