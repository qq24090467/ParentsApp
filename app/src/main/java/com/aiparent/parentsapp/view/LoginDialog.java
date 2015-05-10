package com.aiparent.parentsapp.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiparent.parentsapp.R;
import com.aiparent.parentsapp.activity.LoginActivity;
import com.aiparent.parentsapp.activity.RegsiterActivity;

/**
 * Created by weilanzhuan on 2015/5/9.
 */
public class LoginDialog {
    private Context context;
    private Dialog dialog;
    private Display display;
    private ImageView iv_close;
    private TextView tv_login,tv_zhuce;

    public LoginDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public LoginDialog Builder(){
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_login_go, null);
        tv_login=(TextView)view.findViewById(R.id.tv_login);
        tv_zhuce=(TextView)view.findViewById(R.id.tv_zhuce);
        iv_close=(ImageView)view.findViewById(R.id.iv_close);

        listenter();
        // 定义Dialog布局和参数
        dialog = new Dialog(context,R.style.AlertDialogStyle);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);

        return this;
    }

    private void listenter(){
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });
        tv_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RegsiterActivity.class));
            }
        });
    }


    public void show() {
        dialog.show();
    }
    public void dismiss() {
        dialog.dismiss();
    }


}
