package com.rebuild.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtils {

    /*YYYYMMDD 금일date*/
    public static String getNowDate(){
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMdd");
        String nowDate = formatter.format(now);

        return nowDate;
    }

    public static String getNowTime(){
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String nowTime = formatter.format(now);

        return nowTime;
    }
}
