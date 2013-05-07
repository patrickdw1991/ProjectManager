package com.example.projectmanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeProject extends Activity {
	
	private ProjectDataSource datasource;
	private Project project;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new ProjectDataSource(this);
		project = (Project)getIntent().getSerializableExtra("Project");
		setContentView(R.layout.activity_add_project);
		EditText et1 = (EditText)findViewById(R.id.editText1);
		EditText et2 = (EditText)findViewById(R.id.editText2);
		et1.setText(project.getProject());
		et2.setText(project.getDescription());
	}

}
