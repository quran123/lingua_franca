package com.anushka.lingua_franca.yp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anushka.lingua_franca.R;
import com.anushka.lingua_franca.YP2K20;

public class yp_main extends Activity {
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yp);
        b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),yp_login.class);
                startActivity(i);
            }
        });
        b2=(Button)findViewById(R.id.b1);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(), YP2K20.class);
                startActivity(i);
            }
        });
        b3=(Button)findViewById(R.id.b1);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),YP2K20.class);
                startActivity(i);
            }
        });
    }
}

