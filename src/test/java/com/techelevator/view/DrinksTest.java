package com.techelevator.view;

import com.techelevator.Chips;
import com.techelevator.Drinks;
import org.junit.Assert;
import org.junit.Test;

public class DrinksTest {
    //check that slogan() method returns correct message
    @Test
    public void slogan_returns_correct_message(){
        String message = "Glug Glug, Yum!";
        Drinks Drinks = new Drinks();
        Assert.assertEquals(message,Drinks.slogan());
    }

}
