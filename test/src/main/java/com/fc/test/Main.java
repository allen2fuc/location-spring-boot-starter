package com.fc.test;

import com.fc.location.geoip2.GeoIP2Service;
import com.fc.location.ip2region.IP2RegionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fucheng on 2023/8/22
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        try(var ctx = SpringApplication.run(Main.class, args)){
            IP2RegionService ip2RegionService = ctx.getBean(IP2RegionService.class);
            System.out.println(ip2RegionService.getIpLocation("183.15.156.42"));

            GeoIP2Service geoIP2Service = ctx.getBean(GeoIP2Service.class);
            System.out.println(geoIP2Service.getLocationInfo("183.15.156.42"));
        }
    }
}
