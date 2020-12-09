package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello world";
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/tasks")
    public String getTasks() {
        return "These are tasks";
    }

    @PostMapping("/tasks")
    public String createTask() {
        return "You just POSTed to /tasks";
    }

    @GetMapping("/math/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String getMathCalculate(@RequestParam(defaultValue = "add") String operation, @RequestParam Integer x, @RequestParam Integer y) {
        Integer result = 0;
        if(operation.equals("add")){
            operation = "+";
            result = x + y;
        } else if (operation.equals("subtract")){
            operation = "-";
            result = x - y;
        } else if (operation.equals("multiply")){
            operation = "*";
            result = x * y;
        } else if (operation.equals("divide")){
            operation = "/";
            result = x / y;
        }
        return x.toString() + " " + operation + " " + y.toString() + " = " + result.toString();
    }

    @GetMapping("/math/sum")
    public String getMathSum(@RequestParam MultiValueMap<String, String> params) {
        Integer sum = 0;
        String retStr = "";
        Collection<List<String>> values = params.values();
        for(List<String> n_value : values){
            for (int i = 0; i < n_value.size(); i++){
                String v = n_value.get(i);
                sum += Integer.parseInt(v);
                retStr += v;
                if (i+1 == n_value.size()){
                    retStr += " = ";
                } else {
                    retStr += " + ";
                }
            }
        }
        return retStr + sum.toString();
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String getMathVolume(@PathVariable Integer length, @PathVariable Integer width, @PathVariable Integer height) {
        Integer volume = length * width * height;
        return "The volume of a " + length.toString() + "x" + width.toString() + "x" + height.toString() + " rectangle is " + volume.toString();
    }

    @PostMapping("/math/area")
    public String postMathArea(@RequestParam Map<String, String> body) {
        String retType = "Invalid";
        String type = body.get("type");
        if(type.equals("circle")){
            String radius = body.get("radius");
            if(radius == null){
                return retType;
            } else {
                Integer r = Integer.parseInt(radius);
                Double area = (Double.parseDouble(getPi()) * r * r);
                area = Math.round(area * 100000.0) / 100000.0;
                return "Area of a circle with a radius of " + radius + " is " + area.toString();
            }
        } else if (type.equals("rectangle")){
            String width = body.get("width");
            String height = body.get("height");
            if(width == null || height == null){
                return retType;
            } else {
                Integer w = Integer.parseInt(width);
                Integer h = Integer.parseInt(height);
                Integer area = w * h;
                return "Area of a " + w.toString() + "x" + h.toString() + " rectangle is " + area.toString();
            }

        } else {
            return retType;
        }
    }

    Passenger passenger1;
    Ticket ticket1;
    Flight flight1;
    private void setUpFlight1(){
        passenger1 = new Passenger();
        passenger1.setFirstName("Some name");
        passenger1.setLastName("Some other name");
        ticket1 = new Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(200);
        flight1 = new Flight();
        flight1.setDeparts(new Date(2017-1900, 3, 21, 14-7, 34));
        Ticket[] tickets = new Ticket[]{ticket1};
        flight1.setTickets(tickets);
    }

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        this.setUpFlight1();
        return flight1;
    }

    Passenger passenger2;
    Ticket ticket2;
    Flight flight2;
    @GetMapping("/flights")
    public List<Flight> getFlights(){
        this.setUpFlight1();
        passenger2 = new Passenger();
        passenger2.setFirstName("Some other name");
        ticket2 = new Ticket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(400);
        flight2 = new Flight();
        flight2.setDeparts(new Date(2017-1900, 3, 21, 14-7, 34));
        Ticket[] tickets = new Ticket[]{ticket2};
        flight2.setTickets(tickets);
        return Arrays.asList(flight1, flight2);
    }

    public static class Flight {
        private Date departs;
        private Ticket[] tickets;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonProperty("Departs")
        public Date getDeparts(){ return departs; }
        public void setDeparts(Date departs){ this.departs = departs; }
        //@JsonProperty("Tickets")
        public Ticket[] getTickets() { return tickets; }
        public void setTickets(Ticket[] tickets) { this.tickets = tickets; }
    }

    public static class Ticket {
        private Passenger passenger;
        private Integer price;
        //@JsonProperty("Passenger")
        public Passenger getPassenger(){ return passenger; }
        public void setPassenger(Passenger passenger) { this.passenger = passenger; }
        //@JsonProperty("Price")
        public Integer getPrice(){ return price; }
        public void setPrice(Integer price){ this.price = price; }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Passenger {
        private String firstName;
        private String lastName;
        //@JsonProperty("FirstName")
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        //@JsonProperty("LastName")
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }

    @PostMapping("/flights/tickets/total")
    public Map<String, Integer> ticketTotal(@RequestBody Flight flight){
        Ticket[] tickets = flight.getTickets();
        Integer acc = 0;
        for(Ticket t : tickets){
            acc += t.getPrice();
        }
        Map<String, Integer> json = new HashMap<String, Integer>();
        json.put("result", acc);
        return json;
    }

}
