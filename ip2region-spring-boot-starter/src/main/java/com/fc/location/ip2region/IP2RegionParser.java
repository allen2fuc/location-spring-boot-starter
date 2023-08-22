package com.fc.location.ip2region;

public class IP2RegionParser {
    public static IP2RegionInfo parse(String input) {
        String[] parts = input.split("\\|");

        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid input format [" + parts.length + "]");
        }

        String country = parts[0];
        String region = parts[1];
        String province = parts[2];
        String city = parts[3];
        String isp = parts[4];

        return new IP2RegionInfo(country, region, province, city, isp);
    }
}
