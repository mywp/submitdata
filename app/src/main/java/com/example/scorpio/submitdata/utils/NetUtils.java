package com.example.scorpio.submitdata.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Scorpio on 16/2/6.
 */
public class NetUtils {

    private static final String TAG = "NetUtils";

    /*使用get的方式登陆
    * return登陆的状态*/
    public static String loginOfGet(String username, String password) {
        HttpURLConnection conn = null;
        try {
            String data = "username=" + URLEncoder.encode(username) + "&password=" + URLEncoder.encode(password);
            URL url = new URL("http://10.0.2.2:8080/ServerScorpio/servlet/LoginServlet?"+ data);
            conn = (HttpURLConnection) url.openConnection();
            
            conn.setRequestMethod("GET");//get或者post必须全大写
            conn.setConnectTimeout(10000);//连接的超时时间
            conn.setReadTimeout(5000);//读数据的超时时间
            
            int responseCode = conn.getResponseCode();
            if (responseCode == 200){
                InputStream is = conn.getInputStream();
                String state = getStringFromInputStream(is);
                return state;
            }else {
                Log.i(TAG,"访问失败："+responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                conn.disconnect();
            }
        }
        return null;
    }

    /*使用post的方式登陆*/
    public static String loginOfPost(String username, String password) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://10.0.2.2:8080/ServerScorpio/servlet/LoginServlet");

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);//连接的超时时间
            conn.setReadTimeout(5000);//读数据的超时时间
            conn.setDoOutput(true);//必须设置此方法，允许输出
//            conn.setRequestProperty("Content-Length","234");//设置请求头消息，可以设置多个

            //post请求的参数
            String data = "username=" + username + "&password=" + password;

            //获得一个输出流，用于向服务器写数据，默认情况下，系统不允许向服务器输出内容
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String state = getStringFromInputStream(is);
                return state;
            } else {
                Log.i(TAG, "访问失败：" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
    
    /*根据流返回一个字符串信息*/
    private static String getStringFromInputStream(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        
        while((len = is.read(buffer)) != -1){
            baos.write(buffer,0,len);
        }
        is.close();

         String html = baos.toString();//把流中的数据转换成字符串，采用的编码是：utf-8

        // String html = new String(baos.toByteArray(),"GBK");
        
        baos.close();
        return html;
    }
}
