package com.example.a202sgiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private TextView gender;
    private TextView contact;
    private TextView dob;

    private UserListOpenHelper find;
    private User found;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = (TextView) findViewById(R.id.tvName);
        email = (TextView) findViewById(R.id.tvEmail);
        gender = (TextView) findViewById(R.id.tvGender);
        contact = (TextView) findViewById(R.id.tvContact);
        dob = (TextView) findViewById(R.id.tvDob);

        find = new UserListOpenHelper(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String getEmail = mSharedPreferences.getString(Login.EMAIL_KEY, "");

        found = find.findUser(getEmail);

        name.setText(found.getName());
        email.setText(found.getEmail());
        gender.setText(found.getGender());
        contact.setText(found.getContact());
        dob.setText(found.getDob());
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
                Intent i = new Intent(UserProfile.this, MainActivity.class);
                Thread.sleep(Toast.LENGTH_LONG);
                UserProfile.this.startActivity(i);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };
}
