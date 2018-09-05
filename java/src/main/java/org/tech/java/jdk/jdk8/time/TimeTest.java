package org.tech.java.jdk.jdk8.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTest {

    @Test
    public void test() {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间："+currentTime);
        LocalDate currentDate = LocalDate.now();
        System.out.println("当前日期："+currentDate);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2018);
        System.out.println("date2："+date2);
    }

    @Test
    public void parse() {
        //日期转换为字符串
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
        String nowStr = now .format(format);
        System.out.println(nowStr);

        LocalDateTime time = LocalDateTime.parse("2018年09月05日 04:33 下午", format);
        System.out.println(time);
    }
}
