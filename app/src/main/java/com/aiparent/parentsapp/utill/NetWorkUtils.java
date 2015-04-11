package com.aiparent.parentsapp.utill;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {

	/**
	 * 网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(
						Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = cm.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 跳转到系统网络设置
	 * 
	 * @param context
	 */
	public static void setNetConnection(Context context) {

		Intent intent = new Intent(
				android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		context.startActivity(intent);
	}
}
