package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.config.HttpsConstant;
import com.aiparent.parentsapp.impl.ArticleImpl;
import com.aiparent.parentsapp.other.CreateBmpFactory;
import com.aiparent.parentsapp.utill.AsyncHttpCilentUtil;
import com.aiparent.parentsapp.utill.JsonUtils;
import com.aiparent.parentsapp.utill.StringUtils;
import com.aiparent.parentsapp.utill.ToastUtil;
import com.aiparent.parentsapp.view.ActionSheetDialog;
import com.amap.api.location.AMapLocation;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by weilanzhuan on 2015/5/7.
 */
public class DairyEditActivity extends Activity implements View.OnClickListener{
    private GridView daily_photos_gridview;
    private DairyPhotoAdapter adapter;
    private CreateBmpFactory createBmpFactory;
    private PhotoViewAttacher mAttacher;

    private ImageView save_info=null;
    private LinearLayout back_btn=null;
    private TextView title_text=null;
    private TextView dairy_loation_text;//地图

    private EditText dairy_content_text,dairy_title_text;

    private LayoutInflater inflater;

    private List<Bitmap> bitmapList;
    private int cate_id=1;//默认分类
    private String photos_url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dairy);

        findView();
        InitData();
        if (getIntent()!=null){
            cate_id=getIntent().getExtras().getInt("cate_id");
        }

        Log.d("cate_id",cate_id+"");
    }

    private void findView(){
        daily_photos_gridview=(GridView)findViewById(R.id.daily_photos_gridview);

        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        save_info=(ImageView)findViewById(R.id.save_info);

        title_text=(TextView)findViewById(R.id.title_text);
        title_text.setText(R.string.edit_dairy);

        dairy_content_text=(EditText)findViewById(R.id.dairy_content_text);
        dairy_title_text=(EditText)findViewById(R.id.dairy_title_text);

        dairy_loation_text=(TextView)findViewById(R.id.dairy_loation_text);

        adapter=new DairyPhotoAdapter();

        back_btn.setOnClickListener(this);
        save_info.setOnClickListener(this);
    }

    private  void InitData(){
        bitmapList=new ArrayList<Bitmap>();
        bitmapList.add(null);

        daily_photos_gridview.setAdapter(adapter);
        createBmpFactory=new CreateBmpFactory(this);


        dairy_loation_text.setText(MyApplication.getMyLocationName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_info:
                addArticle();
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }

    private class DairyPhotoAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return bitmapList.size();
        }

        @Override
        public Object getItem(int position) {
            return bitmapList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            inflater=LayoutInflater.from(DairyEditActivity.this);
            if (position==bitmapList.size()-1){
                View addView=inflater.inflate(R.layout.layout__add_diary_photo,null);
                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPicDialog();
                    }
                });
                return addView;
            }else {
                View photos_item=inflater.inflate(R.layout.layout__add_diary_photo_item,null);
                ImageView imageView=(ImageView)photos_item.findViewById(R.id.diary_img_item);
                imageView.setImageBitmap(bitmapList.get(position));
                mAttacher = new PhotoViewAttacher(imageView);
                return photos_item;
            }
        }
    }

    //弹出图片选择框
    public void showPicDialog(){
        new ActionSheetDialog(DairyEditActivity.this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("用相机更换头像", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                createBmpFactory.OpenCamera();
                            }
                        })
                .addSheetItem("去相册选择头像", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                createBmpFactory.OpenGallery();
                            }
                        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String picPath = createBmpFactory.getBitmapFilePath(requestCode,
                resultCode, data);
        Bitmap bmp = createBmpFactory.getBitmapByOpt(picPath);
        if (bmp != null) {
            bitmapList.add(0, bmp);
            adapter.notifyDataSetChanged();
            uploadPic(bmp,picPath);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addArticle(){
        RequestParams params=new RequestParams();
        if (photos_url!=""){
            params.put("photos_url",photos_url.substring(0, photos_url.length() - 1));
        }
        params.put("longitude",MyApplication.getLongitude());
        params.put("latitude",MyApplication.getLatitude());
        params.put("title",dairy_title_text.getText().toString());
        params.put("content",dairy_content_text.getText().toString());
        params.put("cate_id",cate_id);

        ArticleImpl article=new ArticleImpl();
        article.addArticle(params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result=StringUtils.BytesToString(bytes);
                ToastUtil.Show(getApplicationContext(),JsonUtils.getValue(result,"content"));
                int status=Integer.parseInt(JsonUtils.getValue(result,"status"));
                if (status>0){
                    startActivity(new Intent(getApplicationContext(),DairyEditActivity.class));
                    finish();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                ToastUtil.Show(getApplicationContext(),"网络繁忙，请稍后重试！>>>"+i);

            }
        });

    }

    /*
    * 上传图片
    */
    public void uploadPic(Bitmap bitmap,String picPath) {
        try {
            final File photo=new File(picPath);//将要保存图片的路径
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(photo));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

            RequestParams params = new RequestParams();
            params.put("photo", photo);
            params.put("is_article",1);

            AsyncHttpCilentUtil.getInstance().post(HttpsConstant.UPLOAD_PHOTO_URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                                      byte[] bytes) {
                    String result = "";
                    result = StringUtils.BytesToString(bytes);

                    int status = Integer.parseInt(JsonUtils.getValue(result, "status"));
                    if (status > 0) {//修改成功
                        photos_url+=JsonUtils.getValue(result,"content")+",";
                        Toast.makeText(getApplicationContext(), JsonUtils.getValue(result, "content"), 3000).show();
                    } else {
                        Toast.makeText(getApplicationContext(), JsonUtils.getValue(result, "content"), 3000).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] bytes, Throwable error) {
                    Toast.makeText(DairyEditActivity.this,
                            "网络访问异常，错误码  > " + statusCode, 0).show();

                }

                @Override
                public void onFinish() {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
