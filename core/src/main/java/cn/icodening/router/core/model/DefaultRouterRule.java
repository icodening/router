package cn.icodening.router.core.model;

import cn.icodening.router.core.IRouterRule;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class DefaultRouterRule implements IRouterRule {

    private static final String DEFAULT_STRATEGY = "normal";

    private static final String DEFAULT_SOURCE = "HttpHeader";

    private String id;

    private String targetService;

    private String uri;

    private String routingStrategy = DEFAULT_STRATEGY;

    private String routingFactorKey;

    private String routingFactorValue;

    private String routingFactorSource = DEFAULT_SOURCE;

    public DefaultRouterRule() {
    }

    public DefaultRouterRule(String targetService, String uri, String routingFactorKey, String routingFactorValue) {
        this.targetService = targetService;
        this.uri = uri;
        this.routingFactorKey = routingFactorKey;
        this.routingFactorValue = routingFactorValue;
    }

    public void setRoutingStrategy(String routingStrategy) {
        this.routingStrategy = routingStrategy;
    }

    public void setRoutingFactorSource(String routingFactorSource) {
        this.routingFactorSource = routingFactorSource;
    }

    public void setTargetService(String targetService) {
        this.targetService = targetService;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setRoutingFactorKey(String routingFactorKey) {
        this.routingFactorKey = routingFactorKey;
    }

    public void setRoutingFactorValue(String routingFactorValue) {
        this.routingFactorValue = routingFactorValue;
    }

    @Override
    public String getTargetService() {
        return targetService;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getRoutingStrategy() {
        return routingStrategy;
    }

    @Override
    public String getRoutingFactorKey() {
        return routingFactorKey;
    }

    @Override
    public String getRoutingFactorValue() {
        return routingFactorValue;
    }

    @Override
    public String getRoutingFactorSource() {
        return routingFactorSource;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
