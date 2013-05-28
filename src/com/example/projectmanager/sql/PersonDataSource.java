package com.example.projectmanager.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.projectmanager.objects.Person;

import java.util.ArrayList;

public class PersonDataSource {
    // Database fields
    private SQLiteDatabase database;
    private DictionaryOpenHelper dbHelper;
    private String[] allColumns = { DictionaryOpenHelper.person.COLUMN_ID,
            DictionaryOpenHelper.person.COLUMN_NAME, DictionaryOpenHelper.person.COLUMN_EMAIL, DictionaryOpenHelper.person.COLUMN_ADDRESS, DictionaryOpenHelper.person.COLUMN_PHONE, DictionaryOpenHelper.person.COLUMN_SPECIALIZATION };

    public PersonDataSource(Context context) {
        dbHelper = new DictionaryOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Person createPerson(String name, String email, String address, String phone, String specialization) {
        ContentValues values = new ContentValues();
        values.put(DictionaryOpenHelper.person.COLUMN_NAME, name);
        values.put(DictionaryOpenHelper.person.COLUMN_EMAIL, email);
        values.put(DictionaryOpenHelper.person.COLUMN_ADDRESS, address);
        values.put(DictionaryOpenHelper.person.COLUMN_PHONE, phone);
        values.put(DictionaryOpenHelper.person.COLUMN_SPECIALIZATION, specialization);
        long insertId = database.insert(DictionaryOpenHelper.person.TABLE_PERSONS, null,
                values);
        Cursor cursor = database.query(DictionaryOpenHelper.person.TABLE_PERSONS,
                allColumns, DictionaryOpenHelper.person.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Person newPerson = cursorToPerson(cursor);
        cursor.close();
        return newPerson;
    }

    public void deletePerson(Person person) {
        long id = person.getId();
        System.out.println("Person deleted with id: " + id);
        database.delete(DictionaryOpenHelper.person.TABLE_PERSONS, DictionaryOpenHelper.person.COLUMN_ID
                + " = " + id, null);
    }

    public void changePerson(Person person, String name, String email, String address, String phone, String specialization){
        long id = person.getId();
        ContentValues cv = new ContentValues();
        cv.put(DictionaryOpenHelper.person.COLUMN_NAME,name);
        cv.put(DictionaryOpenHelper.person.COLUMN_EMAIL,email);
        cv.put(DictionaryOpenHelper.person.COLUMN_ADDRESS, address);
        cv.put(DictionaryOpenHelper.person.COLUMN_PHONE, phone);
        cv.put(DictionaryOpenHelper.person.COLUMN_SPECIALIZATION, specialization);
        database.update(DictionaryOpenHelper.person.TABLE_PERSONS,cv,DictionaryOpenHelper.person.COLUMN_ID+" = "+id,null);
    }

    public ArrayList<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<Person>();

        Cursor cursor = database.query(DictionaryOpenHelper.person.TABLE_PERSONS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Person person = cursorToPerson(cursor);
            persons.add(person);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return persons;
    }

    public Person getOnePerson(Person personOld){
        long id = personOld.getId();
        Cursor cursor = database.query(DictionaryOpenHelper.person.TABLE_PERSONS,
                allColumns, DictionaryOpenHelper.person.COLUMN_ID+" = "+id, null, null, null, null);

        cursor.moveToFirst();
        Person person = cursorToPerson(cursor);
        cursor.close();
        return person;
    }

    private Person cursorToPerson(Cursor cursor) {
        Person person = new Person();
        person.setId(cursor.getLong(0));
        person.setName(cursor.getString(1));
        person.setEmail(cursor.getString(2));
        person.setAddress(cursor.getString(3));
        person.setPhone(cursor.getString(4));
        person.setSpecialization(cursor.getString(5));
        return person;
    }

}