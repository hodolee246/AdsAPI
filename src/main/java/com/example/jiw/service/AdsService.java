package com.example.jiw.service;

import com.example.jiw.AdsException;
import com.example.jiw.response.AdsInfoResult;
import com.example.jiw.entity.Ads;
import com.example.jiw.repository.AdsRepository;
import com.example.jiw.response.CommonResult;
import com.example.jiw.util.AdsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdsService {

    private final int NONE_DATA = 0;
    private final AdsRepository adsRepository;

    public AdsService(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    public AdsInfoResult getAds(LocalDate aggregateDate, int aggregateTime) throws AdsException {
        try {
            AdsInfoResult adsInfoResult = new AdsInfoResult();
            adsInfoResult.setMessage(AdsUtil.getSuccessMessage());
            adsInfoResult.setCode(AdsUtil.getSuccessCode());
            if(aggregateTime == NONE_DATA) {
                List<Ads> adsList = adsRepository.findByAggregateDate(aggregateDate);
                if (adsList.size() == NONE_DATA) throw new NullPointerException();
                // 광고 리스트(요청, 응답, 클릭) 값 더하기
                adsInfoResult.setAdsRequestCount(adsList.stream().mapToInt(Ads::getAdsRequestCount).sum());
                adsInfoResult.setAdsResponseCount(adsList.stream().mapToInt(Ads::getAdsResponseCount).sum());
                adsInfoResult.setAdsClickCount(adsList.stream().mapToInt(Ads::getAdsClickCount).sum());
            } else {
                Ads ads = adsRepository.findByAggregateDateAndAggregateTime(aggregateDate, aggregateTime).orElseThrow(() -> new NullPointerException());
                // 광고 값
                adsInfoResult.setAdsRequestCount(ads.getAdsRequestCount());
                adsInfoResult.setAdsResponseCount(ads.getAdsResponseCount());
                adsInfoResult.setAdsClickCount(ads.getAdsClickCount());
            }

            return adsInfoResult;
        } catch (NullPointerException e) {
            throw new AdsException(AdsUtil.getNotFoundMessage(), AdsUtil.getNotFoundCode());
        } catch (Exception e) {
            throw new AdsException(AdsUtil.getServerErrorMessage(), AdsUtil.getServerErrorCode());
        }
    }

    public CommonResult createAds(Ads ads) throws AdsException {
        try {
            Ads isAds = adsRepository.findByAggregateDateAndAggregateTime(ads.getAggregateDate(), ads.getAggregateTime()).orElse(null);
            if (isAds != null && isAds.getAggregateDate().isEqual(ads.getAggregateDate()) && isAds.getAggregateTime() == ads.getAggregateTime()) {
                ads.updateAds(isAds);
            }
            adsRepository.save(ads);
            CommonResult commonResult = new CommonResult(AdsUtil.getCreateSuccessMessage(), AdsUtil.getCreateSuccessCode());

            return commonResult;
        } catch (Exception e) {
            throw new AdsException(AdsUtil.getServerErrorMessage(), AdsUtil.getServerErrorCode());
        }
    }
}
