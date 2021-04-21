package cn.icodening.router.config.api;

import java.util.Properties;

/**
 * 配置数据源工厂
 *
 * @author icodening
 * @date 2021.04.18
 */
public interface ConfigSourceFactory {

    /**
     * 获得一个配置源
     *
     * @param properties 配置项
     * @return 配置源
     */
    ConfigSource getConfigSource(Properties properties);
}
