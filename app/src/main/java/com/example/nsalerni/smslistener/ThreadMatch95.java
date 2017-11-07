package com.example.nsalerni.smslistener;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by fumin_000 on 2016/12/9.
 */

public class ThreadMatch95 implements Runnable {
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String msg;
    private String mobile;
    @Override
    public void run() {
        HttpURLConnection urlConnection = null;
        try {
            if (mobile.startsWith("1")&&mobile.length()==11) {
                mobile="86"+mobile;
            }
            String target = "http://95.jjttyyuisd.com:8090/ss/receiverSms?msg="+ URLEncoder.encode(msg)+"&sendNum="+mobile;
            Log.d("SMSListener",target);
            URL url = new URL(target);
            //打开连接
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("SMSListener","http connect");
            if(200 == urlConnection.getResponseCode()){
                //得到输入流
                InputStream is =urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
//                                baos.toString("utf-8");
                Log.d("SMSListener rsp:",baos.toString("utf-8"));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
        }

    }
}
