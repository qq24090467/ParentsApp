package com.aiparent.parentsapp.utill;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;

public class JsonUtils {
	
   public static String getValue(String json,String key){
	   JSONObject jsonObject=null; 
	   
	   try {
			 jsonObject=new JSONObject(json);
			 System.out.println("键值="+jsonObject.getString(key));
			 return jsonObject.getString(key);
			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return null;
   }
}
