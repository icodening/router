package cn.icodening.router.config.api;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author icodening
 * @date 2021.04.18
 */
public abstract class AbstractConfigSourceFactory implements ConfigSourceFactory {

    private Map<String, ConfigSource> configSourceMap = new ConcurrentHashMap<>();

    @Override
    public ConfigSource getConfigSource(Properties properties) {
        String key = getUniqueKey(properties);
        if (key == null) {
            throw new NullPointerException("配置数据源唯一条件不能为空");
        }
        key = key.intern();
        ConfigSource configSource = configSourceMap.get(key);
        if (configSource != null) {
            return configSource;
        }
        synchronized (key) {
            configSource = configSourceMap.get(key);
            if (configSource == null) {
                configSource = createConfigSource(properties);
                configSourceMap.putIfAbsent(key, configSource);
                configSource = configSourceMap.get(key);
            }
        }
        return configSource;
    }

    protected abstract ConfigSource createConfigSource(Properties properties);

    protected abstract String getUniqueKey(Properties properties);
}
