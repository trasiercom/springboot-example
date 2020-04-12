/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package com.trasier.springboot.booking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {
    private int offerId;
    private String paymentStatus;
    private String status;
}
