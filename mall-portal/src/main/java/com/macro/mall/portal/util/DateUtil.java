package com.macro.mall.portal.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 日期工具类
 * Created by macro on 2019/1/29.
 */
public class DateUtil {

    private static final List<DateTimeFormatter> SUPPORTED_FORMATS = List.of(
            DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
    );

    /**
     * 从Date类型的时间中提取日期部分
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 从Date类型的时间中提取时间部分
     */
    public static Date getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 将Redis返回的Object类型转化为Data类型
     * */
    public static Date object2Date(Object obj) {
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Long) {
            return new Date((Long) obj);
        }
        if (obj instanceof String dateStr) {
            for (DateTimeFormatter format : SUPPORTED_FORMATS) {
                try {
                    ZonedDateTime zdt = ZonedDateTime.parse(dateStr, format);
                    return Date.from(zdt.toInstant());
                } catch (Exception e) {
                    System.err.println("日期解析失败" + e.getMessage());
                }
            }
            throw new IllegalArgumentException("无法解析日期字符串: " + dateStr);
        }
        return null;
    }

}
