package com.trasier.springboot.ping.controller;

import com.trasier.springboot.ping.WeatherWsClient;
import com.trasier.wsdl.weather.GetWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PingWeatherController {

    @Autowired
    private WeatherWsClient weatherWsClient;

    private final RestTemplate restTemplate;

    public PingWeatherController() {
        restTemplate = new RestTemplate();
    }

    @RequestMapping("/weather")
    public String index() {
        String url = "http://localhost:8002/weather";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return forEntity.getBody();
    }

    @RequestMapping("/weather/cities/{cityCode}")
    public String index(@PathVariable("cityCode") String cityCode) {
        String url = "http://localhost:8002/weather/cities/"+cityCode;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return forEntity.getBody();
    }

    @RequestMapping("/soap/weather")
    public String soapWeather() {
        GetWeatherResponse response = weatherWsClient.getWeather("BER");
        return "Temperature in " +response.getWeatherInfo().getCity() +": " + response.getWeatherInfo().getTemperature();
    }

}
