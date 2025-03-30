package com.vps.inditex.infrastructure.controller;

import com.vps.inditex.domain.model.Price;
import com.vps.inditex.domain.service.PriceService;
import com.vps.inditex.infrastructure.exception.PriceNotFoundException;
import com.vps.inditex.infrastructure.mapper.PriceControllerMapper;
import com.vps.inditex.domain.dto.PriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceEntityControllerTest {

    @Mock
    private PriceService service;

    @Mock
    private PriceControllerMapper mapper;

    @InjectMocks
    private PriceController controller;

    private PriceDTO priceDTO;

    @BeforeEach
    void setUp() {
        priceDTO = new PriceDTO();
        priceDTO.setProductId(1L);
        priceDTO.setBrandId(1);
        priceDTO.setPrice(100.0);
    }

    @Test
    void getPriceByFilter_ReturnsOk_WhenPricesExist() {

        String date = "2024-06-14T10:00:00";
        Integer productId= 1;
        Integer brandId = 1;

        Price price = new Price();
        price.setId(1);
        price.setProductId((long) productId);
        price.setBrandId(1);
        price.setStartDate("2022-06-14 00:00:00");
        price.setEndDate("2022-06-15 23:59:59");
        price.setPrice(100.00);
        price.setPriceList(1L);
        price.setPriority(1L);


        when(service.getPrice(date, productId, brandId)).thenReturn(price);
        when(mapper.toResponse(price)).thenReturn(priceDTO);

        ResponseEntity<PriceDTO> response = controller.getPriceByFilter(date, productId, brandId);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        verify(service, times(1)).getPrice(date, productId, brandId);
    }

    @Test
    void getPriceByFilter_ShouldThrowException_WhenNoPricesFound() {
        when(service.getPrice(any(), any(), any())).thenReturn(null);

        assertThrows(PriceNotFoundException.class, () -> controller.getPriceByFilter("2024-01-01-00:00:00", 33333, 5));
    }
}
