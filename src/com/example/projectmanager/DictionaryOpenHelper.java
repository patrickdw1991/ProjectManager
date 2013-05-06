package com.example.projectmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DictionaryOpenHelper extends SQLiteOpenHelper {

	  public static final String TABLE_PROJECTS = "projects2";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_DESCRIPTION = "description";

	  private static final String DATABASE_NAME = "projects2.db";
	  private static final int DATABASE_VERSION = 2;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_PROJECTS + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_NAME
	      + " text not null, "+ COLUMN_DESCRIPTION +" text not null);";

	  public DictionaryOpenHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DictionaryOpenHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
	    onCreate(db);
	  }

	} 