package com.ac.inspienweatherapi.controller;

import com.ac.inspienweatherapi.dto.WeatherRequestDTO;
import com.ac.inspienweatherapi.dto.WeatherResponseDTO;
import com.ac.inspienweatherapi.entity.Weather;
import com.ac.inspienweatherapi.service.WeatherService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/weathers")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // 1. 날씨 저장(생성)
    @PostMapping
    public ResponseEntity<WeatherResponseDTO> createWeather(
            @Valid @RequestBody WeatherRequestDTO weatherRequestDTO) {

        WeatherResponseDTO responseDTO = weatherService.createWeather(weatherRequestDTO);
        URI location = URI.create("/weathers/" + responseDTO.getId());

        return ResponseEntity.created(location).body(responseDTO);
    }

    // 2-1. 날씨 전체 조회
    @GetMapping
    public ResponseEntity<List<WeatherResponseDTO>> getAllWeather() {
        List<WeatherResponseDTO> weatherList = weatherService.getAllWeather();

        if (weatherList.isEmpty()) { //날씨 정보가 비어있으면
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(weatherList); // 200 OK
    }

    // 2-1-1. 특정 날짜 조회(id)
    @GetMapping("/{id}")
    public ResponseEntity<WeatherResponseDTO> getWeatherById(@PathVariable Long id) {

        WeatherResponseDTO foundWeather = weatherService.getWeatherById(id);
        return ResponseEntity.ok(foundWeather);
    }

    // 3. 날씨 정보 수정(업데이트)
    @PutMapping("/{id}")
    public ResponseEntity<WeatherResponseDTO> updateWeather(
            @PathVariable Long id,
            @Valid @RequestBody WeatherRequestDTO weatherRequest) {

        WeatherResponseDTO responseDTO = weatherService.updateWeather(id, weatherRequest); //날씨 정보 수정
        URI location = URI.create("/weather/" + responseDTO.getId());

        return ResponseEntity.created(location).body(responseDTO);
    }

    // 4. 날씨 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteWeather(@PathVariable Long id) {
        weatherService.deleteWeather(id); // 날씨 데이터 삭제

        Map<String, Object> response = new HashMap<>();
        response.put("message", + id + "번 ID 날씨 정보가 삭제되었습니다");

        return ResponseEntity.ok(response);
    }

}
