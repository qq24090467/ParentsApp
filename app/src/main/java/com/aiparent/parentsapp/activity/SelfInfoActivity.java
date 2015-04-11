package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.aiparent.parentsapp.utill.SDUtils;
import com.aiparent.parentsapp.view.ActionSheetDialog;
import com.aiparent.parentsapp.view.MyAlertDialog;
import com.aiparent.parentsapp.view.wheelview.ScreenInfo;
import com.aiparent.parentsapp.view.wheelview.WheelMain;
import com.aiparent.parentsapp.utill.JudgeDate;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

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
public class SelfInfoActivity extends Activity {
    private ImageView user_pic;
    private TextView profile_setting_birthday,profile_setting_sex;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    WheelMain wheelMain;

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private Bitmap bitmap;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private Intent dataIntent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_info);

        user_pic=(ImageView)findViewById(R.id.user_pic);
        profile_setting_birthday=(TextView)findViewById(R.id.profile_setting_birthday);

        profile_setting_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showbirthdayDialog();
            }
        });
        user_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicDialog();
            }
        });
        profile_setting_sex=(TextView)findViewById(R.id.profile_setting_sex);
        profile_setting_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ChangeSexActivity.class);
                startActivity(intent);
            }
        });
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
            String url = HttpsConstant.UPLOAD_PHOTO_URL;

            AsyncHttpClient client = new AsyncHttpClient();
            MyApplication myApplication=(MyApplication)getApplicationContext();
            client.setCookieStore(new PersistentCookieStore(myApplication));
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                                      byte[] bytes) {
                    String result="";
                    try {
                        result=new String(bytes,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    int status=Integer.parseInt(JsonUtils.getValue(result,"status"));
                    if (status>0){//修改成功
                        Toast.makeText(getApplicationContext(),JsonUtils.getValue(result,"content"),3000).show();
                    }else {
                        Toast.makeText(getApplicationContext(),JsonUtils.getValue(result,"content"),3000).show();
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

}
