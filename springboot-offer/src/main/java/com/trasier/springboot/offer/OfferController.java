package com.trasier.springboot.offer;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OfferController {

    private static final Map<Integer, Offer> OFFERS = new HashMap<>();

    @GetMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Offer requestOffer(@RequestParam("product") String product) {
        Offer offer = Offer.builder()
                .id(generateRandomNumber())
                .name(product)
                .price("$ " + generateRandomNumber() + "." + generateRandomNumber())
                .status("OFFERED")
                .build();

        OFFERS.put(offer.getId(), offer);

        return offer;
    }

    @GetMapping(value = "/offer/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> loadOffer(@PathVariable("offerId") Integer offerId) {
        Offer offer = OFFERS.get(offerId);
        if (offer != null) {
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100);
    }

}