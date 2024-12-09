package com.ac.inspienweatherapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WeatherRequestDTO {
    @NotNull(message = "지역은 필수 값입니다.")
    private String region;

    @NotNull(message = "날씨는 필수 값입니다.")
    private String weather;

    @NotNull(message = "온도는 필수 값입니다.")
    private Double temperature;
}