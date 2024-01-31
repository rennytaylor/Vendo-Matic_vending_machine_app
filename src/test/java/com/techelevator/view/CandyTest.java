package com.techelevator.view;

import com.techelevator.Candy;
import com.techelevator.Chips;
import org.junit.Assert;
import org.junit.Test;

public class CandyTest {
    //check that slogan() method returns correct message
    @Test
    public void slogan_returns_correct_message(){
        String message = "Munch Munch, Yum!";
        Candy candy = new Candy();
        Assert.assertEquals(message,candy.slogan());
    }

}
