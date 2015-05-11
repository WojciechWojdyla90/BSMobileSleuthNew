package com.Biosys.ListViewsDatas;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.Biosys.DaoClasses.User;
import com.Biosys.bsmobilesleuth.R;

public class UserListData {
	
	private User _user;
	private Context _context;
	
	public UserListData(Context context, User user){
		_user = user;
		_context = context;
	}
	
	public Drawable getIcon(){
		return _context.getResources().getDrawable( R.drawable.ic_launcher);
	}
	
	
	public String getTitle(){
		return _user.getName() + " " + _user.getSurrname();
	}
	
	public String getDescription(){
		return "Email : " + _user.getEmail();
	}
}
