package com.example.calculadorainventario;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.calculadorainventario.R.id.txinputnombre;
import static com.example.calculadorainventario.R.id.txnombre45;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link newclie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newclie extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseReference myrootDbaseref2,mydb2;
    Button btnuevocliente;
    TextInputEditText txinputnombre,txinputape,txinputtel1;
    RecyclerView Recyclercliente;
    View ClientesView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public newclie() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newclie.
     */
    // TODO: Rename and change types and number of parameters
    public static newclie newInstance(String param1, String param2) {
        newclie fragment = new newclie();
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
        ClientesView = inflater.inflate(R.layout.fragment_newclie, container, false);
        //return inflater.inflate(R.layout.fragment_newclie, container, false);
        btnuevocliente=(Button)ClientesView.findViewById(R.id.btnuevocliente);
        txinputnombre=(TextInputEditText) ClientesView.findViewById(R.id.txinputnombre);
        Recyclercliente=(RecyclerView) ClientesView.findViewById(R.id.Recyclercliente);
        //Recyclercliente.setHasFixedSize(true);
        Recyclercliente.setLayoutManager(new LinearLayoutManager(Recyclercliente.getContext()));


        txinputtel1=(TextInputEditText) ClientesView.findViewById(R.id.txinputtel1);
        myrootDbaseref2 = FirebaseDatabase.getInstance().getReference();
        mydb2= FirebaseDatabase.getInstance().getReference().child("CLIENTE");


        btnuevocliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = txinputnombre.getText().toString();

                String Tel = txinputtel1.getText().toString();

                Intent intent=new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent
                .putExtra(ContactsContract.Intents.Insert.PHONE,txinputtel1.getText())
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                .putExtra(ContactsContract.Intents.Insert.NAME,txinputnombre.getText().toString());
                startActivity(intent);

                Map<String, Object> datosclientesnuevos = new HashMap<>();
                datosclientesnuevos.put("Nombre", Nombre);
                datosclientesnuevos.put("Tel", Tel);

                myrootDbaseref2.child("CLIENTE").push().setValue(datosclientesnuevos);


                ((TextInputEditText) ClientesView.findViewById(R.id.txinputnombre)).setText("");

                ((TextInputEditText) ClientesView.findViewById(R.id.txinputtel1)).setText("");
            }
        });



        return  ClientesView;
    }


        public void onStart() {
            super.onStart();
            FirebaseRecyclerOptions<constructornom2> options =
                    new FirebaseRecyclerOptions.Builder<constructornom2>()
                            .setQuery(mydb2, constructornom2.class)
                            .build();
            FirebaseRecyclerAdapter<constructornom2, clientes1>firebaseRecyclerAdapter2=new FirebaseRecyclerAdapter<constructornom2, clientes1>(options) {
                @Override
                protected void onBindViewHolder(clientes1 clientes1, final int i, final constructornom2 constructornom2) {
                    clientes1.txnombre45.setText(constructornom2.getNombre());

                }

                @NonNull
                @Override
                public clientes1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.visorfb,parent,false);
                    clientes1 viewHolder= new clientes1(view);
                    return viewHolder;
                }
            };
            Recyclercliente.setAdapter(firebaseRecyclerAdapter2);
            firebaseRecyclerAdapter2.startListening();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Recyclercliente.getContext());
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            Recyclercliente.setLayoutManager(linearLayoutManager);

            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            //linearLayoutManager.setReverseLayout(true);
            //linearLayoutManager.setStackFromEnd(true);
            //Recyclercliente.setLayoutManager(linearLayoutManager);
        }
    public static class clientes1 extends RecyclerView.ViewHolder {

        TextView txnombre45;
        public clientes1(@NonNull View itemView) {

            super(itemView);
            txnombre45 = itemView.findViewById(R.id.txnombre45);
        }




        }
    }

