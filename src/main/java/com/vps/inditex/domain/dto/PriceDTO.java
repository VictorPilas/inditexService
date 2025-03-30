package com.vps.inditex.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceDTO {

    private Long productId;
    private Integer brandId;
    private Long priceList;
    private String startDate;
    private String endDate;
    private Double price;
}
