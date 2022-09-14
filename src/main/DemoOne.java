package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DemoOne {
    public static void main(String[] args){
        //scanner获取输入
        Scanner input = new Scanner(System.in);
        System.out.println("请输入格式例如2017-04-03的日期");
        String scan =input.nextLine();
        //string转化为date格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
                Date date = sdf.parse(scan);
                System.out.println("该日期为" + getWeek(date));
        } catch (ParseException e) {
                System.out.println("请输入正确日期格式：例如2017-04-03");
        }
        input.close();
    }
    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0){
            index = 0;
        }
        return weeks[index];
    }
}
