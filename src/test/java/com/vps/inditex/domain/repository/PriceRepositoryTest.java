package com.vps.inditex.domain.repository;

import com.vps.inditex.domain.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void findPriceByFields_ShouldReturnResult() {
        Timestamp date = Timestamp.valueOf("2022-06-14 10:00:00");
        int productId = 35455;
        int brandId = 1;

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(1);
        priceEntity.setProductId((long) productId);
        priceEntity.setBrandId(1);
        priceEntity.setStartDate(Timestamp.valueOf("2022-06-14 00:00:00"));
        priceEntity.setEndDate(Timestamp.valueOf("2022-06-15 23:59:59"));
        priceEntity.setPrice(35.50);
        priceEntity.setPriceList(1L);
        priceEntity.setPriority(1L);

        priceRepository.save(priceEntity);

        PriceEntity result = priceRepository.findPriceByFields(date, productId, brandId);

        assertNotNull(result);
        assertEquals(productId, result.getProductId());
    }

    @Test
    void findPriceByFields_NoMatch_ShouldReturnNull() {
        Timestamp date = Timestamp.valueOf("2023-01-01 00:00:00");
        int productId = 99999;
        int brandId = 1;

        PriceEntity result = priceRepository.findPriceByFields(date, productId, brandId);

        assertNull(result);
    }
}
