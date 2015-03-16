package com.ibmboc.server_conf.config;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Date format.
 * Created by maven on 15/2/28.
 */
public class DateUtil {
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
    static SimpleDateFormat dfTimestamp = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    static SimpleDateFormat dfTimestampsecond = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss");
    static SimpleDateFormat dfTimestampsecond24 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat dfTimestampsecond24_2 = new SimpleDateFormat(
            "yyyy-MM-dd_HH-mm-ss");
    static SimpleDateFormat dfTimestampsecond24_3 = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    static SimpleDateFormat dfTimestampsecond24_sort = new SimpleDateFormat(
            "HH:mm:ss");
    static SimpleDateFormat dfYear = new SimpleDateFormat("yyyy");
    static SimpleDateFormat dfCN = new SimpleDateFormat("yyyy年MM月dd日");
    static SimpleDateFormat dfTimestampCN = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm:ss");

    public static Timestamp parseDate(String sDate) throws ParseException {
        return new Timestamp((df.parse(sDate)).getTime());
    }

    public static Timestamp parseTimestamp(String sDate) throws ParseException {
        return new Timestamp((dfTimestamp.parse(sDate)).getTime());
    }

    public static Timestamp parseTimestampAMPM(String s) throws ParseException {
        String date = s.substring(0, s.indexOf(" "));
        String hour = s.substring(s.indexOf(" "), s.indexOf(":"));
        String minutes = s.substring(s.indexOf(":") + 1, s.indexOf(":") + 3);
        if (s.endsWith("PM"))
            hour = "" + (Integer.parseInt(hour.trim()) + 12);
        return new Timestamp((dfTimestamp.parse(date + " " + hour + ":"
                + minutes)).getTime());
    }

    public static Timestamp parseYear(String sDate) throws ParseException {
        return new Timestamp((dfYear.parse(sDate)).getTime());
    }

    public static String getCurrentYear2Digits() {
        int year = GregorianCalendar.getInstance().get(Calendar.YEAR);
        return ("" + year).substring(2);
    }

    public static String getCurrentYear4Digits() {
        int year = GregorianCalendar.getInstance().get(Calendar.YEAR);
        return ("" + year);
    }

    public static String formatDate(Timestamp date) {
        if (date == null)
            return "";
        return df.format(date);
    }

    public static String formatDate2(Timestamp date) {
        if (date == null)
            return "";
        return df2.format(date);
    }

    public static String formatTime(Timestamp date) {
        if (date == null)
            return "";
        return dfTimestampsecond.format(date);
    }

    public static String formatTime12(Timestamp date) {
        if (date == null)
            return "";
        return dfTimestamp.format(date);
    }

    public static String formatTime24(Timestamp date) {
        if (date == null)
            return "";
        return dfTimestampsecond24.format(date);
    }

    public static String formatTime24_2(Timestamp date) {
        if (date == null)
            return "";
        return dfTimestampsecond24_2.format(date);
    }

    public static String formatTime24_3(Timestamp date) {
        if (date == null)
            return "";
        return dfTimestampsecond24_3.format(date);
    }

    public static String formatTime24_sort(Timestamp date) {
        if (date == null)
            return "";
        return dfTimestampsecond24_sort.format(date);
    }

    public static String threeYearsAgo() {
        int currentYear = (Calendar.getInstance()).get(Calendar.YEAR);
        return "" + (currentYear - 3) + "-01" + "-01";
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String getCurrentDateStr() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String current = formatDate(currentTime);
        return current;
    }

    public static String getCurrentTimeStr() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String current = formatTime24(currentTime);
        return current;
    }

    public static String getCurrentTimeStr2() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String current = formatTime24_2(currentTime);
        return current;
    }

    public static Timestamp getCurrentTime() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        return currentTime;
    }

    public static String formatDateCN(Timestamp date) {
        if (date == null)
            return "";
        return dfCN.format(date);
    }

    public static String formatDateTimeCN(Timestamp date) {
        if (date == null)
            return "";
        return dfTimestampCN.format(date);
    }

    /**
     * 获取两个日期相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferDays(Date date1, Date date2) {
        int differDays = 0;
        long millisecond = 0;
        if (date1.after(date2)) {
            millisecond = date1.getTime() - date2.getTime();
        } else {
            millisecond = date2.getTime() - date1.getTime();
        }
        differDays = (int) (millisecond / (24 * 60 * 60 * 1000));
        return differDays;
    }

    /**
     * 格式化Date类型，并返回字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return df.format(date);
    }


    /**
     * 格式化Date类型，并返回字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        if (date == null) {
            return null;
        }
        return sdf.format(date);
    }


    public static Date parseDate(String source, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

}
