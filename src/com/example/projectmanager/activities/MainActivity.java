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
import com.example.projectmanager.sql.PersonDataSource;


public class MainActivity extends FragmentActivity {

    private PersonDataSource datasource;
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datasource = new PersonDataSource(this);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);

        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText("Fragment 1"), MainActivity2.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Fragment 2"), MainActivity3.class, null);

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
        MainActivity2 fragment =
                (MainActivity2) getSupportFragmentManager().findFragmentByTag(
                        "android:switcher:"+R.id.pager+":0");
        if(fragment != null)  // could be null if not instantiated yet
        {
            if(fragment.getView() != null)
            {
                // no need to call if fragment's onDestroyView()
                //has since been called.
                fragment.changingAllTheStuff(this); // do what updates are required
            }
        }


    }


}