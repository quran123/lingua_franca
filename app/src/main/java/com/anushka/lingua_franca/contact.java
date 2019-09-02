package com.anushka.lingua_franca;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class contact extends Activity {
    private androidx.appcompat.widget.Toolbar toolbar;
    Button b1,b2,b3,b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        b1=(Button)findViewById(R.id.email);
        b2=(Button)findViewById(R.id.facebook);
        b3=(Button)findViewById(R.id.instagram);
        b4=(Button)findViewById(R.id.twitter);
        b5=(Button)findViewById(R.id.phone);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setTitle("Reach Us");
        //setSupportActionBar(toolbar);
        setTitleColor(getResources().getColor(R.color.white));
        // setSupportActionBar(toolbar);
    }
}

