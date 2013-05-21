package com.example.projectmanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.example.projectmanager.ChangeProject.getDate;

/**
 * Created by Patrick on 18-5-13.
 */
public class ProjectAdapter extends ArrayAdapter {

    Activity activity;
    List data;

    public ProjectAdapter(Activity activity, List objects) {
        super(activity, R.layout.list_item2 , objects);
        this.activity = activity;
        this.data=objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProjectHolder holder = null;

        if(row == null)
        {
            System.out.println("hallooooooooooooooooooooooooooooooooow");
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.list_item2, null);

            holder = new ProjectHolder();
            holder.label = (TextView)row.findViewById(R.id.label);
            holder.sDescription = (TextView)row.findViewById(R.id.artist);
            holder.date = (TextView)row.findViewById(R.id.duration);

            row.setTag(holder);
        }
        else
        {
            holder = (ProjectHolder)row.getTag();
        }

        Project projectItem = (Project)data.get(position);

        holder.label.setText(projectItem.getProject());
        holder.sDescription.setText(projectItem.getsDescription());
        holder.date.setText(getDate(projectItem.getDate(), "dd/MM/yyyy"));

        return row;
    }

    static class ProjectHolder
    {
        TextView label;
        TextView sDescription;
        TextView date;
    }

}
