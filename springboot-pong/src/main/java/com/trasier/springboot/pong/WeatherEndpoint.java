package com.trasier.springboot.pong;

import com.trasier.example.weather_web_service.GetWeatherRequest;
import com.trasier.example.weather_web_service.GetWeatherResponse;
import com.trasier.example.weather_web_service.WeatherInfo;
import com.trasier.springboot.pong.model.WeatherData;
import com.trasier.springboot.pong.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WeatherEndpoint {

    private static final String NAMESPACE_URI = "http://trasier.com/example/weather-web-service";

    private final WeatherService weatherService;

    @Autowired
    public WeatherEndpoint(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getWeatherRequest")
    @ResponsePayload
    public GetWeatherResponse getWeather(@RequestPayload GetWeatherRequest request) {
        WeatherData weatherData = weatherService.getWeather(request.getCityCode());

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCity(weatherData.getStation().getName());
        weatherInfo.setCityCode(weatherData.getStation().getCode());
        weatherInfo.setTemperature(weatherData.getTemperature());
        weatherInfo.setHumidity(weatherData.getHumidity());
        weatherInfo.setPressure(weatherData.getQfePressure());
        weatherInfo.setWindSpeed(weatherData.getWindSpeed());

        GetWeatherResponse response = new GetWeatherResponse();
        response.setWeatherInfo(weatherInfo);
        return response;
    }


}
