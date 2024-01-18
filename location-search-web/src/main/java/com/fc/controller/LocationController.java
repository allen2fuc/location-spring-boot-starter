package com.fc.controller;

import com.fc.location.ip2region.IP2RegionInfo;
import com.fc.location.ip2region.IP2RegionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author fucheng on 2023/8/22
 */
@RestController
@RequestMapping("/location")
public class LocationController {
    private final IP2RegionService ip2RegionService;
    private final Log LOG = LogFactory.getLog(LocationController.class);

    public LocationController(IP2RegionService ip2RegionService) {
        this.ip2RegionService = ip2RegionService;
    }

    @GetMapping
    public R<IP2RegionInfo> getLocationForIP(@RequestParam("ip") String ip){
        return R.ok(ip2RegionService.getIpLocation(ip));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public R<Void> handlerException(IllegalArgumentException e){
        LOG.error("error: " + e.getMessage());
        return R.fail(e.getMessage());
    }
}
