package com.example.projectmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

	List<Project> values = new ArrayList<Project>();
	private ProjectDataSource datasource;
    ProjectAdapter adapterz = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new ProjectDataSource(this);
	    datasource.open();
		values = datasource.getAllProjects();
		datasource.close();
        adapterz = new ProjectAdapter(this,values);
        this.setListAdapter(adapterz);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
        datasource.open();
        Project project = values.get(position);
		datasource.close();
		Intent intent = new Intent(this, ProjectDetails.class);
		intent.putExtra("Project", (Serializable)project);
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.add:
			datasource.open();
			Intent intent = new Intent(this, AddProject.class);
		    startActivity(intent);
            			datasource.close();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
        super.onResume();
		datasource.open();
		changingAllTheStuff(datasource.getAllProjects());
		datasource.close();

	}

    public void changingAllTheStuff(ArrayList<Project> items){
        values.clear();
        values.addAll(items);
        adapterz.notifyDataSetChanged();
    }
}
