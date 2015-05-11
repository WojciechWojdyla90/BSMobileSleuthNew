package com.Biosys.LocalDatabaseComunication;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.Biosys.DaoClasses.Event;
import com.Biosys.DaoClasses.Positions;
import com.Biosys.DaoClasses.User;
import com.Biosys.Utils.SecurityUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper  {

	private static final String DATABASE_NAME = "mobileSleuthLocalDatabase.db";
	
	private static final int DATABASE_VERSION = 3;
	private SecurityUtil util;
	
	private Dao<User, Integer> userDao = null;
	private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;
	
	private Dao<Positions, Integer> positionDao = null;
	private RuntimeExceptionDao<Positions, Integer> positionRuntimeDao = null;
	
	private Dao<Event, Integer> eventDao = null;
	private RuntimeExceptionDao<Event, Integer> eventRuntimeDao = null;
	
	//private Dao<SimpleData, Integer> simpleDao = null;
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, com.Biosys.bsmobilesleuth.R.raw.ormlite_config);
		try {
			this.util = new SecurityUtil("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		instance = this;
	}
	
    @Override
    public void onCreate(SQLiteDatabase sqlLitedb, ConnectionSource connectionSource){
    	//create database code
    	try {
    		//users
    		Log.i(DatabaseHelper.class.getName(), "onCreate");
    		TableUtils.createTable(connectionSource, User.class);
    		
    		//positions
    		Log.i(DatabaseHelper.class.getName(), "onCreate");
    		TableUtils.createTable(connectionSource, Positions.class);
    		
    		Log.i(DatabaseHelper.class.getName(), "onCreate");
    		TableUtils.createTable(connectionSource, Event.class);
		} catch (SQLException  e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
    	
    	//insert administrator position
    	RuntimeExceptionDao<Positions, Integer> positionDao = getPositioneDataDao();
    	Positions adminPosition = new Positions("Pracownik");
    	positionDao.create(adminPosition);
    	
    	//insert employee position
    	Positions positionEmployeeDao = new Positions("Handlowiec");
    	positionDao.create(positionEmployeeDao);
    	
    	//insert tradesman position
    	Positions tradesmanPosition = new Positions("Manager");
    	positionDao.create(tradesmanPosition);
    	
    	//insert administrator to users
    	//RuntimeExceptionDao<User, Integer> userDao = getUserDataDao();
    	//User userPosition = new User("root", "root", "","admin" , -1, 1, util.EncryptString("admin"));
    	//userDao.create(userPosition);
    	
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase sqlLitedb, ConnectionSource connectionSource, int oldVersion, int newVersion) {         
    	try {
    		Log.i(DatabaseHelper.class.getName(), "onUpgrade");
    		TableUtils.dropTable(connectionSource, User.class, true);
    		TableUtils.dropTable(connectionSource, Positions.class, true);
    		TableUtils.dropTable(connectionSource, Event.class, true);
    		
    		// after we drop the old databases, we create the new ones
    		onCreate(sqlLitedb, connectionSource);
		} catch (SQLException  e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
    }
    
    public Dao<User, Integer> getUserDao(){
    	if(userDao == null){
    		try {
				userDao = getDao(User.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.i(DatabaseHelper.class.getName(), e.getMessage());
			}
    	}
    	return userDao;
    }
    
	public RuntimeExceptionDao<User, Integer> getUserDataDao() {
		if (userRuntimeDao == null) {
			userRuntimeDao = getRuntimeExceptionDao(User.class);
		}
		return userRuntimeDao;
	}
    
    public Dao<Positions, Integer> getPositionsDao(){
    	if(positionDao == null){
    		try {
    			positionDao = getDao(Positions.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.i(DatabaseHelper.class.getName(), e.getMessage());
			}
    	}
    	return positionDao;
    }
    
    public RuntimeExceptionDao<Positions, Integer> getPositioneDataDao() {
		if (positionRuntimeDao == null) {
			positionRuntimeDao = getRuntimeExceptionDao(Positions.class);
		}
		return positionRuntimeDao;
	}

    public Dao<Event, Integer> getEventDao(){
    	if(eventDao == null){
    		try {
    			eventDao = getDao(Event.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.i(DatabaseHelper.class.getName(), e.getMessage());
			}
    	}
    	return eventDao;
    }
    
    public RuntimeExceptionDao<Event, Integer> getEventDataDao() {
		if (eventRuntimeDao == null) {
			eventRuntimeDao = getRuntimeExceptionDao(Event.class);
		}
		return eventRuntimeDao;
	}
    
    
	@Override
	public void close() {
		super.close();
		userDao = null;
		userRuntimeDao = null;
		positionDao = null;
		positionRuntimeDao = null;
		eventDao = null;
		eventRuntimeDao = null;
	}

	private static DatabaseHelper instance;
	public static DatabaseHelper getDatabaseHelperInstance(){
		return instance;
	}

}
