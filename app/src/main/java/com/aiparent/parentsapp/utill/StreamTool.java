package com.aiparent.parentsapp.utill;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamTool {

	public static byte[] readStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int count = 0;
		while ((count = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, count);
		}
		return bos.toByteArray();
	}
	public static String readStreamToString(InputStream inputStream){
	    String Result="";
		try { 
            InputStreamReader inputReader = new InputStreamReader(inputStream, "utf-8"); 
           BufferedReader bufReader = new BufferedReader(inputReader);
           String line="";
       
           while((line = bufReader.readLine()) != null)
               Result += line;
           return Result;
       } catch (Exception e) { 
           e.printStackTrace(); 
       }
	    return Result;
	}

}
