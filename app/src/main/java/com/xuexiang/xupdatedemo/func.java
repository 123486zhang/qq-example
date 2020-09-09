package com.xuexiang.xupdatedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.xuexiang.xhttp2.annotation.RequestParams;
import com.xuexiang.xupdatedemo.AndroidHelper;
import com.xuexiang.xupdatedemo.R;
import com.xuexiang.xupdatedemo.datepick;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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


public class func extends Activity implements View.OnClickListener {
    public RelativeLayout layout;
    public Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    private String encodedString;
    public EditText text1;
    public ImageView image1,image2;
    private TextView text2,text4;
    private long mExitTime;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn3 = (Button) findViewById(R.id.btn3);
        btn4= (Button) findViewById(R.id.btn4);
        btn5= (Button) findViewById(R.id.btn5);
        btn6= (Button) findViewById(R.id.btn6);
        btn7= (Button) findViewById(R.id.btn7);
        image1= (ImageView) findViewById(R.id.image1);
        image2= (ImageView) findViewById(R.id.image2);
        text1=(EditText)findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text4=(TextView) findViewById(R.id.text4);
        layout=(RelativeLayout) findViewById(R.id.layout);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        text2.setText(userName);

      /**  while (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setTextViewStyles(btn1);
            setTextViewStyles(btn2);
            break;
        }
        while (getRequestedOrientation()== ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setTextViewStyles2(btn1);
            setTextViewStyles2(btn2);
            break;
        }
       **/
        AndroidHelper.AutoBackground(this, layout, R.drawable.findbg, R.drawable.bgjbs);
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
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
                break;
            case R.id.btn1:
                if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                }
            case R.id.btn2:
                Intent intent2 = new Intent(this, datepick.class);
                startActivity(intent2);
                break;
            case R.id.btn3:
                if (!text1.getText().toString().equals("")) {
                    String username1 = text2.getText().toString();
                    username1 = username1.substring(5);
                    if (!text1.getText().toString().equals(username1)) {
                        new Thread(() -> {
                            String username = text1.getText().toString();
                            // final String response = LoginService.loginByGet(username,password);
                            final String response = mes.loginByPost(username);
                            runOnUiThread(() -> {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String zt = jsonObject.getString("查询状态");
                                    if (zt.equals("查询成功")) {
                                        Toast.makeText(func.this, " 查询成功！", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(func.this, layout2.class);
                                        intent1.putExtra("username", text1.getText().toString());
                                        startActivity(intent1);
                                    } else {
                                        text1.setText("");
                                        Toast.makeText(func.this, "该用户不存在，请重新输入！", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            });

                        }).start();
                    } else {
                        text1.setText("");
                        Toast.makeText(func.this, "不能查看自己信息！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(func.this, "用户ID不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn4:
                finish();
                Intent intent3 = new Intent(func.this, updatauser.class);
                intent3.putExtra("username", text2.getText().toString());
                startActivity(intent3);
                break;
            case R.id.btn5:
            {
                Intent intent4 = new Intent(Intent.ACTION_PICK, null);
                intent4.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent4, 2);
        }
                break;
            case R.id.btn6:
                new Thread(() -> {
                    String id=text1.getText().toString();
                String strURL = "http://49.234.95.95/uploads/"+id+"touxiang.png";
                try {
                    BitmapDrawable bitmap = getBitmap(strURL);

                    runOnUiThread(() -> {
                        if(bitmap!=null) {
                            image2.setImageDrawable(bitmap);
                        }
                        else
                        {
                            image2.setImageResource(R.mipmap.mcat);
                        }
                });
                }catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }).start();
                    break;
            case R.id.btn7:
                if(image1.getDrawable()!=null) {
                    Bitmap bitmap = ((BitmapDrawable) image1.getDrawable()).getBitmap();
                    String name1 = text1.getText().toString();
                    try {
                        String path = save(bitmap, name1);
                        doFileUpload(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.mcat);
                    String name1 = text1.getText().toString();
                    try {
                        String path = save(bitmap, name1);
                        doFileUpload(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {

            // 从相册返回的数据
            if (data != null) {
                System.out.println("成功");
                // 得到图片的全路径
                Uri uri = data.getData();
                System.out.println(uri);
                ContentResolver cr=this.getContentResolver();
                try{
                    Bitmap bitmap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                    BitmapDrawable image2=new BitmapDrawable(bitmap);
                    System.out.println(bitmap);
                    image1.setImageDrawable(image2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

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
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
        fos.close();
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
        return  path;

    }


}
