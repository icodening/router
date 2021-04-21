package cn.icodening.router.admin.controller;

import cn.icodening.router.admin.service.RouterRuleService;
import cn.icodening.router.admin.vo.Result;
import cn.icodening.router.core.model.DefaultRouterRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author icodening
 * @date 2021.04.19
 */
@RestController
@RequestMapping("/rules")
public class RouterRuleController {

    @Autowired
    private RouterRuleService routerRuleService;

    @GetMapping
    public Object list() {
        return null;
    }

    @PostMapping("/{application}/{serviceId}")
    public Object addRule(@PathVariable(name = "application") String application,
                          @PathVariable(name = "serviceId") String serviceId,
                          @RequestBody DefaultRouterRule routerRule) {
        routerRuleService.addRule(application, serviceId, routerRule);
        return new Result();
    }

    @DeleteMapping("/{application}/{serviceId}/{routeRuleId}")
    public Object deleteRule(@PathVariable(name = "application") String application,
                             @PathVariable(name = "serviceId") String serviceId,
                             @PathVariable("routeRuleId") String routeRuleId) {
        routerRuleService.deleteRule(application, serviceId, routeRuleId);
        return new Result();
    }

}
