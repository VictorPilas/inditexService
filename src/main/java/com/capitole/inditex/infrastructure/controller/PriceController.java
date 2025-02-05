package com.capitole.inditex.infrastructure.controller;

import com.capitole.inditex.application.service.PriceService;
import com.capitole.inditex.infrastructure.mapper.PriceControllerMapper;
import com.capitole.inditex.infrastructure.response.PriceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
@AllArgsConstructor
public class PriceController {

    private final PriceService service;
    private final PriceControllerMapper mapper;

    @GetMapping()
    public ResponseEntity<List<PriceResponse>> getPriceByFilter(@RequestParam(required = false) String date,
                                                @RequestParam(required = false) Integer productId,
                                                @RequestParam(required = false) Integer brandId) {

        final List<PriceResponse> response = service.getPrices(date,productId,brandId).stream()
                .map(mapper::toResponse).toList();

            if (!response.isEmpty()) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.badRequest().body(response);


    }
}
