package com.example.q.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;



public class Imagefrag extends Fragment {

    public Imagefrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedlnstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.imagefrag, container, false);

        return view;
    }
}
