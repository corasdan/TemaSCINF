package com.company.model;

public class Atlas extends Book {
    //Returns price in yen
    @Override
    public double getPrice() {
        return super.getPrice()*19.99;
    }
}
