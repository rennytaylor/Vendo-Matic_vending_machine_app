package com.techelevator;

public class Chips extends Vendable{
    private String slogan = "Crunch Crunch, Yum!";

    public Chips(String slotID, String snackName, double price, String snackType){
        super(slotID, snackName, price, snackType);
    }
    public Chips(){}

    @Override
    public String slogan() {
        return slogan;
    }
}
