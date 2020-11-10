package com.example.jiw.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
@ToString
@Entity
@Getter
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long idx;

    @Column
    private LocalDate aggregateDate;

    @Column
    private int aggregateTime;

    @Column
    private int adsRequestCount;

    @Column
    private int adsResponseCount;

    @Column
    private int adsClickCount;

    public void updateAds(Ads ads) {
        this.idx = ads.getIdx();
        this.adsRequestCount += ads.getAdsRequestCount();
        this.adsResponseCount += ads.getAdsResponseCount();
        this.adsClickCount += ads.getAdsClickCount();
    }
}
