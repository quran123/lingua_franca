package com.anushka.lingua_franca;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class twofragment extends androidx.fragment.app.Fragment {

    ImageView i;

    public twofragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment, container, false);
        i = (ImageView) view.findViewById(R.id.i);
        return view;
    }
}