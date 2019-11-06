package com.trasier.springboot.payment;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    @GetMapping(value = "/payment/{offerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkPayment(@PathVariable("offerId") String offerId) throws InterruptedException {
        if ("42".equals(offerId)) {
            return "{ \"offerId\": " + offerId + ", \"status\": \"PAID\" }";
        } else {
            TimeUnit.SECONDS.sleep(5);
            return "{ \"offerId\": " + offerId + ", \"status\": \"DECLINED\" }";
        }
    }
}