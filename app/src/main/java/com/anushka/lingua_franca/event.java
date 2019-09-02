package com.anushka.lingua_franca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class event extends Activity {
    Button b1,b2,b3,b4,b5,b6,b7,b8;
    LinearLayout l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b4=(Button)findViewById(R.id.b4);
        b5=(Button)findViewById(R.id.b5);
        b6=(Button)findViewById(R.id.b6);
        b7=(Button)findViewById(R.id.b7);
        b8=(Button)findViewById(R.id.b8);
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                        // here you can add functions
                        Intent i=new Intent(getApplicationContext(),description.class);
                        i.putExtra("title","Quiz");
                i.putExtra("desc","1");
                        startActivity(i);
                    }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),description.class);
                i.putExtra("title","Corporate Competance");
               // i.putExtra("ti",""+R.string.co);
                i.putExtra("desc","2");
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(), description.class);
                i.putExtra("desc","3");
                i.putExtra("title","Youth Parliament");
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // here you can add functions
                Intent i=new Intent(getApplicationContext(),description.class);
                i.putExtra("title","Turncoat");
                i.putExtra("desc","4");
                startActivity(i);
            }
        });

        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),description.class);
                i.putExtra("title","Tasveer-E--Sher-O-Shayari");
                i.putExtra("desc","5");
                startActivity(i);
            }
        });

        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(), description.class);
                i.putExtra("title","Comic oodle");
                i.putExtra("desc","6");
                startActivity(i);
            }
        });
        b7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // here you can add functions
                Intent i=new Intent(getApplicationContext(),description.class);
                i.putExtra("desc","7");
                i.putExtra("title","Word snipping");
                startActivity(i);
            }
        });
        b8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // here you can add functions
                Intent i=new Intent(getApplicationContext(),description.class);
                i.putExtra("desc","8");
                i.putExtra("title","Grafitti");
                startActivity(i);
            }
        });

    }
}

