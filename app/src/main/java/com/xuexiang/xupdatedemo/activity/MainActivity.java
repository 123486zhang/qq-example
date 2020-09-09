/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.xupdatedemo.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdatedemo.ImageViewCriCle;
import com.xuexiang.xupdatedemo.LoginService;
import com.xuexiang.xupdatedemo.R;
import com.xuexiang.xupdatedemo.custom.CustomUpdateParser;
import com.xuexiang.xupdatedemo.layout1;
import com.xuexiang.xupdatedemo.login;
import com.xuexiang.xupdatedemo.playView;
import com.xuexiang.xupdatedemo.utils.NotifyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends XPageActivity implements View.OnClickListener {
    public RelativeLayout frameLayout;
    public EditText text1;
    public String s = "";
    public String Ts = "";
    public int hour;
    public int minute;
    private ImageViewCriCle image2;
    private Button text3, text4;
    private TextView text5, text6, ping, text7;
    private Handler handler;
    private long mExitTime;
    Calendar calendar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (RelativeLayout) findViewById(R.id.layout);
        final playView playvies = new playView(this);
        text1 = (EditText) findViewById(R.id.text1);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        ping = (TextView) findViewById(R.id.ping);
        text7 = (TextView) findViewById(R.id.text7);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn11 = (Button) findViewById(R.id.btn11);
        Button btn12 = (Button) findViewById(R.id.btn12);
        Button btn13 = (Button) findViewById(R.id.btn13);
        Button btn14 = (Button) findViewById(R.id.btn14);
        Button btn15 = (Button) findViewById(R.id.btn15);
        Button btn16 = (Button) findViewById(R.id.btn16);
        Button btn17 = (Button) findViewById(R.id.btn17);
        Button btn18 = (Button) findViewById(R.id.btn18);
        Button btn19 = (Button) findViewById(R.id.btn19);
        Button btn10 = (Button) findViewById(R.id.btn10);
        text3 = (Button) findViewById(R.id.text3);
        text4 = (Button) findViewById(R.id.text4);
        text3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);/*下划线*/
        text3.getPaint().setAntiAlias(true);/*抗锯齿*/
        setTextViewStyles(text3);
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if (hour < 10) {
            text7.setText("程序启动时间为:0" + hour + "时" + minute + "分" + second + "秒");
        } else {
            text7.setText("程序启动时间为:" + hour + "时" + minute + "分" + second + "秒");
        }
        setTextViewStyles2(text7);
        text4.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);/*下划线*/
        text4.getPaint().setAntiAlias(true);/*抗锯齿*/
        setTextViewStyles(text4);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn10.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);

        setTextViewStyles(btn2);
        setTextViewStyles(btn3);
        setTextViewStyles(btn11);
        setTextViewStyles(btn12);
        setTextViewStyles(btn13);
        setTextViewStyles(btn14);
        setTextViewStyles(btn15);
        setTextViewStyles(btn16);
        setTextViewStyles(btn17);
        setTextViewStyles(btn18);
        setTextViewStyles(btn19);
        setTextViewStyles(btn10);
        setTextViewStyles2(ping);
        /**  竖横屏背景AndroidHelper.AutoBackground(this, frameLayout, R.mipmap.bg, R.mipmap.bg2);**/
        playvies.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                playvies.bitmapX = motionEvent.getX();
                playvies.bitmapY = motionEvent.getY();
                playvies.invalidate();
                return true;
            }
        });
        frameLayout.addView(playvies);
        new Thread(() -> {
            try {
                getVersion();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
        if (!NotifyUtils.isNotifyPermissionOpen(this)) {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("通知权限未打开，是否前去打开？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int w) {
                            NotifyUtils.openNotifyPermissionSetting(MainActivity.this);
                        }
                    })
                    .setNegativeButton("否", null)
                    .show();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text4:
                String s1 = text5.getText().toString();
                String s2 = text6.getText().toString();
                String s3 = s1.substring(s1.length() - 1);
                String s4 = s2.substring(s2.length() - 1);
                int i1 = Integer.parseInt(s3);
                int i2 = Integer.parseInt(s4);
                if (i1 < i2) {
                    Toast.makeText(MainActivity.this, "检测到新版本，请前往更新。", Toast.LENGTH_SHORT).show();
                    XUpdate.newBuild(MainActivity.this)
                            .updateUrl("http://49.234.95.95/catupdater.json")
                            .updateParser(new CustomUpdateParser())
                            .update();
                } else {
                    Toast.makeText(MainActivity.this, "当前已经是最新版本！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.text3:
                String qq = "2593977310";//填入要联系的qq
                if (checkApk(MainActivity.this, "com.tencent.mobileqq")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qq + "&version=1")));
                } else {
                    Toast.makeText(MainActivity.this, "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn11:
                s = s + "1";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn12:
                s = s + "2";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn13:
                s = s + "3";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn14:
                s = s + "4";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn15:
                s = s + "5";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn16:
                s = s + "6";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn17:
                s = s + "7";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn18:
                s = s + "8";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn19:
                s = s + "9";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn10:
                s = s + "0";
                text1.setText(s);
                setTextViewStyles2(text1);
                break;
            case R.id.btn2:
                if (text1.getText().length() > 0) {
                    s = s.substring(0, s.length() - 1);
                    text1.setText(s);
                    setTextViewStyles2(text1);
                    break;
                } else
                    break;
            case R.id.btn3:
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                hour = hour * 4;
                minute = minute + 30;
                if (hour <= 10) {
                    Ts = "" + hour;
                    Ts = "0" + Ts;
                    Ts = Ts + minute;
                } else {
                    Ts = "" + hour;
                    Ts = Ts + minute;
                }
                s = "" + text1.getText();
                if (s.equals(Ts)) {
                    finish();
                    Intent intent1 = new Intent(this, login.class);
                    startActivity(intent1);
                } else {
                    if (text1.getText().length() == 4) {
                        s = s.substring(0, s.length() - 4);
                        text1.setText(s);
                        Toast.makeText(this, "Ping码错误请重试！", Toast.LENGTH_LONG).show();
                    }
                    if (text1.getText().length() == 3) {
                        s = s.substring(0, s.length() - 3);
                        text1.setText(s);
                        Toast.makeText(this, "Ping码错误请重试！", Toast.LENGTH_LONG).show();
                    }
                    if (text1.getText().length() == 2) {
                        s = s.substring(0, s.length() - 2);
                        text1.setText(s);
                        Toast.makeText(this, "Ping码错误请重试！", Toast.LENGTH_LONG).show();
                    }
                    if (text1.getText().length() == 1) {
                        s = s.substring(0, s.length() - 1);
                        text1.setText(s);
                        Toast.makeText(this, "Ping码错误请重试！", Toast.LENGTH_LONG).show();
                    }
                    if (text1.getText().length() == 0) {
                        text1.setText(s);
                        Toast.makeText(this, "Ping码错误请重试！", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
        }
    }

    private void setTextViewStyles(TextView textView) {
        /**
         int[] colors = {Color.RED, Color.GREEN, Color.BLUE};//颜色的数组
         float[] position = {0f, 0.7f, 1.0f};//颜色渐变位置的数组
         LinearGradient mLinearGradient = dea LinearGradient(0, 0, textView.getPaint().getTextSize() * textView.getText().length(), 0, colors, position, Shader.TileMode.CLAMP);
         textView.getPaint().setShader(mLinearGradient);
         textView.invalidate();
         **/
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getPaint().getTextSize() * textView.getText().length(), 0, Color.parseColor("#FFFF68FF"), Color.parseColor("#FFFED732"), Shader.TileMode.CLAMP);
        textView.getPaint().setShader(mLinearGradient);
        textView.invalidate();
    }

    private void setTextViewStyles2(TextView textView) {

        int[] colors = {Color.RED, Color.GREEN, Color.BLUE};//颜色的数组
        float[] position = {0f, 0.7f, 1.0f};//颜色渐变位置的数组
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getPaint().getTextSize() * textView.getText().length(), 0, colors, position, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(mLinearGradient);
        textView.invalidate();

    }

    public boolean checkApk(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private int getVersion() throws IOException, JSONException {
        Looper.prepare();
        URL url = new URL("http://49.234.95.95/catupdate.json");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        System.out.println(httpURLConnection);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(2000);
        int code = httpURLConnection.getResponseCode();//获取返回码
        System.out.println(code);
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        StringBuffer sb = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        String string;
        string = sb.toString();
        System.out.println(string);
        //对json数据进行解析
        JSONObject jsonObject = new JSONObject(string);
        int versionCode = jsonObject.getInt("versionCode");
        String versionNname = jsonObject.getString("versionName");
        int mLocalVersionCode = getVersionCode();
        String mlocalname = getVersionName();
        if (mLocalVersionCode < versionCode) {
            //消息机制
            System.out.println("有更新");
            XUpdate.newBuild(this)
                    .updateUrl("http://49.234.95.95/catupdate.json")
                    .updateParser(new CustomUpdateParser())
                    .update();
            text5.setText("当前版本为:" + mlocalname);
            text6.setText("最新版本为:" + versionNname);
            setTextViewStyles2(text5);
            setTextViewStyles2(text6);
        } else {
            text5.setText("当前版本为:" + mlocalname);
            text6.setText("最新版本为:" + versionNname);
            setTextViewStyles2(text5);
            setTextViewStyles2(text6);
        }
        return versionCode;
    }

    private int getVersionCode() {
        PackageManager pm = getPackageManager();
        //版本号都在里面,0代表基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(this.getPackageName(), 0);
            //获取对应版本名称
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String getVersionName() {
        PackageManager pm = getPackageManager();
        //版本号都在里面,0代表基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(this.getPackageName(), 0);
            //获取对应版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}

