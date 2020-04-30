package com.trasier.springboot.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    private final RestTemplate restTemplate;

    private static final Map<Integer, Payment> PAYMENTS = new HashMap<>();

    @Autowired
    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> payment(@PathVariable("offerId") Integer offerId) {
        Offer offer = restTemplate.getForObject("http://localhost:7000/offer/" + offerId, Offer.class);
        if(offer != null) {
            //TODO validate offer vs. payment

            Payment.PaymentBuilder builder = Payment.builder()
                    .id(generateRandomNumber())
                    .offerId(offerId);
            if (offerId < 80) {
                builder.status("PAID");
                Payment payment = builder.build();
                PAYMENTS.put(payment.getId(), payment);
                return new ResponseEntity<>(payment, HttpStatus.CREATED);
            } else {
                builder.status("DECLINED");
                Payment payment = builder.build();
                PAYMENTS.put(payment.getId(), payment);
                return new ResponseEntity<>(payment, HttpStatus.OK);
            }
        } else {
            throw new IllegalStateException("Offer not found.");
        }
    }

    @GetMapping(value = "/payment/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> checkPayment(@PathVariable("paymentId") Integer paymentId) {
        Payment payment = PAYMENTS.get(paymentId);
        if (payment != null) {
            if(payment.getId() < 20) {
                throw new RuntimeException("Exception we didn't expect.");
            }
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100);
    }

}