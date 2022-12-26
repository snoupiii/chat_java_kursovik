package com.example.demo1;

public class Number {

    private String Number;


    public void setNumber(String PNum) {
        Number = PNum;
        if (Number.length() == 11 && Number.charAt(0) == '8') {
            Number = Number.replaceFirst("8", "+7");
        }
        else if (Number.length() == 12 && Number.charAt(0) == '+' && Number.charAt(1) == '7') {
        }
        else {
            Number = "1";
        }
    }
    public String getNumber() {return Number;}

}