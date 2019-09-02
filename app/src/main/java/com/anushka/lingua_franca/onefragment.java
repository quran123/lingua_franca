package com.anushka.lingua_franca;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class onefragment extends androidx.fragment.app.Fragment {

    ImageView i;

    public onefragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment, container, false);
        i = (ImageView) view.findViewById(R.id.i);
        return view;
    }
}