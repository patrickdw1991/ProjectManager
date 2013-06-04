package com.example.projectmanager.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.projectmanager.objects.Person;
import com.example.projectmanager.R;

import java.util.List;

import static com.example.projectmanager.activities.ChangeProject.getDate;

/**
 * Created by Patrick on 18-5-13.
 */
public class PersonAdapter extends ArrayAdapter {

    Activity activity;
    List data;

    public PersonAdapter(Activity activity, List objects) {
        super(activity, R.layout.list_item2 , objects);
        this.activity = activity;
        this.data=objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        personHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.list_item2, null);

            holder = new personHolder();
            holder.label = (TextView)row.findViewById(R.id.label);
            holder.sDescription = (TextView)row.findViewById(R.id.artist);
            holder.date = (TextView)row.findViewById(R.id.duration);

            row.setTag(holder);
        }
        else
        {
            holder = (personHolder)row.getTag();
        }

        Person personItem = (Person)data.get(position);

        holder.label.setText(personItem.getName());
        holder.sDescription.setText(personItem.getEmail());
        //holder.date.setText(getDate(projectItem.getDate(), "dd/MM/yyyy"));

        return row;
    }

    static class personHolder
    {
        TextView label;
        TextView sDescription;
        TextView date;
    }



}
