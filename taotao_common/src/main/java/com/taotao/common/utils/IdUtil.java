package com.taotao.common.utils;

import java.util.Random;

public class IdUtil {
    public static long genItemId(){
        //提取当前的长整型值包含毫秒
        long millis = System.currentTimeMillis();
        //生成一个两位的随机数
        int random = new Random().nextInt(100);//不包括指定值的0-99
        //转化成字符串
        String str = millis + String.format("%02d",random);
        long id = new Long(str);
        return id;
    }
}
