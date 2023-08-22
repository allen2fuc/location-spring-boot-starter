package com.fc.controller;

import com.fc.location.ip2region.IP2RegionInfo;
import com.fc.location.ip2region.IP2RegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fucheng on 2023/8/22
 */
@RestController
@RequestMapping("/location")
public class LocationController {
    private final IP2RegionService ip2RegionService;

    public LocationController(IP2RegionService ip2RegionService) {
        this.ip2RegionService = ip2RegionService;
    }

    @GetMapping
    public IP2RegionInfo getLocationForIP(@RequestParam("ip") String ip){
        return ip2RegionService.getIpLocation(ip);
    }

}
