package com.techelevator;

public class Gum extends Vendable{
    private String slogan = "Chew Chew, Yum!";

    public Gum(String slotID, String snackName, double price, String snackType) {
        super(slotID, snackName, price, snackType);
    }
    public Gum(){}

    @Override
    public String slogan() {
        return slogan;
    }
}
