package com.robot.robotapocalypse.util;

import com.robot.robotapocalypse.domain.AppResponse;

public enum AppUtils {

    INSTANCE;

    AppUtils() {
    }

    public static boolean pageValidator(int page , int size) {
        return page >= 1 && size > 0;
    }

    public static AppResponse buildAppResponse(String message, boolean success, Object data, Object error) {
        return AppResponse.builder()
                .data(data)
                .error(error)
                .message(message)
                .success(success)
                .build();
    }
}
