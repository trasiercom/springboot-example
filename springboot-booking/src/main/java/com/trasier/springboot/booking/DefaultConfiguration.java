package com.trasier.springboot.booking;

import io.jaegertracing.samplers.ProbabilisticSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DefaultConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public io.opentracing.Tracer jaegerTracer() {
        return new io.jaegertracing.Configuration("booking", new io.jaegertracing.Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
                new io.jaegertracing.Configuration.ReporterConfiguration())
                .getTracer();
    }
}