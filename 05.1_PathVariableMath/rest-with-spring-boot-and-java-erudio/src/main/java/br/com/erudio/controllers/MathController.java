package br.com.erudio.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math") //http://localhost:8080/math
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}") //http://localhost:8080/math/sum/x/y
    public Double sum(@PathVariable("numberOne") Double numberOne, @PathVariable("numberTwo")Double numberTwo){
        return numberOne+numberTwo;
    }

}
