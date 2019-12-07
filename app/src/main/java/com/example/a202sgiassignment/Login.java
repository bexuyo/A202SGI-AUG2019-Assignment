package com.example.a202sgiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText email,password;
    private Button login_btn;
    private UserListOpenHelper helper;
    private SharedPreferences login;

    public static final String EMAIL_KEY = "com.example.a202sgiassignment.email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPass);
        login_btn = (Button) findViewById(R.id.login);

        helper = new UserListOpenHelper(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        login = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void login()
    {
        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
            Toast.makeText(Login.this, "Your username or password is empty", Toast.LENGTH_LONG).show();
        else
        {
            if(helper.checkUser(email.getText().toString().trim(), password.getText().toString().trim()))
            {
                String email = this.email.getText().toString();

                SharedPreferences.Editor editor = login.edit();
                editor.putString(EMAIL_KEY, email);

                editor.apply();

                Intent i = new Intent(this, EducationContent.class);
                startActivity(i);

                finish();
            }
            else
            {
                Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void tvRegister_onClick(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}
