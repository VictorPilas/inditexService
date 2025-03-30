package com.vps.inditex.infrastructure.mapper;

import com.vps.inditex.domain.entity.PriceEntity;
import com.vps.inditex.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {
    private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

    @Test
    void testPriceMapper_toDTO() {
        PriceEntity entity = new PriceEntity();
        entity.setStartDate(Timestamp.valueOf("2024-03-30 10:00:00"));
        entity.setEndDate(Timestamp.valueOf("2024-03-30 18:00:00"));

        Price price = priceMapper.toDTO(entity);
        assertNotNull(price);
        assertEquals("2024-03-30-10.00.00", price.getStartDate());
        assertEquals("2024-03-30-18.00.00", price.getEndDate());
    }

    @Test
    void testPriceMapper_toPriceDTO() {
        PriceEntity entity = new PriceEntity();
        entity.setId(1);

        Integer priceId = priceMapper.toPriceDTO(entity);
        assertEquals(1, priceId);
    }

    @Test
    void testPriceMapper_toPriceEntity() {
        PriceEntity entity = priceMapper.toPriceEntity(1);
        assertNotNull(entity);
        assertEquals(1, entity.getId());
    }
}
