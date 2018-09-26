package com.trasier.springboot.pong.service;

import com.trasier.springboot.pong.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeather(String cityCode) {
        String url = "https://opendata.netcetera.com/smn/smn/"+cityCode;
        ResponseEntity<WeatherData> forEntity = restTemplate.getForEntity(url, WeatherData.class);

        WeatherData weatherData = forEntity.getBody();
        return weatherData;
    }
}
