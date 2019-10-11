package com.example.demo.backstage.pojo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lala
 */
public class RandomSecurityCode {
    public static String getCode() {
        String[] buffer = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(buffer);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString().substring(1,6);
    }
}

