package com.Biosys.Controlers;

import java.util.List;

import com.Biosys.DaoClasses.Positions;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class PositionsDaoControler {
	public static String GetPositionName(int id, RuntimeExceptionDao<Positions, Integer> userDao){
		
		List<Positions> positionsList = userDao.queryForAll();
    	
    	for(Positions position :positionsList)
    	{
    		if(position.getId() == id){
    			return position.getPositionName();
    		}
    	}
		
		return "";
	}
}
