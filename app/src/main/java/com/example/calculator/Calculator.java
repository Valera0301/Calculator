package com.example.calculator;

public class  Calculator {
    private static final int maxSizeOfNumber = 6;

    public static String setNumber(String currentNumber, String number){
        if (currentNumber.length() > maxSizeOfNumber)
            throw new IllegalArgumentException();

        if (currentNumber.equals("0"))
            currentNumber = number;
        else
            currentNumber += number;

        return currentNumber;
    }

    public static double calculate(double firstNumber, double secondNumber, String operation){
        switch (operation){
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber != 0)
                    return firstNumber / secondNumber;
                else
                    throw new ArithmeticException();
        }
        throw new IllegalArgumentException();
    }
    public static String changeSign(String currentNumber){
        if (currentNumber.equals("0")){
            throw new IllegalArgumentException();
        }
        if (currentNumber.startsWith("-")){
            return currentNumber.substring(1);
        }
        else{
            return "-"+currentNumber;
        }
    }
}
