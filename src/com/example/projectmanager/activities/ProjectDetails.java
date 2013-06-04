package com.example.projectmanager.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.projectmanager.objects.Person;
import com.example.projectmanager.R;
import com.example.projectmanager.sql.PersonDataSource;

import java.io.Serializable;

public class ProjectDetails extends Activity {
	
	private PersonDataSource datasource;
	private Person project;
	private Intent intent;
    TextView t1 = null;
    ActionBar actionBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new PersonDataSource(this);
		project = (Person)getIntent().getSerializableExtra("Project");
		setContentView(R.layout.activity_project_details);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(project.getProject());
		System.out.println(project.getDescription());
		t1 = (TextView)findViewById(R.id.textView1);
		t1.setText(project.getDescription());
        intent = new Intent(this, ChangeProject.class);

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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.edit:
                intent.putExtra("Project", (Serializable)project);
                startActivity(intent);
                return true;

            case R.id.discard:
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.pm)
                        .setTitle("Delete Project")
                        .setMessage("Are you sure you want to delete this project?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datasource.open();
                                datasource.deleteProject(project);
                                datasource.close();
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;

            default:
                finish();
                return true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.project_details, menu);
        return true;
    }

}
