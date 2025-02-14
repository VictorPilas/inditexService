package com.vps.inditex.domain;

import com.vps.inditex.domain.model.Brand;
import com.vps.inditex.domain.model.Price;
import com.vps.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void findPriceByFields_ShouldReturnResults() {
        Timestamp date = Timestamp.valueOf("2022-06-14 10:00:00");
        int productId = 35455;
        Brand brand = new Brand();
        brand.setId(1);

        Price price = new Price();
        price.setId(1);
        price.setProductId((long) productId);
        price.setBrand(brand);
        price.setStartDate(Timestamp.valueOf("2022-06-14 00:00:00"));
        price.setEndDate(Timestamp.valueOf("2022-06-15 23:59:59"));
        price.setPrice(35.50);
        price.setPriceList(1L);
        price.setPriority(1L);

        priceRepository.save(price);

        List<Price> result = priceRepository.findPriceByFields(date, productId, brand);

        assertFalse(result.isEmpty());
        assertEquals(productId, result.get(0).getProductId());
    }

    @Test
    void findPriceByFields_NoMatch_ShouldReturnEmptyList() {
        Timestamp date = Timestamp.valueOf("2023-01-01 00:00:00");
        int productId = 99999;
        Brand brand = new Brand();
        brand.setId(2);

        List<Price> result = priceRepository.findPriceByFields(date, productId, brand);

        assertTrue(result.isEmpty());
    }
}
