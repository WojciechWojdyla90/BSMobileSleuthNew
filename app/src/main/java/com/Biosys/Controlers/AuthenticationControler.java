package com.Biosys.Controlers;

import java.security.NoSuchAlgorithmException;
import java.util.List;




import com.Biosys.DaoClasses.User;
import com.Biosys.Utils.SecurityUtil;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class AuthenticationControler {

	private static SecurityUtil util;
	
	public static User GetUserInDatabaseByLogin(String login, String password, RuntimeExceptionDao<User, Integer> userDao) throws NoSuchAlgorithmException{
		
		util = new SecurityUtil("MD5");
		
    	List<User> userList = userDao.queryForAll();
    	
    	for(User user :userList)
    	{
    		String userLogin = user.getLogin().toString();
    		String userPassword = user.getPassword().toString();
    		String encryptPass = util.EncryptString(password);
    		
    		if(userLogin.equals(login) && userPassword.equals(encryptPass))
    			return user;
    	}
    	
    	return null;
	}
	
}
