package com.dengyuecang.www.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by acang on 16/8/22.
 */
public class RegexUtils {

    public static List<String> getImgsFromHtml(String html){


        String regex;
        List<String> list = new ArrayList<String>();

        regex = "(?i)(src)[=\"\'\\s]+([^\"\']*)(?=[\"\'\\s>]+)";

        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(html);
        while (ma.find())
        {
            list.add(ma.group(2));
        }

        return list;

    }

    public static void main(String[] args) {




        String xmlString = "<p>啦咯啦咯啦咯啦</p><p><img src=\"http://test.static.dengyuecang.com/article/2016/08/23/12/2eeed3a7-b99a-475d-88f6-afbb8c1881a3.jpg\"></p>";

        List<String> l = RegexUtils.getImgsFromHtml(xmlString);


        for (String s:
             l) {
            System.out.println(s);
        }

    }

}