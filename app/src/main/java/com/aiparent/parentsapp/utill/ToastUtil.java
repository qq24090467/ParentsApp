package com.aiparent.parentsapp.utill;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by weilanzhuan on 2015/5/2.
 */
public class ToastUtil {
    public static void Show(Context context,String showText){
        Toast.makeText(context,showText,Toast.LENGTH_SHORT).show();
    }
}
