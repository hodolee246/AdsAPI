package com.example.jiw.util;


public class AdsUtil {
    // 500 err
    private final static String SERVER_ERROR_MESSAGE = "서버에 문제가 발생하여 요청을 처리하지 못하였습니다.";
    private final static int SERVER_ERROR_CODE = 500;
    // 404 err
    private final static String NOT_FOUND_MESSAGE = "요청하신 정보는 없는 정보입니다.";
    private final static int NOT_FOUND_CODE = 404;
    // 200 ok
    private final static String SUCCESS_MESSAGE = "조회에 성공했습니다.";
    private final static int SUCCESS_CODE = 200;
    // 201 ok
    private final static String CREATE_SUCCESS_MESSAGE = "생성에 성공했습니다.";
    private final static int CREATE_SUCCESS_CODE = 201;

    public static String getServerErrorMessage() {
        return SERVER_ERROR_MESSAGE;
    }

    public static int getServerErrorCode() {
        return SERVER_ERROR_CODE;
    }

    public static String getNotFoundMessage() {
        return NOT_FOUND_MESSAGE;
    }

    public static int getNotFoundCode() {
        return NOT_FOUND_CODE;
    }

    public static String getSuccessMessage() {
        return SUCCESS_MESSAGE;
    }

    public static int getSuccessCode() {
        return SUCCESS_CODE;
    }

    public static String getCreateSuccessMessage() {
        return CREATE_SUCCESS_MESSAGE;
    }

    public static int getCreateSuccessCode() {
        return CREATE_SUCCESS_CODE;
    }
}
