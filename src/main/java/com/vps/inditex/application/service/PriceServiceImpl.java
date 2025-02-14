package com.vps.inditex.application.service;

import com.vps.inditex.application.dto.PriceDTO;
import com.vps.inditex.application.mapper.PriceMapper;
import com.vps.inditex.domain.model.Brand;
import com.vps.inditex.domain.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService{

    private final PriceRepository repository;
    private final PriceMapper mapper;

    @Override
    public List<PriceDTO> getPrices(String date, Integer productId,Integer brandId) {

        return repository.findPriceByFields(stringToTimestamp(date),productId, toBrand(brandId)).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    private static Timestamp stringToTimestamp(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        String pattern = "yyyy-MM-dd-HH.mm.ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return Timestamp.valueOf(LocalDateTime.from(formatter.parse(date)));
    }

    private static Brand toBrand(Integer brandId) {
        if (brandId == null ) {
            return null;
        }
        return Brand.builder().id(brandId).build();
    }


}

