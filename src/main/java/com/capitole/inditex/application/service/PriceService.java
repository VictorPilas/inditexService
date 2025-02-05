package com.capitole.inditex.application.service;

import com.capitole.inditex.application.dto.PriceDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PriceService {
    List<PriceDTO> getPrices(String date, Integer productId,Integer brandId);

}
