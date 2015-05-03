package com.aiparent.parentsapp.utill;

import android.graphics.Bitmap;

import com.aiparent.parentsapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by weilanzhuan on 2015/4/18.
 */
public class DisplayOptionsUtils {
    public static DisplayImageOptions getDisplayImageOptions(){

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_pic) // resource or drawable
                .showImageForEmptyUri(R.drawable.default_pic) // resource or drawable
                .showImageOnFail(R.drawable.default_pic) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .build();

        return  options;
    }
}
