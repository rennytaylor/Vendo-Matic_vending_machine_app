package com.techelevator.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Log {

    private static LocalDateTime timeStamp = LocalDateTime.now();
    private static DateTimeFormatter dateAndTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
    private static String path = "logs/Log.txt";
    private static File logFile = new File(path);

    public static void log(String message){
        try(PrintWriter log = new PrintWriter(new FileOutputStream(logFile, true))){
            log.println(timeStamp.format(dateAndTimeFormat) + message);
        }catch(IOException e){
            LogException x = new LogException("An error occurred opening the log: " + e.getMessage());
        }catch (Exception e){
            throw new LogException("Sorry, an error occurred: " + e.getMessage());
        }
    }
}
