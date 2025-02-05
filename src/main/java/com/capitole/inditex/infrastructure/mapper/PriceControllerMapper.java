package com.capitole.inditex.infrastructure.mapper;

import com.capitole.inditex.application.dto.PriceDTO;
import com.capitole.inditex.infrastructure.response.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceControllerMapper {

    PriceResponse toResponse(PriceDTO dto);
}
