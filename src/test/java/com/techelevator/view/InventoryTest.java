package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {
    //requirements from README
    //reads inventory
    //restock each time application runs
    //each slot has room for 5 items (done in ChipsTest)
    //each product stocked to max at start (done in ChipsTest)

    //test that populateInventory handles exceptions for Null entries?
    //test that populateInventory creates objects with correct parameters



    @Test
    public void vendable_objects_name_matches_corresponding_name_in_text_file(){
        String name = "Triplemint";

        var inventory = new Inventory();
        inventory.populateInventory();

        Assert.assertEquals("Triplemint",inventory.getInventory().get("D4").getSnackName());
    }

    @Test
    public void vendable_objects_price_matches_corresponding_price_in_text_file(){
        double price = 0.75;

        var inventory = new Inventory();
        inventory.populateInventory();

        Assert.assertEquals(0.75,inventory.getInventory().get("D4").getPrice(), 0.00);
    }

}
