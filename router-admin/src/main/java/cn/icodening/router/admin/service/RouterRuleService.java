package cn.icodening.router.admin.service;

import cn.icodening.router.admin.model.PushData;
import cn.icodening.router.config.api.ConfigSource;
import cn.icodening.router.core.IRouterRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icodening
 * @date 2021.04.19
 */
@Service
public class RouterRuleService {

    private ConfigSource configSource;

    @Autowired
    public void setConfigSource(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public PushData addRule(String application, String serviceId, IRouterRule routerRule) {
        configSource.addRouterRule(application, serviceId, routerRule);
        return new PushData();
    }

    public void deleteRule(String application, String serviceId, String routeRuleId) {
        configSource.removeRouterRule(application, serviceId, routeRuleId);
    }

    public List<IRouterRule> query() {
        return new ArrayList<>();
    }
}

