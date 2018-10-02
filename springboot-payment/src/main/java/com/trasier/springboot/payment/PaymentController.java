package com.trasier.springboot.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {
    private final RestTemplate restTemplate;

    @Autowired
    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String index(@PathVariable("offerId") String offerId) {
        if ("42".equals(offerId)) {
            return "{ \"offerId\": " + offerId + ", \"status\": \"PAID\" }";
        } else {
            return "{ \"offerId\": " + offerId + ", \"status\": \"DECLINED\" }";
        }
    }

}