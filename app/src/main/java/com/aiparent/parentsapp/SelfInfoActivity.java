package com.aiparent.parentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiparent.parentsapp.view.ActionSheetDialog;
import com.aiparent.parentsapp.view.LoadingDialog;
import com.aiparent.parentsapp.view.MyAlertDialog;
import com.aiparent.parentsapp.view.wheelview.ScreenInfo;
import com.aiparent.parentsapp.view.wheelview.WheelMain;
import com.aiparent.parentsapp.utill.JudgeDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by weilanzhuan on 2015/3/31.
 */
public class SelfInfoActivity extends Activity {
    private ImageView user_pic;
    private TextView profile_setting_birthday;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    WheelMain wheelMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_info);

        user_pic=(ImageView)findViewById(R.id.user_pic);
        profile_setting_birthday=(TextView)findViewById(R.id.profile_setting_birthday);
        profile_setting_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater1 = LayoutInflater.from(SelfInfoActivity.this);
                final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                        null);
                ScreenInfo screenInfo1 = new ScreenInfo(SelfInfoActivity.this);
                wheelMain = new WheelMain(timepickerview1);
                wheelMain.screenheight = screenInfo1.getHeight();
                String time1 = "1991-03-22";
                Calendar calendar1 = Calendar.getInstance();
                if (JudgeDate.isDate(time1, "yyyy-MM-dd")) {
                    try {
                        calendar1.setTime(dateFormat.parse(time1));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                int year1 = calendar1.get(Calendar.YEAR);
                int month1 = calendar1.get(Calendar.MONTH);
                int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year1, month1, day1);
                final MyAlertDialog dialog = new MyAlertDialog(SelfInfoActivity.this)
                        .builder()
                        .setTitle("生日")
                                // .setMsg("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
                                // .setEditText("1111111111111")
                        .setView(timepickerview1)
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                dialog.setPositiveButton("保存", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),
                                wheelMain.getTime(), 1).show();
                    }
                });
                dialog.show();
            }
        });
        user_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(SelfInfoActivity.this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("用相机更换头像", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                })
                        .addSheetItem("去相册选择头像", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                    }
                                }).show();
            }
        });
    }
}
