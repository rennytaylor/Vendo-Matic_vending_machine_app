package com.techelevator;

public class Drinks extends Vendable{
    private String slogan = "Glug Glug, Yum!";

    public Drinks(String slotID, String snackName, double price, String snackType) {
        super(slotID, snackName, price, snackType);
    }
    public Drinks(){}

    @Override
    public String slogan() {
        return slogan;
    }
}
