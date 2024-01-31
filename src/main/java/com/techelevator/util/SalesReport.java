package com.techelevator.util;

import com.techelevator.Inventory;
import com.techelevator.Vendable;
import com.techelevator.VendingMachineCLI;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalesReport {
    private static LocalDateTime timeStamp = LocalDateTime.now();
    private static DateTimeFormatter dateAndTimeFormat = DateTimeFormatter.ofPattern("MMddyy_HHmm");
    private static String fileName = fileName();
    private static String path = "logs/" + fileName + ".txt";
    private static File salesReportFile = new File(path);
    public static String fileName(){
        return timeStamp.format(dateAndTimeFormat) + "_salesreport";
    }

    public static void generateSalesReport(String message){

        try(PrintWriter logSales = new PrintWriter(salesReportFile)){
            logSales.println(message);
        } catch (Exception e){
            throw new LogException("Sorry, an error occurred: " + e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
