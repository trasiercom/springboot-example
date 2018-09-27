package com.trasier.springboot.ping.configuration;

import com.trasier.springboot.ping.WeatherWsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.trasier.wsdl.weather");
        return marshaller;
    }

    @Bean
    public WeatherWsClient weatherWsClient(Jaxb2Marshaller marshaller) {
        WeatherWsClient client = new WeatherWsClient();
        client.setDefaultUri("http://localhost:8002/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
