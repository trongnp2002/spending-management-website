package com.group6.moneymanagementbooking.util;

public class StringUtils {
    
    public static boolean isNumberic(String num){
        try{
            int number = Integer.parseInt(num);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}
