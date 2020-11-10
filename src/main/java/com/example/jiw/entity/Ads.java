package com.example.jiw.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@ToString
public class Ads {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "인덱스")
    private Long idx;

    @Column
    @ApiModelProperty(value = "집계날짜")
    private LocalDate aggregateDate;

    @Column
    @ApiModelProperty(value = "집계시간")
    private int aggregateTime;

    @Column
    @ApiModelProperty(value = "광고요청 수")
    private int adsRequestCount;

    @Column
    @ApiModelProperty(value = "광고응답 수")
    private int adsResponseCount;

    @Column
    @ApiModelProperty(value = "광고클릭 수")
    private int adsClickCount;

    public void updateAds(Ads ads) {
        this.idx = ads.getIdx();
        this.adsRequestCount += ads.getAdsRequestCount();
        this.adsResponseCount += ads.getAdsResponseCount();
        this.adsClickCount += ads.getAdsClickCount();
    }
}
