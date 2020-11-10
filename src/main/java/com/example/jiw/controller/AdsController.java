package com.example.jiw.controller;

import com.example.jiw.AdsException;
import com.example.jiw.entity.Ads;
import com.example.jiw.response.AdsInfoResult;
import com.example.jiw.response.CommonResult;
import com.example.jiw.service.AdsService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@Api(value = "AdsController V1")
public class AdsController {

    private final AdsService adsService;
    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @ApiOperation(value = "광고 종합 정보 조회", notes = "선택한 시간에 발생한 광고 종합 정보를 조회한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message ="조회에 성공했습니다."),
            @ApiResponse(code = 404, message ="요청하신 정보는 없는 정보입니다."),
            @ApiResponse(code = 500, message ="서버에 문제가 발생하여 요청을 처리하지 못하였습니다.")
    })
    @GetMapping("/ads")
    public ResponseEntity<?> getAds(
                    @ApiParam(value = "집계날짜", required = true, example = "2020-11-09")@RequestParam("aggregateDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate aggregateDate,
                    @ApiParam(value = "집계시간", required = false, example = "1")@RequestParam(name = "aggregateTime", defaultValue = "0") int aggregateTime) throws AdsException {
        log.info("aggregateDate : {}, aggregateTime : {}", aggregateDate, aggregateTime);
        AdsInfoResult adsInfoResult = adsService.getAds(aggregateDate, aggregateTime);

        return ResponseEntity.status(200).body(adsInfoResult);
    }

    @ApiOperation(value = "광고 종합 정보 생성", notes = "입력한 시간에 정보가 존재하지 않으면 새로운 광고정보를 생성하며 광고정보가 존재한다면 값을 갱신합니다. ")
    @ApiResponses({
            @ApiResponse(code = 201, message ="생성에 성공했습니다."),
            @ApiResponse(code = 500, message ="서버에 문제가 발생하여 요청을 처리하지 못하였습니다.")
    })
    @PostMapping("/ads")
    public ResponseEntity<?> createAds(@RequestBody Ads ads) throws AdsException {
        log.info("ads : {}", ads.toString());
        CommonResult commonResult = adsService.createAds(ads);

        return ResponseEntity.status(201).body(commonResult);
    }
}
