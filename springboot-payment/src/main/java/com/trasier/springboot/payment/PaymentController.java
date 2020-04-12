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

    private static final Map<String, String> PAYMENT_STATUS = new HashMap<>();

    @PostMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> payment(@PathVariable("offerId") String offerId) {
        if (Integer.parseInt(offerId) < 80) {
            PAYMENT_STATUS.put(offerId, "PAID");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            PAYMENT_STATUS.put(offerId, "DECLINED");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkPayment(@PathVariable("offerId") String offerId) {
        String status = PAYMENT_STATUS.get(offerId);
        if (status != null) {
            return new ResponseEntity<String>(status, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}