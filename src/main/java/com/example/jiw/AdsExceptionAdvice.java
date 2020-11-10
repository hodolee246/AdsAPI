package com.example.jiw;

import com.example.jiw.response.CommonResult;
import com.example.jiw.util.AdsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AdsExceptionAdvice {

    @ExceptionHandler(AdsException.class)
    public ResponseEntity adsException(AdsException e) {
        log.error("adsExceptionMessage : {}, adsExceptionCode : {}", e.getMessage(), e.getCode());
        CommonResult errorResult = new CommonResult(e.getMessage(), e.getCode());
        if(e.getCode() == AdsUtil.getNotFoundCode()) {
            return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity ServerException(Exception e) {
        log.error("exceptionMessage : {}", e.getMessage());
        CommonResult errorResult = new CommonResult(AdsUtil.getServerErrorMessage(), AdsUtil.getServerErrorCode());
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
