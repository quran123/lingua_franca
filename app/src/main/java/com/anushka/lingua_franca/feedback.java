package com.anushka.lingua_franca;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.anushka.lingua_franca.Getter.registergetset;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class feedback extends Activity {

    EditText data;
    RadioGroup type;
    Button send;
    RadioButton typeselected;
    private static final String TAG = feedback.class.getSimpleName();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    FirebaseAuth auth;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        auth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReferenceFromUrl("https://linguafranca-71b67.firebaseio.com/").child("User");

        data = (EditText) findViewById(R.id.data);
        type=(RadioGroup)findViewById(R.id.type);
        send = (Button) findViewById(R.id.send);
        //data.setText("");
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("User");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        // Save / update the user
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId=type.getCheckedRadioButtonId();
                typeselected=(RadioButton)findViewById(selectedId);
               // Toast.makeText(MainActivity.this,radioSexButton.getText(),Toast.LENGTH_SHORT).show();
                if(!(data.getText().toString().equals("")) &&selectedId>0)
                {String data1 = data.getText().toString();
                String type1 = typeselected.getText().toString();
                Toast.makeText(feedback.this, "Feedback send", Toast.LENGTH_SHORT).show();
                data.setText("");
                    Log.e(TAG, "onClick:1 "+auth.getCurrentUser().getUid() );
               // userId=mFirebaseDatabase.push().getKey();
                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    //send1(data1,type1);
                    Log.e(TAG, "onClick: "+userId );
//                    createUser(data1, type1);
                }}
                else
                    Toast.makeText(getApplicationContext(),"Empty Field",Toast.LENGTH_LONG).show();
            }
        });

        //toggleButton();
    }

//    // Changing button text
//    private void toggleButton() {
//        if (TextUtils.isEmpty(userId)) {
//            send.setText("Save");
//        } else {
//            send.setText("Update");
//        }
//    }

    /**
     * Creating new user node under 'users'
     */
    private void send1(final String data1,final String type1)
    {
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) throws DatabaseException {
                Random rand = new Random();
                //int value = rand.nextInt(50);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot dsw : ds.getChildren()) {
                        // for(int i=1;i<=1;i++){
                        feedbackdata user = ds.getValue(feedbackdata.class);
                        registergetset user2=ds.getValue(registergetset.class);
                        if (auth.getCurrentUser().getEmail().equals(user2.email)) {
                            Log.e(TAG, "onDataChange: " + auth.getCurrentUser().getEmail());
                            userId = mFirebaseDatabase.push().getKey();
                            String userId1 = (String.valueOf(rand.nextInt(100000000)));
//                        mFirebaseDatabase.child("data").setValue(data1);
                            mFirebaseDatabase.child("feedbackdata").child(userId).child("data").setValue(data1);
                            mFirebaseDatabase.child("feedbackdata").child(userId).child("type").setValue(type1);

                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void createUser(String data1, String type1) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();

        }

        registergetset user = new registergetset(data1, type1);
        //mFirebaseDatabase.child('user').child("feedbackdata").child(userId).child("data").setValue(data1);
        mFirebaseDatabase.child("feedbackdata").child(userId).child("type").setValue(type1);
        mFirebaseDatabase.child("feedbackdata").child(userId).child("type").setValue(type1);
        addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                registergetset user = dataSnapshot.getValue(registergetset.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.data + ", " + user.type);


                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String data, String type) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(data))
            mFirebaseDatabase.child("User").child(userId).child("feedbackdata").child(userId).child("data").setValue(data);

        if (!TextUtils.isEmpty(type))
            mFirebaseDatabase.child("User").child(userId).child("feedbackdata").child(userId).child("type").setValue(type);
    }
}