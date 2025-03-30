package com.vps.inditex.infrastructure.controller;

import com.vps.inditex.domain.service.PriceService;
import com.vps.inditex.infrastructure.exception.PriceNotFoundException;
import com.vps.inditex.infrastructure.mapper.PriceControllerMapper;
import com.vps.inditex.domain.dto.PriceDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prices")
@AllArgsConstructor
public class PriceController {

    private final PriceService service;
    private final PriceControllerMapper mapper;

    @GetMapping
    public ResponseEntity<PriceDTO> getPriceByFilter(@RequestParam(required = false) String date,
                                                     @RequestParam(required = false) Integer productId,
                                                     @RequestParam(required = false) Integer brandId) {
        PriceDTO response = mapper.toResponse(service.getPrice(date, productId, brandId));


        if (response == null) {
            throw new PriceNotFoundException("No price found for the given filters.");
        }

        return ResponseEntity.ok(response);
    }
}
