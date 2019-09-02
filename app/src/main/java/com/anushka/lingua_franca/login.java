package com.anushka.lingua_franca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class login extends Activity {
    Button b1, b2;
    EditText inputEmail, inputPassword;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        auth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        b1 = (Button) findViewById(R.id.b1);
        inputEmail=(EditText)findViewById(R.id.e1);
        inputPassword=(EditText)findViewById(R.id.e2);
        forgot=(TextView)findViewById(R.id.forgotpasswor);
        forgot.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
              Intent i=new Intent(getApplicationContext(),forgotPassword.class);
              startActivity(i);
                //OnCLick Stuff
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                 progressBar.setVisibility(View.VISIBLE);
                auth = FirebaseAuth.getInstance();
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(login.this, nav.class);
                                    intent.putExtra("email",email);
                                    //intent.putExtra("name",auth.getCurrentUser().getEmail());
                                    Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                }
                            }
//               Intent i = new Intent(getApplicationContext(), login.class);
//               startActivity(i);
                        });
            }}
        });
    }
}


