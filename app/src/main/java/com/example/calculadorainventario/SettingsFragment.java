package com.example.calculadorainventario;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    public static final String KEY_PREF_COMPANY = "companyname";


//    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, final String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);




    }

    @Override
    public void onResume() {


        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

//        Constants.getSP(getContext()).setCompanyname(pcompanypreference.getText());


        super.onResume();

    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_PREF_COMPANY)) {
            EditTextPreference pcompanypreference = findPreference(key);

            pcompanypreference.getText();
            pcompanypreference.setSummary(sharedPreferences.getString(key, ""));

        }
    }}