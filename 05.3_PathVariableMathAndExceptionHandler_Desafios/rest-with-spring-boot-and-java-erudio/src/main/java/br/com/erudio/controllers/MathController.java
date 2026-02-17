package br.com.erudio.controllers;

import br.com.erudio.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math") //http://localhost:8080/math
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}") //http://localhost:8080/math/sum/x/y
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)  throws IllegalArgumentException{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Por favor, insira um valor numerico");
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}") //http://localhost:8080/math/sub/x/y
    public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)  throws IllegalArgumentException{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Por favor, insira um valor numerico");
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}") //http://localhost:8080/math/multiplication/x/y
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)  throws IllegalArgumentException{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Por favor, insira um valor numerico");
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping("/division/{numberOne}/{numberTwo}") //http://localhost:8080/math/division/x/y
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)  throws IllegalArgumentException{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Por favor, insira um valor numerico");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    private boolean isNumeric(String strNumber){
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //if (number.matches("[-+]?[0-9]*\\.?[0-9]+")) return true;
    }

    private Double convertToDouble(String strNumber) throws IllegalArgumentException{
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Por favor, insira um valor numerico");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }
}
