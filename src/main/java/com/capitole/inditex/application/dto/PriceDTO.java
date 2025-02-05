package com.capitole.inditex.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

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
