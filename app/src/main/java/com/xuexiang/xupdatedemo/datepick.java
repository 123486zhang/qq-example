package com.xuexiang.xupdatedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


public class datepick extends Activity {
    DatePicker datepick;
    TextView textView;
    int year,month,day;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepick);
        setTextViewStyles2 set=new setTextViewStyles2();
        datepick=(DatePicker)findViewById(R.id.date);
        textView=(TextView)findViewById(R.id.text1);
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        datepick.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                s=textView.getText().toString();
                if(s.equals("人总是喜欢在失去与得到之间徘徊迷茫，其实，那些经历过的事情都会成为你的财富。"))
                    textView.setText("没有谁的人生是一帆风顺的，也没有谁一生下来就懂得成长，正是经过了不断的磨练，我们才学会了成长。");
                if(s.equals("没有谁的人生是一帆风顺的，也没有谁一生下来就懂得成长，正是经过了不断的磨练，我们才学会了成长。"))
                    textView.setText("只要有想做的事，想见的人，那就不要考虑结果会如何，努力奔跑便是了。");
                if(s.equals("只要有想做的事，想见的人，那就不要考虑结果会如何，努力奔跑便是了。"))
                    textView.setText("一定不要让自己觉得很舒服，因为只有下楼时才不会觉得累，只有下坡时才不用踩油门。");
                if(s.equals("一定不要让自己觉得很舒服，因为只有下楼时才不会觉得累，只有下坡时才不用踩油门。"))
                    textView.setText("“你若盛开，蝴蝶自来。”若是你所处的环境、周围的一切都不尽人意，那只能说明你还不够好。");
                if(s.equals("“你若盛开，蝴蝶自来。”若是你所处的环境、周围的一切都不尽人意，那只能说明你还不够好。"))
                    textView.setText("有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了。");
                if(s.equals("有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了。"))
                    textView.setText("喜欢说自己运气好的人，一般都比其他人更努力。");
                if(s.equals("喜欢说自己运气好的人，一般都比其他人更努力。"))
                    textView.setText("生活就像黑夜里在海上航行，有灯塔才能找到方向。");
                if(s.equals("生活就像黑夜里在海上航行，有灯塔才能找到方向。"))
                    textView.setText("人总是喜欢在失去与得到之间徘徊迷茫，其实，那些经历过的事情都会成为你的财富。");
            }
        });
        set.setTextViewStylestwo(textView);

    }
}
