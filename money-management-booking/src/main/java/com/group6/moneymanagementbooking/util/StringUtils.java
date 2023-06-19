package com.group6.moneymanagementbooking.util;

import java.util.regex.Pattern;

public class StringUtils {
    
    public static boolean isNumberic(String num){
        try{
           Integer.parseInt(num);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    public static boolean patternMatchesEmail(String emailAddress, String regexPattern){
        return Pattern.compile(regexPattern)
      .matcher(emailAddress)
      .matches();
    }

    public static boolean isDigit(String str){
        try{
            Integer.parseInt(str);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
