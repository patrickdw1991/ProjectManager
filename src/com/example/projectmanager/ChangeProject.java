package com.example.projectmanager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChangeProject extends Activity {
	
    Calendar dateTime=Calendar.getInstance();
    DateFormat formatDateTime= DateFormat.getDateInstance();
    ActionBar actionBar = null;
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            dateTime.set(Calendar.YEAR,year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            long dateim = dateTime.getTimeInMillis();
            System.out.println(getDate(dateim,"dd/MM/yyyy"));
            final Button b1 = (Button) findViewById(R.id.button1);
            b1.setText(getDate(dateim,"dd/MM/yyyy"));
        }
    };
	private ProjectDataSource datasource;
	private Project project;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new ProjectDataSource(this);
		project = (Project)getIntent().getSerializableExtra("Project");
		setContentView(R.layout.activity_add_project2);
		EditText et1 = (EditText)findViewById(R.id.editText1);
		EditText et2 = (EditText)findViewById(R.id.editText2);
        EditText et3 = (EditText)findViewById(R.id.editText3);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        datasource.open();
		et1.setText(datasource.getOneProject(project).getProject());
		et2.setText(datasource.getOneProject(project).getDescription());
        et3.setText(datasource.getOneProject(project).getsDescription());
        dateTime.setTimeInMillis(datasource.getOneProject(project).getDate());

        final Button b1 = (Button) findViewById(R.id.button1);
        b1.setBackgroundResource(android.R.drawable.btn_dropdown);
        b1.setText(getDate(datasource.getOneProject(project).getDate(),"dd/MM/yyyy"));
        datasource.close();


	}

    public void datePicker(View view){
        new DatePickerDialog(ChangeProject.this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.accept:
                String name = ((EditText) findViewById(R.id.editText1)).getText().toString();
                String description = ((EditText) findViewById(R.id.editText2)).getText().toString();
                String sDescription = ((EditText) findViewById(R.id.editText3)).getText().toString();
                long date = dateTime.getTimeInMillis();
                System.out.println("title: "+name + " Description: "+description);
                if(!name.matches("")&&!description.matches("")){
                    datasource.open();
                    datasource.changeProject(project, name, description, sDescription, date);
                    datasource.close();
                    finish();
                }
                return true;

            default:
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_change_project, menu);
        return true;
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }



}
