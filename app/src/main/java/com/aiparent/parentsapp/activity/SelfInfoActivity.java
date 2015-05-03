package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.config.SystemConstant;
import com.aiparent.parentsapp.config.UserInfoConfig;
import com.aiparent.parentsapp.impl.UserImpl;
import com.aiparent.parentsapp.utill.AsyncHttpCilentUtil;
import com.aiparent.parentsapp.utill.DisplayOptionsUtils;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.aiparent.parentsapp.utill.SDUtils;
import com.aiparent.parentsapp.utill.ToastUtil;
import com.aiparent.parentsapp.view.ActionSheetDialog;
import com.aiparent.parentsapp.view.CircularImage;
import com.aiparent.parentsapp.view.MyAlertDialog;
import com.aiparent.parentsapp.view.wheelview.ScreenInfo;
import com.aiparent.parentsapp.view.wheelview.WheelMain;
import com.aiparent.parentsapp.utill.JudgeDate;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by weilanzhuan on 2015/3/31.
 */
public class SelfInfoActivity extends Activity implements View.OnClickListener {
    private CircularImage user_pic;
    private TextView profile_setting_birthday,profile_setting_profession,profile_setting_sex,profile_setting_current_location,profile_setting_location,profile_setting_desc,profile_setting_self_style;
    private  TextView nickname_text,profession_text,birthday_text,home_location_text,live_location_text=null;
    private ImageView sex_img=null;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    WheelMain wheelMain;

    private LinearLayout back_btn;
    private TextView common_title;

    private SharedPreferences shared;

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果


    private static final int LIVE_REQUEST_CODE = 4;// 居住地
    private static final int HOME_REQUEST_CODE = 5;// 家庭住址

    private static final int DESC_REQUEST_CODE = 6;// 居住地
    private static final int SELFSTYLE_REQUEST_CODE = 7;// 家庭住址
    private static final int SEX_REQUEST_CODE = 8;// 家庭住址

