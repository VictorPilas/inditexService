package com.vps.inditex.application.service;

import com.vps.inditex.domain.model.Price;
import com.vps.inditex.infrastructure.mapper.PriceMapper;
import com.vps.inditex.domain.repository.PriceRepository;
import com.vps.inditex.domain.service.PriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository repository;
    private final PriceMapper mapper;

    @Override
    public Price getPrice(String date, Integer productId, Integer brandId) {
        return mapper.toDTO(repository.findPriceByFields(stringToTimestamp(date),productId, brandId));
    }

    private static Timestamp stringToTimestamp(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        String pattern = "yyyy-MM-dd-HH.mm.ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return Timestamp.valueOf(LocalDateTime.from(formatter.parse(date)));
    }


}

