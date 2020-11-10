package com.example.jiw;

import com.example.jiw.response.CommonResult;
import com.example.jiw.util.AdsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AdsExceptionAdvice {

    @ExceptionHandler(AdsException.class)
    public ResponseEntity<?> adsException(AdsException e) {
        log.error("AdsExceptionAdvice adsException / {}, {}", e.getMessage(), e.getCode());
        CommonResult errorResult = new CommonResult(e.getMessage(), e.getCode());

        return ResponseEntity.ok().body(errorResult);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ServerException(Exception e) {
        log.error("AdsExceptionAdvice Exception / {}", e.getMessage());
        CommonResult errorResult = new CommonResult(e.getMessage(), AdsUtil.getServerErrorCode());

        return ResponseEntity.ok().body(errorResult);
    }
}
