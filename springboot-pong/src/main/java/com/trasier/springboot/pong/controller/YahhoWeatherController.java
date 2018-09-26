package com.trasier.springboot.pong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class YahhoWeatherController {

    private final RestTemplate restTemplate;

    public YahhoWeatherController() {
        restTemplate = new RestTemplate();
    }

    @RequestMapping("/weather")
    public String index() {
        String city = "Bern";
        String country = "Switzerland";
        String format = "json";

        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%2C%20"+country+"%22)&format="+format+"&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        System.out.println(url);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return forEntity.toString();
    }

}
