package com.trasier.springboot.pong.model;

import lombok.Data;

@Data
public class WeatherData {

    private String temperature;
    private String windSpeed;
    private String humidity;
    private String qfePressure;
    private Station station;

}
