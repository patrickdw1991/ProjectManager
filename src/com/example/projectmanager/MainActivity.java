package com.example.projectmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	ArrayAdapter<Project> arry = null;
	List<Project> values = new ArrayList<Project>();
	private ProjectDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		datasource = new ProjectDataSource(this);
		datasource.open();
		values = datasource.getAllProjects();
		// Binding resources Array to ListAdapter
		arry = new ArrayAdapter<Project>(this, R.layout.list_item, R.id.label,
				values);
		this.setListAdapter(arry);
		datasource.close();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		// super.onListItemClick(l, v, position, id);
		Project project = (Project) l.getItemAtPosition(position);

		Context context = getApplicationContext();
		CharSequence text = project.getDescription();;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		datasource.open();
		datasource.deleteProject(project);
		arry.remove(project);
		datasource.close();
		
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
			changingAllTheStuff(datasource.getAllProjects());
			datasource.close();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		datasource.open();
		changingAllTheStuff(datasource.getAllProjects());
		datasource.close();
		super.onResume();
	}

	
	public void changingAllTheStuff(ArrayList<Project> items){
		values.clear();
		values.addAll(items);
		arry.notifyDataSetChanged();
	}

}
