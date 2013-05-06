package com.example.projectmanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ProjectDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project_details, menu);
		return true;
	}

}
