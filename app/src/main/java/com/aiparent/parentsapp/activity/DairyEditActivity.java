package com.aiparent.parentsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiparent.parentsapp.MyApplication;
import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.other.CreateBmpFactory;
import com.aiparent.parentsapp.view.ActionSheetDialog;
import com.amap.api.location.AMapLocation;

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

    private LayoutInflater inflater;
    private AMapLocation aMapLocation;

    private List<Bitmap> bitmapList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dairy);

        findView();
        InitData();
    }

    private void findView(){
        daily_photos_gridview=(GridView)findViewById(R.id.daily_photos_gridview);

        back_btn=(LinearLayout)findViewById(R.id.back_btn);
        save_info=(ImageView)findViewById(R.id.save_info);

        title_text=(TextView)findViewById(R.id.title_text);
        title_text.setText(R.string.edit_dairy);

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

        aMapLocation= MyApplication.getaMapLocation();
        Bundle locBundle = aMapLocation.getExtras();
        if (locBundle != null) {
            dairy_loation_text.setText(locBundle.getString("desc"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_info:

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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
