package com.ut3.ehg.turismotepic;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LAB-DES-05 on 07/02/2017.
 */

public class Ayuda extends Fragment {

    ViewGroup root;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //final AppBarLayout appBarLayout;
        root = (ViewGroup) inflater.inflate(R.layout.ayuda, null);

    }
}
