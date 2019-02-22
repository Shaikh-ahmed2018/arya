package com.centris.campus.daoImpl;

public class NumberToWord {
    private static final String[] specialNames = {
        "",
        " thousand",
        " million",
        " billion",
        " trillion",
        " quadrillion",
        " quintillion"
    };
    
    private static final String[] tensNames = {
        "",
        " ten",
        " twenty",
        " thirty",
        " forty",
        " fifty",
        " sixty",
        " seventy",
        " eighty",
        " ninety"
    };
    
    private static final String[] numNames = {
        "",
        " one",
        " two",
        " three",
        " four",
        " five",
        " six",
        " seven",
        " eight",
        " nine",
        " ten",
        " eleven",
        " twelve",
        " thirteen",
        " fourteen",
        " fifteen",
        " sixteen",
        " seventeen",
        " eighteen",
        " nineteen"
    };
    
    private String convertLessThanOneThousand(int number) {
        String current;
        
        if (number % 100 < 20){
            current = numNames[number % 100];
            number /= 100;
        }
        else {
            current = numNames[number % 10];
            number /= 10;
            
            current = tensNames[number % 10] + current;
            number /= 10;
        }
        if (number == 0) return current;
        return numNames[number] + " hundred" + current;
    }
    
    public String convert(long l) {

        if (l == 0) { return "zero"; }
        
        String prefix = "";
        
        if (l < 0) {
            l = -l;
            prefix = "negative";
        }
        
        String current = "";
        int place = 0;
        
        do {
            int n = (int) (l % 1000);
            if (n != 0){
                String s = convertLessThanOneThousand(n);
                current = s + specialNames[place] + current;
            }
            place++;
            l /= 1000;
        } while (l > 0);
        
        return (prefix + current).trim();
    }


}
