package com.trasier.springboot.payment;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @GetMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkPayment(@PathVariable("offerId") String offerId) {
        if ("42".equals(offerId)) {
            return "{ \"offerId\": " + offerId + ", \"status\": \"PAID\" }";
        } else {
            return "{ \"offerId\": " + offerId + ", \"status\": \"DECLINED\" }";
        }
    }
}