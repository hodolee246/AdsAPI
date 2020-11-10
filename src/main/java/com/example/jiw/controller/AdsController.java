package com.example.jiw.controller;

import com.example.jiw.AdsException;
import com.example.jiw.entity.Ads;
import com.example.jiw.repository.AdsRepository;
import com.example.jiw.response.AdsInfoResult;
import com.example.jiw.response.CommonResult;
import com.example.jiw.service.AdsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
public class AdsController {

    private final AdsService adsService;
    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @ApiOperation(value = "광고 종합 정보 조회", notes = "선택한 시간에 발생한 광고 종합 정보를 조회한다. ")
    @GetMapping("/ads")
    public ResponseEntity<?> get(@RequestParam("aggregateDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate aggregateDate,
                              @RequestParam(name = "aggregateTime", defaultValue = "0") int aggregateTime) throws AdsException {
        log.info("AdsController / {} / {}", aggregateDate, aggregateTime);
        AdsInfoResult adsInfoResult = adsService.getAds(aggregateDate, aggregateTime);

        return ResponseEntity.ok().body(adsInfoResult);
    }

    @ApiOperation(value = "광고 종합 정보 생성", notes = "입력한 시간에 정보가 존재하지 않으면 새로운 광고정보를 생성하며 광고정보가 존재한다면 값을 갱신합니다. ")
    @PostMapping("/ads")
    public ResponseEntity<?> post(@RequestBody Ads ads) throws AdsException {
        log.info("AdsController / {}", ads.toString());
        CommonResult commonResult = adsService.createAds(ads);

        return ResponseEntity.ok().body(commonResult);
    }
}
