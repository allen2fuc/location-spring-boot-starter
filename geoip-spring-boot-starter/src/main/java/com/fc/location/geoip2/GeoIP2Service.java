package com.fc.location.geoip2;

import cn.hutool.core.lang.RegexPool;
import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.util.ReUtil;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @author fucheng on 2023/8/22
 */
public class GeoIP2Service implements InitializingBean, DisposableBean {

    private static final String LOCALE = "zh-CN";
    private DatabaseReader reader;
    private final String mmdbPath;

    public GeoIP2Service(String mmdbPath) {
        this.mmdbPath = mmdbPath;
    }

    public GeoIP2Info getLocationInfo(String address) {
        if (!isValidIPv4(address) || isInternalIp(address)) {
            return null;
        }

        try {
            CityResponse response = fetchCityResponse(address);
            return createGeoIP2Info(response);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isValidIPv4(String ipAddress) {
        return ReUtil.isMatch(RegexPool.IPV4, ipAddress);
    }

    private boolean isInternalIp(String ipAddress) {
        return Ipv4Util.isInnerIP(ipAddress);
    }

    private CityResponse fetchCityResponse(String ipAddress) throws IOException, GeoIp2Exception {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        return reader.city(inetAddress);
    }

    private GeoIP2Info createGeoIP2Info(CityResponse response) {
        String country = response.getCountry().getNames().get(LOCALE);
        String province = response.getSubdivisions().get(0).getNames().get(LOCALE);
        String city = response.getCity().getNames().get(LOCALE);
        Double latitude = response.getLocation().getLatitude();
        Double longitude = response.getLocation().getLongitude();

        GeoIP2Info geoIP2Info = new GeoIP2Info();
        geoIP2Info.setCountry(country);
        geoIP2Info.setProvince(province);
        geoIP2Info.setCity(city);
        geoIP2Info.setLatitude(latitude);
        geoIP2Info.setLongitude(longitude);

        return geoIP2Info;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (mmdbPath == null || mmdbPath.isEmpty()) {
            throw new IllegalArgumentException("The 'geoip2.mmdb' property must be configured.");
        }

        File database = new File(mmdbPath);

        if (!database.exists()) {
            throw new FileNotFoundException("GeoIP2 database file not found: " + mmdbPath);
        }
        this.reader = new DatabaseReader.Builder(database).build();
    }

    @Override
    public void destroy() throws Exception {
        this.reader.close();
    }
}
