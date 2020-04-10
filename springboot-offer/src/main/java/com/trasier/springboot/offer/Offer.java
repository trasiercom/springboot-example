/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package com.trasier.springboot.offer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {
    private int id;
    private String name;
    private String price;
    private String status;
}
