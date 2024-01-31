package com.techelevator;

public class Candy extends Vendable{
    private String slogan = "Munch Munch, Yum!";

    public Candy(String slotID, String snackName, double price, String snackType) {
        super(slotID, snackName, price, snackType);
    }
    public Candy(){}

    @Override
    public String slogan() {
        return slogan;
    }
}
