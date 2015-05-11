package com.Biosys.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SecurityUtil {
	private MessageDigest digester ;
	
	public SecurityUtil(String algirithmName) throws NoSuchAlgorithmException{
		digester = MessageDigest.getInstance(algirithmName);
	}
	
	public String EncryptString(String str)
	{
		byte[] passBytes = str.getBytes();
		digester.reset();
		byte[] digested = digester.digest(passBytes);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<digested.length;i++){
		    sb.append(Integer.toHexString(0xff & digested[i]));
		}
		return sb.toString();
	}
	
	public static boolean HasActiveInternetConnection(Context context) {
	    if (isNetworkAvailable(context)) {
	        try {
	            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
	            urlc.setRequestProperty("User-Agent", "Test");
	            urlc.setRequestProperty("Connection", "close");
	            urlc.setConnectTimeout(1500); 
	            urlc.connect();
	            return (urlc.getResponseCode() == 200);
	        } catch (IOException e) {
	        }
	    } else {
	    }
	    return false;
	}
	public static boolean isNetworkAvailable(Context context) {
	    ConnectivityManager connectivityManager 
	         = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
	}
}
