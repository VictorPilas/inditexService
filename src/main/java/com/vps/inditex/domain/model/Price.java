package com.vps.inditex.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    private Integer id;
    private Integer brandId;
    private String startDate;
    private String endDate;
    private Long priceList;
    private Long productId;
    private Long priority;
    private Double price;
    private String curr;
}
