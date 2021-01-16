package com.example.calculadorainventario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NEWMATFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NEWMATFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseReference myrootDbaseref2;
    Button btmaterialinput;
    TextInputEditText txmaterialinput;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NEWMATFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NEWMATFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NEWMATFragment newInstance(String param1, String param2) {
        NEWMATFragment fragment = new NEWMATFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_n_e_w_m_a_t, container, false);
       //return inflater.inflate(R.layout.fragment_n_e_w_m_a_t, container, false);



        btmaterialinput=(Button)view.findViewById(R.id.btmaterialinput);
        txmaterialinput=(TextInputEditText) view.findViewById(R.id.txmaterialinput);
        myrootDbaseref2 = FirebaseDatabase.getInstance().getReference();

        btmaterialinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Material = txmaterialinput.getText().toString();


                 Map<String, Object> datosmaterialnuevo = new HashMap<>();
                datosmaterialnuevo.put("TIPO_CUERO", Material);



                myrootDbaseref2.child("PRODUCTOS").push().setValue(datosmaterialnuevo);


                ((TextInputEditText)view.findViewById(R.id.txmaterialinput)).setText("");

            }
        });
        return  view;
    }



}
