package com.sanedge.junitmockitojunit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.junitmockitojunit.dto.CalculatorDto;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @GetMapping("/add/{num3}")
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2,
            @PathVariable("num3") Double num3) {
        return num1 + num2;
    }

    @GetMapping("/sub/{num1}/{num2}")
    public Double substract(@PathVariable("num1") Double num1, @PathVariable("num2") Double num2) {
        Double result = null;

        if (num1 > num2) {
            result = num1 - num2;
        } else {
            result = num2 - num1;
        }

        return result;
    }

    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDto calculatorDto) {
        Double result = null;
        result = calculatorDto.getNum1() * calculatorDto.getNum2() * calculatorDto.getNum3() * calculatorDto.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.OK);
        return responseEntity;
    }
}
