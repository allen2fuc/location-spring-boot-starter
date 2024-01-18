package com.fc.location.ip2region;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

/**
 * @author fucheng on 2023/8/22
 */
@EnableConfigurationProperties(IP2RegionProperties.class)
@AutoConfiguration
public class IP2RegionAutoConfiguration implements InitializingBean {
    private final IP2RegionProperties ip2RegionProperties;

    public IP2RegionAutoConfiguration(IP2RegionProperties ip2RegionProperties) {
        this.ip2RegionProperties = ip2RegionProperties;
    }

    @Bean
    @ConditionalOnMissingBean(name = "ip2RegionService")
    public IP2RegionService ip2RegionService(){
        return new IP2RegionService(ip2RegionProperties.xdb());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(ip2RegionProperties.xdb(), "ip2region.xdb file path must be specified.");
    }
}
