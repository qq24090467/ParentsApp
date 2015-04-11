package com.aiparent.parentsapp.utill;

import com.aiparent.parentsapp.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

/**
 * Created by weilanzhuan on 2015/4/10.
 */
public class AsyncHttpCilentUtil {
    private static AsyncHttpClient client;

    public static AsyncHttpClient getInstance() {
        if (client == null) {
            client = new AsyncHttpClient();
            MyApplication myApplication=MyApplication.getMyApplication();
            PersistentCookieStore myCookieStore = new PersistentCookieStore(myApplication);
            client.setCookieStore(myCookieStore);
        }
        return client;
    }
}
