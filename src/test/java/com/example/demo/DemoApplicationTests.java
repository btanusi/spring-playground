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
	public void testGetMathCalculateAdd() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("4 + 6 = 10"));
	}

	@Test
	public void testGetMathCalculateMultiply() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("4 * 6 = 24"));
	}

	@Test
	public void testGetMathCalculateSubtract() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=4&y=6");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("4 - 6 = -2"));
	}

	@Test
	public void testGetMathCalculateDivide() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=30&y=5");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("30 / 5 = 6"));
	}

	@Test
	public void testGetMathCalculateGivenXandY() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?x=30&y=5");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("30 + 5 = 35"));
	}

}
