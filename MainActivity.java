package com.goreserved.reserved;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText EmailOrPhoneET;//ET is for edit text
    private EditText PasswordET;
    private Button SignIn;
    private int counter = 5;
    private TextView numOfAttempts;
    private Button SignUp;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmailOrPhoneET = (EditText)findViewById(R.id.editText_emailOrPhone);
        PasswordET = (EditText)findViewById(R.id.editText_password);
        SignIn = (Button)findViewById(R.id.button_signIn);
        numOfAttempts = (TextView)findViewById(R.id.textView_NumOfAttempts);
        SignUp = (Button)findViewById(R.id.button_signUp);

        numOfAttempts.setText("Attempts remaining: 5");


        SignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(EmailOrPhoneET.getText().toString(), PasswordET.getText().toString());
            }
        });


        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goToSignUpPage();
            }
        });


    }

    /**
     *
     * @param userName
     * @param password
     */
    private void validate(String userName, String password){
        if((userName.equals("Admin")) && (password.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else{
            counter--;
            numOfAttempts.setText("Attempts remaining: " + String.valueOf(counter) );
            if(counter == 0){
                SignIn.setEnabled(false);
            }
        }
    }

    /**
     *
     */
    public void goToSignUpPage()
    {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        }

        }
