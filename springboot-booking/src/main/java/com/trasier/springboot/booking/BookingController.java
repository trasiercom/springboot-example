package com.trasier.springboot.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Booking> bookOffer(@RequestParam("offerId") String offerId, @RequestParam("paymentId") String paymentId) {
        Offer offer = restTemplate.getForObject("http://localhost:7000/offer/" + offerId, Offer.class);
        if(offer != null) {
            boolean offerHasBeenPaid = checkIfOfferHasBeenPaid(paymentId);
            return new ResponseEntity<>(Booking.builder()
                    .id(generateRandomNumber())
                    .offerId(Integer.parseInt(offerId))
                    .status(offerHasBeenPaid ? "CONFIRMED" : "UNPAID")
                    .build(), HttpStatus.OK);
        } else {
            throw new IllegalStateException("Offer not found.");
        }
    }

    private boolean checkIfOfferHasBeenPaid(@RequestParam("paymentId") String paymentId) {
        try {
            Payment payment = restTemplate.getForObject("http://localhost:7001/payment/" + paymentId, Payment.class);
            return payment != null && "PAID".equals(payment.getStatus());
        } catch (Exception e) {
            return false;
        }
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100);
    }

}