package com.fc.location.ip2region;

public class LocationSearchException extends RuntimeException {
    public LocationSearchException(String message, Throwable e) {
        super(message, e);
    }
}