    private Bitmap bitmap;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private Intent dataIntent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_info);
        findView();
        InitData();
        listener();
    }

    public void findView(){
        user_pic=(CircularImage)findViewById(R.id.user_pic);

        profile_setting_birthday=(TextView)findViewById(R.id.profile_setting_birthday);
        profile_setting_sex=(TextView)findViewById(R.id.profile_setting_sex);
        profile_setting_current_location=(TextView)findViewById(R.id.profile_setting_current_location);
        profile_setting_location=(TextView)findViewById(R.id.profile_setting_location);
        profile_setting_self_style=(TextView)findViewById(R.id.profile_setting_self_style);
        profile_setting_desc=(TextView)findViewById(R.id.profile_setting_desc);
        profile_setting_profession=(TextView)findViewById(R.id.profile_setting_profession);
        common_title=(TextView)findViewById(R.id.common_title);

        nickname_text=(TextView)findViewById(R.id.nickname_text);
        profession_text=(TextView)findViewById(R.id.profession_text);
        birthday_text=(TextView)findViewById(R.id.birthday_text);
        home_location_text=(TextView)findViewById(R.id.home_location_text);
        live_location_text=(TextView)findViewById(R.id.live_location_text);

        back_btn=(LinearLayout)findViewById(R.id.back_btn);

        sex_img=(ImageView)findViewById(R.id.sex_img);

        common_title.setText(R.string.profile_zone);
    }

    public void InitData(){
        shared= new UserInfoConfig(getApplicationContext()).GetConfig();
        String user_info_json=shared.getString(SystemConstant.DETAIL_INFO,"");

        nickname_text.setText(JsonUtils.getValue(user_info_json, "username"));
        profession_text.setText(JsonUtils.getValue(user_info_json,"job"));
        birthday_text.setText(JsonUtils.getValue(user_info_json,"birthday"));
        profile_setting_desc.setText(JsonUtils.getValue(user_info_json,"desc"));
        profile_setting_self_style.setText(JsonUtils.getValue(user_info_json,"self_style"));

        int sex_flag=Integer.parseInt(JsonUtils.getValue(user_info_json,"sex"));
        if (sex_flag==1){
            sex_img.setImageResource(R.drawable.personal_male_selected);
        }else{
            sex_img.setImageResource(R.drawable.personal_female_selected);
        }

        ImageLoader.getInstance().displayImage(JsonUtils.getValue(user_info_json,"photos"),user_pic, DisplayOptionsUtils.getDisplayImageOptions());

    }

    public void listener(){
        user_pic.setOnClickListener(this);
        profile_setting_birthday.setOnClickListener(this);
        profile_setting_sex.setOnClickListener(this);
        profile_setting_current_location.setOnClickListener(this);
        profile_setting_location.setOnClickListener(this);
        profile_setting_self_style.setOnClickListener(this);
        profile_setting_desc.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        profile_setting_profession.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_setting_birthday:
                showbirthdayDialog();
                break;
            case R.id.user_pic:
                showPicDialog();
                break;
            case R.id.profile_setting_sex:
                Intent intent=new Intent(getApplicationContext(),ChangeSexActivity.class);
                startActivityForResult(intent,SEX_REQUEST_CODE);
                break;
            case R.id.profile_setting_location:
                Intent intent2=new Intent(getApplicationContext(),LocationChooseActivity.class);
                intent2.putExtra("request_code",HOME_REQUEST_CODE);
                intent2.putExtra("title",getString(R.string.edit_home_location));
                startActivityForResult(intent2, HOME_REQUEST_CODE);
                break;
            case R.id.profile_setting_current_location:
                Intent intent3=new Intent(getApplicationContext(),LocationChooseActivity.class);
                intent3.putExtra("request_code",LIVE_REQUEST_CODE);
                intent3.putExtra("title",getString(R.string.edit_live_location));
                startActivityForResult(intent3, LIVE_REQUEST_CODE);
                break;
            case R.id.profile_setting_desc:
                Intent intent4=new Intent(getApplicationContext(),EditSelfInfoActivity.class);
                intent4.putExtra("title",getString(R.string.edit_desc));
                intent4.putExtra("request_code",DESC_REQUEST_CODE);
                startActivityForResult(intent4,DESC_REQUEST_CODE);
                break;
            case R.id.profile_setting_self_style:
                Intent intent5=new Intent(getApplicationContext(),EditSelfInfoActivity.class);
                intent5.putExtra("title",getString(R.string.edit_style));
                intent5.putExtra("request_code",SELFSTYLE_REQUEST_CODE);
                startActivityForResult(intent5,SELFSTYLE_REQUEST_CODE);
                break;
            case R.id.back_btn:
                finish();
                break;
            case R.id.profile_setting_profession:
                break;
        }
    }
    //弹出生日对话框
    public void showbirthdayDialog(){
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
                .setView(timepickerview1)
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        dialog.setPositiveButton("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthday_text.setText(wheelMain.getTime());
                RequestParams params=new RequestParams();
                params.put("birthday",wheelMain.getTime());
                update_info(params);
            }
        });
        dialog.show();
    }
    //弹出头像选择框
    public void showPicDialog(){
        new ActionSheetDialog(SelfInfoActivity.this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("用相机更换头像", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                getFromCamera();
                            }
                        })
                .addSheetItem("去相册选择头像", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                getFromGallery();
                            }
        }).show();
    }

    /*
	 * 上传图片
	 */
    public void uploadPic() {
        try {
           final File photo=new File(Environment
                    .getExternalStorageDirectory(), PHOTO_FILE_NAME);//将要保存图片的路径
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(photo));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

            RequestParams params = new RequestParams();
            params.put("photo", photo);

            AsyncHttpCilentUtil.getInstance().post(HttpsConstant.UPLOAD_PHOTO_URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                                      byte[] bytes) {
                    String result = "";
                    try {
                        result = new String(bytes, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    int status = Integer.parseInt(JsonUtils.getValue(result, "status"));
                    if (status > 0) {//修改成功
                        sendBroadcastTo();
                        Toast.makeText(getApplicationContext(), JsonUtils.getValue(result, "content"), 3000).show();
                    } else {
                        Toast.makeText(getApplicationContext(), JsonUtils.getValue(result, "content"), 3000).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] bytes, Throwable error) {
                    Toast.makeText(SelfInfoActivity.this,
                            "网络访问异常，错误码  > " + statusCode, 0).show();

                }

                @Override
                public void onFinish() {
                    boolean delete = photo.delete();
                    System.out.println("delete = " + delete);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 从相册获取
     */
    public void getFromGallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void getFromCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (SDUtils.hasSDCard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (SDUtils.hasSDCard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(SelfInfoActivity.this, "未找到存储卡，无法存储照片！", 0).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                user_pic.setImageBitmap(bitmap);
                boolean delete = tempFile.delete();
                System.out.println("delete = " + delete);
                uploadPic();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }else if (requestCode==LIVE_REQUEST_CODE){
              if (data!=null){
                  String live_location=data.getExtras().getString("location");
                  RequestParams params=new RequestParams();
                  params.put("live_district", live_location.substring(0, live_location.length() - 1));
                  update_info(params);
              }
        }else if(requestCode==HOME_REQUEST_CODE){
              if (data!=null){
                  String home_location=data.getExtras().getString("location");
                  RequestParams params=new RequestParams();
                  params.put("home_district",home_location.substring(0,home_location.length()-1));
                  update_info(params);
              }
        }else if (requestCode==DESC_REQUEST_CODE){
              if (data!=null){
                  String desc=data.getExtras().getString("info");
                  RequestParams params=new RequestParams();
                  params.put("desc",desc);
                  update_info(params);
              }
        }else if(requestCode==SELFSTYLE_REQUEST_CODE){
           if (data!=null){
               String self_style=data.getExtras().getString("info");
               RequestParams params=new RequestParams();
               params.put("self_style",self_style);
               update_info(params);
           }
        }else if (requestCode==SEX_REQUEST_CODE){
            if (data!=null){
                RequestParams params=new RequestParams();
                params.put("sex",data.getExtras().getString("sex"));
                update_info(params);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 剪切图片
     *
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private void update_info(RequestParams params){
        UserImpl userImpl=new UserImpl();
        userImpl.UpdateInfo(params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result = "";
                try {
                    result = new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.v("result=", result);
                int status = Integer.parseInt(JsonUtils.getValue(result, "status"));
                if (status > 0) {//提交成功
                    sendBroadcastTo();
                    ToastUtil.Show(getApplicationContext(),JsonUtils.getValue(result, "content"));
                } else {
                    ToastUtil.Show(getApplicationContext(), JsonUtils.getValue(result, "content"));
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "网络繁忙，请稍后重试！"+i, 3000).show();

            }
        });

    }

    //发送广播
    private void sendBroadcastTo(){
        Intent intent = new Intent();
        intent.setAction("com.aiparent.parentsapp.broadCastFlag");
        SelfInfoActivity.this.sendBroadcast(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            SelfInfoActivity.this.finish();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        InitData();
    }


}
