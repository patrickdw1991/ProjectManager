package com.example.projectmanager.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.projectmanager.objects.Project;
import com.example.projectmanager.activities.ProjectDetails;
import com.example.projectmanager.adapters.ProjectAdapter;
import com.example.projectmanager.sql.ProjectDataSource;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends ListFragment {

	List<Project> values = new ArrayList<Project>();
	private ProjectDataSource datasource;
    ProjectAdapter adapterz = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource = new ProjectDataSource(getActivity());
	    datasource.open();
		values = datasource.getAllProjects();
		datasource.close();
        adapterz = new ProjectAdapter(getActivity(),values);
        this.setListAdapter(adapterz);
        return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
        datasource.open();
        Project project = values.get(position);
		datasource.close();
		Intent intent = new Intent(getActivity(), ProjectDetails.class);
		intent.putExtra("Project", (Serializable)project);
	    startActivity(intent);
	}

    public void changingAllTheStuff(){
        System.out.println("tester2");
       if(datasource != null){
           System.out.println("tester3");
        datasource.open();
        ArrayList<Project> items = datasource.getAllProjects();
        datasource.close();
        values.clear();
        values.addAll(items);
        adapterz.notifyDataSetChanged();
       }
    }
}
