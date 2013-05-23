package com.example.projectmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DictionaryOpenHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "projects.db";
    private static final int DATABASE_VERSION = 7;

    // Database creation sql statement
    private static final String DATABASE_CREATE_PROJECTS = "create table "
            + project.TABLE_PROJECTS + "(" + project.COLUMN_ID
            + " integer primary key autoincrement, " + project.COLUMN_NAME
            + " text not null, " + project.COLUMN_DESCRIPTION + " text, " + project.COLUMN_SDESCRIPTION + " text, " + project.COLUMN_DATE + " bigint not null);";

    private static final String DATABASE_CREATE_PERSONS = "create table "
            + person.TABLE_PERSONS + "(" + person.COLUMN_ID
            + " integer primary key autoincrement, " + person.COLUMN_NAME
            + " text not null, " + person.COLUMN_EMAIL + " text not null, " + person.COLUMN_ADDRESS + " text, " + person.COLUMN_PHONE + " text, " + person.COLUMN_SPECIALIZATION + " text);";

    private static final String DATABASE_CREATE_TASKS = "create table "
            + tasks.TABLE_TASKS + "(" + tasks.COLUMN_ID
            + " integer primary key autoincrement, " + tasks.COLUMN_TIMESPEND
            + " int, " + tasks.COLUMN_GROUPID + " int not null, " + tasks.COLUMN_PERSONID+ " int);";

    private static final String DATABASE_CREATE_MILESTONES = "create table "
            + milestones.TABLE_MILESTONES + "(" + milestones.COLUMN_ID
            + " integer primary key autoincrement, " + milestones.COLUMN_NAME
            + " text not null, " + milestones.COLUMN_DATE + " bigint not null, " + milestones.COLUMN_PROJECTID + " int not null);";

    private static final String DATABASE_CREATE_TEAM = "create table "
            + team.TABLE_TEAM + "(" + team.COLUMN_ID
            + " integer primary key autoincrement, " + team.COLUMN_NAME
            + " text not null);";

    private static final String DATABASE_CREATE_TASKGROUP = "create table "
            + taskgroup.TABLE_TASKGROUP + "(" + taskgroup.COLUMN_ID
            + " integer primary key autoincrement, " + taskgroup.COLUMN_NAME
            + " text not null, " + taskgroup.COLUMN_ESTTIME + " int not null, " + taskgroup.COLUMN_PROJECTID + " int not null, " + taskgroup.COLUMN_STARTDATE + " bigint not null);";

    private static final String DATABASE_CREATE_PERSONTEAM = "create table "
            + personteam.TABLE_PERSONTEAM + "(" + personteam.COLUMN_ID
            + " integer primary key autoincrement, " + personteam.COLUMN_PERSONID
            + " int not null, " + personteam.COLUMN_TEAMID + " int not null);";

    public DictionaryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_PROJECTS);
        database.execSQL(DATABASE_CREATE_PERSONS);
        database.execSQL(DATABASE_CREATE_TASKS);
        database.execSQL(DATABASE_CREATE_MILESTONES);
        database.execSQL(DATABASE_CREATE_TEAM);
        database.execSQL(DATABASE_CREATE_TASKGROUP);
        database.execSQL(DATABASE_CREATE_PERSONTEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DictionaryOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + project.TABLE_PROJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + person.TABLE_PERSONS);
        db.execSQL("DROP TABLE IF EXISTS " + tasks.TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + taskgroup.TABLE_TASKGROUP);
        db.execSQL("DROP TABLE IF EXISTS " + milestones.TABLE_MILESTONES);
        db.execSQL("DROP TABLE IF EXISTS " + team.TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + personteam.TABLE_PERSONTEAM);
        onCreate(db);
    }

    public class project {
        public static final String TABLE_PROJECTS = "projects";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_SDESCRIPTION = "sdescription";
        public static final String COLUMN_DATE = "date";
    }

    public class person {
        public static final String TABLE_PERSONS = "persons";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_SPECIALIZATION = "specialization";
    }

    public class tasks {
        public static final String TABLE_TASKS = "tasks";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_GROUPID = "groupid";
        public static final String COLUMN_TIMESPEND = "timespend";
        public static final String COLUMN_PERSONID = "personid";
    }

    public class milestones {
        public static final String TABLE_MILESTONES = "milestones";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PROJECTID = "projectid";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_NAME = "name";
    }

    public class team {
        public static final String TABLE_TEAM = "team";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
    }

    public class taskgroup {
        public static final String TABLE_TASKGROUP = "taskgroup";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_ESTTIME = "esttime";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_STARTDATE = "startdate";
        public static final String COLUMN_PROJECTID = "projectid";

    }

    public class personteam {
        public static final String TABLE_PERSONTEAM = "personteam";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TEAMID = "teamid";
        public static final String COLUMN_PERSONID = "personid";

    }
}