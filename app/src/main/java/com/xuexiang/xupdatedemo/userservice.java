package com.xuexiang.xupdatedemo;

import com.xuexiang.xupdatedemo.StreamTools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class userservice {
    public static String loginByGet(String username, String qqname,String gxqm,String sex,String city,String xingzuo,String yingyue,String tel){
        try {
            //提交数据到服务器
            //拼装路径
            String path = "http://49.234.95.95:8080/Demo/updatauser?username="
                    + URLEncoder.encode(username,"UTF-8") + "&qqname=" + URLEncoder.encode(qqname,"UTF-8")
                    + "&gxqm=" + URLEncoder.encode(gxqm,"UTF-8")+ "&sex=" + URLEncoder.encode(sex,"UTF-8")
                    + "&city=" + URLEncoder.encode(city,"UTF-8")+ "&xingzuo=" + URLEncoder.encode(xingzuo,"UTF-8")
                    + "&yingyue=" + URLEncoder.encode(yingyue,"UTF-8")
                    + "&tel=" + URLEncoder.encode(tel,"UTF-8");
            URL url = new URL(path);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//打开连接

            conn.setRequestMethod("GET");//设置请求方式为get

            conn.setConnectTimeout(4000);//设置连接超时时间为5秒

            int code = conn.getResponseCode();//获得请求码
            if(code == 200){
                InputStream is = conn.getInputStream();
                String text = StreamTools.readInputStream(is);
                return text;
            }else{
                return null;
            }
//            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //这里提交的路径一定要写准确，填写你当前所在局域网的ip + 项目名 + Servlet Url
    public static String loginByPost(String username, String qqname,String gxqm,String sex,String city,String xingzuo,String yingyue,String tel){
        try {
            String path = "http://49.234.95.95:8080/Demo/updatauser?username="
                    + URLEncoder.encode(username,"UTF-8") + "&qqname=" + URLEncoder.encode(qqname,"UTF-8")
                    + "&gxqm=" + URLEncoder.encode(gxqm,"UTF-8")+ "&sex=" + URLEncoder.encode(sex,"UTF-8")
                    + "&city=" + URLEncoder.encode(city,"UTF-8")+ "&xingzuo=" + URLEncoder.encode(xingzuo,"UTF-8")
                    + "&yingyue=" + URLEncoder.encode(yingyue,"UTF-8")
                    + "&tel=" + URLEncoder.encode(tel,"UTF-8");
            URL url = new URL(path);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestMethod("POST");
            String data = "username="+URLEncoder.encode(username)+"&qqname="
                    +URLEncoder.encode(qqname)+"&gxqm="
                    +URLEncoder.encode(gxqm)+"&sex="
                    +URLEncoder.encode(sex)+"&city="
                    +URLEncoder.encode(city)+"&xingzuo="
                    +URLEncoder.encode(xingzuo) +"&yingyue="
                    +URLEncoder.encode(yingyue)
                    +"&tel="
                    +URLEncoder.encode(tel);
            System.out.println(data);
            conn.setRequestProperty("Content=Type", "application/x-wwww-form-urlencoded");
            conn.setRequestProperty("Content-length", data.length()+"");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream is = conn.getInputStream();
                String text = StreamTools.readInputStream(is);
                return text;
            }else {
                return null;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("111111");
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
