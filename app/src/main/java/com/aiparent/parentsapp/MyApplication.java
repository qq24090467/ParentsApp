package com.aiparent.parentsapp;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import java.net.CookieStore;

/**
 * Created by weilanzhuan on 2015/3/30.
 */
public class MyApplication extends Application {

    private static final String LOG_TAG = "MyApplication";
    private static MyApplication myApplication = null;

    public static MyApplication getMyApplication() {
        return myApplication;
    }


    @Override
    public void onCreate() {
        setStrictMode();
        super.onCreate();
        myApplication = this;
    }

    private void setStrictMode() {
        if (Integer.valueOf(Build.VERSION.SDK_INT) > 3) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }

}
