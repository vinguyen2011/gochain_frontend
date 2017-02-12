package com.gochain.gochainandroid.services;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class DateConverter {

    public DateConverter() {}

    public long getDaysTillExpireDate(long timestamp) {

        Date date = new Date();
        long daysInMillis = date.getTime() - timestamp;

        //86400000 is milli seconds in a day
        return daysInMillis / 86400000;
    }

}