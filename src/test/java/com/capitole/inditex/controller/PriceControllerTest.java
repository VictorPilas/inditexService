package com.capitole.inditex.controller;

import com.capitole.inditex.application.dto.PriceDTO;
import com.capitole.inditex.application.service.PriceService;
import com.capitole.inditex.domain.model.Brand;
import com.capitole.inditex.domain.model.Price;
import com.capitole.inditex.infrastructure.controller.PriceController;
import com.capitole.inditex.infrastructure.mapper.PriceControllerMapper;
import com.capitole.inditex.infrastructure.response.PriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @Mock
    private PriceControllerMapper priceControllerMapper;

    @InjectMocks
    private PriceController priceController;

    private PriceResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new PriceResponse();
        mockResponse.setProductId(1L);
        mockResponse.setBrandId(1);
        mockResponse.setPrice(100.0);
    }

    @Test
    void getPriceByFilter_ReturnsOk_WhenPricesExist() {

        String date = "2024-06-14T10:00:00";
        Integer productId= 1;
        Integer brandId = 1;

        PriceDTO price = new PriceDTO();
        price.setId(1);
        price.setProductId((long) productId);
        price.setBrandId(1);
        price.setStartDate("2022-06-14 00:00:00");
        price.setEndDate("2022-06-15 23:59:59");
        price.setPrice(100.00);
        price.setPriceList(1L);
        price.setPriority(1L);

        List<PriceDTO> priceDTOS = new ArrayList<>();
        priceDTOS.add(price);

        when(priceService.getPrices(date, productId, brandId)).thenReturn(priceDTOS);
        when(priceControllerMapper.toResponse(price)).thenReturn(mockResponse);

        // Act
        ResponseEntity<List<PriceResponse>> response = priceController.getPriceByFilter(date, productId, brandId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().isEmpty());
        verify(priceService, times(1)).getPrices(date, productId, brandId);
    }

    @Test
    void getPriceByFilter_ReturnsBadRequest_WhenNoPricesExist() {
        // Arrange
        String date = "2024-06-14T10:00:00";
        Integer productId = 1;
        Integer brandId = 1;

        when(priceService.getPrices(date, productId, brandId)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<PriceResponse>> response = priceController.getPriceByFilter(date, productId, brandId);

        // Assert
        assertEquals(400, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getBody()).isEmpty());
        verify(priceService, times(1)).getPrices(date, productId, brandId);
    }
}
