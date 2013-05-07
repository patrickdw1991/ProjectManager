package com.example.projectmanager;

import javax.sql.DataSource;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProject extends Activity {

	ProjectDataSource datasource = new ProjectDataSource(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_project);
		
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

}
