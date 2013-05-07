package com.example.projectmanager;

import java.io.Serializable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProjectDetails extends Activity {
	
	private ProjectDataSource datasource;
	private Project project;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new ProjectDataSource(this);
		project = (Project)getIntent().getSerializableExtra("Project");
		setContentView(R.layout.activity_project_details);
		System.out.println(project.getDescription());
		TextView t1 = (TextView)findViewById(R.id.textView1);
		TextView t2 = (TextView)findViewById(R.id.textView2);
		t1.setText(project.getProject());
		t2.setText(project.getDescription());
		
		final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	datasource.open();
            	datasource.deleteProject(project);
            	datasource.close();
                finish();
                return;
            	            }
        });
        intent = new Intent(this, ChangeProject.class);
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
        		intent.putExtra("Project", (Serializable)project);
        	    startActivity(intent);
                finish();
                return;
            	            }
        });
        
	}

}
