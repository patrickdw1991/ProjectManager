package com.example.projectmanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddProject extends Activity {

	ProjectDataSource datasource = new ProjectDataSource(this);
    Calendar dateTime=Calendar.getInstance();
    DateFormat formatDateTime= DateFormat.getDateInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_project2);
    }

    public void datePicker(View view){
        new DatePickerDialog(AddProject.this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            dateTime.set(Calendar.YEAR,year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            long dateim = dateTime.getTimeInMillis();
            System.out.println(getDate(dateim, "dd/MM/yyyy"));
            final Button b1 = (Button) findViewById(R.id.button1);
            b1.setText(getDate(dateim, "dd/MM/yyyy"));
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.accept:
                String project = ((EditText) findViewById(R.id.editText1)).getText().toString();
                String description = ((EditText) findViewById(R.id.editText2)).getText().toString();
                String sDescription = ((EditText) findViewById(R.id.editText3)).getText().toString();
                long date = dateTime.getTimeInMillis();
                System.out.println("title: "+project + " Description: "+description);
                if(!project.matches("")&&!description.matches("")){
                    datasource.open();
                    datasource.createProject(project, description, sDescription, date);
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

    public String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
