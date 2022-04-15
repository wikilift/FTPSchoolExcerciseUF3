package Helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public  class Helpers {

    public static String getStringTime(){
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormatter.format(System.currentTimeMillis());
    }
}
