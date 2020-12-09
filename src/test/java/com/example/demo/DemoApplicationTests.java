package com.example.demo;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

	@Test
	public void testGetMathSum() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/sum?n=4&n=5&n=6");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("4 + 5 + 6 = 15"));
	}

	@Test
	public void testGetMathVolume() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/math/volume/3/4/5");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
	}

	@Test
	public void testPostMathVolume() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/math/volume/3/4/5");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
	}

	@Test
	public void testPatchMathVolume() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.patch("/math/volume/3/4/5");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
	}

	@Test
	public void testMathAreaCircle() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/math/area")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("type", "circle")
				.param("radius", "4");
		;
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
	}

	@Test
	public void testMathAreaRectangle() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/math/area")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("type", "rectangle")
				.param("width", "4")
				.param("height", "7");
		;
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("Area of a 4x7 rectangle is 28"));
	}

	/*
	{
  "departs": "2017-04-21 14:34",
  "tickets": [
    {
      "passenger": {
        "firstName": "Some name",
        "lastName": "Some other name"
      },
      "price": 200
    }
  ]
}
	 */
	/*
	@Test
	public void testFlight() throws Exception {
		this.mvc.perform(
				MockMvcRequestBuilders
				.get("/flights/flight")
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
				.andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")))
				.andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Some other name")))
				.andExpect(jsonPath("$.tickets[0].price", is(200)));
	}

	@Test
	public void testListOfFlights() throws Exception {
		this.mvc.perform(
				MockMvcRequestBuilders
						.get("/flights")
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
				.andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Some name")))
				.andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Some other name")))
				.andExpect(jsonPath("$[0].tickets[0].price", is(200)))
				.andExpect(jsonPath("$[1].departs", is("2017-04-21 14:34")))
				.andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Some other name")))
				//.andExpect(jsonPath("$[1].tickets[0].passenger.lastName", is(null)))
				.andExpect(jsonPath("$[1].tickets[0].price", is(400)));
	}
	*/
	@Test
	public void refactedTestListOfFlights() throws Exception {
		this.mvc.perform(
				MockMvcRequestBuilders
						.get("/flights")
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
				.andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
				.andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Some other name")))
				.andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
				.andExpect(jsonPath("$[1].Departs", is("2017-04-21 14:34")))
				.andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Some other name")))
				.andExpect(jsonPath("$[1].Tickets[0].Price", is(400)));
	}

}
