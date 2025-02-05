package com.capitole.inditex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class InditexApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testPriceAt10AMOn14th() throws Exception {
		mockMvc.perform(get("/prices")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-14-10.00.00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].price").value(25.45));
	}

	@Test
	void testPriceAt16PMOn14th() throws Exception {
		mockMvc.perform(get("/prices")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-14-16.00.00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].price").value(25.45));
	}

	@Test
	void testPriceAt21PMOn14th() throws Exception {
		mockMvc.perform(get("/prices")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-14-21.00.00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].price").value(25.45));
	}

	@Test
	void testPriceAt10AMOn15th() throws Exception {
		mockMvc.perform(get("/prices")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-15-10.00.00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].price").value(25.45));
	}

	@Test
	void testPriceAt21PMOn16th() throws Exception {
		mockMvc.perform(get("/prices")
						.param("productId", "35455")
						.param("brandId", "1")
						.param("applicationDate", "2020-06-16-21.00.00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].price").value(25.45));
	}
}

