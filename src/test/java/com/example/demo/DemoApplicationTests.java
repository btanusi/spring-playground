package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(com.example.demo.HelloController.class)
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testIndexEndpoint() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/hello");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("Hello world"));
	}

	@Test
	public void testGetPi() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("3.141592653589793"));
	}

	@Test
	public void testGetMathCalculate() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("4 + 6 = 10"));
	}

}
