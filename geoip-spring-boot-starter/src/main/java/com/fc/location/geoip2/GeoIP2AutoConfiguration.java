package com.fc.location.geoip2;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author fucheng on 2023/8/22
 */
@EnableConfigurationProperties(GeoIP2Properties.class)
@AutoConfiguration
public class GeoIP2AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GeoIP2Service geoip2Service(GeoIP2Properties geoip2Properties) {
        return new GeoIP2Service(geoip2Properties.mmdb());
    }
}
