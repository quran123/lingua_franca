package com.anushka.lingua_franca;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anushka.lingua_franca.Getter.registergetset;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class registerActivity extends FragmentActivity {
    String name,branch,email,password;
    int year;
    long contact;
    private static final String TAG = registerActivity.class.getSimpleName();
    public static final int RequestSignInCode = 7;
    private ProgressDialog mProgressDialog;
    private GoogleApiClient mGoogleApiClient;
    FirebaseAuth auth;
    public GoogleApiClient googleApiClient;
    private String userId;
    EditText e1, e2, e3, e4, e5, e6,e7;
    Button b1;
    com.google.android.gms.common.SignInButton signInButton;
    private ProgressBar progressBar;
    private DatabaseReference mFirebaseDatabase;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseDatabase mFirebaseInstance;
    private static final int RC_SIGN_IN = 234;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data);
        auth = FirebaseAuth.getInstance();
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        e6 = (EditText) findViewById(R.id.e6);
        e7=(EditText)findViewById(R.id.e7);
        //signInButton = (com.google.android.gms.common.SignInButton) findViewById(R.id.btn_sign_in);
        b1 = (Button) findViewById(R.id.b1);
        mFirebaseDatabase = mFirebaseInstance.getInstance().getReferenceFromUrl("https://linguafranca-71b67.firebaseio.com/").child("User");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//                //createUser();
//                //firebaseAuthWithGoogle(auth.);
//
//            }
//        });
        // store app title to 'app_title' node
        mFirebaseInstance.getInstance().getReference("app_title").setValue("Lingua Franca");

        // app_title change listener
        mFirebaseInstance.getInstance().getReference("app_title").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      if(TextUtils.isEmpty(e1.getText())||TextUtils.isEmpty(e2.getText())||TextUtils.isEmpty(e3.getText())||TextUtils.isEmpty(e4.getText())||TextUtils.isEmpty(e5.getText())||TextUtils.isEmpty(e6.getText()))
                                          Toast.makeText(getApplicationContext(),"Empty field",Toast.LENGTH_LONG).show();
                                      else
                                      {
                                      year = 0;
                                      contact = 0;
                                      name = e1.getText().toString();
                                      year = Integer.parseInt(String.valueOf(e3.getText()));
                                      branch = e2.getText().toString();
                                      contact = Long.parseLong(String.valueOf(e4.getText()));
                                      email = e5.getText().toString();
                                      password = e6.getText().toString();
                                      // Check for already existed userId
                                      if (TextUtils.isEmpty(email)) {
                                          Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                                          return;
                                      }

                                      if (TextUtils.isEmpty(password)) {
                                          Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                                          return;
                                      }

                                      if (password.length() < 6) {
                                          Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_LONG).show();
                                          return;
                                      }
                                      if(e6.getText().toString().equals(e7.getText().toString())){
                                      //create user
                                      auth.createUserWithEmailAndPassword(email, password)
                                              .addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                                                  @Override
                                                  public void onComplete(@NonNull Task<AuthResult> task) {
                                                      Toast.makeText(registerActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_LONG).show();
                                                      // If sign in fails, display a message to the user. If sign in succeeds
                                                      // the auth state listener will be notified and logic to handle the
                                                      // signed in user can be handled in the listener.
                                                      if (!task.isSuccessful()) {

                                                          Toast.makeText(registerActivity.this, "Authentication failed." + task.getException().getLocalizedMessage(),
                                                                  Toast.LENGTH_LONG).show();
                                                      } else {
                                                          createUser(userId,name, branch, year, contact, email, password);
                                                          Toast.makeText(registerActivity.this, "Profile Created", Toast.LENGTH_SHORT).show();

                                                      }
                                                  }
                                              });}
                                      else{Log.e(TAG, "onClick: "+e6.getText()+""+e7.getText() );
                                          Toast.makeText(getApplicationContext(),"Password and confirm password do not match",Toast.LENGTH_LONG).show();
                                  }}
                              }}
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.e(TAG, "onActivityResult: "+task.getException() );
                Toast.makeText(registerActivity.this, "signed in123", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(registerActivity.this,nav.class));
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential

    }

    private void createUser(String userid,String name, String branch, int year, long contact, String email, String password) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        registergetset user = new registergetset(userId,name, branch, year, contact, email, password);
        mFirebaseDatabase.child("User").child(userId).setValue(user);
//        mFirebaseDatabase.child(userId).child("userid").setValue(userId);
//        mFirebaseDatabase.child(userId).child("name").setValue(name);
//        mFirebaseDatabase.child(userId).child("branch").setValue(branch);
//        mFirebaseDatabase.child(userId).child("year").setValue(year);
//        mFirebaseDatabase.child(userId).child("contact").setValue(contact);
//        mFirebaseDatabase.child(userId).child("email").setValue(email);
//        mFirebaseDatabase.child(userId).child("password").setValue(password);
        Intent i = new Intent(registerActivity.this, login.class);
        i.putExtra("name",user.name);
        i.putExtra("email",user.email);
        startActivity(i);
      //  addUserChangeListener();
    }
//    private void createUser() {
//        // TODO
//        // In real apps this userId should be fetched
//        // by implementing firebase auth
//        if (TextUtils.isEmpty(userId)) {
//            userId = mFirebaseDatabase.push().getKey();
//        }
//
//        //registergetset user = new registergetset(name, branch, year, contact, email, password);
//
//        //mFirebaseDatabase.child(userId).setValue(user);
//
//    //    addUserChangeListener();
//    }

    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}