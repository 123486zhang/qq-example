package com.xuexiang.xupdatedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.xupdatedemo.activity.MainActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class layout1 extends Activity implements View.OnClickListener {
    private static Handler handler;
    public RelativeLayout layout;
    public Button btn1,btn2,btn3,btn4,good,music,next,pre;
    private Button image1;
    private  String userName="",username1="";
    private int flag=0,fcount=0,ncount=0,pcount=0;
    private static Random r=new Random();
    private static String state=null,signs=null;
    private SeekBar seek;
    private Timer timer;
    private TimerTask task;
    public TextView text1,text2,text3,text4,text5,text6,text8,text9,text10,text11,text12,text13,text14,text15,text16,text19,lyr,lyrnow,lyrend;
    private ImageView image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image18,rote;
    private long mExitTime;
    private File cameraSavePath;
    private  boolean flag1=true;
    private static int ran1,can=0;
    private List<Integer> mTimeList;
    private MediaPlayer me=new MediaPlayer();
    private String mp3Path;
    private int INTERVAL=45,ii=30;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        cameraSavePath= new File(Environment.getExternalStorageDirectory().getPath() + "/temp1.jpg");
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        btn1 = (Button) findViewById(R.id.btn1);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        good = (Button) findViewById(R.id.good);
        music = (Button) findViewById(R.id.music);
        seek = (SeekBar) findViewById(R.id.seek);
        next = (Button) findViewById(R.id.next);
        pre = (Button) findViewById(R.id.pre);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        lyrnow = (TextView) findViewById(R.id.lyrnow);
        lyrend = (TextView) findViewById(R.id.lyrend);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        lyr = (TextView) findViewById(R.id.lyr);//歌词
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text8 = (TextView) findViewById(R.id.text8);
        text9 = (TextView) findViewById(R.id.text9);
        text10 = (TextView) findViewById(R.id.text10);
        text11 = (TextView) findViewById(R.id.text11);
        text12 = (TextView) findViewById(R.id.text12);
        text13 = (TextView) findViewById(R.id.text13);
        text14 = (TextView) findViewById(R.id.text14);
        text15 = (TextView) findViewById(R.id.text15);
        text16 = (TextView) findViewById(R.id.text16);
        text19 = (TextView) findViewById(R.id.text19);
        image1 = (Button) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        rote = (ImageView) findViewById(R.id.rote);//旋转图片
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);
        image7 = (ImageView) findViewById(R.id.image7);
        image8 = (ImageView) findViewById(R.id.image8);
        image9 = (ImageView) findViewById(R.id.image9);
        image10 = (ImageView) findViewById(R.id.image10);
        image11 = (ImageView) findViewById(R.id.image11);
        image12 = (ImageView) findViewById(R.id.image12);
        image13 = (ImageView) findViewById(R.id.image13);
        image18 = (ImageView) findViewById(R.id.image18);
        layout = (RelativeLayout) findViewById(R.id.layout);
         ran1 = r.nextInt(13);
        btn1.setOnClickListener(this);
        pre.setOnClickListener(this);
        good.setOnClickListener(this);
        music.setOnClickListener(this);
        next.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        text9.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
         text1.setText(userName);
         new Thread(() -> {
            String strURL = "http://49.234.95.95/uploads/"+userName+"touxiang.png";
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
        new Thread(() -> {
            String strURL = "http://49.234.95.95/uploads/"+userName+"bg.png";
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
             signs=sign;
             state=signState;
             int dji=Integer.parseInt(sign);
             int hongdj =dji/5;
             int lvdj =dji/8;
             int huangdj =dji/7;
             int landj =dji/6;
             int fendj =dji/6;
             int zidj =dji/4;
             int dji2=dji/5+1;
             int vipdj=dji/10;
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

        int  z=Integer.parseInt(zan);
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
             if(state.equals("false"))
             {
                 btn4.setText("签到");
             }
             if(state.equals("true"))
             {
                 btn4.setText("今天已签到");
             }

         }
         else
         {
         Toast.makeText(layout1.this, "查询失败！", Toast.LENGTH_SHORT).show();
         }
         }
         else
         {
         Toast.makeText(layout1.this, "服务器无响应，请稍后再试！", Toast.LENGTH_SHORT).show();
         }
         } catch (JSONException e) {
         e.printStackTrace();
         }

         });
         }).start();
         }
         else
         {
         Toast.makeText(layout1.this, "无网络，请检查网络连接！", Toast.LENGTH_SHORT).show();
         }
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
// TODO Auto-generated method stub
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
// TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
// TODO Auto-generated method stub
                if (fromUser) {
                    me.seekTo(progress);

                }
            }
        });
         handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //处理消息
                Bundle bundle=msg.getData();
                //获取歌曲长度和当前播放位置，并设置到进度条上
                int duration=bundle.getInt("duration");
                int currentposition=bundle.getInt("currentposition");
                int cm=currentposition/60000;
                int cs=(currentposition/1000)%60;
                int dm=duration/60000;
                int ds=(duration/1000)%60;
                String cm1="",cs1="",dm1="",ds1="";
                if(cm<10){
                     cm1="0"+cm;
                }
                if(cm>=10){
                     cm1=""+cm;
                }
                if(cs<10){
                     cs1="0"+cs;
                }
                if(cs>=10){
                     cs1=""+cs;
                }
                if(dm<10){
                     dm1="0"+dm;
                }
                if(dm>=10){
                     dm1=""+dm;
                }
                if(ds<10){
                     ds1="0"+ds;
                }
                if(ds>=10){
                     ds1=""+ds;
                }
                lyrnow.setText(cm1+":"+cs1);
                lyrend.setText(dm1+":"+ds1);
                seek.setMax(duration);
                seek.setProgress(currentposition);

            }
        };


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            showNormalDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                me.pause();
                flag1=true;
                music.setBackgroundResource(R.mipmap.start);
                lyr.setText("");
                rote.clearAnimation();
                Intent intent2=new Intent(this, func.class);
                intent2.putExtra("username", text1.getText().toString());
                startActivity(intent2);
                break;
            case R.id.image1:
               showNormalDialog();
                break;
            case R.id.image2:
                Intent intent5 = new Intent(Intent.ACTION_PICK, null);
                intent5.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent5, 3);
                break;
            case R.id.good:
               /** new Thread(() -> {
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
            **/

              Toast.makeText(layout1.this,"不能给自己点赞哦",Toast.LENGTH_LONG).show();
               break;
            case R.id.image3:
                Intent intent4 = new Intent(Intent.ACTION_PICK, null);
                intent4.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent4, 2);
                break;
            case R.id.btn3:
                timer.cancel();
                task.cancel();
                me.release();
                lyr.setText("");
                can=0;
                fcount=0;
                flag1=true;
                finish();
                Intent intent3 = new Intent(layout1.this, updatauser.class);
                intent3.putExtra("username", text1.getText().toString());
                startActivity(intent3);
                break;
            case R.id.btn4:
                if(btn4.getText().toString().equals("今天已签到"))
                {
                    Toast.makeText(layout1.this,"不能重复签到哦，明天再来吧！",Toast.LENGTH_LONG).show();
                }
                else
                {
                    new Thread(() -> {
                        int signss=Integer.parseInt(signs);
                        signss++;
                        String signe=""+signss;  //返回签到次数
                        String usernames=text1.getText().toString();
                        String usernamee=usernames.substring(5); //返回id
                        String signstatee="true"; //返回签到状态
                        // final String response = LoginService.loginByGet(username,password);
                        final String response = upstate.loginByPost(usernamee,signe,signstatee);
                        runOnUiThread(() -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String zt = jsonObject.getString("状态");
                                if(zt.equals("签到成功")) {
                                    Toast.makeText(layout1.this, "签到成功！", Toast.LENGTH_LONG).show();
                                    btn4.setText("今天已签到");
                                    lyr.setText("");
                                    can=0;
                                    fcount=0;
                                    flag1=true;
                                    onCreate(null);
                                }
                                else
                                {
                                    Toast.makeText(layout1.this, "签到失败！", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        });
                    }).start();
                }
                break;
            case R.id.text9:
                int signss=Integer.parseInt(signs);
                int lv=signss%5;
                int lvup=5-lv;
                Toast.makeText(layout1.this, "距离下一等级还有"+lvup+"天！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music: {
                if(can!=0){
                    timer.cancel();
                    task.cancel();
                }
                if (flag1&fcount==0) {

                    switch (ran1){
                        case 0:
                            me = MediaPlayer.create(layout1.this, R.raw.memories1);
                            lyr.setText("Memories Maroon 5");
                            break;
                        case 1:
                            me = MediaPlayer.create(layout1.this, R.raw.shifengdong2);
                            lyr.setText("是风动 银临-河图");
                            break;
                        case 2:
                            me = MediaPlayer.create(layout1.this, R.raw.caiyu3);
                            lyr.setText("柴鱼のcalling【已售】 幸子小姐拜托了");
                            break;
                        case 3:
                            me = MediaPlayer.create(layout1.this, R.raw.chaoxihuanni4);
                            lyr.setText("超喜欢你 沈以诚");
                            break;
                        case 4:
                            me = MediaPlayer.create(layout1.this, R.raw.chuimie5);
                            lyr.setText("吹灭小山河");
                            break;
                        case 5:
                            me = MediaPlayer.create(layout1.this, R.raw.chun6);
                            lyr.setText("椿 沈以诚");
                            break;
                        case 6:
                            me = MediaPlayer.create(layout1.this, R.raw.huanyou7);
                            lyr.setText("环游星空 Gifty");
                            break;
                        case 7:
                            me = MediaPlayer.create(layout1.this, R.raw.sidegeermo8);
                            lyr.setText("斯德哥尔摩情人 陈奕迅");
                            break;
                        case 8:
                            me = MediaPlayer.create(layout1.this, R.raw.wodeyigedaogu9);
                            lyr.setText("我的一个道姑朋友 双笙");
                            break;
                        case 9:
                            me = MediaPlayer.create(layout1.this, R.raw.xiaozhang10);
                            lyr.setText("嚣张 en");
                            break;
                        case 10:
                            me = MediaPlayer.create(layout1.this, R.raw.xingrong11);
                            lyr.setText("形容 沈以诚");
                            break;
                        case 11:
                            me = MediaPlayer.create(layout1.this, R.raw.freeloop12);
                            lyr.setText("Free Loop Daniel Powter");
                            break;
                        case 12:
                            me = MediaPlayer.create(layout1.this, R.raw.trouble13);
                            lyr.setText("Trouble I'm In Twinbed");
                            break;

                    }
                    me.start();
                    seekPlayProgress();
                    Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
                    DecelerateInterpolator lin = new DecelerateInterpolator();
                    operatingAnim.setInterpolator(lin);
                    if (operatingAnim != null) {
                        rote.startAnimation(operatingAnim);
                    }

                    music.setBackgroundResource(R.mipmap.stop);
                }
                if (flag1&fcount!=0) {
                    switch (ran1){
                        case 0:
                            lyr.setText("Memories Maroon 5");
                            break;
                        case 1:
                            lyr.setText("是风动 银临-河图");
                            break;
                        case 2:
                            lyr.setText("柴鱼のcalling【已售】 幸子小姐拜托了");
                            break;
                        case 3:
                            lyr.setText("超喜欢你 沈以诚");
                            break;
                        case 4:
                            lyr.setText("吹灭小山河");
                            break;
                        case 5:
                            lyr.setText("椿 沈以诚");
                            break;
                        case 6:
                            lyr.setText("环游星空 Gifty");
                            break;
                        case 7:
                            lyr.setText("斯德哥尔摩情人 陈奕迅");
                            break;
                        case 8:
                            lyr.setText("我的一个道姑朋友 双笙");
                            break;
                        case 9:
                            lyr.setText("嚣张 en");
                            break;
                        case 10:
                            lyr.setText("形容 沈以诚");
                            break;
                        case 11:
                            lyr.setText("Free Loop Daniel Powter");
                            break;
                        case 12:
                            lyr.setText("Trouble I'm In Twinbed");
                            break;

                    }
                    me.start();
                    seekPlayProgress();
                    Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
                    DecelerateInterpolator lin = new DecelerateInterpolator();
                    operatingAnim.setInterpolator(lin);
                    if (operatingAnim != null) {
                        rote.startAnimation(operatingAnim);
                    }
                    music.setBackgroundResource(R.mipmap.stop);
                }
                if (!flag1) {
                    lyr.setText("");
                    rote.clearAnimation();
                    music.setBackgroundResource(R.mipmap.start);
                    me.pause();
                }
                can++;
                flag1 = !flag1;
                fcount++;
                break;
            }
            case R.id.next:{
                if(can!=0){
                    timer.cancel();
                    task.cancel();
                }
                me.release();
                flag1=false;
                ++ran1;
                if(ran1==13){
                    ran1=0;
                }
                switch (ran1){
                    case 0:
                        me = MediaPlayer.create(layout1.this, R.raw.memories1);
                        lyr.setText("Memories Maroon 5");
                        break;
                    case 1:
                        me = MediaPlayer.create(layout1.this, R.raw.shifengdong2);
                        lyr.setText("是风动 银临-河图");
                        break;
                    case 2:
                        me = MediaPlayer.create(layout1.this, R.raw.caiyu3);
                        lyr.setText("柴鱼のcalling【已售】 幸子小姐拜托了");
                        break;
                    case 3:
                        me = MediaPlayer.create(layout1.this, R.raw.chaoxihuanni4);
                        lyr.setText("超喜欢你 沈以诚");
                        break;
                    case 4:
                        me = MediaPlayer.create(layout1.this, R.raw.chuimie5);
                        lyr.setText("吹灭小山河");
                        break;
                    case 5:
                        me = MediaPlayer.create(layout1.this, R.raw.chun6);
                        lyr.setText("椿 沈以诚");
                        break;
                    case 6:
                        me = MediaPlayer.create(layout1.this, R.raw.huanyou7);
                        lyr.setText("环游星空 Gifty");
                        break;
                    case 7:
                        me = MediaPlayer.create(layout1.this, R.raw.sidegeermo8);
                        lyr.setText("斯德哥尔摩情人 陈奕迅");
                        break;
                    case 8:
                        me = MediaPlayer.create(layout1.this, R.raw.wodeyigedaogu9);
                        lyr.setText("我的一个道姑朋友 双笙");
                        break;
                    case 9:
                        me = MediaPlayer.create(layout1.this, R.raw.xiaozhang10);
                        lyr.setText("嚣张 en");
                        break;
                    case 10:
                        me = MediaPlayer.create(layout1.this, R.raw.xingrong11);
                        lyr.setText("形容 沈以诚");
                        break;
                    case 11:
                        me = MediaPlayer.create(layout1.this, R.raw.freeloop12);
                        lyr.setText("Free Loop Daniel Powter");
                        break;
                    case 12:
                        me = MediaPlayer.create(layout1.this, R.raw.trouble13);
                        lyr.setText("Trouble I'm In Twinbed");
                        break;

                }
                music.setBackgroundResource(R.mipmap.stop);
                me.start();
                seekPlayProgress();
                Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
                DecelerateInterpolator lin = new DecelerateInterpolator();
                operatingAnim.setInterpolator(lin);
                if (operatingAnim != null) {
                    rote.startAnimation(operatingAnim);
                }
                can++;
                fcount++;
                break;
            }
            case R.id.pre:{
                if(can!=0){
                    timer.cancel();
                    task.cancel();
                }
                me.release();
                flag1=false;
                --ran1;
                if(ran1==-1){
                    ran1=12;
                }
                switch (ran1){
                    case 0:
                        me = MediaPlayer.create(layout1.this, R.raw.memories1);
                        lyr.setText("Memories Maroon 5");
                        break;
                    case 1:
                        me = MediaPlayer.create(layout1.this, R.raw.shifengdong2);
                        lyr.setText("是风动 银临-河图");
                        break;
                    case 2:
                        me = MediaPlayer.create(layout1.this, R.raw.caiyu3);
                        lyr.setText("柴鱼のcalling【已售】 幸子小姐拜托了");
                        break;
                    case 3:
                        me = MediaPlayer.create(layout1.this, R.raw.chaoxihuanni4);
                        lyr.setText("超喜欢你 沈以诚");
                        break;
                    case 4:
                        me = MediaPlayer.create(layout1.this, R.raw.chuimie5);
                        lyr.setText("吹灭小山河");
                        break;
                    case 5:
                        me = MediaPlayer.create(layout1.this, R.raw.chun6);
                        lyr.setText("椿 沈以诚");
                        break;
                    case 6:
                        me = MediaPlayer.create(layout1.this, R.raw.huanyou7);
                        lyr.setText("环游星空 Gifty");
                        break;
                    case 7:
                        me = MediaPlayer.create(layout1.this, R.raw.sidegeermo8);
                        lyr.setText("斯德哥尔摩情人 陈奕迅");
                        break;
                    case 8:
                        me = MediaPlayer.create(layout1.this, R.raw.wodeyigedaogu9);
                        lyr.setText("我的一个道姑朋友 双笙");
                        break;
                    case 9:
                        me = MediaPlayer.create(layout1.this, R.raw.xiaozhang10);
                        lyr.setText("嚣张 en");
                        break;
                    case 10:
                        me = MediaPlayer.create(layout1.this, R.raw.xingrong11);
                        lyr.setText("形容 沈以诚");
                        break;
                    case 11:
                        me = MediaPlayer.create(layout1.this, R.raw.freeloop12);
                        lyr.setText("Free Loop Daniel Powter");
                        break;
                    case 12:
                        me = MediaPlayer.create(layout1.this, R.raw.trouble13);
                        lyr.setText("Trouble I'm In Twinbed");
                        break;

                }
                can++;
                music.setBackgroundResource(R.mipmap.stop);
                me.start();
                seekPlayProgress();
                Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
                DecelerateInterpolator lin = new DecelerateInterpolator();
                operatingAnim.setInterpolator(lin);
                if (operatingAnim != null) {
                    rote.startAnimation(operatingAnim);
                }
                fcount++;
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
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(layout1.this);
        normalDialog.setTitle("Tips");
        normalDialog.setMessage("\n       确认注销登录吗？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer.cancel();
                        task.cancel();
                        me.release();
                        lyr.setText("");
                        can=0;
                        fcount=0;
                        flag1=true;
                        finish();
                        Intent intent1=new Intent(layout1.this, login.class);
                        startActivity(intent1);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.show();
    }
    private void showNormalDialog1(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(layout1.this);
        normalDialog.setTitle("Tips");
        normalDialog.setMessage("\n       是否确认修改头像？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File f=new File(getExternalCacheDir(),"temp.jpg");
                        Uri uri1=Uri.fromFile(f);
                        Uri uri2=file2Content(uri1);
                        ContentResolver cr=layout1.this.getContentResolver();
                        Bitmap bitmapq= null;
                        try {
                            bitmapq = BitmapFactory.decodeStream(cr.openInputStream(uri2));
                            BitmapDrawable image2=new BitmapDrawable(bitmapq);
                            System.out.println(bitmapq);
                            image3.setImageDrawable(image2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmapn = ((BitmapDrawable) image3.getDrawable()).getBitmap();
                        String name2n = text1.getText().toString();
                        String name1n=name2n.substring(5);
                        try {
                            String path = save(bitmapn, name1n);
                            doFileUpload(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.show();
    }
    private void showNormalDialog2(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(layout1.this);
        normalDialog.setTitle("Tips");
        normalDialog.setMessage("\n       是否确认修改背景？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File f=new File(getExternalCacheDir(),"tempbg.jpg");
                        Uri uri1=Uri.fromFile(f);
                        Uri uri2=file2Content(uri1);
                        ContentResolver cr=layout1.this.getContentResolver();
                        Bitmap bitmapq= null;
                        try {
                            bitmapq = BitmapFactory.decodeStream(cr.openInputStream(uri2));
                            BitmapDrawable image21=new BitmapDrawable(bitmapq);
                            System.out.println(bitmapq);
                            image2.setImageDrawable(image21);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmapn = ((BitmapDrawable) image2.getDrawable()).getBitmap();
                        String name2n = text1.getText().toString();
                        String name1n=name2n.substring(5);
                        try {
                            String path = save2(bitmapn, name1n);  //保存背景
                            doFileUpload(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                System.out.println("成功");
                // 得到图片的全路径
                Uri uri = data.getData();
                File f=new File(getExternalCacheDir(),"temp.jpg");
                Uri uri1=Uri.fromFile(f);
                Uri uri2=file2Content(uri1);
                callSystemImageCropper(uri,uri2);
                showNormalDialog1();

            }
            }
       else if (requestCode == 3) {
            // 从相册返回的数据
            if (data != null) {
                System.out.println("成功");
                // 得到图片的全路径
                Uri uri = data.getData();
                File f=new File(getExternalCacheDir(),"tempbg.jpg");
                Uri uri1=Uri.fromFile(f);
                Uri uri2=file2Content(uri1);
                callSystemImageCropper2(uri,uri2);
                showNormalDialog2();

            }
        }
        else
        {
            System.out.println("没有选择图片");
       }
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
    private void doFileUpload(String path) {

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;
        String existingFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + path;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        String responseFromServer = "";
        String urlString = "http://49.234.95.95/upload.php";

        try {

            //------------------ CLIENT REQUEST
            FileInputStream fileInputStream = new FileInputStream(new File(existingFileName));
            // open a URL connection to the Servlet
            URL url = new URL(urlString);
            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + existingFileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            // close streams
            Log.e("Debug", "File is written");
            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            Log.e("Debug", "error: " + ex.getMessage(), ex);
        } catch (IOException ioe) {
            Log.e("Debug", "error: " + ioe.getMessage(), ioe);
        }

        //------------------ read the SERVER RESPONSE
        try {

            inStream = new DataInputStream(conn.getInputStream());
            String str;

            while ((str = inStream.readLine()) != null) {

                Log.e("Debug", "Server Response " + str);

            }

            inStream.close();

        } catch (IOException ioex) {
            Log.e("Debug", "error: " + ioex.getMessage(), ioex);
        }
    }
    public String save(Bitmap bitmap,String name1) throws  IOException{
        String sd="sdcard";
        String name="/"+name1+"touxiang";
        String filename=sd+name+".png";
        String path=name+".png";
        File file=new File(filename);
        FileOutputStream fos=new FileOutputStream(file);
        Matrix matrix=new Matrix();
        matrix.setScale(1f,1f);
        bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);

        fos.close();
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
        return  path;

    }
    public String save2(Bitmap bitmap,String name1) throws  IOException{
        String sd="sdcard";
        String name="/"+name1+"bg";
        String filename=sd+name+".png";
        String path=name+".png";
        File file=new File(filename);
        FileOutputStream fos=new FileOutputStream(file);
        Matrix matrix=new Matrix();
        matrix.setScale(1f,1f);
        bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);

        fos.close();
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
        return  path;

    }
    private void callSystemImageCropper(Uri srcUri, Uri desUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(srcUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
        intent.putExtra("outputX", 350);
        intent.putExtra("outputY", 350);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        startActivityForResult(intent, 222);
    }
    private void callSystemImageCropper2(Uri srcUri, Uri desUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(srcUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("aspectX", 1.8);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
        intent.putExtra("outputX", 437);
        intent.putExtra("outputY", 240);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        startActivityForResult(intent, 22234);
    }
    /**
     * file开头的uri转换为content开头的uri
     * @param uri 任意uri
     * @return content开头的uri
     */
    private Uri file2Content(Uri uri) {
        if (uri.getScheme().equals("file")) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(")
                        .append(MediaStore.Images.ImageColumns.DATA)
                        .append("=")
                        .append("'" + path + "'")
                        .append(")");
                Cursor cur = cr.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    //do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }
    public void seekPlayProgress(){
        /*1.获取当前歌曲的总时长*/
        final int duration=me.getDuration();

        //计时器对象
         timer=new Timer();
         task=new TimerTask() {
            @Override
            public void run() {
                //开启线程定时获取当前播放进度
                int currentposition=me.getCurrentPosition();
                //利用message给主线程发消息更新seekbar进度
                Message ms= Message.obtain();
                Bundle bundle=new Bundle();
                bundle.putInt("duration",duration);
                bundle.putInt("currentposition",currentposition);

                //设置发送的消息内容
                ms.setData(bundle);
                //发送消息
                layout1.handler.sendMessage(ms);
            }
        };
        timer.schedule(task,300,500);
        //当播放结束时停止播放
        me.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.cancel();
                task.cancel();
                me.release();
               ++ran1 ;
               if(ran1==13){
                   ran1=0;
               }
               switch (ran1){
                    case 0:
                        me = MediaPlayer.create(layout1.this, R.raw.memories1);
                        lyr.setText("Memories Maroon 5");
                        break;
                    case 1:
                        me = MediaPlayer.create(layout1.this, R.raw.shifengdong2);
                        lyr.setText("是风动 银临-河图");
                        break;
                    case 2:
                        me = MediaPlayer.create(layout1.this, R.raw.caiyu3);
                        lyr.setText("柴鱼のcalling【已售】 幸子小姐拜托了");
                        break;
                    case 3:
                        me = MediaPlayer.create(layout1.this, R.raw.chaoxihuanni4);
                        lyr.setText("超喜欢你 沈以诚");
                        break;
                    case 4:
                        me = MediaPlayer.create(layout1.this, R.raw.chuimie5);
                        lyr.setText("吹灭小山河");
                        break;
                    case 5:
                        me = MediaPlayer.create(layout1.this, R.raw.chun6);
                        lyr.setText("椿 沈以诚");
                        break;
                    case 6:
                        me = MediaPlayer.create(layout1.this, R.raw.huanyou7);
                        lyr.setText("环游星空 Gifty");
                        break;
                    case 7:
                        me = MediaPlayer.create(layout1.this, R.raw.sidegeermo8);
                        lyr.setText("斯德哥尔摩情人 陈奕迅");
                        break;
                    case 8:
                        me = MediaPlayer.create(layout1.this, R.raw.wodeyigedaogu9);
                        lyr.setText("我的一个道姑朋友 双笙");
                        break;
                    case 9:
                        me = MediaPlayer.create(layout1.this, R.raw.xiaozhang10);
                        lyr.setText("嚣张 en");
                        break;
                    case 10:
                        me = MediaPlayer.create(layout1.this, R.raw.xingrong11);
                        lyr.setText("形容 沈以诚");
                        break;
                    case 11:
                        me = MediaPlayer.create(layout1.this, R.raw.freeloop12);
                        lyr.setText("Free Loop Daniel Powter");
                        break;
                    case 12:
                        me = MediaPlayer.create(layout1.this, R.raw.trouble13);
                        lyr.setText("Trouble I'm In Twinbed");
                        break;

                }
                me.start();
               seekPlayProgress();
            }
        });

    }


}
