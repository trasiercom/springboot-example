package com.trasier.springboot.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookingController {
    private final RestTemplate restTemplate;

    @Autowired
    public BookingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public String bookOffer(@RequestParam("offerId") String offerId) {
        String result = restTemplate.getForObject("http://localhost:7001/payment/" + offerId, String.class);
        if (result.contains("PAID")) {
            return "{ \"offerId\": " + offerId + ", \"status\": \"BOOKED\" }";
        } else {
            return "{ \"offerId\": " + offerId + ", \"status\": \"FAILED\" }";
        }
    }

}