package cn.icodening.router.core.model;

import cn.icodening.router.core.IServiceInstance;

/**
 * @author icodening
 * @date 2021.04.17
 */
public abstract class ServiceInstanceDelegate<T> implements IServiceInstance {

    private final T instance;

    public ServiceInstanceDelegate(T instance) {
        this.instance = instance;
    }

    public T getInstance() {
        return instance;
    }
}
