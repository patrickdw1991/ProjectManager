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
import com.example.projectmanager.adapters.PersonAdapter;
import com.example.projectmanager.sql.PersonDataSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends ListFragment {

	List<Person> values = new ArrayList<Person>();
	private PersonDataSource datasource;
    //
    PersonAdapter adapterz = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		datasource = new PersonDataSource(getActivity());
	    datasource.open();
        datasource.createPerson("name","email","adress","phone","spec");
		values = datasource.getAllPersons();
		datasource.close();
        //
        adapterz = new PersonAdapter(getActivity(),values);
        this.setListAdapter(adapterz);
        return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
        datasource.open();
        Person person = values.get(position);
		datasource.close();
        //
		Intent intent = new Intent(getActivity(), ProjectDetails.class);
		intent.putExtra("Person", (Serializable)person);
	    startActivity(intent);
	}

    public void changingAllTheStuff(){
        datasource.open();
        ArrayList<Person> items = datasource.getAllPersons();
        datasource.close();
        values.clear();
        values.addAll(items);
        adapterz.notifyDataSetChanged();
    }


}
