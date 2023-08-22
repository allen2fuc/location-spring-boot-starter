package com.fc.location.ip2region;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author fucheng on 2023/8/22
 */
@EnableConfigurationProperties(IP2RegionProperties.class)
@AutoConfiguration
public class IP2RegionAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = "ip2RegionService")
    public IP2RegionService ip2RegionService(IP2RegionProperties ip2RegionProperties){
        return new IP2RegionService(ip2RegionProperties.xdb());
    }
}
