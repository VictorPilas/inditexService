package com.vps.inditex.application.service;

import com.vps.inditex.application.dto.PriceDTO;

import java.util.List;

public interface PriceService {
    List<PriceDTO> getPrices(String date, Integer productId,Integer brandId);

}
