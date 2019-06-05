package com.goreserved.reserved;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * This is the first page of the Reserved App
 * Author Bethlehem Teshome
 * 5/31/2019
 */
public class MainActivity extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    private EditText EmailET;//ET is for edit text
    private EditText PasswordET;
    private Button SignIn;
    private int counter = 5;
    private TextView numOfAttempts;
    private TextView NewUserSignUp;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intializeUIViews();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }

        });

        numOfAttempts.setText("Attempts remaining: 5");


        SignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(EmailET.getText().toString(), PasswordET.getText().toString());
            }
        });


        NewUserSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
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
    private void intializeUIViews(){
        EmailET = (EditText)findViewById(R.id.editText_email);
        PasswordET = (EditText)findViewById(R.id.editText_password);
        SignIn = (Button)findViewById(R.id.button_signIn);
        numOfAttempts = (TextView)findViewById(R.id.textView_NumOfAttempts);
        NewUserSignUp = (TextView)findViewById(R.id.textView_newUser);
        signInButton = findViewById(R.id.sign_in_button);

    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
      super.onActivityResult(requestCode, resultCode, data);

      if(requestCode == RC_SIGN_IN){
          Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
          handleSignInResult(task);
      }
    }

    /**
     *
     * @param completedTask
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
        catch(ApiException e){
            Log.w("Google Sign In Error", "signInResult:failed code="+e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
    /**
     *
     */
    protected void onStart(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
        super.onStart();
    }

}
