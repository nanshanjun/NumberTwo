package com.bwei.zhangjiyong1501a20161212.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyUtils {

	public static String getMessage(){

	    try {
	    	
	    	URL url = new URL("http://mock.eoapi.cn/success/aDpzG1ZiKPbEI6JdXjqasb958Q1rBg9j");  
			
	    	HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		
	       urlConn.setRequestMethod("GET");
	       
	       int responseCode = urlConn.getResponseCode();
	       
	       if (responseCode==200) {
	    	   
	    	   InputStream is = urlConn.getInputStream();
			
	    	ByteArrayOutputStream baos=new ByteArrayOutputStream();
	    	
	    	byte[] arr=new byte[1024];
	    	
	    	int len;
	    	
	    	while((len=is.read(arr))!=-1){
	    		
	    		baos.write(arr, 0, len);
	    		
	    	}
	    	
	    	return baos.toString();
	    	   
		}
	    	
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return null;
		
	}
	
}
