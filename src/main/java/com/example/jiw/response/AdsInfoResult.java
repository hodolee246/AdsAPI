package com.example.jiw.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdsInfoResult {
    private String message;
    private int code;
    private int AdsRequestCount;
    private int AdsResponseCount;
    private int AdsClickCount;
}
