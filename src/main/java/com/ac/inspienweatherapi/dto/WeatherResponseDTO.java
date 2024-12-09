package com.ac.inspienweatherapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherResponseDTO {
    private Long id; // 데이터베이스 ID

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime; // 날짜 및 시간

    private String region;     // 지역
    private String weather;    // 날씨
    private Double temperature; // 온도

    //기본 생성자
    public WeatherResponseDTO() {}
    // 모든 필드 초기화하는 생성자
    public WeatherResponseDTO(Long id, LocalDateTime dateTime, String region, String weather, Double temperature) {
        this.id = id;
        this.dateTime = dateTime;
        this.region = region;
        this.weather = weather;
        this.temperature = temperature;
    }


}
