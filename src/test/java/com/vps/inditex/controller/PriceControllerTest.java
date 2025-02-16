package com.vps.inditex.controller;

import com.vps.inditex.application.dto.PriceDTO;
import com.vps.inditex.application.service.PriceService;
import com.vps.inditex.infrastructure.controller.PriceController;
import com.vps.inditex.infrastructure.exception.PriceNotFoundException;
import com.vps.inditex.infrastructure.mapper.PriceControllerMapper;
import com.vps.inditex.infrastructure.response.PriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService service;

    @Mock
    private PriceControllerMapper mapper;

    @InjectMocks
    private PriceController controller;

    private PriceResponse priceResponse;

    @BeforeEach
    void setUp() {
        priceResponse = new PriceResponse();
        priceResponse.setProductId(1L);
        priceResponse.setBrandId(1);
        priceResponse.setPrice(100.0);
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

        when(service.getPrices(date, productId, brandId)).thenReturn(priceDTOS);
        when(mapper.toResponse(price)).thenReturn(priceResponse);

        ResponseEntity<List<PriceResponse>> response = controller.getPriceByFilter(date, productId, brandId);

        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().isEmpty());
        verify(service, times(1)).getPrices(date, productId, brandId);
    }

    @Test
    void getPriceByFilter_ShouldThrowException_WhenNoPricesFound() {
        when(service.getPrices(any(), any(), any())).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> controller.getPriceByFilter("2024-01-01-00:00:00", 33333, 5));
    }
}
