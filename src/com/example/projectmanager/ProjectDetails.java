package com.example.projectmanager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class ProjectDetails extends Activity {
	
	private ProjectDataSource datasource;
	private Project project;
	private Intent intent;
    TextView t1 = null;
    ActionBar actionBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new ProjectDataSource(this);
		project = (Project)getIntent().getSerializableExtra("Project");
		setContentView(R.layout.activity_project_details);
        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(project.getProject());
		System.out.println(project.getDescription());
		t1 = (TextView)findViewById(R.id.textView1);
		t1.setText(project.getDescription());

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
                return;
            	            }
        });
        
	}

    @Override
    protected void onResume() {
        super.onResume();
        datasource.open();
        t1.setText(datasource.getOneProject(project).getDescription());
        actionBar.setTitle(datasource.getOneProject(project).getProject());
        datasource.close();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        finish();
        return true;
    }
}
