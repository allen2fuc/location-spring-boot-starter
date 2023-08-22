package com.fc.location.ip2region;


import java.io.Serializable;

public class IP2RegionInfo implements Serializable {
    private String country;
    private String region;
    private String province;
    private String city;
    private String isp;

    // 构造函数
    public IP2RegionInfo(String country, String region, String province, String city, String isp) {
        this.country = country;
        this.region = region;
        this.province = province;
        this.city = city;
        this.isp = isp;
    }

    // Getter 和 Setter 方法
    // 注意：这里为了简化示例，省略了 Getter 和 Setter 方法的定义


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                '}';
    }
}
