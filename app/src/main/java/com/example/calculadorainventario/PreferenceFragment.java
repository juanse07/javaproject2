package com.example.calculadorainventario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calculadorainventario.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferenceFragment extends PreferenceFragmentCompat {

    public PreferenceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference,rootKey);


    }


}
