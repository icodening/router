package cn.icodening.router.admin.service;

import cn.icodening.router.admin.model.PushData;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author icodening
 * @date 2021.04.19
 */
@Component
public class PushDataAdvisor extends AbstractGenericPointcutAdvisor {

    private PushDataService pushDataService;

    @Autowired
    public void setPushDataService(PushDataService pushDataService) {
        this.pushDataService = pushDataService;
    }

    @Override
    public Pointcut getPointcut() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return PushData.class.isAssignableFrom(method.getReturnType());
            }
        };
    }

    public PushDataAdvisor() {
        setAdvice((AfterReturningAdvice) (returnValue, method, args, target) -> {
            if (returnValue == null) {
                return;
            }
            pushDataService.pushData((PushData) returnValue);
        });
    }
}
