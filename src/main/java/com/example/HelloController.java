package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

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

}
