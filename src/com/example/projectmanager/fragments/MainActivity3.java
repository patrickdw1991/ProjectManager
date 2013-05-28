package com.example.projectmanager.fragments;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.projectmanager.objects.Person;
import com.example.projectmanager.activities.ProjectDetails;
import com.example.projectmanager.adapters.ProjectAdapter;
import com.example.projectmanager.sql.PersonDataSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends ListFragment {

	List<Person> values = new ArrayList<Person>();
	private PersonDataSource datasource;
    ProjectAdapter adapterz = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		datasource = new PersonDataSource(getActivity());
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
        Person project = values.get(position);
		datasource.close();
		Intent intent = new Intent(getActivity(), ProjectDetails.class);
		intent.putExtra("Project", (Serializable)project);
	    startActivity(intent);
	}

    public void changingAllTheStuff(){
        datasource.open();
        ArrayList<Person> items = datasource.getAllProjects();
        datasource.close();
        values.clear();
        values.addAll(items);
        adapterz.notifyDataSetChanged();
    }


}
