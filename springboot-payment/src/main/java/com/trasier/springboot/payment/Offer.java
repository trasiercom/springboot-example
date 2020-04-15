/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package com.trasier.springboot.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private int id;
    private String name;
    private String price;
    private String status;
}
