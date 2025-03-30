package com.vps.inditex.application.service;

import com.vps.inditex.domain.model.Price;
import com.vps.inditex.infrastructure.mapper.PriceMapper;
import com.vps.inditex.application.service.PriceServiceImpl;
import com.vps.inditex.domain.entity.PriceEntity;
import com.vps.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceEntityServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        priceService = new PriceServiceImpl(priceRepository, priceMapper);
    }

    @Test
    void getPrices_ShouldReturnPrice() {
        String dateStr = "2022-06-14-10.00.00";
        Timestamp date = Timestamp.valueOf("2022-06-14 10:00:00");
        int productId = 35455;
        int brandId = 1;

        PriceEntity priceEntity = new PriceEntity();
        Price price = new Price();

        when(priceRepository.findPriceByFields(date, productId, brandId))
                .thenReturn(priceEntity);
        when(priceMapper.toDTO(priceEntity)).thenReturn(price);

        Price result = priceService.getPrice(dateStr, productId, brandId);

        assertNotNull(result);
        verify(priceRepository, times(1)).findPriceByFields(date, productId, brandId);
        verify(priceMapper, times(1)).toDTO(priceEntity);
    }

    @Test
    void getPrices_WithNullDate_ShouldHandleCorrectly() {
        int productId = 35455;
        int brandId = 1;

        when(priceRepository.findPriceByFields(null, productId, brandId)).thenReturn(null);

        Price result = priceService.getPrice(null, productId, brandId);

        assertNull(result);
        verify(priceRepository, times(1)).findPriceByFields(null, productId, brandId);
    }
}
