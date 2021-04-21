package cn.icodening.router.admin;

import cn.icodening.router.config.api.ConfigSource;
import cn.icodening.router.config.nacos.NacosConfigSourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * @author icodening
 * @date 2021.04.19
 */
@SpringBootApplication
public class RouterAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouterAdminApplication.class);
    }

    @Bean
    public ConfigSource nacosConfigSource() {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1:8848");
        return new NacosConfigSourceFactory().getConfigSource(properties);
    }

}
