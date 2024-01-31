package com.techelevator.view;

import com.techelevator.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

public class PurchaseTest {
    //(test Feed Money)
        //"feed money" adds money
        //current money provided is correct amount

    //(test Select Product)
        //item dispensed when valid product selected
        //if sold out message displayed, return to purchase menu
        //if product code invalid, message displayed, return to purchase menu
        //correct message is displayed when item dispensed
        //balance updated after vending

    //(test Finish Transaction):
        //change return is smallest amount of coins possible
        //current balance is set to 0


    Purchase purchase = new Purchase();
    VendingMachineCLI vend = new VendingMachineCLI();

    private static final DecimalFormat moneyDecimalFormat = new DecimalFormat("0.00");


    @Test
    public void current_money_provided_matches_feed_money_value(){ //tests feedMoney()
        purchase.feedMoney(5);

        Assert.assertEquals(5, purchase.getCurrentMoneyProvided(), 0.00);
    }

    @Test
    public void dispensing_item_displays_correct_info() {
        vend.getCurrentMoney().feedMoney(5.0);
        String productTestCode = "A1";
        Assert.assertEquals("Potato Crisps 3.05\nYour remaining balance is: $1.95\nCrunch Crunch, Yum!",
                            vend.selectProduct(productTestCode));
    }

    @Test
    public void sold_out_item_displays_correct_info() {
        vend.getCurrentMoney().feedMoney(10.00);
        vend.getCurrentMoney().feedMoney(10.00);
        String productTestCode = "A1";
        vend.selectProduct(productTestCode);
        vend.selectProduct(productTestCode);
        vend.selectProduct(productTestCode);
        vend.selectProduct(productTestCode);
        vend.selectProduct(productTestCode);

        Assert.assertEquals("SOLD OUT", vend.selectProduct(productTestCode));
    }
    @Test
    public void insufficient_funds_displays_correct_info() {
        String productTestCode = "A1";
        Assert.assertEquals("Insufficient Funds, please feed more money.", vend.selectProduct(productTestCode));
    }

    @Test
    public void balance_updated_after_vending(){
        vend.getCurrentMoney().feedMoney(5.00);
        String productTestCode = "A1";
        vend.selectProduct(productTestCode);
        Assert.assertEquals("1.95", moneyDecimalFormat.format(vend.getCurrentMoney().getCurrentMoneyProvided()));
    }

    @Test
    public void balance_returns_to_zero_after_finish_transaction(){
        purchase.feedMoney(5);

        purchase.makeChange(purchase.getCurrentMoneyProvided());

        Assert.assertEquals(0.00, purchase.getCurrentMoneyProvided(), 0.00);

    }

    @Test
    public void change_returned_uses_smallest_amount_of_coins() { //tests makeChange

        purchase.makeChange(2.10);

        Assert.assertEquals("Your change of $2.10 been dispensed as 8 quarters, 1 dimes, 0 nickels, and 0 pennies.",
                            purchase.getChangeReturned());
    }
}
