package com.aiparent.parentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;


/**
 * Created by weilanzhuan on 2015/3/27.
 */
public class ProfessionActivity extends Activity{
    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);

        mInflater = LayoutInflater.from(this);
        initData();
        initView();

    }

    private void initData()
    {
        mImgIds = new int[] { R.drawable.icon_profession_1, R.drawable.icon_profession_1, R.drawable.icon_profession_1,
                R.drawable.icon_profession_1, R.drawable.icon_profession_1, R.drawable.icon_profession_1, R.drawable.icon_profession_1,
                R.drawable.icon_profession_1, R.drawable.icon_profession_1 };
    }
    private void initView()
    {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < mImgIds.length; i++)
        {

            View view = mInflater.inflate(R.layout.gallery_profession_item,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.profession_img);
            img.setImageResource(mImgIds[i]);
            RadioButton txt = (RadioButton) view
                    .findViewById(R.id.profession_check);
            mGallery.addView(view);
        }
    }


}
