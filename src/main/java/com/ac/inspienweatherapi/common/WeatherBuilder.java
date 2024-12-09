package com.ac.inspienweatherapi.common;

import com.ac.inspienweatherapi.entity.Weather;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public class WeatherBuilder {
    //Entity 클래스와 동일한 필드
    private Long id;
    private LocalDateTime dateTime; // 날짜 및 시간
    private String region;     // 지역
    private String weather;  // 날씨
    private Double temperature; // 온도

    public WeatherBuilder(Weather weather) {
        this.id =weather.getId();
        this.dateTime = weather.getDateTime();
        this.region = weather.getRegion();
        this.weather = weather.getWeather();
        this.temperature = weather.getTemperature();
    }


    // 각 멤버에 대한 setter 메소드 구현
    public WeatherBuilder id(Long val) {
        this.id = val;
        return this;
    }
    public WeatherBuilder dateTime(LocalDateTime val) {
        this.dateTime = val;
        return this;
    }
    public WeatherBuilder region(String val) {
        this.region = val;
        return this;
    }
    public WeatherBuilder weather(String val) {
        this.weather = val;
        return this;
    }
    public WeatherBuilder temperature(Double val) {
        this.temperature = val;
        return this;
    }
    public Weather builder() {
        return new Weather(id, dateTime, region, weather, temperature);
    }
}
