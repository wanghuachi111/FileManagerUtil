package com.utils.files.rconvenience.service;

import org.springframework.stereotype.Service;


import java.io.File;
import java.util.Arrays;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;



/**
 * @author whc
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/1122:05
 */
@Service
public class FileService {


    public String calculateTime(String FilePath) {
        File file = new File(FilePath);
        File[] arr = file.listFiles();
        System.out.println(Arrays.toString(arr));
        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].isDirectory()) {
                continue;
            }else {
                result +=addTime(arr[i], FilePath);
            }
        }
        return "路径: " + FilePath +" 下时长总和为: " + result + "min, 请注意记录!";
    }



    public String RenameFiles(String FilePath)  {
        File file = new File(FilePath);
        File[] arr = file.listFiles();
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].isDirectory()) {
                continue;
            }else {
                changeName(arr[i], FilePath);
            }
        }
        return "路径: " + FilePath + "执行完成";
    }

    public static void changeName(File target, String FilePath){

        System.out.println("--------------------");
        String oldName = target.getName();
        int length = oldName.length();
        Encoder encoder = new Encoder();
        File source = new File(FilePath + "\\" + oldName);
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration();
            System.out.println("时长：" + ls + "秒");
            long second = ls/1000/60;
            System.out.println("换算为：" + second + "分");
            String lastName = oldName.substring(length - 4, length);
            oldName = oldName.substring(0,oldName.length() - 4);
            System.out.println("文件名: " + oldName);
            File dest = new File(FilePath + "\\" + oldName  +"_" + second + "min" + lastName);
            while(!target.renameTo(dest)) {   //确保重命名成功
            }
            System.out.println("修改成功");
            System.out.println("----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static long addTime(File target, String FilePath) {
        System.out.println("--------------------");
        String oldName = target.getName();
        Encoder encoder = new Encoder();
        File source = new File(FilePath + "\\" + oldName);
        long second = 0;
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration();
            System.out.println("时长：" + ls + "秒");
            second = ls/1000/60;
            System.out.println("换算为：" + second + "分");
            System.out.println("----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return second;
    }

}
