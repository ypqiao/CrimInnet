package me.ypqiao.criminnet.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ypqiao on 4/3/2015.
 */
public class DateUtil {


    public static final String F1 = "yyyy-mm-dd HH:mm:ss";

    public static String formatDate(Date date, String format){
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
}
