package com.trasier.springboot.pong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    private final RestTemplate restTemplate;

    public WeatherController() {
        restTemplate = new RestTemplate();
    }

    @RequestMapping("/weather")
    public String index() {
        String city = "BER";
        String url = "https://opendata.netcetera.com/smn/smn/"+city;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return forEntity.getBody();
    }

    @RequestMapping("/weather/cities/{cityCode}")
    public String index(@PathVariable("cityCode") String cityCode) {
        String url = "https://opendata.netcetera.com/smn/smn/"+cityCode;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return forEntity.getBody();
    }

}
