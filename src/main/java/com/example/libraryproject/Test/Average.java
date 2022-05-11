package com.example.libraryproject.Test;

public class Average implements Testning{

    public int[] listArray;

    @Override
    public double getAverage() {
        int length = listArray.length;
        int sum = 0;


        for (int i = 0; i< listArray.length; i++){
            sum += listArray[i];
        }
        if (listArray.length == 0) {
            throw new ArithmeticException("Listan är tom");
        }

        double average = sum/length;

        return average;
    }
}
