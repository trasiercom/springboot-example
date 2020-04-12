package com.trasier.springboot.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

    private int offerId;
    private String status;

}