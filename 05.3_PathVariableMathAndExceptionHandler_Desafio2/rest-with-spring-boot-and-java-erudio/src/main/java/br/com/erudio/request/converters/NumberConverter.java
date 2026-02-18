package br.com.erudio.request.converters;

import br.com.erudio.exception.UnsupportedMathOperationException;

public class NumberConverter {

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double convertToDouble(String strNumber) throws IllegalArgumentException {
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Por favor, insira um valor numerico!");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

}
