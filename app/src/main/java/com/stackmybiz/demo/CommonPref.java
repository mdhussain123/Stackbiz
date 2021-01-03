package com.stackmybiz.demo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



/**
 * Created by CKS on 15/06/2018.
 */
public class CommonPref {


	CommonPref() {

	}






    public static void setUserDetails(Activity activity,UserData userData) {
		String key = "_USER_DETAILS";

		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);

		Editor editor = prefs.edit();

			editor.putString("username", userData.getName());
			editor.putString("mobile",userData.getMobile());
			editor.putString("email", userData.getEmail());

		editor.commit();
    }

    public static UserData getUserDetails(Activity activity){
		String key = "_USER_DETAILS";
		UserData userData=new UserData();
		SharedPreferences prefs = activity.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		userData.setName(prefs.getString("username", ""));
		userData.setMobile(prefs.getString("mobile", ""));
		userData.setEmail(prefs.getString("email", ""));
		return userData;
	}


}
