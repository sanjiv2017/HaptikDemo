package com.haptik.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sanjiv on 22/02/17.
 */

public class Utils {

    /**
     *
     * @param dateString date String
     * @param formatString String pattern for output.
     * @return Returns the formatted Date String.
     * This method converts the date format to simple date format/
     */
    public static String getDate(String dateString,String formatString){
        //yyyy-MM-dd'T'HH:mm:ss
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try{
          Date date =  parse.parse(dateString);

            return format.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }



}
