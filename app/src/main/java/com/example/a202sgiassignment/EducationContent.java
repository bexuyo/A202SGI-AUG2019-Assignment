package com.example.a202sgiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class EducationContent extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_content);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.math));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.science));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        EducationAdapter educationAdapter = new EducationAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(educationAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        switch (item.getItemId())
        {
            case R.id.menu_profile:
                if(mSharedPreferences.getString(Login.EMAIL_KEY, "").isEmpty())
                {
                    Intent i = new Intent(this, Login.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(this, UserProfile.class);
                    startActivity(i);
                }
                break;

            case R.id.logout:
                if(mSharedPreferences.getString(Login.EMAIL_KEY, "").isEmpty())
                    Toast.makeText(this, "You are not logged in", Toast.LENGTH_LONG).show();
                else
                {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.clear();
                    editor.apply();

                    Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show();
                    mThread.start();
                }
        }

        return true;
    }

    Thread mThread = new Thread()
    {
        @Override
        public void run() {
            try
            {
                Thread.sleep(Toast.LENGTH_LONG);
                EducationContent.this.finish();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };
}
