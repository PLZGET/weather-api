package com.ac.inspienweatherapi.controller;

public class WeatherNotFoundException extends RuntimeException  {
    public WeatherNotFoundException(String message) {
        super(message);
    }
}
