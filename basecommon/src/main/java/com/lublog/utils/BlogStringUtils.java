package com.lublog.utils;

/**
 * @Description: java类作用描述StringUtils
 * @Author: lxy
 * @time: 2020/7/26 1:13
 */
public class BlogStringUtils {
    private static String POUND = "#";

    public static Integer getNum(String str) {
        Integer num;
        if (str.contains(POUND)) {
            String subStr = str.substring(0, str.indexOf(POUND));
            num = Integer.parseInt(subStr);
            return num;
        }
        num = Integer.parseInt(str);
        return num;
    }
}
