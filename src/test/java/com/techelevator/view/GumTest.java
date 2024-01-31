package com.techelevator.view;

import com.techelevator.Drinks;
import com.techelevator.Gum;
import org.junit.Assert;
import org.junit.Test;

public class GumTest {
    //check that slogan() method returns correct message
    @Test
    public void slogan_returns_correct_message(){
        String message = "Chew Chew, Yum!";
        Gum Gum = new Gum();
        Assert.assertEquals(message,Gum.slogan());
    }

}
