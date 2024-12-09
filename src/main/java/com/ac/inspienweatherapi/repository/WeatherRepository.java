package com.ac.inspienweatherapi.repository;

import com.ac.inspienweatherapi.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    // 2-2. 특정 날짜에 해당하는 날씨 조회
    @Query("SELECT w FROM Weather w WHERE DATE(w.dateTime) = :date")
    List<Weather> findByDate(@Param("date") LocalDate date);

    // 2-3. 특정 지역(region) and 날짜 범위에 해당 하는 날씨 조회
    @Query(value = "SELECT * FROM weather w WHERE w.region = :region AND w.date_time BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Weather> findByRegionAndDateRangeNative(
            @Param("region") String region,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
