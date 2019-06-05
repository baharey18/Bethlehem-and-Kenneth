package com.goreserved.reserved;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the Sign up page for new users
 * Author Bethlehem Teshome
 * 5/31/2019
 */
public class SignUpActivity extends AppCompatActivity {
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private Button Next;
    private TextView BackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeUIViews();
        // if(validate()){
        //upload data to database
        // }

        Next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                enterCarAndAddress();
            }
        });

        BackToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });
    }

    /**
     *
     */
    public void enterCarAndAddress(){

        Intent intent = new Intent(SignUpActivity.this, CarAndResidenceInfo.class);
        startActivity(intent);

    }
    /**
     *
     */
    private void initializeUIViews(){
        FirstName = (EditText)findViewById(R.id.editText_firstName);
        LastName = (EditText)findViewById(R.id.editText_lastName);
        Email = (EditText)findViewById(R.id.editText_email);
        Password = (EditText)findViewById(R.id.editText_password);
        Next = (Button)findViewById(R.id.button_next);
        BackToLogin = (TextView)findViewById(R.id.textView_backToLogin);
    }
    /**
     *
     */
    private boolean validate(){
        boolean allInfoEntered = false;
        String firstName = FirstName.getText().toString();
        String lastName = LastName.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter all details!",Toast.LENGTH_SHORT).show();
        }
        else{
            allInfoEntered = true;
        }
        return allInfoEntered;
    }


}
