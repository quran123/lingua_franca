package com.anushka.lingua_franca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class register extends Activity {
    Button b1,b2;
    FirebaseAuth auth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),registerActivity.class);
                startActivity(i);
            }
        });
        auth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);

        //if the user is already signed in
        //we will close this activity
        //and take the user to com.anushka.lingua_franca.profile activity
        if (auth.getCurrentUser() != null) {
            finish();

            startActivity((new Intent(this, nav.class)).putExtra("email",auth.getCurrentUser().getEmail()));
        }
    }
}
