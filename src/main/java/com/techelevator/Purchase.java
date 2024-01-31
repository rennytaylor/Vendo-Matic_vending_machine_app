package com.techelevator;

import com.techelevator.util.Log;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Purchase {
    private double currentMoneyProvided = 0.00;
    private final double ONE_DOLLAR_BILL = 1.00;
    private final double FIVE_DOLLAR_BILL = 5.00;
    private final double TEN_DOLLAR_BILL = 10.00;
    private static final DecimalFormat moneyDecimalFormat = new DecimalFormat("0.00");
    public final double QUARTER = 0.25;
    public final double DIME = 0.10;
    public final double NICKEL = 0.05;
    public final double PENNY = 0.01;
    public String getChangeReturned() {
        return changeReturned;
    }

    private String changeReturned;

    public Purchase(){}

    public void feedMoney(double moneyProvided) {
        double sum = 0.00;
        if(moneyProvided == ONE_DOLLAR_BILL){
            sum++;
        } else if (moneyProvided == FIVE_DOLLAR_BILL){
            sum += FIVE_DOLLAR_BILL;
        } else if (moneyProvided == TEN_DOLLAR_BILL) {
            sum += TEN_DOLLAR_BILL;
        }
        currentMoneyProvided += sum;
        Log.log(" FEED MONEY: " + "$" + moneyDecimalFormat.format(moneyProvided) + " "
                + "$" + moneyDecimalFormat.format(currentMoneyProvided));
    }

    public void makeChange(double currentMoneyProvided) {
        //return change in COINS with ***the least amount*** of coins possible...
        double sum = 0.00;
        double dimes = 0.0;
        double nickels = 0.0;
        double pennies = 0.0;
        double quarters = Math.floor(currentMoneyProvided / QUARTER);
        double quartersTotal = QUARTER * quarters;
        sum = currentMoneyProvided - quartersTotal;

        if (sum >= 0.10){
            dimes = Math.floor(sum / DIME);
            double dimesTotal = DIME * dimes;
            sum -= dimesTotal;
        }

        if (sum >= 0.05){
            nickels = Math.floor(sum / NICKEL);
            double nickelsTotal = NICKEL * nickels;
            sum -= nickelsTotal;
        }

        if (sum >= 0.01){
            pennies = Math.floor(sum / PENNY);
            double penniesTotal = PENNY * pennies;
            sum -= penniesTotal;
        }

        this.currentMoneyProvided = 0.0;

        this.changeReturned = "Your change of $" + moneyDecimalFormat.format(currentMoneyProvided) + " been dispensed as "
                                + (int)quarters + " quarters, " + (int)dimes + " dimes, " + (int)nickels + " nickels, and "
                                + (int)pennies + " pennies.";

        System.out.println("Your change of $" + moneyDecimalFormat.format(currentMoneyProvided) + " been dispensed as "
                            + (int)quarters + " quarters, " + (int)dimes + " dimes, " + (int)nickels + " nickels, and "
                            + (int)pennies + " pennies.");

        System.out.println("Current Money Provided: $" + moneyDecimalFormat.format(this.currentMoneyProvided) + "\n");
        //log updates
        Log.log(" GIVE CHANGE: $" + currentMoneyProvided + " $" + moneyDecimalFormat.format(this.currentMoneyProvided));
    }

    public void subtractPrice(double snackPrice){
        currentMoneyProvided -= snackPrice;
    }

    public double getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public String getCurrentMoneyDisplay() {
        return moneyDecimalFormat.format(currentMoneyProvided);
    }


}
