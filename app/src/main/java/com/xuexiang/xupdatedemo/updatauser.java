package com.xuexiang.xupdatedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Calendar;
import java.util.PrimitiveIterator;
import java.util.Random;

public class updatauser extends Activity implements View.OnClickListener {
    private Button btn1,btn2;
    private EditText text18, text12, text3,text6,text24;
    private Spinner spinner1,spinner2;
    private ImageView imagebot,imagecenter;
    private TextView text9,text20,text15,text21,text22;
    private String[] buf;
    private  String month1,day1;
    private int flag=0;
    private int flag1=0;
    private  Calendar calendar;
    private Random r = new Random();
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatauser);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        text3=(EditText)findViewById(R.id.text3);
        text6=(EditText)findViewById(R.id.text6);
        text12=(EditText)findViewById(R.id.text12);
        text18=(EditText)findViewById(R.id.text18);
        text24=(EditText)findViewById(R.id.text24);
        text9=(TextView)findViewById(R.id.text9);
        text20=(TextView)findViewById(R.id.text20);
        text15=(TextView)findViewById(R.id.text15);
        text21=(TextView)findViewById(R.id.text21);
        text22=(TextView)findViewById(R.id.text22);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        imagebot=(ImageView) findViewById(R.id.imagebot);
        imagecenter=(ImageView) findViewById(R.id.imagecenter);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        text3.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        imagebot.setOnClickListener(this);
        imagecenter.setOnClickListener(this);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        text15.setText(userName);
        calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        month++;
        if(month<10)
        {
             month1="0"+month;
        }
        if(month>=10)
        {
             month1=""+month;
        }
        if(day<10)
        {
             day1="0"+day;
        }
        if(day>=10)
        {
             day1=""+day;
        }
        text22.setText(year+"-"+month1+"-"+day1);
        int ran1 = r.nextInt(16);
        switch (ran1)
        {
            case 1:
                text21.setText("\n\t\t\t\t人总是喜欢在失去与得到之间徘徊迷茫，其实，那些经历过的事情都会成为你的财富。");
                break;
            case 2:
                text21.setText("\n\t\t\t\t没有谁的人生是一帆风顺的，也没有谁一生下来就懂得成长，正是经过了不断的磨练，我们才学会了成长。");
                break;
            case 3:
                text21.setText("\n\n\t只要有想做的事，想见的人，那就不要考虑结果会如何，努力奔跑便是了。");
                break;
            case 4:
                text21.setText("\n\n\t\t\t\t一定不要让自己觉得很舒服，因为只有下楼时才不会觉得累，只有下坡时才不用踩油门。");
                break;
            case 5:
                text21.setText("\n\t\t\t\t“你若盛开，蝴蝶自来。”若是你所处的环境、周围的一切都不尽人意，那只能说明你还不够好。");
                break;
            case 6:
                text21.setText("\n\t\t\t\t有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了。");
                break;
            case 7:
                text21.setText("\n\n\n\t\t\t\t喜欢说自己运气好的人，一般都比其他人更努力。");
                break;
            case 8:
                text21.setText("\n\n\t\t\t\t生活就像黑夜里在海上航行，有灯塔才能找到方向。");
                break;
            case 9:
                text21.setText("\n\n\t\t\t\t生活比电影狠多了，从来不给弱者安排大逆转的情节。");
                break;
            case 10:
                text21.setText("\n\n\t\t\t\t当你觉得孤独无助时，想一想还有十几亿的细胞只为了你一个人而活。");
                break;
            case 11:
                text21.setText("\n\n\t\t\t\t神在白天做梦，太阳从西边升起\n\t\t\t\t黑夜苏醒盲目，东边归去飞鸟");
                break;
            case 12:
                text21.setText("\n\n\t\t\t\t羁绊无影无踪，藤蔓一斩就断\n\t\t\t\t记忆触手可得，蓝天延绵不绝");
                break;
            case 13:
                text21.setText("\n\n\t\t\t\t盛夏白瓷梅子汤\n\t\t\t\t\t\t\t\t碎冰碰壁当啷响");
                break;
            case 14:
                text21.setText("\n\n\t\t\t\t盐于律己，甜以待人。");
                break;
            case 15:
                text21.setText("\n\n\t\t\t\t今夜我心头的月亮又被你打翻了。");
                break;
        }
        new Thread(() -> {
            String username1=text15.getText().toString();
            String username=username1.substring(5);
            String strURL = "http://49.234.95.95/uploads/"+username+"touxiang.png";
            try {
                BitmapDrawable bitmap = getBitmap(strURL);
                runOnUiThread(() -> {
                    if(bitmap!=null) {
                        imagebot.setImageDrawable(bitmap);
                    }
                    else
                    {
                        imagebot.setImageResource(R.mipmap.dea);
                    }
                });
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  }).start();
        new Thread(() -> {
            String username1=text15.getText().toString();
            String username=username1.substring(5);
            String strURL = "http://49.234.95.95/uploads/"+username+"bg1.png";
            try {
                BitmapDrawable bitmap = getBitmap(strURL);
                runOnUiThread(() -> {
                    if(bitmap!=null) {
                        imagecenter.setImageDrawable(bitmap);
                    }
                    else
                    {
                        imagecenter.setImageResource(R.mipmap.qqbg1);
                    }
                });
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  }).start();
        new Thread(() -> {
            String username1=text15.getText().toString();
            String username=username1.substring(5);
            // final String response = LoginService.loginByGet(username,password);
            final String response = mes.loginByPost(username);
            runOnUiThread(() -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String zt = jsonObject.getString("查询状态");
                    String xingzuo = jsonObject.getString("星座");
                    String gxqm = jsonObject.getString("个性签名");
                    String yingyue = jsonObject.getString("音乐");
                    String city = jsonObject.getString("城市");
                    String sex = jsonObject.getString("性别");
                    String name = jsonObject.getString("昵称");
                    String tel = jsonObject.getString("手机号");
                    if (name.equals("null")) name = "该用户没有昵称";
                    if (gxqm.equals("null")) gxqm = "该用户还没有个性签名";
                    if (yingyue.equals("null")) yingyue = "该用户暂时没有喜爱的歌曲";
                    if (sex.equals("null")) sex = "保密";
                    if (city.equals("null")) city = "中国";
                    if (xingzuo.equals("null")) xingzuo = "无星座";
                    if (tel.equals("")) tel = "***********";
                    if (name.equals("")) name = "该用户没有昵称";
                    if (gxqm.equals("")) gxqm = "该用户还没有个性签名";
                    if (yingyue.equals("")) yingyue = "该用户暂时没有喜爱的歌曲";
                    if (sex.equals("")) sex = "保密";
                    if (city.equals("")) city = "中国";
                    if (xingzuo.equals("")) xingzuo = "无星座";
                    if (tel.equals("null")) tel = "***********";
                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                           text9.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            text20.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    text9.setText(sex);
                    text3.setText(name);
                    text6.setText(gxqm);
                    text12.setText(city);
                    text18.setText(yingyue);
                    text20.setText(xingzuo);
                    text24.setText(tel);
                }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                });
            }).start();


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if(text24.getText().toString().length()==11) {
                new Thread(() -> {
                    String username1 = text15.getText().toString();
                    String username = username1.substring(5);
                    String name = text3.getText().toString();
                    String gxqm = text6.getText().toString();
                    String test1 = (String) spinner1.getSelectedItem();
                    String tel = text24.getText().toString();
                    if (test1.equals("")) {
                        test1 = text9.getText().toString();
                    }
                    String city = text12.getText().toString();
                    String test2 = (String) spinner2.getSelectedItem();
                    if (test2.equals("")) {
                        test2 = text20.getText().toString();
                    }
                    String yingyue = text18.getText().toString();
                    // final String response = LoginService.loginByGet(username,password);
                    final String response = userservice.loginByPost(username, name, gxqm, test1, city, test2, yingyue, tel);
                    runOnUiThread(() -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String zt = jsonObject.getString("状态");
                            if (zt.equals("更新资料成功")) {
                                Toast.makeText(updatauser.this, "更新资料成功！", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(updatauser.this, "更新资料失败！", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    });
                }).start();
                finish();
                Intent intent3 = new Intent(updatauser.this, layout1.class);
                String username1 = text15.getText().toString();
                String username2 = username1.substring(5);
                intent3.putExtra("userName", username2);
                startActivity(intent3);
            }
            else
            {
                text24.requestFocus();
                Toast.makeText(updatauser.this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.btn1:
                if(text24.getText().toString().length()==11) {
                    new Thread(() -> {
                        String username1 = text15.getText().toString();
                        String username = username1.substring(5);
                        String name = text3.getText().toString();
                        String gxqm = text6.getText().toString();
                        String tel = text24.getText().toString();
                        String test1 = (String) spinner1.getSelectedItem();
                        if (test1.equals("")) {
                            test1 = text9.getText().toString();
                        }
                        String city = text12.getText().toString();
                        String test2 = (String) spinner2.getSelectedItem();
                        if (test2.equals("")) {
                            test2 = text20.getText().toString();
                        }
                        String yingyue = text18.getText().toString();
                        // final String response = LoginService.loginByGet(username,password);
                        final String response = userservice.loginByPost(username, name, gxqm, test1, city, test2, yingyue, tel);
                        runOnUiThread(() -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String zt = jsonObject.getString("状态");
                                if (zt.equals("更新资料成功")) {
                                    Toast.makeText(updatauser.this, "更新资料成功！", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(updatauser.this, "更新资料失败！", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        });
                    }).start();
                    finish();
                    Intent intent3 = new Intent(updatauser.this, layout1.class);
                    String username1 = text15.getText().toString();
                    String username2 = username1.substring(5);
                    intent3.putExtra("userName", username2);
                    startActivity(intent3);
                }
                else
                {
                    text24.requestFocus();
                    Toast.makeText(updatauser.this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn2:
                int ran1 = r.nextInt(16);
                switch (ran1)
                {
                    case 1:
                        text21.setText("\n\t\t\t\t人总是喜欢在失去与得到之间徘徊迷茫，其实，那些经历过的事情都会成为你的财富。");
                        break;
                    case 2:
                        text21.setText("\n\t\t\t\t没有谁的人生是一帆风顺的，也没有谁一生下来就懂得成长，正是经过了不断的磨练，我们才学会了成长。");
                        break;
                    case 3:
                        text21.setText("\n\n\t只要有想做的事，想见的人，那就不要考虑结果会如何，努力奔跑便是了。");
                        break;
                    case 4:
                        text21.setText("\n\n\t\t\t\t一定不要让自己觉得很舒服，因为只有下楼时才不会觉得累，只有下坡时才不用踩油门。");
                        break;
                    case 5:
                        text21.setText("\n\t\t\t\t“你若盛开，蝴蝶自来。”若是你所处的环境、周围的一切都不尽人意，那只能说明你还不够好。");
                        break;
                    case 6:
                        text21.setText("\n\t\t\t\t有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了。");
                        break;
                    case 7:
                        text21.setText("\n\n\n\t\t\t\t喜欢说自己运气好的人，一般都比其他人更努力。");
                        break;
                    case 8:
                        text21.setText("\n\n\t\t\t\t生活就像黑夜里在海上航行，有灯塔才能找到方向。");
                        break;
                    case 9:
                        text21.setText("\n\n\t\t\t\t生活比电影狠多了，从来不给弱者安排大逆转的情节。");
                        break;
                    case 10:
                        text21.setText("\n\n\t\t\t\t当你觉得孤独无助时，想一想还有十几亿的细胞只为了你一个人而活。");
                        break;
                    case 11:
                        text21.setText("\n\n\t\t\t\t神在白天做梦，太阳从西边升起\n\t\t\t\t黑夜苏醒盲目，东边归去飞鸟");
                        break;
                    case 12:
                        text21.setText("\n\n\t\t\t\t羁绊无影无踪，藤蔓一斩就断\n\t\t\t\t记忆触手可得，蓝天延绵不绝");
                        break;
                    case 13:
                        text21.setText("\n\n\t\t\t\t盛夏白瓷梅子汤\n\t\t\t\t\t\t\t\t碎冰碰壁当啷响");
                        break;
                    case 14:
                        text21.setText("\n\n\t\t\t\t盐于律己，甜以待人。");
                        break;
                    case 15:
                        text21.setText("\n\n\t\t\t\t今夜我心头的月亮又被你打翻了。");
                        break;
                }
                break;
            case R.id.imagebot:
                Intent intent4 = new Intent(Intent.ACTION_PICK, null);
                intent4.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent4, 2);
                break;

            case R.id.imagecenter:
                Intent intent5 = new Intent(Intent.ACTION_PICK, null);
                intent5.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent5, 3);
                break;
        }
    }
    private void showNormalDialog1(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(updatauser.this);
        normalDialog.setTitle("Tips");
        normalDialog.setMessage("\n       是否确认修改头像？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File f=new File(getExternalCacheDir(),"temp.jpg");
                        Uri uri1=Uri.fromFile(f);
                        Uri uri2=file2Content(uri1);
                        ContentResolver cr=updatauser.this.getContentResolver();
                        Bitmap bitmapq= null;
                        try {
                            bitmapq = BitmapFactory.decodeStream(cr.openInputStream(uri2));
                            BitmapDrawable image2=new BitmapDrawable(bitmapq);
                            System.out.println(bitmapq);
                            imagebot.setImageDrawable(image2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmapn = ((BitmapDrawable) imagebot.getDrawable()).getBitmap();
                        String name2n = text15.getText().toString();
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
                new AlertDialog.Builder(updatauser.this);
        normalDialog.setTitle("Tips");
        normalDialog.setMessage("\n       是否确认修改背景？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File f=new File(getExternalCacheDir(),"tempbg.jpg");
                        Uri uri1=Uri.fromFile(f);
                        Uri uri2=file2Content(uri1);
                        ContentResolver cr=updatauser.this.getContentResolver();
                        Bitmap bitmapq= null;
                        try {
                            bitmapq = BitmapFactory.decodeStream(cr.openInputStream(uri2));
                            BitmapDrawable image21=new BitmapDrawable(bitmapq);
                            System.out.println(bitmapq);
                            imagecenter.setImageDrawable(image21);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmapn = ((BitmapDrawable) imagecenter.getDrawable()).getBitmap();
                        String name2n = text15.getText().toString();
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
        String name="/"+name1+"bg1";
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
        intent.putExtra("aspectX", 2.6);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
        intent.putExtra("outputX", 429);
        intent.putExtra("outputY", 165);
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


}
