package com.vps.inditex.infrastructure.mapper;

import com.vps.inditex.domain.dto.PriceDTO;
import com.vps.inditex.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PriceControllerMapperTest {

    private final PriceControllerMapper priceControllerMapper = Mappers.getMapper(PriceControllerMapper.class);

    @Test
    void testPriceControllerMapper_toResponse() {
        Price price = new Price();
        PriceDTO priceDTO = priceControllerMapper.toResponse(price);
        assertNotNull(priceDTO);
    }
}
