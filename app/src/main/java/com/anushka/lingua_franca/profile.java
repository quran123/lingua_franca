package com.anushka.lingua_franca;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.anushka.lingua_franca.Getter.registergetset;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class profile extends Activity {
    private static final int GALLERY_REQUEST = 100;
    private static final String TAG = "asd";
    ImageView im;
    Uri imageUri;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    Button update,cancle;
    FirebaseAuth auth;
    String userId;
    registergetset user;
    FirebaseDatabase database;
    DatabaseReference ref;
    ChildEventListener mChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReferenceFromUrl("https://linguafranca-71b67.firebaseio.com/").child("User");
        im=(ImageView)findViewById(R.id.i2);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);
        e7=(EditText)findViewById(R.id.e7);
        e8=(EditText)findViewById(R.id.e8);
        update=(Button)findViewById(R.id.update);
        cancle=(Button)findViewById(R.id.cancel);
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                update1();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),nav.class);
                startActivity(i);
            }
            });
//        im.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                photoPickerIntent.setType("image/*");
//                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
//            }
//        });
        attachDatabaseReadListener();
}
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(imageStream), 400, 400, true);
                //final Bitmap selectedImage = ;
                im.setImageBitmap(bitmap);

                //im.setOutlineAmbientShadowColor(getResources().getColor(R.color.white));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    public void update1()
    {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) throws DatabaseException{
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot dsw : ds.getChildren()) {
                        try {
                    registergetset user=ds.getValue(registergetset.class);
                        Log.e(TAG, "onDataChange: " + dataSnapshot.getChildren());
                    Log.e(TAG, "onDataChange:1 "+user.name );
                            Log.e(TAG, "onDataChange:2 "+user.userid );
                    //if(user.email.equals(userId))
                            if(e6.getText().toString().equals(user.email)){
                    if(e1.getText().toString()!=null) {
                            ref.child("" + user.userid).child("rollnumber").setValue(e1.getText().toString());
                        }
                        else
                        {
                            ref.child("" + user.userid).child("rollnumber").setValue(null);
                        }
                    if (e2.getText().toString()!=null) {
                            ref.child("" + user.userid).child("name").setValue(e2.getText().toString());
                        }
                    else
                    {
                        ref.child(""+user.userid).child("name").setValue(null);
                    }
                    if (e3.getText().toString()!=null) {
                        ref.child("" + user.userid).child("branch").setValue(e3.getText().toString());
                    }
                    else
                    {
                        ref.child(""+user.userid).child("branch").setValue(null);
                    }
                    if (e4.getText().toString()!=null)
                    {
                        ref.child(""+user.userid).child("year").setValue(Integer.valueOf(e4.getText().toString()));
                    }
                    else {
                        ref.child(""+user.userid).child("year").setValue(Integer.valueOf(null));
                    }
                    if (e5.getText().toString()!=null){
                        ref.child(""+user.userid).child("contact").setValue(Long.valueOf(e5.getText().toString()));
                    }
                    else{
                        ref.child(""+user.userid).child("contact").setValue(Long.valueOf(null));
                    }
                    if (e6.getText().toString()!=null){
                        ref.child(""+user.userid).child("email").setValue(e6.getText().toString());
                    }
                    else {
                        ref.child(""+user.userid).child("email").setValue(null);
                    }
                    if (e7.getText().toString()!=null){
                        ref.child(""+user.userid).child("designation").setValue(e7.getText().toString());
                    }
                    else {
                        ref.child(""+user.userid).child("designation").setValue(null);
                    }
                    if (e8.getText().toString()!=null){
                        ref.child(""+user.userid).child("birth").setValue(e8.getText().toString());
                    }
                    else {
                        ref.child(""+user.userid).child("birth").setValue(null);
                    } }
//                    try{
//                    if(imageUri.toString()!=null) {
//                        ref.child("" + user.userid).child("photourl").setValue(Uri.parse(imageUri.getPath()));
//                    }else {
//                        ref.child("" + user.userid).child("photourl").setValue(null);
//                    }
//                    }catch (Exception e){
//                        Log.e(TAG, "onDataChange: "+e.getMessage() );
//                    }
                    Toast.makeText(getApplicationContext(),"Updated successfully",Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onDataChange:3 "+ref.child(""+user.userid).child("rollnumber") );
                    }
                    catch(DatabaseException e)
                    {
                        Log.e(TAG, "onDataChange:exp "+e.getStackTrace().toString());
                    }
                    catch (Exception e)
                    {
                        Log.e(TAG, "onDataChange: "+e.getMessage() );
                    }
                    }
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});
    }
    private void attachDatabaseReadListener() {
        final String nu=null;
        if (mChildEventListener == null) {
            Log.e(TAG, "attachDatabaseReadListener: " );
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) throws DatabaseException {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        try {
                            registergetset friendlyMessage = dataSnapshot.getValue(registergetset.class);
                            if (auth.getCurrentUser().getEmail().equals(friendlyMessage.email)) {
                                if ((friendlyMessage.rollnumber) != null) {
                                    e1.setText(friendlyMessage.rollnumber);
                                }
                                if ((friendlyMessage.name) != null) {
                                e2.setText(friendlyMessage.name);}
                                if ((friendlyMessage.branch) != null) {
                                e3.setText(friendlyMessage.branch);}
                                if ((friendlyMessage.year) !=0) {
                                e4.setText(Integer.toString(friendlyMessage.year));}
                                if ((friendlyMessage.contact!=0) ) {
                                e5.setText(Long.toString(friendlyMessage.contact));}
                                if ((friendlyMessage.email) != null) {
                                e6.setText(friendlyMessage.getEmail());}
                                if (friendlyMessage.designation!=null) {
                                    e7.setText(friendlyMessage.designation);
                                }
                                if ((friendlyMessage.birth != null)) {
                                    e8.setText((CharSequence) friendlyMessage.birth);
                                }
                                if (friendlyMessage.getPhotourl() !=null)
                                {
                                    imageUri=friendlyMessage.getPhotourl();
                                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                                    final Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(imageStream), 400, 400, true);
                                    //final Bitmap selectedImage = ;
                                    im.setImageBitmap(bitmap);
                                }
                                Log.e(TAG, "onChildAdded: " + friendlyMessage.email);
                            }
                        } catch (DatabaseException e) {
                            Log.e(TAG, "onChildAdded: exp"+e.getMessage() );

                        } catch (FileNotFoundException e) {
                            Log.e(TAG, "onChildAdded: "+e.getMessage() );
                        }
                    }
                    //mMessageAdapter.add(friendlyMessage)
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            ref.addChildEventListener(mChildEventListener);
        }
    }
}
