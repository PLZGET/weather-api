package com.ac.inspienweatherapi.service;

import com.ac.inspienweatherapi.common.WeatherBuilder;
import com.ac.inspienweatherapi.dto.WeatherRequestDTO;
import com.ac.inspienweatherapi.dto.WeatherResponseDTO;
import com.ac.inspienweatherapi.entity.Weather;
import com.ac.inspienweatherapi.controller.WeatherNotFoundException;
import com.ac.inspienweatherapi.repository.WeatherRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository,
                          ModelMapper modelMapper) {
        this.weatherRepository = weatherRepository;
        this.modelMapper = modelMapper;
    }

    // 1. 날씨 저장(생성)
    public WeatherResponseDTO createWeather(WeatherRequestDTO weatherRequestDTO) {
        // DTO -> Entity 변환
        Weather weather = modelMapper.map(weatherRequestDTO, Weather.class);
        // 날씨 데이터 저장
        Weather savedWeather = weatherRepository.save(weather);
        // Entity -> DTO 변환
        WeatherResponseDTO responseDTO = modelMapper.map(savedWeather, WeatherResponseDTO.class);
        return responseDTO;
    }

    // 2-1. 날씨 전체 조회
    public List<WeatherResponseDTO> getAllWeather() {

        List<Weather> weatherList = weatherRepository.findAll(); // 모든 Weather 조회

        // WeatherDTO로 변환하여 반환(modelMapper 사용)
        List<WeatherResponseDTO> weatherResponseDTOList = weatherList.stream()                        //
                .map(weather -> modelMapper.map(weather, WeatherResponseDTO.class))
                .toList();

        return weatherResponseDTOList;
    }

    // 2-1-1. 특정 날짜 조회(id)
    public WeatherResponseDTO getWeatherById(Long id) {
        WeatherResponseDTO foundWeather = weatherRepository.findById(id)                   //
                .map(weather -> modelMapper.map(weather, WeatherResponseDTO.class))
                .orElseThrow(() -> new WeatherNotFoundException("해당 날씨 찾을 수 없음"));

        return foundWeather;
    }

    // 3. 날씨 정보 수정(업데이트)
    @Transactional
    public WeatherResponseDTO updateWeather(Long id, WeatherRequestDTO weatherRequest) {
        // 1. 기존 데이터 조회
        Weather existingWeather = weatherRepository.findById(id)
                .orElseThrow(() -> new WeatherNotFoundException("해당 날씨 찾을 수 없음"));
        // 2. 요청 데이터를 기존 데이터에 반영 (Builder 클래스를  구현한거 사용)
        existingWeather = new WeatherBuilder(existingWeather)
                .weather(weatherRequest.getWeather())
                .region(weatherRequest.getRegion())
                .temperature(weatherRequest.getTemperature())
                .builder();
        // 3. 데이터 저장
        Weather updatedWeather = weatherRepository.save(existingWeather);
        // 4. 응답 DTO로 변환해서 반환
        return modelMapper.map(updatedWeather, WeatherResponseDTO.class);
    }

    // 4. 날씨 정보 삭제
    public void deleteWeather(Long id) {
        // 1. 데이터 존재 여부 확인
        if (!weatherRepository.existsById(id)) {
            throw new WeatherNotFoundException("해당 날씨 찾을 수 없음");
        }
        // 2. 데이터 삭제
        weatherRepository.deleteById(id);
    }

}
