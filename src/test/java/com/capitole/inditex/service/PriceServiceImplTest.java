package com.capitole.inditex.service;

import com.capitole.inditex.application.dto.PriceDTO;
import com.capitole.inditex.application.mapper.PriceMapper;
import com.capitole.inditex.application.service.PriceServiceImpl;
import com.capitole.inditex.domain.model.Brand;
import com.capitole.inditex.domain.model.Price;
import com.capitole.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

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
    void getPrices_ShouldReturnPriceDTOList() {
        String dateStr = "2022-06-14-10.00.00";
        Timestamp date = Timestamp.valueOf("2022-06-14 10:00:00");
        int productId = 35455;
        int brandId = 1;
        Brand brand = Brand.builder().id(brandId).build();

        Price price = new Price();
        PriceDTO priceDTO = new PriceDTO();

        when(priceRepository.findPriceByFields(date, productId, brand))
                .thenReturn(List.of(price));
        when(priceMapper.toDTO(price)).thenReturn(priceDTO);

        List<PriceDTO> result = priceService.getPrices(dateStr, productId, brandId);

        assertFalse(result.isEmpty());
        verify(priceRepository, times(1)).findPriceByFields(date, productId, brand);
        verify(priceMapper, times(1)).toDTO(price);
    }

    @Test
    void getPrices_WithNullDate_ShouldHandleCorrectly() {
        int productId = 35455;
        int brandId = 1;
        Brand brand = Brand.builder().id(brandId).build();

        when(priceRepository.findPriceByFields(null, productId, brand))
                .thenReturn(Collections.emptyList());

        List<PriceDTO> result = priceService.getPrices(null, productId, brandId);

        assertTrue(result.isEmpty());
        verify(priceRepository, times(1)).findPriceByFields(null, productId, brand);
    }
}
