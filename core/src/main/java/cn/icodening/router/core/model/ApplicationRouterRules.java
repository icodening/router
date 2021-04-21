package cn.icodening.router.core.model;

import cn.icodening.router.core.IRouterRule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author icodening
 * @date 2021.04.21
 */
public class ApplicationRouterRules extends AbstractMap<String, List<IRouterRule>> {

    private final Map<String, List<IRouterRule>> map = new ConcurrentHashMap<>();

    @Override
    public List<IRouterRule> put(String key, List<IRouterRule> value) {
        return map.put(key, value);
    }

    @Override
    public List<IRouterRule> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public Set<Entry<String, List<IRouterRule>>> entrySet() {
        return map.entrySet();
    }

    @Override
    public List<IRouterRule> get(Object key) {
        return map.get(key);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ApplicationRouterRules that = (ApplicationRouterRules) object;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), map);
    }
}
