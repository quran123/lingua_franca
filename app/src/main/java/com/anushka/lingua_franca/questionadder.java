package com.anushka.lingua_franca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class questionadder extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="asd" ;
    Button b;
    String id;
    EditText e1,e2,e3,e4,e5,e6;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference ref;
    String question,answer,questionnumber="a";
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionadder);
        b=(Button)findViewById(R.id.b);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);
        database=FirebaseDatabase.getInstance();
        ref=database.getReferenceFromUrl("https://linguafranca-71b67.firebaseio.com");
        auth=FirebaseAuth.getInstance();
        question=e1.getText().toString();
        answer=e2.getText().toString();
        b.setOnClickListener(this);


    }
    private void addUserChangeListener() {
        // User data change listener
        ref.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                question user = dataSnapshot.getValue(question.class);
                id=null;
                questionnumber = (char) (questionnumber.charAt(0) + 1) + "";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}
//                public  void addQuestion(String question,String answer,String number)
//    {
//        question add=new question(question,answer,number,o);
//        if (TextUtils.isEmpty(id)) {
//            id = ref.push().getKey();
//        }
//        //Log.e(TAG, "addQuestion: "+ref.child("question").push().get );
//        if(!number.equals(null)&&number.equals(questionnumber)&&c==1) {
//            questionnumber = (char) (questionnumber.charAt(0) + 1) + "";
//            Log.e(TAG, "addQuestion: "+number );
//            c--;
//        }
//        else{
//            ref.child("quiz").child(id).child("question").setValue(e1.getText().toString());
//            ref.child("quiz").child(id).child("answer").setValue(e2.getText().toString());
//            ref.child("quiz").child(id).child("number").setValue(questionnumber);
//            e1.setText("");
//            e2.setText("");
//        }
//        // String questionnumber=
//    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(id)) {
            id = ref.push().getKey();
        }
        question q=new question(e1.getText().toString(),e2.getText().toString(),questionnumber,e3.getText().toString(),e4.getText().toString(),e5.getText().toString(),e6.getText().toString());
        //ref.child("quiz").child(id).child("question").setValue(e1.getText().toString());
        //ref.child("quiz").child(id).child("answer").setValue(e2.getText().toString());
        //ref.child("quiz").child(id).child("number").setValue(questionnumber);
        ref.child("quiz").child(id).setValue(q);
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e6.setText("");
        addUserChangeListener();
        //      ref.child(id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //for(DataSnapshot ds:dataSnapshot.getChildren()) {
//                    question add = dataSnapshot.getValue(question.class);
//
//                    try {
//                        addQuestion(e1.getText().toString(), e2.getText().toString(), add.number);
//                    }
//                    catch (Exception e){
//                        Log.e(TAG, "onDataChange: " );
//                        c=1;
//                     //   addQuestion(e1.getText().toString(), e2.getText().toString(),"a");
//                    }
//                    Toast.makeText(getApplicationContext(), "question sent", Toast.LENGTH_LONG).show();
//                }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        addQuestion( question, answer,"a");
    }
}
