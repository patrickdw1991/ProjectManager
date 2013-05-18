package com.example.projectmanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;

public class AddProject extends Activity {

	ProjectDataSource datasource = new ProjectDataSource(this);
    Calendar dateTime=Calendar.getInstance();
    DateFormat formatDateTime= DateFormat.getDateInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_project2);
		
		final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	String project = ((EditText) findViewById(R.id.editText1)).getText().toString();
            	String description = ((EditText) findViewById(R.id.editText2)).getText().toString();
            	System.out.println("title: "+project + " Description: "+description);
            	if(!project.matches("")&&!description.matches("")){
            		datasource.open();
                datasource.createProject(project, description);
                datasource.close();
                finish();
                return;
            	}
            }
        });
        
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                return;
            }
        });
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
            System.out.println(dateTime.getTime().toString());
            final Button b3 = (Button) findViewById(R.id.button3);
            b3.setText(formatDateTime.format(dateTime.getTime()));
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
    }

}
