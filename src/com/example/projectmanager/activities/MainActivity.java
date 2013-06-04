package com.example.projectmanager.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.projectmanager.fragments.MainActivity2;
import com.example.projectmanager.fragments.MainActivity3;
import com.example.projectmanager.R;
import com.example.projectmanager.adapters.TabsAdapter;
import com.example.projectmanager.sql.ProjectDataSource;


public class MainActivity extends FragmentActivity {

    private ProjectDataSource datasource;
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datasource = new ProjectDataSource(this);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);

        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText("Projects"), MainActivity2.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Persons"), MainActivity3.class, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
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
        MainActivity3 fragment =
                (MainActivity3) getSupportFragmentManager().findFragmentByTag(
                        "android:switcher:"+R.id.pager+":1");
        if(fragment != null)  // could be null if not instantiated yet
        {
            if(fragment.getView() != null)
            {
                // no need to call if fragment's onDestroyView()
                //has since been called.
                fragment.changingAllTheStuff(); // do what updates are required
            }
        }

        MainActivity2 fragment1 =
                (MainActivity2) getSupportFragmentManager().findFragmentByTag(
                        "android:switcher:"+R.id.pager+":0");
        if(fragment1 != null)  // could be null if not instantiated yet
        {
            if(fragment1.getView() != null)
            {
                // no need to call if fragment's onDestroyView()
                //has since been called.
                fragment1.changingAllTheStuff(); // do what updates are required
            }
        }


    }


}