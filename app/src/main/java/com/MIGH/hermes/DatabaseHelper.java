package com.MIGH.hermes;

import com.readystatesoftware.android.sqliteassethelper.*;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/*
 * BREAK IN CASE OF EMERGENCY
 * This file isn't used, but in the event that extending the AssetHelper
 * breaks my database, ill come start afresh here in this code
 */

public class DatabaseHelper extends SQLiteAssetHelper {
	
	private static final String DATABASE_FILE_NAME = "TESTCompanyDatabaseFile.db";
	private static final int DATABASE_VERSION =1;
	

	public DatabaseHelper(Context context) {
		
		super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
//	public Cursor getEmployees() {
//
//		SQLiteDatabase db = getReadableDatabase();
//
//		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//		String [] sqlSelect = {"0 _id", "FirstName", "LastName"}; 
//		String sqlTables = "Employees";
//		
//
//		qb.setTables(sqlTables);
//		Cursor c = qb.query(db, sqlSelect, null, null,
//				null, null, null);
//
//		c.moveToFirst();
//		return c;
//
//	}
	
	

}
