package com.vps.inditex.infrastructure.mapper;

import com.vps.inditex.application.dto.PriceDTO;
import com.vps.inditex.infrastructure.response.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceControllerMapper {

    PriceResponse toResponse(PriceDTO dto);
}
