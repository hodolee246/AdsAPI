package com.example.jiw;

import com.example.jiw.entity.Ads;
import com.example.jiw.repository.AdsRepository;
import com.example.jiw.response.AdsInfoResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
public class AdsRepositoryTest {

    @Autowired
    private AdsRepository adsRepository;

    private final int NONE_TIME_DATA = 0;

    @Test
    @Transactional
    public void AdsSaveAndFind() {
        LocalDate nowDate = LocalDate.now();
        int nowTime = 15;
        Ads ads = new Ads();
        ads.setAggregateDate(nowDate);
        ads.setAggregateTime(nowTime);
        ads.setAdsRequestCount(4);
        ads.setAdsResponseCount(5);
        ads.setAdsClickCount(6);
        adsRepository.save(ads);
        Ads isAds = adsRepository.findByAggregateDateAndAggregateTime(nowDate, nowTime).orElseGet(Ads::new);
        log.info("ads / {}", ads.toString());
    }

    @Test
    public void getAds() {
        LocalDate date = LocalDate.of(2020, 11, 10);
        Random random = new Random();
        int time = random.nextInt(25);
        if(time == NONE_TIME_DATA) {
            List<Ads> isAds = getListAds(date);
            int totalAdsRequestCount = isAds.stream().mapToInt(Ads::getAdsRequestCount).sum();
            int totalAdsResponseCount = isAds.stream().mapToInt(Ads::getAdsResponseCount).sum();
            int totalAdsClickCount = isAds.stream().mapToInt(Ads::getAdsClickCount).sum();
            log.info("totalAdsRequestCount : {}, totalAdsResponseCount : {}, totalAdsClickCount : {}", totalAdsRequestCount, totalAdsResponseCount, totalAdsClickCount);
        } else {
            Ads ads = getAds(date, time);
            log.info("ads : {}", ads.toString());
        }
    }

    @Test
    @Transactional
    public void createAds() {
        Ads ads = new Ads();
        ads.setAggregateDate(LocalDate.of(2020, 11, 10));
        ads.setAggregateTime(15);
        ads.setAdsRequestCount(3);
        ads.setAdsResponseCount(5);
        ads.setAdsClickCount(7);
        Ads isAds = getAdsOrNull(ads.getAggregateDate(), ads.getAggregateTime());
        if(isAds != null && ads.getAggregateDate().isEqual(isAds.getAggregateDate()) && ads.getAggregateTime() == isAds.getAggregateTime()) {
            ads.updateAds(isAds);
        }
        adsRepository.save(ads);
    }

    public List<Ads> getListAds(LocalDate date) {
        return adsRepository.findByAggregateDate(date);
    }

    public Ads getAds(LocalDate date, int time) {
        return adsRepository.findByAggregateDateAndAggregateTime(date, time).orElseGet(Ads::new);
    }

    public Ads getAdsOrNull(LocalDate date, int time) {
        return adsRepository.findByAggregateDateAndAggregateTime(date, time).orElse(null);
    }

/*    @Test
    public void createAds2(Ads ads) {
        Ads isAds = adsRepository.findByAggregateDateAndAggregateTime(ads.getAggregateDate(), ads.getAggregateTime()).orElse(null);
        if(isAds != null && ads.getAggregateDate().isEqual(isAds.getAggregateDate()) && ads.getAggregateTime() == isAds.getAggregateTime()) {
            ads.updateAds(isAds);
        }
        adsRepository.save(ads);
    }

    @Test
    public void getAds2(Ads ads) {
        if(ads.getAggregateTime() == NONE_TIME_DATA) {
            List<Ads> isAds = adsRepository.findByAggregateDate(ads.getAggregateDate());
            int totalAdsRequestCount = isAds.stream().mapToInt(Ads::getAdsRequestCount).sum();
            int totalAdsResponseCount = isAds.stream().mapToInt(Ads::getAdsResponseCount).sum();
            int totalAdsClickCount = isAds.stream().mapToInt(Ads::getAdsClickCount).sum();
        } else {
            Ads isAds = adsRepository.findByAggregateDateAndAggregateTime(ads.getAggregateDate(), ads.getAggregateTime()).orElse(null);
        }
    }*/
}

