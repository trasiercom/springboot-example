package com.trasier.springboot.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookingController {

    private final RestTemplate restTemplate;

    @Autowired
    public BookingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> bookOffer(@RequestParam("offerId") String offerId) {
        String result = restTemplate.getForObject("http://localhost:7001/payment/" + offerId, String.class);
        if(result != null) {
            String status = result.equals("PAID") ? "BOOKED" : null;
            return new ResponseEntity<>(Booking.builder()
                    .offerId(Integer.parseInt(offerId))
                    .paymentStatus(result)
                    .status(status)
                    .build(), HttpStatus.OK);
        } else {
            throw new IllegalStateException("Offer has not been paid yet.");
        }
    }

}