package com.anushka.lingua_franca;

import android.content.Intent;
import android.os.Bundle;
import com.anushka.lingua_franca.Getter.registergetset;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "asd";
    Intent i;
    ImageButton im;
    FirebaseAuth auth;
    View navHeader;
    String userId;
    private ChildEventListener mChildEventListener;

// ...

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReferenceFromUrl("https://linguafranca-71b67.firebaseio.com/").child("User");
        //mRef = new FirebaseDatabase("https://linguafranca-71b67.firebaseio.com/");
        //toolbar.setTitleTextColor(getResources(R.color.pink));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        LayoutInflater inflater = getLayoutInflater();
//        View listHeaderView = inflater.inflate(R.layout.header_nav,null, false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // navigationView.addHeaderView(listHeaderView);
        navHeader = (View) findViewById(R.id.navheader);
        navHeader = navigationView.getHeaderView(0);
//        im=(ImageButton)navHeader.findViewById(R.id.imageView);
//        im.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent intentGalley = new Intent(Intent.ACTION_PICK);
//                intentGalley.setType("image/*");
//                startActivityForResult(intentGalley, PICK_GALLERY_IMAGE);
//            }});

        //im.setImageDrawable(getResources().getDrawable(R.drawable.logo));
        t1 = (TextView) navHeader.findViewById(R.id.t1);
        t2 = (TextView) navHeader.findViewById(R.id.t2);
        int uid1 = 1;
        int userId = mFirebaseDatabase.getKey().charAt(0);
        Log.e(TAG, "onCreate1: " + userId);
        FirebaseDatabase m = mFirebaseDatabase.getDatabase();
        //String userId1 = m.getReference("-LmUvUNd2dh_6IUgQqml/branch").getRoot().getKey();
        //Log.e(TAG, "onCreate: " + userId1);
//        userId = mFirebaseDatabase.child("user").push().getKey();
        //Intent intent = getIntent();
        //t1.setText(intent.getStringExtra("name"));
        //t1.setText("Hi!!!!!!!!");
        //t1.setText(intent.getStringExtra("name"));
        //t1.setText();
       // t1.setText(auth.getCurrentUser().getEmail());
        //t2.setText(mFirebaseDatabase.child("uid").child("name").toString());
        attachDatabaseReadListener();
//        t1.setText(user.name);
        //t1.setText(mFirebaseDatabase.child("user").child("userId").child("name").getRoot().toString());
        // t1.setText(mFirebaseDatabase.child("user").child(userId).child("name").toString());
        //t1.setText(""+mDatabase.child("name").setValue("user"));

        // t1.setText("");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            auth.signOut();
            if (auth.getCurrentUser() != null)
                Toast.makeText(getApplicationContext(), "logg", Toast.LENGTH_LONG).show();
            else {
                startActivity(new Intent(getApplicationContext(), register.class));
                Toast.makeText(getApplicationContext(), "Signed Out", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.team) {
            i = new Intent(getApplicationContext(), team.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.event) {
            i = new Intent(getApplicationContext(), event.class);
            startActivity(i);
        } else if (id == R.id.write) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"linguafrancajssaten@gmail.com"});
            //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
            //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
            email.putExtra(Intent.EXTRA_SUBJECT, "Query:");
            email.putExtra(Intent.EXTRA_TEXT, "Dear sir/ma'am");
            // email.putExtra(Intent.EXTRA_PHONE_NUMBER,971893083);
            //need this to prompts email client only
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client :"));
            //i = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
            //startActivity(i);
            //i=new Intent(getApplicationContext(),write.class);
            //startActivity(i);
        } else if (id == R.id.contact) {
            i = new Intent(getApplicationContext(), contact.class);
            startActivity(i);
        } else if (id == R.id.feedback) {
            i = new Intent(getApplicationContext(), feedback.class);
            startActivity(i);
        } else if (id == R.id.zealicon) {
            Toast.makeText(getApplicationContext(), "Event is not yet started", Toast.LENGTH_LONG).show();
        } else if (id == R.id.profile) {
            Intent i = new Intent(getApplicationContext(), profile.class);
            startActivity(i);
        }else if(id==R.id.questionsetter){
            FragmentManager fragmentManager = getSupportFragmentManager();
            InputNameDialogFragment inputNameDialog = new InputNameDialogFragment();
            inputNameDialog.setCancelable(false);
            inputNameDialog.setDialogTitle("Enter Passcode");
            inputNameDialog.show(fragmentManager, "Input Dialog");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@Nullable DataSnapshot dataSnapshot, @Nullable String s) throws DatabaseException {
                    try {
                        registergetset user = dataSnapshot.getValue(registergetset.class);
                        String email1 = dataSnapshot.getValue(registergetset.class).getEmail();
                        if (auth.getCurrentUser().getEmail().equals(email1)) {
                            t1.setText(user.getName());
                            t2.setText(""+email1);
                            Log.e(TAG, "onChildAdded: " + email1);
                        } else
                            Log.e(TAG, "onChildAdded: ");
                    }catch (DatabaseException e){
                        Log.e(TAG, "onChildAdded: exp"+ e.toString());
                    }
                }@Override
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
                    Log.e(TAG, "onCancelled: "+databaseError.getDetails() );
                }
            };
            mFirebaseDatabase.addChildEventListener(mChildEventListener);
        }
    }
//    private void detachDatabaseReadListener(){
//        if(mChildEventListener!=null){
//            mFirebaseDatabase.removeEventListener(mChildEventListener);
//        }
//        mChildEventListener=null;
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        auth.addAuthStateListener(mAuthStateListener);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        auth.addAuthStateListener(mAuthStateListener);
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (mAuthStateListener != null) {
//           auth.removeAuthStateListener(mAuthStateListener);
//        }
//        detachDatabaseReadListener();
//}
}

