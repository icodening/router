package cn.icodening.router.config.nacos;

import cn.icodening.router.config.api.AbstractConfigSourceFactory;
import cn.icodening.router.config.api.ConfigSource;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

/**
 * @author icodening
 * @date 2021.04.18
 */
public class NacosConfigSourceFactory extends AbstractConfigSourceFactory {

    @Override
    protected ConfigSource createConfigSource(Properties properties) {
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            NacosConfigSource nacosConfigSource = new NacosConfigSource(configService);
            nacosConfigSource.init(properties);
            return nacosConfigSource;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getUniqueKey(Properties properties) {
        if (properties == null) {
            return "NULL";
        }
        return properties.getProperty("serverAddr");
    }
}
