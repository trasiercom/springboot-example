package com.trasier.springboot.offer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {
    @GetMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    public String requestOffer(@RequestParam("product") String product) {
        return "{ \"id\": 42, \"name\": \"" + product + "\", \"price\": \"$99.00\", \"status\": \"OFFERED\" }";
    }
}