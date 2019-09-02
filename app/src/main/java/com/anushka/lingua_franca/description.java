package com.anushka.lingua_franca;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
public class description extends AppCompatActivity {
    TextView t1;
    Button b1;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        t1=(TextView)findViewById(R.id.t1);
        b1=(Button)findViewById(R.id.b1);
        tool=(Toolbar)findViewById(R.id.tool);
        tool.setBackgroundColor(getResources().getColor(R.color.black));
        b1.setBackgroundColor(getResources().getColor(R.color.black));
        tool.setTitle(getIntent().getStringExtra("title"));
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        t1.setText(getIntent().getStringExtra("ti"));
        switch(getIntent().getStringExtra("desc"))
        {
            case "1":t1.setText(getResources().getText(R.string.quiz));break;
            case "2":t1.setText(getResources().getText(R.string.corporatecompetance));break;
            case "3":t1.setText(getResources().getText(R.string.yp));break;
            case "4":t1.setText(getResources().getText(R.string.turncoat));break;
            case "5":t1.setText(getResources().getText(R.string.tasveereshayari));break;
            case "6":t1.setText(getResources().getText(R.string.comicoodle));break;
            case "7":t1.setText(getResources().getText(R.string.wordsnipping));break;
            case "8":t1.setText(getResources().getText(R.string.grafitti));break;
        }
        t1.setSelected(true);
        t1.setMovementMethod(new ScrollingMovementMethod());

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                switch(getIntent().getStringExtra("desc"))
                {
                    case "1":Intent i=new Intent(getApplicationContext(),quiz1.class);
                    startActivity(i);
                    break;
                    default:
                // here you can add functions
                Toast.makeText(getApplicationContext(),"Event is not going on right now",Toast.LENGTH_LONG).show();
                //Intent i=new Intent(getApplicationContext(),description.class);
                //startActivity(i);
            }}
        });
    }
}