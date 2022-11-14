package tech.na_app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelperUtil {

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static Date simpleFormatDate(Date date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        String format = simpleDateFormat.format(date);
        return simpleDateFormat.parse(format);
    }

}
