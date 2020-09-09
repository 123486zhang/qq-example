package com.xuexiang.xupdatedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xuexiang.xupdatedemo.AndroidHelper;
import com.xuexiang.xupdatedemo.R;
import com.xuexiang.xupdatedemo.datepick;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class layout2 extends Activity implements View.OnClickListener {
    public RelativeLayout layout;
    public Button btn2,btn3,good;
    private Button image1;
    private int flag=0;
    public TextView text1,text2,text3,text4,text5,text6,text8,text9,text10,text11,text12,text13,text14,text15,text16,text19;
    private ImageView image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image18;
    private long mExitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        good = (Button) findViewById(R.id.good);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text8 = (TextView) findViewById(R.id.text8);
        text9 = (TextView) findViewById(R.id.text9);
        text10 = (TextView) findViewById(R.id.text10);
        text19 = (TextView) findViewById(R.id.text19);
        text11 = (TextView) findViewById(R.id.text11);
        text12 = (TextView) findViewById(R.id.text12);
        text13 = (TextView) findViewById(R.id.text13);
        text14 = (TextView) findViewById(R.id.text14);
        text15 = (TextView) findViewById(R.id.text15);
        text16 = (TextView) findViewById(R.id.text16);
        image1 = (Button) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);
        image7 = (ImageView) findViewById(R.id.image7);
        image8 = (ImageView) findViewById(R.id.image8);
        image9 = (ImageView) findViewById(R.id.image9);
        image10 = (ImageView) findViewById(R.id.image10);
        image18 = (ImageView) findViewById(R.id.image18);
        image11 = (ImageView) findViewById(R.id.image11);
        image12 = (ImageView) findViewById(R.id.image12);
        image13 = (ImageView) findViewById(R.id.image13);
        layout = (RelativeLayout) findViewById(R.id.layout);
        good.setOnClickListener(this);
        image1.setOnClickListener(this);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        if (userName == null) {
            userName = "NULL";
        }
        text1.setText(userName);
        String finalUserName = userName;
        new Thread(() -> {
            String strURL = "http://49.234.95.95/uploads/"+ finalUserName +"touxiang.png";
            try {
                BitmapDrawable bitmap = getBitmap(strURL);
                runOnUiThread(() -> {
                    if(bitmap!=null) {
                        image3.setImageDrawable(bitmap);
                    }
                    else
                    {
                        image3.setImageResource(R.mipmap.dea);
                    }
                });
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  }).start();
        String finalUserName1 = userName;
        new Thread(() -> {
            String strURL = "http://49.234.95.95/uploads/"+ finalUserName1 +"bg.png";
            try {
                BitmapDrawable bitmap = getBitmap(strURL);
                runOnUiThread(() -> {
                    if(bitmap!=null) {
                        image2.setImageDrawable(bitmap);
                    }
                    else
                    {
                        image2.setImageResource(R.mipmap.qqbg);
                    }
                });
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  }).start();

        if(isNetworkConnected(this)) {
            new Thread(() -> {
                String username = text1.getText().toString();
                // final String response = LoginService.loginByGet(username,password);
                final String response = mes.loginByPost(username);
                runOnUiThread(() -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String zt = jsonObject.getString("查询状态");
                        String xingzuo = jsonObject.getString("星座");
                        String gxqm = jsonObject.getString("个性签名");
                        String qqdengji = jsonObject.getString("等级");
                        String yingyue = jsonObject.getString("音乐");
                        String city = jsonObject.getString("城市");
                        String sex = jsonObject.getString("性别");
                        String name = jsonObject.getString("昵称");
                        String zan = jsonObject.getString("赞");

                        String sign = jsonObject.getString("签到次数");
                        String signState = jsonObject.getString("签到状态");

                        int dji=Integer.parseInt(sign);
                        int hongdj =dji/5;
                        int lvdj =dji/8;
                        int huangdj =dji/7;
                        int landj =dji/6;
                        int fendj =dji/6;
                        int zidj =dji/4;
                        int dji2=dji/5+1;
                        int vipdj=dji/10;
                        int z=0;
                        if(name.equals("null")) name="该用户没有昵称";
                        if(gxqm.equals("null")) gxqm="该用户还没有个性签名";
                        if(yingyue.equals("null")) yingyue="该用户暂时没有喜爱的歌曲";
                        if(sex.equals("null")) sex="保密";
                        if(city.equals("null")) city="中国";
                        if(xingzuo.equals("null")) xingzuo="无星座";
                        if(zan.equals("null")) zan="0";

                        if(name.equals("")) name="该用户没有昵称";
                        if(gxqm.equals("")) gxqm="该用户还没有个性签名";
                        if(yingyue.equals("")) yingyue="该用户暂时没有喜爱的歌曲";
                        if(sex.equals("")) sex="保密";
                        if(city.equals("")) city="中国";
                        if(xingzuo.equals("")) xingzuo="无星座";
                        if(zan.equals("")) zan="0";
                        z=Integer.parseInt(zan);
                        if(z>=0&&z<10)
                        {
                            zan=z+"     ";
                        }
                        if(z>=10&&z<100)
                        {
                            zan=z+"    ";
                        }
                        if(z>=100&&z<1000)
                        {
                            zan=z+"   ";
                        }
                        if(z>=1000&&z<10000)
                        {
                            zan=z+"  ";
                        }
                        if(z>=10000&&z<100000)
                        {
                            zan=z+" ";
                        }
                        if(z>=100000)
                        {
                            zan=""+z;
                        }
                        if (response != null) {
                            if(zt.equals("查询成功")){
                                text1.setText("ID:  "+username);
                                text2.setText(name);
                                text6.setText(zan);
                                text3.setText(sex);
                                text4.setText(city);
                                text5.setText(xingzuo);
                                text8.setText(gxqm);
                                text9.setText(dji2+"级(当前已签到"+sign+"天)");
                                text10.setText(yingyue);
                                if(vipdj!=0){
                                    text19.setText(""+vipdj);
                                    image18.setBackgroundResource(R.mipmap.vip);
                                }
                                if(vipdj==0){
                                    image18.setBackgroundResource(R.mipmap.vipb);
                                }
                                if(hongdj!=0){
                                    text11.setText(""+hongdj);
                                    image8.setBackgroundResource(R.mipmap.hongzuan);
                                }
                                if(hongdj==0){
                                    image8.setBackgroundResource(R.mipmap.huise);
                                }
                                if(huangdj!=0){
                                    text12.setText(""+huangdj);
                                    image9.setBackgroundResource(R.mipmap.huangzuan);
                                }
                                if(huangdj==0){
                                    image9.setBackgroundResource(R.mipmap.huise);
                                }
                                if(lvdj!=0){
                                    text13.setText(""+lvdj);
                                    image10.setBackgroundResource(R.mipmap.lvzuan);
                                }
                                if(lvdj==0){
                                    image10.setBackgroundResource(R.mipmap.huise);
                                }
                                if(landj!=0){
                                    text14.setText(""+landj);
                                    image11.setBackgroundResource(R.mipmap.lanzuan);
                                }
                                if(landj==0){
                                    image11.setBackgroundResource(R.mipmap.huise);
                                }
                                if(fendj!=0){
                                    text15.setText(""+fendj);
                                    image12.setBackgroundResource(R.mipmap.fenzuan);
                                }
                                if(fendj==0){
                                    image12.setBackgroundResource(R.mipmap.huise);
                                }
                                if(zidj!=0){
                                    text16.setText(""+zidj);
                                    image13.setBackgroundResource(R.mipmap.zizuan);
                                }
                                if(zidj==0){
                                    image13.setBackgroundResource(R.mipmap.huise);
                                }

                            }
                            else
                            {
                                Toast.makeText(layout2.this, "查询失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(layout2.this, "服务器无响应，请稍后再试！", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                });
            }).start();
        }
        else
        {
            Toast.makeText(layout2.this, "无网络，请检查网络连接！", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK ) {
          finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image1:
            finish();
                break;
            case R.id.good:
                new Thread(() -> {
                    // final String response = LoginService.loginByGet(username,password);
                    String username1=text1.getText().toString();
                    String username=username1.substring(5);
                    String response = mes.loginByPost(username);
                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String zs=jsonObject.getString("赞");
                            if(zs.equals("")) zs="0";
                            if(zs.equals("null")) zs="0";
                            int zzs=Integer.parseInt(zs);
                            zzs++;
                            if(zzs>=0&&zzs<10)
                            {
                                zs=zzs+"     ";
                            }
                            if(zzs>=10&&zzs<100)
                            {
                                zs=zzs+"    ";
                            }
                            if(zzs>=100&&zzs<1000)
                            {
                                zs=zzs+"   ";
                            }
                            if(zzs>=1000&&zzs<10000)
                            {
                                zs=zzs+"  ";
                            }
                            if(zzs>=10000&&zzs<100000)
                            {
                                zs=zzs+" ";
                            }
                            if(zzs>=100000)
                            {
                                zs=""+zzs;
                            }
                            text6.setText(zs);
                            zs=zs.replaceAll(" +", "");
                            flag++;
                            if(flag==1)
                                good.setBackgroundResource(R.mipmap.good1);
                            if(flag==2)
                                good.setBackgroundResource(R.mipmap.good2);
                            if(flag==3)
                                good.setBackgroundResource(R.mipmap.good3);
                            if(flag==4)
                                good.setBackgroundResource(R.mipmap.good4);
                            if(flag==5)
                                good.setBackgroundResource(R.mipmap.good5);
                            if(flag==6) {
                                good.setBackgroundResource(R.mipmap.good6);
                                flag=0;

                            }
                            String finalZs = zs;
                            new Thread(() -> {
                                // final String response = LoginService.loginByGet(username,password);
                                updatazan.loginByPost(username, finalZs);
                            }).start();

                            System.out.println(zs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    });

                }).start();

                break;


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
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getPaint().getTextSize()* textView.getText().length(), 0, Color.parseColor("#FFFF68FF"), Color.parseColor("#FFFED732"), Shader.TileMode.CLAMP);
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
    private void setTextViewStyles3(TextView textView) {
        /**
         int[] colors = {Color.RED, Color.GREEN, Color.BLUE};//颜色的数组
         float[] position = {0f, 0.7f, 1.0f};//颜色渐变位置的数组
         LinearGradient mLinearGradient = dea LinearGradient(0, 0, textView.getPaint().getTextSize() * textView.getText().length(), 0, colors, position, Shader.TileMode.CLAMP);
         textView.getPaint().setShader(mLinearGradient);
         textView.invalidate();
         **/
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getPaint().getTextSize()* textView.getText().length(), 0, Color.parseColor("#FFff0000"), Color.parseColor("#FF00ffff"), Shader.TileMode.CLAMP);
        textView.getPaint().setShader(mLinearGradient);
        textView.invalidate();
    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    public BitmapDrawable getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                BitmapDrawable image2=new BitmapDrawable(bitmap);
                return image2;
            }
            else
            {
                return null ;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
