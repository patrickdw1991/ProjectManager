package com.example.projectmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by Patrick on 23-5-13.
 */
public class main2 extends Activity {
    public int test;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //datasource.open();
        //changingAllTheStuff(datasource.getAllProjects());
        //datasource.close();

    }
}