package main;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DemoTwo {
    public static void main(String[] args) throws IOException {
        //获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeDirectory=fsv.getHomeDirectory();
        String path = homeDirectory.getPath()+"/"+ "Desktop"+"/"+"demo";
        System.out.println("文件路径是"+path);

        //在桌面创建demo文件
        File file =new File(path);

        if(file.createNewFile()){
            System.out.println("文件创建成功");
        }else {
            System.out.println("该文件已存在");
        }

        //scanner获取输入日期
        Scanner input = new Scanner(System.in);
        System.out.println("请输入格式例如2017-04-03的日期");
        String scan =input.nextLine();

        //将输入日期String写入文件 并换行
        try{
            BufferedWriter bw =new BufferedWriter(new FileWriter(file,true));
            bw.write(scan);
            bw.newLine();
            bw.close();
            System.out.println("已将输入内容写入文件");
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("日期存入文件失败");
        }

        //从文件读取最后一行日期String
        String lastLine = "";
        try(BufferedReader br =new BufferedReader(new FileReader(file))){
            String currentLine = "";
            while((currentLine = br.readLine()) != null){
                lastLine = currentLine;
            }
        }catch (Exception e) {
            System.out.println("读取文件失败");
        }

        //先将日期String转化为Date格式，然后计算，然后写入文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = sdf.parse(lastLine);

            //将计算后的星期写入文件
            try{
                BufferedWriter bw =new BufferedWriter(new FileWriter(file,true));
                bw.write(getWeek(date));
                bw.newLine();
                bw.close();
                System.out.println("该日期为" + getWeek(date));
                System.out.println("日期信息已写入文件");
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("星期存入文件失败");
            }

        }catch (ParseException e){
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
