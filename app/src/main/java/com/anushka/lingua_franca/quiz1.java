package com.anushka.lingua_franca;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anushka.lingua_franca.Getter.registergetset;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static com.firebase.ui.auth.AuthUI.TAG;

public class quiz1 extends Activity  {
    String ans;
    TextView t1;
    RadioGroup r;
    RadioButton b1,b2,b3,b4;
    Button b;
    FirebaseAuth auth;
    String userId;
    FirebaseDatabase database;
    DatabaseReference ref;
    ChildEventListener mChildEventListener1;
    String questionnumber="a";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz1);
        t1 = (TextView) findViewById(R.id.question);
        r = (RadioGroup) findViewById(R.id.r);
        b1=(RadioButton)findViewById(R.id.r1);
        b2 = (RadioButton) findViewById(R.id.r2);
        b3 = (RadioButton) findViewById(R.id.r3);
        b4 = (RadioButton) findViewById(R.id.r4);
        //r=(RecyclerView)findViewById(R.id.answer);
        b = (Button) findViewById(R.id.b);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReferenceFromUrl("https://linguafranca-71b67.firebaseio.com/").child("quiz");
        //setdata();
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = r.getCheckedRadioButtonId();
                b1= (RadioButton) findViewById(selectedId);
                ans=b1.getText().toString();
                if(selectedId==-1){
                    Toast.makeText(getApplicationContext(),"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{

                    Log.e("as", "onClick: " );
                    try {
                        attachDatabaseReadListener();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //check();
                    Toast.makeText(getApplicationContext(),b1.getText(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public  void check()
    {
        ref.child("quiz").addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                quizhandler question = dataSnapshot.getValue(quizhandler.class);
              //  registergetset user=dataSnapshot.child("User").getValue(registergetset.class);
                Log.e(TAG, "onDataChange: " + question);
//                ref.child("quiz").child("").child("question").setValue("a");
//                ref.child("quiz").child("answer").setValue("option 2");
//                ref.child("quiz").child("number").setValue(questionnumber);
                Log.e(TAG, "onDataChange:5 "+questionnumber );
                Log.e(TAG, "onDataChange: 5"+question.question );
                Log.e(TAG, "onDataChange: 5"+question.answer );
                if((question.question.equals(t1.getText().toString()))&&(question.answer.equals(b1.getText()))) {
                    Log.e(TAG, "onDataChange: 4" );
//                        ref.child("user").child(userId).child("score").setValue(user.score++);
                    questionnumber = (char) (questionnumber.charAt(0) + 1) + "";
//                    char q=questionnumber.charAt(0);
//                        q=(char)(q+1);
//                        questionnumber=q+"";
                    Log.e(TAG, "onDataChange: " + questionnumber);
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        for (DataSnapshot dsw : dataSnapshot.getChildren()) {
                            quizhandler question1 = dsw.getValue(quizhandler.class);
                            if (questionnumber.equals(question.number)) {
                                t1.setText(question.question);
                                b1.setText(question.option1);
                                b2.setText(question.option2);
                                Log.e(TAG, "onDataChange: 3");
                                b3.setText(question.option3);
                                b4.setText(question.option4);
                            } else {
                                Intent i = new Intent(getApplicationContext(), score.class);
                                startActivity(i);
                            }
                            Log.e(TAG, "onDataChange: 2");
                        }
                    }}
                else {
                    Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });        }
    private void attachDatabaseReadListener() throws Exception{
        Log.e("as", "attachDatabaseReadListener: ");
        if (mChildEventListener1 == null) {
            Log.e("as", "attachDatabaseReadListener: 1");
            try {
                mChildEventListener1 = new ChildEventListener() {
                    private static final String TAG = "quiz1";
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) throws DatabaseException {
                        Log.e(TAG, "attachDatabaseReadListener: 2");
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            try {
                                quizhandler q = dataSnapshot.child("quiz").getValue(quizhandler.class);
                                if (q.question.equals(t1.getText().toString())) {
                                    if (q.answer.equals(ans)) {
                                        update();
                                        if (q.number.equals(questionnumber)) {
                                            t1.setText(q.question);
                                            b1.setText(q.option1);
                                            b2.setText(q.option2);
                                            b3.setText(q.option3);
                                            b4.setText(q.option4);
                                            Log.e("asd", "onChildAdded: " + q.number);
                                        } else
                                            startActivity(new Intent(getApplicationContext(), score.class));

                                    }
                                }


                            } catch (Exception e) {
                                Log.e("asd", "onChildAdded: " + e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.e(TAG, "attachDatabaseReadListener: 3");
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        Log.e(TAG, "attachDatabaseReadListener: 4");
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.e(TAG, "attachDatabaseReadListener: 5");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "attachDatabaseReadListener: 6");
                    }
                };
                ref.child("quiz").addChildEventListener(mChildEventListener1);
            }
            catch (Exception e) {
                Log.e("as", "attachDatabaseReadListener: " + e.getMessage());
            }
        }}
    public void update()
    {

        ref.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) throws DatabaseException{
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    try {
                        registergetset user=ds.getValue(registergetset.class);
                        if(auth.getCurrentUser().getEmail().equals(user.email)){
                            int s=user.score+1;
                            ref.child("" + user.userid).child("score").setValue(s);
                            questionnumber = (char) (questionnumber.charAt(0) + 1) + "";
                        }
                }
                catch (Exception e){
                    Log.e("asd", "onDataChange: "+e.getMessage() );
                }
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}
//
//    private void attachDatabaseReadListener() {
//        if (mChildEventListener == null) {
//            mChildEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(@Nullable DataSnapshot dataSnapshot, @Nullable String s) throws DatabaseException {
//                    try {
//                        quizhandler question = dataSnapshot.child("quiz").getValue(quizhandler.class);
//                        registergetset user=dataSnapshot.child("User").getValue(registergetset.class);
//                        Log.e("asd", "onDataChange: " + question);
////                ref.child("quiz").child("").child("question").setValue("a");
////                ref.child("quiz").child("answer").setValue("option 2");
////                ref.child("quiz").child("number").setValue(questionnumber);
//                        Log.e("asd", "onDataChange:5 "+questionnumber );
//                        Log.e("asd", "onDataChange: 5"+question.question );
//                        Log.e("asd", "onDataChange: 5"+question.answer );
//                        if((question.question.equals("a"))&&(question.answer.equals("option 2"))) {
//                            Log.e("asd", "onDataChange: 4" );
////                        ref.child("user").child(userId).child("score").setValue(user.score++);
//                            questionnumber = (char) (questionnumber.charAt(0) + 1) + "";
////                    char q=questionnumber.charAt(0);
////                        q=(char)(q+1);
////                        questionnumber=q+"";
//                            Log.e("asd", "onDataChange: " + questionnumber);
//                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                for (DataSnapshot dsw : dataSnapshot.getChildren()) {
//                                    quizhandler question1 = dsw.getValue(quizhandler.class);
//                                    if (questionnumber.equals(question.number)) {
//                                        t1.setText(question.question);
//                                        b1.setText(question.option1);
//                                        b2.setText(question.option2);
//                                        Log.e("asd", "onDataChange: 3");
//                                        b3.setText(question.option3);
//                                        b4.setText(question.option4);
//                                    } else {
//                                        Intent i = new Intent(getApplicationContext(), score.class);
//                                        startActivity(i);
//                                    }
//                                    Log.e("asd", "onDataChange: 2");
//                                }
//                            }}
//                        else {
//                            Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_LONG).show();
//                        }
//                    }catch (DatabaseException e){
//                        Log.e("asd", "onChildAdded: exp"+ e.toString());
//                    }
//                }@Override
//                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Log.e("asd", "onCancelled: "+databaseError.getDetails() );
//                }
//            };
//            ref.addChildEventListener(mChildEventListener);
//        }
//    }
    }
