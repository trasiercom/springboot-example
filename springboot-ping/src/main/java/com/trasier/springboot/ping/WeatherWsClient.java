package com.trasier.springboot.ping;

import com.trasier.wsdl.weather.GetWeatherRequest;
import com.trasier.wsdl.weather.GetWeatherResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class WeatherWsClient extends WebServiceGatewaySupport {

    public GetWeatherResponse getWeather(String cityCode) {
        GetWeatherRequest request = new GetWeatherRequest();
        request.setCityCode(cityCode);
        return (GetWeatherResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8002/ws/weather", request,
                        new SoapActionCallback("http://trasier.com/example/weather-web-service/GetWeatherRequest"));
    }
}
