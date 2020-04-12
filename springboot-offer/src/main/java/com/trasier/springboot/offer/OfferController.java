package com.trasier.springboot.offer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    @GetMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Offer requestOffer(@RequestParam("product") String product) {
        return Offer.builder()
                .id(generateRandomNumber())
                .name(product)
                .price("$ " + generateRandomNumber() + "." + generateRandomNumber())
                .status("OFFERED")
                .build();
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100);
    }

}