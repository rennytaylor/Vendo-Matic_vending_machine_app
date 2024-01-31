package com.techelevator;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Vendable {
    private String slotID;
    private String snackName;
    private double price;
    private String snackType;
    private int quantityRemaining = 5;
    boolean isSoldOut = false;
    private int maxQuantity = 5;

    public Vendable() {
    }

    public abstract String slogan();

    public Vendable(String slotID, String snackName, double price, String snackType) {
        this.slotID = slotID;
        this.snackName = snackName;
        this.price = price;
        this.snackType = snackType;
    }
    public String getSlotID() {
        return slotID;
    }

    public String getSnackName() {
        return snackName;
    }

    public double getPrice() {
        return price;
    }

    public String getSnackType() {
        return snackType;
    }

    public void setQuantityRemaining(int quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }

    public int getQuantitySold() {
        return maxQuantity - quantityRemaining;
    }

    public void removeItem() {             //method to change inventory amount
        if (getQuantityRemaining() > 0) {
            this.quantityRemaining--;
        } else if (getQuantityRemaining() == 0) {
            this.isSoldOut = true;
        }
    }
}


