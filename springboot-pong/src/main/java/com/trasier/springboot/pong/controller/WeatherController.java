package com.trasier.springboot.pong.controller;

import com.trasier.springboot.pong.model.WeatherData;
import com.trasier.springboot.pong.service.WeatherService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping("/weather")
    public String index() {
        WeatherData ber = weatherService.getWeather("BER");
        return ber.toString();
    }

    @RequestMapping("/weather/cities/{cityCode}")
    public String index(@PathVariable("cityCode") String cityCode) {
        WeatherData ber = weatherService.getWeather(cityCode);
        return ber.toString();
    }

}
