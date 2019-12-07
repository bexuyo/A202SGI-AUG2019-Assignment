package com.example.a202sgiassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private TextView dob;
    private EditText name,email, password, confirm,contact;
    private RadioGroup gender;
    private Button register;
    private UserListOpenHelper helper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.cPassword);
        contact = (EditText) findViewById(R.id.contact);
        dob = (TextView) findViewById(R.id.dob);
        gender = (RadioGroup) findViewById(R.id.gender);
        register = (Button) findViewById(R.id.register);

        helper = new UserListOpenHelper(this);
        user = new User();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registration())
                    finish();
            }
        });
    }

    public void setDate(int year, int month, int day)
    {
        String birth = day + " /" + month + " /" + year;
        dob.setText(birth);
    }

    public void dateOnClick(View view) {
        DateFragment df = new DateFragment();
        df.show(getSupportFragmentManager(), "Pick a date");
    }

    private boolean registration()
    {
        int selectedId = gender.getCheckedRadioButtonId();
        String gender;

        if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty() || confirm.getText().toString().isEmpty() ||
                selectedId == -1 || contact.getText().toString().isEmpty() ||
                dob.getText().toString().equals("Not Dated"))
        {
            Toast.makeText(Register.this, "Please fill in all the field", Toast.LENGTH_LONG).show();

            return false;
        }
        else if(password.getText().toString().equals(confirm.getText().toString()) &&
                !password.getText().toString().isEmpty())
        {
            if(selectedId == R.id.male)
                gender = "Male";
            else if(selectedId == R.id.female)
                gender = "Female";
            else
                gender = "Others";

            if(!helper.checkUser(email.getText().toString().trim()))
            {
                user.setName(name.getText().toString().trim());
                user.setEmail(email.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                user.setGender(gender);
                user.setContact(contact.getText().toString().trim());
                user.setDob(dob.getText().toString().trim());

                helper.addUser(user);

                Toast.makeText(Register.this, "Your registration is successful", Toast.LENGTH_LONG).show();

                return true;
            }
            else
            {
                String exist = "User with email " + email.getText().toString() + " already exist";
                Toast.makeText(Register.this, exist, Toast.LENGTH_LONG).show();

                return false;
            }
        }
        else
        {
            Toast.makeText(Register.this, "Your passwords are not same", Toast.LENGTH_LONG).show();

            return false;
        }
    }
}
