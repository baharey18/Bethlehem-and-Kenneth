package com.goreserved.reserved;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirstName = (EditText)findViewById(R.id.editText_firstName);
        LastName = (EditText)findViewById(R.id.editText_lastName);
        Email = (EditText)findViewById(R.id.editText_email);
        Password = (EditText)findViewById(R.id.editText_password);
        Next = (Button)findViewById(R.id.button_next);


        Next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                enterCarAndAddress();
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


}
