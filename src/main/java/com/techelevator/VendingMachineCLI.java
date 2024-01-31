package com.techelevator;

import com.techelevator.util.Log;
import com.techelevator.util.SalesReport;
import com.techelevator.view.Menu;
import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
														MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT };
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION };
	private static final String FEED_MONEY_MENU_ONE_DOLLAR_BILL = "Insert $1 Bill";
	private static final String FEED_MONEY_MENU_FIVE_DOLLAR_BILL = "Insert $5 Bill";
	private static final String FEED_MONEY_MENU_TEN_DOLLAR_BILL = "Insert $10 Bill";
	private static final String[] FEED_MONEY_MENU_OPTIONS ={FEED_MONEY_MENU_ONE_DOLLAR_BILL, FEED_MONEY_MENU_FIVE_DOLLAR_BILL,
															FEED_MONEY_MENU_TEN_DOLLAR_BILL};
	private Inventory displayList = new Inventory();
	private Purchase currentMoney = new Purchase();
	private static final DecimalFormat moneyDecimalFormat = new DecimalFormat("0.00");

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public VendingMachineCLI(){}


	public void run() {

		welcomeMessage();

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println(displayList.inventoryDisplay());

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				purchaseMenu();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// exit
				System.out.println("\n---*THANK YOU FOR SHOPPING AT THE SNACK B.A.A.R.*---");
				System.exit(0);

			} else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)){
				//generates sales report
				SalesReport.generateSalesReport(displayList.salesReportDisplay());
			}
		}
	}

	private void purchaseMenu() {
		while (true) {

			System.out.println("Current Money Provided: $" + currentMoney.getCurrentMoneyDisplay());

				String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				if (choice.equals(PURCHASE_MENU_FEED_MONEY)) {
					//feed machine money
					feedMoneyMenu();

				} else if (choice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
					//displays inventory
					System.out.println(displayList.inventoryDisplay());
					//user input for selecting an item
					System.out.print("Enter the slot ID of the product you want to purchase: ");
					Scanner userInput = new Scanner(System.in);
					String productCode = userInput.nextLine().toUpperCase(); // Slot ID is in Upper case
					System.out.println(selectProduct(productCode)); // select product method

				} else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
					//gives change and returns user to main menu
					currentMoney.makeChange(currentMoney.getCurrentMoneyProvided());
					run();

				} else {
					System.out.println("Invalid choice. Please select a valid option.");

				}

		}
	}
	public String selectProduct(String productCode) {

		String outputMessage = "";


		// If Slot ID not valid
		if(!displayList.getInventory().containsKey(productCode)){
			outputMessage = ("Slot ID does not exist, please enter a valid Slot ID.");
		}
		// If valid Slot ID but Insufficient Funds
		else if (displayList.getInventory().containsKey(productCode) &&
				 currentMoney.getCurrentMoneyProvided() < displayList.getInventory().get(productCode).getPrice()) {

			outputMessage = ("Insufficient Funds, please feed more money.");
		}
		// If valid Slot ID but sold out
		else if (displayList.getInventory().containsKey(productCode) &&
				 displayList.getInventory().get(productCode).getQuantityRemaining() == 0) {

			outputMessage = ("SOLD OUT");
		}
		// If valid Slot ID and is available for purchase
		else if (displayList.getInventory().containsKey(productCode) &&
				 displayList.getInventory().get(productCode).getQuantityRemaining() > 0) {

			currentMoney.subtractPrice(displayList.getInventory().get(productCode).getPrice());	// subtract price from balance
			displayList.getInventory().get(productCode).removeItem();	// dispense item and update inventory
			// print snack name and price of snack
			outputMessage +=(displayList.getInventory().get(productCode).getSnackName()
							+ " " + displayList.getInventory().get(productCode).getPrice());
			// print balance remaining
			outputMessage +=("\nYour remaining balance is: $" + moneyDecimalFormat.format(currentMoney.getCurrentMoneyProvided()));
			// print snack message
			outputMessage +=("\n" + displayList.getInventory().get(productCode).slogan());
			// log updates
			Log.log(" " + displayList.getInventory().get(productCode).getSnackName()
					+ " " + displayList.getInventory().get(productCode).getSlotID()
					+ " $" + moneyDecimalFormat.format(displayList.getInventory().get(productCode).getPrice())
					+ " " + "$" + moneyDecimalFormat.format( currentMoney.getCurrentMoneyProvided()));
		}

		return outputMessage;
	}


	private void feedMoneyMenu() {
		while (true) {
			System.out.println("Current Money Provided: $" + currentMoney.getCurrentMoneyDisplay());

			String choice = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU_OPTIONS);

			if (choice.equals(FEED_MONEY_MENU_ONE_DOLLAR_BILL)) {
				//adds $1
				currentMoney.feedMoney(1.00);
				purchaseMenu();

			} else if (choice.equals(FEED_MONEY_MENU_FIVE_DOLLAR_BILL)) {
				//adds $5
				currentMoney.feedMoney(5.00);
				purchaseMenu();

			} else if (choice.equals(FEED_MONEY_MENU_TEN_DOLLAR_BILL)) {
				//adds $10
				currentMoney.feedMoney(10.00);
				purchaseMenu();

			} else {
				System.out.println("Invalid choice. Please select a valid option.");

			}

		}
	}

	public void welcomeMessage() {
		System.out.println("---*WELCOME TO THE SNACK B.A.A.R.*---");
		System.out.println("---*MACHINE MODEL VENDO-MATIC 800*---");
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public Purchase getCurrentMoney() {
		return currentMoney;
	}

}
