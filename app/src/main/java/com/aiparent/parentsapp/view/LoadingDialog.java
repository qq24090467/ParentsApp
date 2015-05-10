package com.aiparent.parentsapp.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aiparent.parentsapp.R;

/**
 * Created by weilanzhuan on 2015/4/6.
 */
public class LoadingDialog  {
    private Context context;
    private Dialog dialog;
    private Display display;
    private ProgressBar loading_proessbar;
    private TextView loading_text;

     public LoadingDialog(Context context) {
            this.context = context;
            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public LoadingDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_loading, null);

        // 获取自定义Dialog布局中的控件

        loading_proessbar = (ProgressBar) view.findViewById(R.id.loading_proessbar);
        loading_text=(TextView)view.findViewById(R.id.loading_text);

        // 定义Dialog布局和参数
        dialog = new Dialog(context,R.style.AlertDialogStyle);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小


        return this;
    }
    public LoadingDialog setLoadMsg(String msg){
        if ("".equals(msg)) {
            loading_text.setText("加载中");
        } else {
            loading_text.setText(msg);
        }
        return this;
    }
    public LoadingDialog setMsgVisible(boolean flag){
        if (!flag){
            loading_text.setVisibility(View.GONE);
        }else{
            loading_text.setVisibility(View.VISIBLE);
        }
        return this;
    }



    public void show() {
        dialog.show();
    }
    public void dismiss() {
        dialog.dismiss();
    }
}
