 package com.cn.douban.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.status.StatusConsoleListener;
import org.springframework.context.support.StaticApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sun.swing.internal.plaf.basic.resources.basic;

/**
 * @author Admin
 * @date 2019/07/30
 */
public class Test {
   static String josnStr = "{\"user\":\"aaa\",\"age\":\"111\",\"name\":\"bbb\",\"adress\":{ \"AAA\":{ \"AAA\":\"1111\", \"user\":\"aaaa\"}, \"user\":\"aaaa\"}}";
    
    public static JSONObject replaceString (String string ,String source,String dest) {
        JSONObject jsonObject = null;
        if(isJSONValid(string)) {
           System.out.println("111");
           jsonObject = JSON.parseObject(string);
               for (Entry<String, Object> entry : jsonObject.entrySet()) {
                   
                   if (entry.getKey().equals(source)) {
                       jsonObject.replace(entry.getKey(), dest);
                   
               }
                   JSONObject jsonObject2=replaceString(entry.getValue().toString(), source, dest);
                   if(jsonObject2 != null && !jsonObject2.isEmpty()) {
                       jsonObject.put(entry.getKey(), jsonObject2);
                   }
               }
           }
       return jsonObject;
    }
    
    /**
     * 暴力解析:Alibaba fastjson
     * @param test
     * @return
     */
    public final static boolean isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }
    
    public static void main (String [] args ) {
        
       String string =  replaceString (josnStr, "user", "*****").toJSONString();
       
       String str = josnStr.replace("\"user\":\"[a-z][3]*\"","\"user\":\"*****\"");
       System.out.println(string);
    }

    
    public static JSONObject toMap(String str,String source,String dest){
        JSONObject jsonObject= null;
        if(isJSONValid(str)) {
            jsonObject=JSONObject.parseObject(str);
        //遍历
        Set<String> set=jsonObject.keySet();
        if(set.size()>0) {
            
     
        for (String string : set) {
                if (source.equals(string)) {
                    jsonObject.put(source, dest);
                }
              
                        JSONObject jsonObject2=toMap(jsonObject.getString(string), source, dest);
                        if(jsonObject2 != null && !jsonObject2.isEmpty()) {
                            jsonObject.put(string, jsonObject2);
                        }
//                        jsonObject.put(string, jsonObject2);
                    }
        }
        }

        return jsonObject;
    }

}
