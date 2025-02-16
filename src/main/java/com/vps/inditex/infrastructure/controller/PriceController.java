package com.vps.inditex.infrastructure.controller;

import com.vps.inditex.application.service.PriceService;
import com.vps.inditex.infrastructure.exception.PriceNotFoundException;
import com.vps.inditex.infrastructure.mapper.PriceControllerMapper;
import com.vps.inditex.infrastructure.response.PriceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prices")
@AllArgsConstructor
public class PriceController {

    private final PriceService service;
    private final PriceControllerMapper mapper;

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getPriceByFilter(@RequestParam(required = false) String date,
                                                                @RequestParam(required = false) Integer productId,
                                                                @RequestParam(required = false) Integer brandId) {
        List<PriceResponse> response = service.getPrices(date, productId, brandId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        if (response.isEmpty()) {
            throw new PriceNotFoundException("No prices found for the given filters.");
        }

        return ResponseEntity.ok(response);
    }
}
