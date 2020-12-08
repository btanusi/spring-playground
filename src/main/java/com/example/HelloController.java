package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

}
