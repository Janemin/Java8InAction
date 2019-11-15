package com.helium.dateTimeApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by JaneMin on 2019-11-13.
 */
public class DateTest {
    public static void main(String[] args) throws ParseException, InterruptedException {
/*        Date date = new Date(114, 2, 18);
        System.out.println(date);*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int x = 0; x < 100; x++) {
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse("20160507");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }*/
/*        Date parseDate = sdf.parse("20160505");
        System.out.println(parseDate);*/

//        testLocalDate();
        // testLocalDateTime();
        testInstant();
        testDuration();
        testPeriod();
        testDateFormat();
        testDateParse();
    }

    private static void testLocalDate(){
        LocalDate localDate = LocalDate.of(2019, 11, 11);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());

        LocalDate.now();
    }

    private static void testLocalTime() {
        LocalTime now = LocalTime.now();

        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
    }

    private static void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        System.out.println(now.toString());
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    private static void testDuration() {
        LocalTime start = LocalTime.now();

        LocalTime before = start.minusHours(1);

        Duration between = Duration.between(start, before);

        System.out.println(between.toHours());
    }

    private static void testPeriod() {
        Period period = Period.between(LocalDate.of(2014, 1, 10), LocalDate.now());
        System.out.println(period.getYears());
    }

    private static void testDateFormat() {
        LocalDate localDate = LocalDate.now();

        String format = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format);
        System.out.println(format1);
    }

    private static void testDateParse() {
        String date = "20191111";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);


    }
}
