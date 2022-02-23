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
    public static final String KEY_PREF_ADDRESS = "companyaddress";
    public static final String KEY_PREF_PHONE = "companyphone";
    public static final String KEY_PREF_EMAIL = "companyemail";


//    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, final String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
        EditTextPreference pcompanypreference = findPreference(KEY_PREF_COMPANY);
        pcompanypreference.setSummary(Constants.getSP(getContext()).getCompanyname());
     EditTextPreference paddress = findPreference(KEY_PREF_ADDRESS);
        paddress.setSummary(Constants.getSP(getContext()).getAdressname());

        EditTextPreference pPHONE = findPreference(KEY_PREF_PHONE);
        pPHONE.setSummary(Constants.getSP(getContext()).getCompanyphone());
        EditTextPreference pEMAIL = findPreference(KEY_PREF_EMAIL);
        pEMAIL.setSummary(Constants.getSP(getContext()).getCOMPANYEMAIL());






    }

    @Override
    public void onResume() {


        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);




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
            pcompanypreference.setSummary(sharedPreferences.getString(key, "NO DATA"));
            Constants.getSP(getContext()).setCompanyname(pcompanypreference.getSummary().toString());

        }
        if (key.equals(KEY_PREF_ADDRESS)) {
            EditTextPreference paddress = findPreference(key);

            paddress.getText();
            paddress.setSummary(sharedPreferences.getString(key, "NO DATA"));
            Constants.getSP(getContext()).setAddressname(paddress.getSummary().toString());

        }

        if (key.equals(KEY_PREF_PHONE)) {
            EditTextPreference pPHONE = findPreference(key);

            pPHONE.getText();
            pPHONE.setSummary(sharedPreferences.getString(key, "NO DATA"));
            Constants.getSP(getContext()).setCompanyphone(pPHONE.getSummary().toString());

        }

        if (key.equals(KEY_PREF_EMAIL)) {
            EditTextPreference pEMAIL = findPreference(key);

            pEMAIL.getText();
            pEMAIL.setSummary(sharedPreferences.getString(key, "NO DATA"));
            Constants.getSP(getContext()).setCOMPANYEMAIL(pEMAIL.getSummary().toString());

        }
    }}