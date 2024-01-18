package com.fc.location.ip2region;

import cn.hutool.core.lang.RegexPool;
import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.util.ReUtil;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * @author fucheng on 2023/8/15
 */
public class IP2RegionService implements InitializingBean, DisposableBean {

    private final String xdbPath;
    private Searcher searcher;

    public IP2RegionService(String xdbPath) {
        this.xdbPath = xdbPath;
    }

    public IP2RegionInfo getIpLocation(String ipAddress) {
        if (!isValidIPv4(ipAddress)) {
            // 不是有效的IPv4地址，抛出异常
            throw new IllegalArgumentException("Invalid IPv4 address: " + ipAddress);
        }

        if (isInternalIp(ipAddress)){
            // 内网IP，抛出异常
            throw new IllegalArgumentException("Inner IP: " + ipAddress + ", no need to search.");
        }

        try {
            String locationString = searchLocationInfo(ipAddress);
            return parseLocationInfo(locationString);
        } catch (LocationSearchException e) {
            // 处理地理位置搜索异常
            throw new IllegalArgumentException("Parse location information failed for IP: " + ipAddress, e);
        }
    }

    private boolean isValidIPv4(String ipAddress) {
        return ReUtil.isMatch(RegexPool.IPV4, ipAddress);
    }

    private boolean isInternalIp(String ipAddress) {
        return Ipv4Util.isInnerIP(ipAddress);
    }

    private String searchLocationInfo(String ipAddress) throws LocationSearchException {
        try {
            return searcher.search(ipAddress);
        } catch (Exception e) {
            // 可能有其他异常，将其包装为自定义异常以提供更多信息
            throw new LocationSearchException("Error searching location information for IP: " + ipAddress, e);
        }
    }

    private IP2RegionInfo parseLocationInfo(String locationString) {
        return IP2RegionParser.parse(locationString);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkXdbPath();

        byte[] contentBuffer = loadXdbContent();
        this.searcher = createSearcherWithBuffer(contentBuffer);
    }

    private void checkXdbPath() {
        Assert.notNull(xdbPath, "The 'ip2region.xdb' property must be provided.");
    }

    private byte[] loadXdbContent() throws IOException {
        return Searcher.loadContentFromFile(xdbPath);
    }

    private Searcher createSearcherWithBuffer(byte[] buffer) throws IOException {
        return Searcher.newWithBuffer(buffer);
    }

    @Override
    public void destroy() throws Exception {
        searcher.close();
    }


}
