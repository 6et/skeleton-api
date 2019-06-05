package com.sixet.skeleton.utils;

import org.springframework.http.HttpStatus;

public class RestUtil {

    private RestUtil() {
    }

    public static boolean isClientError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return HttpStatus.Series.CLIENT_ERROR.equals(series);
    }

    public static boolean isServerError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return HttpStatus.Series.SERVER_ERROR.equals(series);
    }

    public static boolean isError(HttpStatus status) {
        return isClientError(status) || isServerError(status);
    }
}
