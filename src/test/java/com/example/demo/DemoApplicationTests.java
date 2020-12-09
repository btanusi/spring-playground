package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(com.example.demo.HelloController.class)
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;
/*
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
		RequestBuilder request = post("/math/volume/3/4/5");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
	}

	@Test
	public void testPatchMathVolume() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.patch("/math/volume/3/4/5");
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
	}

	@Test
	public void testMathAreaCircle() throws Exception {
		RequestBuilder request = post("/math/area")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("type", "circle")
				.param("radius", "4");
		;
		this.mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
	}

	@Test
	public void testMathAreaRectangle() throws Exception {
		RequestBuilder request = post("/math/area")
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
*/
	//POST request to /flights/tickets/total
	@Test
	public void testTotalWithString() throws Exception {
		String json = "{\"tickets\":[{\"passenger\":{\"firstName\":\"Some name\",\"lastName\":\"Some other name\"},\"price\":200},{\"passenger\":{\"firstName\":\"Name B\",\"lastName\":\"Name C\"},\"price\":150}]}";

		MockHttpServletRequestBuilder request = post("/flights/tickets/total")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("{\"result\":350}"));
	}


	ObjectMapper objectMapper = new ObjectMapper();                    // 1

	@Test
	public void testTotalWithJackson() throws Exception {
		HashMap<String, Object> p1 = new HashMap<String, Object>(){  // 2
			{
				put("firstName", "Some name");
				put("lastName", "Some other name");
			}
		};
		HashMap<String, Object> p2 = new HashMap<String, Object>(){  // 2
			{
				put("firstName", "Name B");
				put("lastName", "Name C");
			}
		};
		HashMap<String, Object> t1 = new HashMap<String, Object>(){  // 2
			{
				put("passenger", p1);
				put("price", 200);
			}
		};
		HashMap<String, Object> t2 = new HashMap<String, Object>(){  // 2
			{
				put("passenger", p2);
				put("price", 150);
			}
		};
		HashMap<String, Object> tickets = new HashMap<String, Object>(){  // 2
			{
				put("tickets", Arrays.asList(t1, t2));
			}
		};

		String json = objectMapper.writeValueAsString(tickets);            // 3

		MockHttpServletRequestBuilder request = post("/flights/tickets/total")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);                                         // 4

		this.mvc.perform(request).andExpect(status().isOk());
	}


	@Test
	public void testTotalWithFileFixture() throws Exception {
		String json = getJSON("/ticketTotalData.json");

		MockHttpServletRequestBuilder request = post("/flights/tickets/total")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("{\"result\":350}"));
	}

	private String getJSON(String path) throws Exception {
		URL url = this.getClass().getResource(path);
		return new String(Files.readAllBytes(Paths.get(url.getFile())));
	}

}
