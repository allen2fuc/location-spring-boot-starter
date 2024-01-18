package com.fc.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author fucheng on 2024/1/18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
// 响应类
public record R<T>(int code, String message, T data) {
    public static <T> R<T> ok(T data) {
        return new R<>(200, "success", data);
    }

    public static <T> R<T> fail(String message) {
        return new R<>(500, message, null);
    }
}
