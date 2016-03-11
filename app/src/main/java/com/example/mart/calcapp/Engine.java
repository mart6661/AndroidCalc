package com.example.mart.calcapp;

/**
 * Created by Mart on 3/11/2016.
 */
public class Engine {

    public String calcAns(double number1, double number2, String sign) {

        if (sign.contains("+")) {
            return Double.toString(number1 + number2);
        } else if (sign.contains("-")) {
            return Double.toString(number1 - number2);

        } else if (sign.contains("*")) {
            return Double.toString(number1 * number2);

        } else if (sign.contains("/")) {
            if (number2 == 0) {
                // Cannot Divide By Zero
                return "Cannot Divide By Zero!";
            } else {
                return Double.toString(number1 / number2);

            }

        }
        return null;
    }

}
