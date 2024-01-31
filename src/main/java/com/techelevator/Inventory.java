package com.techelevator;

import com.techelevator.view.PurchaseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Inventory {

    public final String INVENTORY_FILE = "vendingmachine.csv";
    File inventoryFile = new File(INVENTORY_FILE);
    private static final DecimalFormat moneyDecimalFormat = new DecimalFormat("0.00");
    public Map<String, Vendable> getInventory() {
        return inventory;
    }
    private Map<String,Vendable> inventory = new LinkedHashMap<>(populateInventory());

      public Map<String,Vendable> populateInventory(){
          //creates inventory from csv file
          Map<String,Vendable> inventory = new LinkedHashMap<>();

          try {
              Scanner dataInput = new Scanner(inventoryFile);

              while(dataInput.hasNextLine()){

                  String[] itemArray = dataInput.nextLine().split("\\|");
                  String slotID = itemArray[0];
                  String snackName = itemArray[1];
                  double price = Double.parseDouble(itemArray[2]);
                  String snackType = itemArray[3];


                  if (price < 0){
                      throw new PurchaseException("Warning, price is a negative number.");
                  }else if (itemArray[3].equals("Chip")){
                      inventory.put(slotID, new Chips(slotID, snackName, price, snackType));
                  }else if (itemArray[3].equals("Candy")){
                      inventory.put(slotID, new Candy(slotID, snackName, price, snackType));
                  }else if (itemArray[3].equals("Drink")){
                      inventory.put(slotID, new Drinks(slotID, snackName, price, snackType));
                  }else if (itemArray[3].equals("Gum")){
                      inventory.put(slotID, new Gum(slotID, snackName, price, snackType));
                  }
              }
        } catch (FileNotFoundException e) {
            System.err.println("File containing inventory was not found.");
        } catch (PurchaseException e) {
              throw new RuntimeException(e);
          }
          return inventory;

    }

    public String inventoryDisplay(){
          //prints out a visual display of the inventory for the user to reference

          String display = "";

          for (Map.Entry<String, Vendable> snack: inventory.entrySet()) {

              if (snack.getValue().getQuantityRemaining() == 0){
                  //snack is sold out
                  display += (snack.getKey() + "|" + snack.getValue().getSnackName() + "|"
                              + moneyDecimalFormat.format(snack.getValue().getPrice())
                              + "|" + snack.getValue().getSnackType() + "|SOLD OUT" + "\n");
              } else {
                  //snack is available
                  display += (snack.getKey() + "|" + snack.getValue().getSnackName() + "|"
                              + moneyDecimalFormat.format(snack.getValue().getPrice())
                              + "|" + snack.getValue().getSnackType() + "|" + snack.getValue().getQuantityRemaining() + "\n");
              }
          } return display;
    }

    public double createSalesTotal(){
          double sum = 0.00;
        for (Map.Entry<String, Vendable> snack: inventory.entrySet()){
            sum += snack.getValue().getQuantitySold() * snack.getValue().getPrice();
        }
        return sum;
    }
    public String salesReportDisplay(){
          String display = "";
        for (Map.Entry<String, Vendable> snack: inventory.entrySet()) {
            display += (snack.getValue().getSnackName() + "|" + snack.getValue().getQuantitySold() + "\n");
        }
        display += "**TOTAL SALES**: $" + moneyDecimalFormat.format(createSalesTotal());
        return display;
    }
}
