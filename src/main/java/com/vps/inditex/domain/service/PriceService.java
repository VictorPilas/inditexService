package com.vps.inditex.domain.service;

import com.vps.inditex.domain.model.Price;

public interface PriceService {
    Price getPrice(String date, Integer productId, Integer brandId);

}
