package com.fc.location.ip2region;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fucheng on 2023/8/22
 */
@ConfigurationProperties(prefix = "ip2region")
public record IP2RegionProperties(String xdb) {
}
