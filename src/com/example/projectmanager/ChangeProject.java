package com.example.projectmanager;

import android.app.ActionBar;
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

public class ChangeProject extends Activity {
	
	private ProjectDataSource datasource;
	private Project project;
    Calendar dateTime=Calendar.getInstance();
    DateFormat formatDateTime= DateFormat.getDateInstance();
    ActionBar actionBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new ProjectDataSource(this);
		project = (Project)getIntent().getSerializableExtra("Project");
		setContentView(R.layout.activity_add_project2);
		EditText et1 = (EditText)findViewById(R.id.editText1);
		EditText et2 = (EditText)findViewById(R.id.editText2);
        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        datasource.open();
		et1.setText(datasource.getOneProject(project).getProject());
		et2.setText(datasource.getOneProject(project).getDescription());
        datasource.close();
        final Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.editText1)).getText().toString();
                String description = ((EditText) findViewById(R.id.editText2)).getText().toString();
                System.out.println("title: "+name + " Description: "+description);
                if(!name.matches("")&&!description.matches("")){
                    datasource.open();
                    datasource.changeProject(project, name, description);
                    datasource.close();
                   finish();
                return;
                }
            }
        });
        final Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                return;
            }
        });

        final Button b3 = (Button) findViewById(R.id.button3);
        b3.setBackgroundResource(android.R.drawable.btn_dropdown);



	}

    public void datePicker(View view){
        new DatePickerDialog(ChangeProject.this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
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
