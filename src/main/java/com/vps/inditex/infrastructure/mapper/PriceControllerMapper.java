package com.vps.inditex.infrastructure.mapper;

import com.vps.inditex.domain.model.Price;
import com.vps.inditex.domain.dto.PriceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceControllerMapper {

    PriceDTO toResponse(Price dto);
}
