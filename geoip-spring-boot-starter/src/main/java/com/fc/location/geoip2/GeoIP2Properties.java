package com.fc.location.geoip2;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fucheng on 2023/8/22
 */
@ConfigurationProperties(prefix = "geoip2")
public record GeoIP2Properties(String mmdb) {

}
