package com.techelevator.view;

import com.techelevator.Candy;
import com.techelevator.Chips;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChipsTest {
    /*these all test methods in vendable abstract class. I don't think there's any need to repeat them in tests for
    other classes that inherit from vendable, so those will only have the slogan test. Could separate chip slogan test
    and rename this vendableTest for clarity but since you can't actually test an instance of an abstract class I'm leaving
    it as ChipsTest for now.
     */


    @Test
    public void slogan_returns_correct_message() { //tests slogan() method
        String message = "Crunch Crunch, Yum!";
        Chips chips = new Chips();
        Assert.assertEquals(message, chips.slogan());
    }

    @Test
    public void constructor_sets_correct_parameters() {  //tests constructor
        Chips chips = new Chips("1A", "name", 1.23, "Chips");
        Assert.assertEquals("1A", chips.getSlotID());
        Assert.assertEquals("name", chips.getSnackName());
        Assert.assertEquals(1.23, chips.getPrice(), 0.00);
        Assert.assertEquals(("Chips"), chips.getSnackType());
    }

    @Test
    public void quantityRemaining_is_not_negative(){
        var chips = new Chips();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        Assert.assertEquals(0, chips.getQuantityRemaining());

    }

    @Test
    public void quantity_remaining_updated_when_item_removed(){
        var chips = new Chips();
        chips.removeItem();
        Assert.assertEquals(4, chips.getQuantityRemaining());
    }



    @Test
    public void item_is_sold_out_when_quantity_remaining_is_0(){
        var chips = new Chips();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        chips.removeItem();
        Assert.assertEquals(true, chips.isSoldOut());

    }
    @Test
    public void item_is_stocked_to_maximum_when_created(){ //should this go in inventory tests?
        var chips = new Chips();
        Assert.assertEquals(5, chips.getQuantityRemaining());
    }
}

