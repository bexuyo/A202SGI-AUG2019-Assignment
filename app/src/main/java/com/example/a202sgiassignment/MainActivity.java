package com.example.a202sgiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button register, login;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
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
                    Toast.makeText(this, "You are not logged in", Toast.LENGTH_LONG).show();

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
                }
        }

        return true;
    }
}
