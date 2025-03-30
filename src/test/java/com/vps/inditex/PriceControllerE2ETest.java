package com.vps.inditex;

import static org.junit.jupiter.api.Assertions.*;

import com.vps.inditex.domain.dto.PriceDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = InditexApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @CsvSource({
        "2020-06-14-10.00.00, 35455, 1",
        "2020-06-14-16.00.00, 35455, 1",
        "2020-06-14-21.00.00, 35455, 1",
        "2020-06-15-10.00.00, 35455, 1",
        "2020-06-16-21.00.00, 35455, 1"
    })
    @Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGetPriceByFields_E2E(String requestDate, int productId, int brandId) {
        String url = "/prices?requestDate=" + requestDate + "&productId=" + productId + "&brandId=" + brandId;
        
        ResponseEntity<PriceDTO> response = restTemplate.getForEntity(url, PriceDTO.class);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productId, response.getBody().getProductId());
        assertEquals(brandId, response.getBody().getBrandId());
    }

    @Test
    void testGetPriceByFields_NotFound() {
        String url = "/prices?requestDate=2025-03-30-00.00.00&productId=99999&brandId=3";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
