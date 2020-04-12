package com.trasier.springboot.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    private static final Map<Integer, Payment> PAYMENTS = new HashMap<>();

    @PostMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> payment(@PathVariable("offerId") Integer offerId) {
        Payment.PaymentBuilder builder = Payment.builder().offerId(offerId);
        if (offerId < 80) {
            builder.status("PAID");
            Payment payment = builder.build();
            PAYMENTS.put(offerId, payment);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } else {
            builder.status("DECLINED");
            Payment payment = builder.build();
            PAYMENTS.put(offerId, payment);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> checkPayment(@PathVariable("offerId") Integer offerId) {
        Payment payment = PAYMENTS.get(offerId);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}