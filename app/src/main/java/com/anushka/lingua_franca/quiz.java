package com.anushka.lingua_franca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

public class quiz extends Activity implements View.OnClickListener {
    Button b1;
    TextView t1;
    Toolbar tool;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        b1=(Button)findViewById(R.id.b1);
        b1.setBackgroundColor(getResources().getColor(R.color.black));
        //ScrollView scroller = new ScrollView(this);
        t1=(TextView)findViewById(R.id.t1);
        //scroller.addView(t1);
        t1.setText(getResources().getText(R.string.quiz));
                //.setMovementMethod(ArrowKeyMovementMethod.getInstance()););
        tool=(Toolbar)findViewById(R.id.tool);
        setActionBar(tool);
        tool.setBackgroundColor(getResources().getColor(R.color.black));
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        scrollView = (ScrollView) findViewById(R.id.scrollable);
        t1.setSelected(true);
        t1.setMovementMethod(new ScrollingMovementMethod());
        //.onGenericMotionEvent(t1,R.string.description,));
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(getApplicationContext(),quiz1.class);
    }
}
