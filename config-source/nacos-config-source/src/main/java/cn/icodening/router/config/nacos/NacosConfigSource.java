package cn.icodening.router.config.nacos;

import cn.icodening.router.config.api.ConfigSource;
import cn.icodening.router.core.IRouterRule;
import cn.icodening.router.core.model.ApplicationRouterRules;
import cn.icodening.router.core.model.DefaultRouterRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.filter.impl.ConfigRequest;
import com.alibaba.nacos.client.config.http.HttpAgent;
import com.alibaba.nacos.client.config.http.ServerHttpAgent;
import com.alibaba.nacos.client.config.impl.HttpSimpleClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author icodening
 * @date 2021.04.18
 */
public class NacosConfigSource implements ConfigSource {

    private static final String DEFAULT_GROUP = "ROUTER-GROUP";

    private final ConfigService configService;

    private long getConfigTimeout = 5000;

    private HttpAgent httpAgent;

    public NacosConfigSource(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public List<IRouterRule> getRules(String applicationName, String serviceId) {
        ApplicationRouterRules rules = getRules(applicationName);
        if (rules == null) {
            return Collections.unmodifiableList(Collections.emptyList());
        }
        List<IRouterRule> routerRules = rules.get(serviceId);
        if (routerRules == null || routerRules.isEmpty()) {
            return Collections.unmodifiableList(Collections.emptyList());
        }
        return Collections.unmodifiableList(routerRules);
    }

    @Override
    public List<ApplicationRouterRules> query() {
        return null;
    }

    @Override
    public ApplicationRouterRules getRules(String applicationName) {
        try {
            String config = configService.getConfig(applicationName, DEFAULT_GROUP, getConfigTimeout);
            if (config == null) {
                return new ApplicationRouterRules();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Map<String, List<DefaultRouterRule>> stringListMap = objectMapper.readValue(config, new TypeReference<Map<String, List<DefaultRouterRule>>>() {
            });
            ApplicationRouterRules applicationRouterRules = new ApplicationRouterRules();
            stringListMap.forEach((key, value) -> applicationRouterRules.put(key, new ArrayList<>(value)));
            return applicationRouterRules;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addRouterRule(String applicationName, String serviceId, IRouterRule routerRule) {
        ApplicationRouterRules applicationRouterRules = getRules(applicationName);
        if (applicationRouterRules == null) {
            applicationRouterRules = new ApplicationRouterRules();
        }
        List<IRouterRule> routerRules = applicationRouterRules.get(serviceId);
        if (routerRules == null) {
            routerRules = new ArrayList<>();
        }
        Set<IRouterRule> set = new HashSet<>(routerRules);
        if (!set.add(routerRule)) {
            return;
        }
        routerRules.add(routerRule);
        applicationRouterRules.put(serviceId, routerRules);
        String content = JSON.toJSONString(applicationRouterRules);
        publishConfig(applicationName, content);
    }

    @Override
    public void removeRouterRule(String applicationName, String serviceId, String id) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(serviceId)) {
            return;
        }
        ApplicationRouterRules applicationRouterRules = getRules(applicationName);
        if (applicationRouterRules == null) {
            return;
        }
        List<IRouterRule> rules = applicationRouterRules.get(serviceId);
        if (rules == null) {
            return;
        }
        List<IRouterRule> collect = rules.stream().filter(rule -> id.equals(rule.getId())).collect(Collectors.toList());
        applicationRouterRules.put(serviceId, collect);
        String content = JSON.toJSONString(applicationRouterRules);
        publishConfig(applicationName, content);
    }

    @Override
    public void removeRouterRule(String applicationName, String serviceId) {
        ApplicationRouterRules rules = getRules(applicationName);
        rules.remove(serviceId);
        String content = JSON.toJSONString(rules);
        publishConfig(applicationName, content);
    }

    @Override
    public void removeRouterRule(String applicationName) {
        try {
            configService.removeConfig(applicationName, DEFAULT_GROUP);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    private HttpSimpleClient.HttpResult publishConfig(String applicationName, String content) {
        ConfigRequest cr = new ConfigRequest();
        cr.setDataId(applicationName);
        cr.setGroup(DEFAULT_GROUP);
        cr.setContent(content);
        String url = Constants.CONFIG_CONTROLLER_PATH;
        List<String> params = new ArrayList<>();
        params.add("dataId");
        params.add(applicationName);
        params.add("group");
        params.add(DEFAULT_GROUP);
        params.add("content");
        params.add(content);
        params.add("type");
        params.add("json");
        List<String> headers = new ArrayList<>();
        try {
            return httpAgent.httpPost(url, headers, params, "UTF-8", 5000);
        } catch (IOException ignore) {
        }
        return null;
    }

    public void init(Properties properties) {
        this.getConfigTimeout = Integer.parseInt(properties.getProperty("timeoutMs", "5000"));
        try {
            this.httpAgent = new ServerHttpAgent(properties);
            this.httpAgent.start();
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
