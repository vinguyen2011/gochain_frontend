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
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);

        return TimeUnit.DAYS.convert(timestamp - Calendar.getInstance().get(Calendar.MILLISECOND), TimeUnit.MILLISECONDS);

    }

}