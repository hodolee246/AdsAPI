package com.example.jiw.repository;

import com.example.jiw.entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdsRepository extends JpaRepository<Ads, Long> {
    List<Ads> findByAggregateDate(LocalDate aggregateDate);
    Optional<Ads> findByAggregateDateAndAggregateTime(LocalDate aggregateDate, int aggregateTime);
}
