package cn.test.dao.Test4jdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jiangcaijun on 2017/6/21.
 */
public class HashMapTest {
    public static void main(String[] args)
    {
        Map<String,String>  hashMap= new HashMap<String,String>();
        hashMap.put("1","1");
        hashMap.put("2","2");
        hashMap.remove("1");
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
    }
}
