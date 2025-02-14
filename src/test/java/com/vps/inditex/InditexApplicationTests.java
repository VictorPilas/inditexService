package com.vps.inditex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class InditexApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private static Stream<Arguments> testValidityParams() {
		return Stream.of(
				Arguments.of("35455","1","2020-06-14-10.00.00",status().isOk(),35.5),
				Arguments.of("35455","1","2020-06-14-16.00.00",status().isOk(),25.45),
				Arguments.of("35455","1","2020-06-14-21.00.00",status().isOk(),35.5),
				Arguments.of("35455","1","2020-06-15-10.00.00",status().isOk(),30.5),
				Arguments.of("35455","1","2020-06-16-21.00.00",status().isOk(),35.5)
		);
	}

	@ParameterizedTest
	@MethodSource(value = "testValidityParams")
	void testRestValues(String productId, String brandId, String applicationDate, ResultMatcher status, Double amount) throws Exception {
		mockMvc.perform(get("/prices")
						.param("productId", productId)
						.param("brandId", brandId)
						.param("date", applicationDate))
				.andExpect(status)
				.andExpect(jsonPath("$[0].price").value(amount));
	}
}

