package com.example.projectmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProjectDataSource {
	// Database fields
	  private SQLiteDatabase database;
	  private DictionaryOpenHelper dbHelper;
	  private String[] allColumns = { DictionaryOpenHelper.project.COLUMN_ID,
	      DictionaryOpenHelper.project.COLUMN_NAME, DictionaryOpenHelper.project.COLUMN_DESCRIPTION, DictionaryOpenHelper.project.COLUMN_SDESCRIPTION, DictionaryOpenHelper.project.COLUMN_DATE };

	  public ProjectDataSource(Context context) {
	    dbHelper = new DictionaryOpenHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Project createProject(String project, String description, String sDescription, long date) {
	    ContentValues values = new ContentValues();
	    values.put(DictionaryOpenHelper.project.COLUMN_NAME, project);
	    values.put(DictionaryOpenHelper.project.COLUMN_DESCRIPTION, description);
        values.put(DictionaryOpenHelper.project.COLUMN_SDESCRIPTION, sDescription);
        values.put(DictionaryOpenHelper.project.COLUMN_DATE, date);
	    long insertId = database.insert(DictionaryOpenHelper.project.TABLE_PROJECTS, null,
	        values);
	    Cursor cursor = database.query(DictionaryOpenHelper.project.TABLE_PROJECTS,
	        allColumns, DictionaryOpenHelper.project.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Project newProject = cursorToProject(cursor);
	    cursor.close();
	    return newProject;
	  }

	  public void deleteProject(Project project) {
	    long id = project.getId();
	    System.out.println("Project deleted with id: " + id);
	    database.delete(DictionaryOpenHelper.project.TABLE_PROJECTS, DictionaryOpenHelper.project.COLUMN_ID
	        + " = " + id, null);
	  }

    public void changeProject(Project project, String name, String description, String sDescription, long date){
        long id = project.getId();
        ContentValues cv = new ContentValues();
        cv.put(DictionaryOpenHelper.project.COLUMN_NAME,name);
        cv.put(DictionaryOpenHelper.project.COLUMN_DESCRIPTION,description);
        cv.put(DictionaryOpenHelper.project.COLUMN_SDESCRIPTION, sDescription);
        cv.put(DictionaryOpenHelper.project.COLUMN_DATE, date);
        database.update(DictionaryOpenHelper.project.TABLE_PROJECTS,cv,DictionaryOpenHelper.project.COLUMN_ID+" = "+id,null);
}

	  public ArrayList<Project> getAllProjects() {
	    ArrayList<Project> projects = new ArrayList<Project>();

	    Cursor cursor = database.query(DictionaryOpenHelper.project.TABLE_PROJECTS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Project project = cursorToProject(cursor);
	      projects.add(project);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return projects;
	  }

    public Project getOneProject(Project projectOld){
        long id = projectOld.getId();
        Cursor cursor = database.query(DictionaryOpenHelper.project.TABLE_PROJECTS,
                allColumns, DictionaryOpenHelper.project.COLUMN_ID+" = "+id, null, null, null, null);

        cursor.moveToFirst();
            Project project = cursorToProject(cursor);
        cursor.close();
        return project;
    }

	  private Project cursorToProject(Cursor cursor) {
	    Project project = new Project();
	    project.setId(cursor.getLong(0));
	    project.setProject(cursor.getString(1));
	    project.setDescription(cursor.getString(2));
        project.setsDescription(cursor.getString(3));
        project.setDate(cursor.getLong(4));
	    return project;
	  }

}