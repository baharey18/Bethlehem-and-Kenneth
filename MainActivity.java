package com.goreserved.reserved; //there exists a package name
// that is the same as this one
// in google play so make sure
// to change the package name
// before putting it in google play

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

/**
 * This is the first page of the Reserved App
 * Author Bethlehem Teshome
 * 5/31/2019
 */
public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 1; // this can be any number you want 7117
    List<AuthUI.IdpConfig> providers;
    Button btn_sign_out;
    Button btn_calendar;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sign_out = (Button)findViewById(R.id.btn_sign_out);
        btn_calendar = (Button)findViewById(R.id.btn_calendar);


        btn_sign_out.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AuthUI.getInstance()
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_sign_out.setEnabled(false);
                                showSignInOptions();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        btn_calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });

        //Init provider
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),//email builder
                new AuthUI.IdpConfig.FacebookBuilder().build(),//facebook builder
                new AuthUI.IdpConfig.GoogleBuilder().build()//google builder
        );
        showSignInOptions();
    }
    /**
     *
     */
    private void showSignInOptions(){
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .build(),MY_REQUEST_CODE

        );

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
       super.onActivityResult(requestCode, resultCode, data);
       if(requestCode == MY_REQUEST_CODE){
           IdpResponse response = IdpResponse.fromResultIntent(data);
           if(resultCode == RESULT_OK){
               //Get user
               FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
               //show email on toast
               Toast.makeText(this, ""+user.getEmail(),Toast.LENGTH_SHORT).show();
               //set button signout
               btn_sign_out.setEnabled(true);
               btn_calendar.setEnabled(true);

           }
           else{
               Toast.makeText(this, ""+response.getError().getMessage(), Toast.LENGTH_SHORT).show();
           }
       }
    }


}
