package com.example.calculadorainventario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ingresodatfr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ingresodatfr extends Fragment implements ClickInterface1 {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txcliente1, tx_cliente, tx_producto;
    // Spinner listaclientes, listaclientes2,listacueros,spoperacion,spmetodo;
    EditText precio1, pagotext,txtterminos;

    String opcion, Producto;
    ArrayList<String> ritmo = new ArrayList<String>();
    ArrayList<String> metodo = new ArrayList<String>();
    ImageView restarprecio, sumarprecio;


    RadioGroup grupotipo;
    RadioButton rbventa, rbcompra, rbborrador;
    ImageView logomini, pagomenos, pagomas,imagefrase,imgSignature11,imgSignature22;
    CardView card_operacion;
    View ingresoview;
    DatabaseReference ref;
    private SharedViewModel sharedViewModel;
    ClickInterface1 listener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ingresodatfr() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ingresodatfr.
     */
    // TODO: Rename and change types and number of parameters
    public static ingresodatfr newInstance(String param1, String param2) {
        ingresodatfr fragment = new ingresodatfr();
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
        ingresoview = inflater.inflate(R.layout.fragment_ingresodatfr, container, false);
        //spoperacion = ingresoview.findViewById(R.id.spoperacion);
        precio1 = (EditText) ingresoview.findViewById(R.id.precio1);
        restarprecio = (ImageView) ingresoview.findViewById(R.id.restarprecio);
        sumarprecio = (ImageView) ingresoview.findViewById(R.id.sumarprecio);
        tx_cliente = ingresoview.findViewById(R.id.tx_cliente);
        tx_producto = ingresoview.findViewById(R.id.tx_producto);
        grupotipo = ingresoview.findViewById(R.id.grupotipo);
        logomini = ingresoview.findViewById(R.id.logomini);
        rbborrador = ingresoview.findViewById(R.id.rbborrador);
        rbventa = ingresoview.findViewById(R.id.rbventa);
        rbcompra = ingresoview.findViewById(R.id.rbcompra);
        pagomenos = ingresoview.findViewById(R.id.pagomenos);
        pagomas = ingresoview.findViewById(R.id.pagomas);
        pagotext = ingresoview.findViewById(R.id.pagotext);
        imagefrase=ingresoview.findViewById(R.id.imagefrase);
        txtterminos=ingresoview.findViewById(R.id.txtterminos);
        imgSignature11=ingresoview.findViewById(R.id.imgSignature11);
        imgSignature22=ingresoview.findViewById(R.id.imgSignature22);

        //card_operacion = ingresoview.findViewById(R.id.card_operacion);
        pagotext.setText("0");



        pagotext.setText( Constants.getSP(getContext()).getDIAS());



        File imgFile2 = new File("/storage/emulated/0/PyMESoft/Frase.jpg");
        if (imgFile2.exists()) {

            Bitmap myBitmap2 = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());


            imagefrase.setImageBitmap(myBitmap2);
        }




        File imgFile = new File("/storage/emulated/0/PyMESoft/Logotipo/logopng");
        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


            logomini.setImageBitmap(myBitmap);
        }
        File imgSig = new File("/storage/emulated/0/PyMESoft/Signature/signaturepng");
        if (imgSig.exists()) {

            Bitmap myBitmap2 = BitmapFactory.decodeFile(imgSig.getAbsolutePath());


            imgSignature11.setImageBitmap(myBitmap2);
        }
        if(pagotext.getText().toString().equals("0")){
            txtterminos.setText("El cliente se compromete a pagar en un plazo no superior a"+" "+ "contado");

        }else {
            txtterminos.setText("El cliente se compromete a pagar en un plazo no superior a" + " " + pagotext.getText().toString() + " " + "días");
        }

       // loadRadioButtons3();
        int estado=grupotipo.indexOfChild(ingresoview.findViewById(grupotipo.getCheckedRadioButtonId()));
      //  sharedViewModel.setbotonestado(estado);

      //  int selectedId = grupotipo.getCheckedRadioButtonId();
        //RadioButton radioButton = (RadioButton) ingresoview.findViewById(selectedId);
        //sharedViewModel.setboton(radioButton.getText().toString());
        grupotipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){
                    case R.id.rbborrador:
                        sharedViewModel.setboton(rbborrador.getText().toString());
                        //getActivity().findViewById(R.id.card_vigilancia).setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        getActivity().findViewById(R.id.card_vigilancia).getBackground().setTint(getResources().getColor(R.color.colorPrimaryDark));
                        Constants.getSP(getContext()).setRBBORRADOR(rbborrador.getText().toString());
                        break;
                    case R.id.rbcompra:
                        sharedViewModel.setboton(rbcompra.getText().toString());
                       // getActivity().findViewById(R.id.card_vigilancia).setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
                        getActivity().findViewById(R.id.card_vigilancia).getBackground().setTint(getResources().getColor(R.color.colorDarkRed));
                        Constants.getSP(getContext()).setRBBORRADOR(rbcompra.getText().toString());
                        break;
                    case R.id.rbventa:
                        sharedViewModel.setboton(rbventa.getText().toString());
                        //getActivity().findViewById(R.id.card_vigilancia).setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
                        getActivity().findViewById(R.id.card_vigilancia).getBackground().setTint(getResources().getColor(R.color.colorDarkBlue));
                        Constants.getSP(getContext()).setRBBORRADOR(rbventa.getText().toString());

                        break;

            }}
        });



        logomini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), logochoose.class);
                startActivity(intent);

            }
        });
        imgSignature11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Signature1Activity.class);
                startActivity(intent);

            }
        });
        imgSignature22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Signature2.class);
                startActivity(intent);

            }
        });

        rbcompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });











pagomas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int getinput = Integer.parseInt(pagotext.getText().toString());
        //shareViewModel2.setHorapdf(txactual.getText().toString());
        if (getinput == 0) {
            int valorprecio = Integer.parseInt(pagotext.getText().toString());
            int valorritmo = 8;
            int valornuevosuma = valorprecio + valorritmo;
            pagotext.setText(valornuevosuma + "");



        } else if (getinput == 8) {
            int valorprecio = Integer.parseInt(pagotext.getText().toString());
            int valorritmo = 7;
            int valornuevosuma = valorprecio + valorritmo;
            pagotext.setText(valornuevosuma + "");
        } else if (getinput >= 15 && getinput < 120) {
            int valorprecio = Integer.parseInt(pagotext.getText().toString());
            int valorritmo = 15;
            int valornuevosuma = valorprecio + valorritmo;
            pagotext.setText(valornuevosuma + "");




        }
        sharedViewModel.setDias(pagotext.getText().toString());
        sharedViewModel.setDiasfinal(Integer.parseInt(pagotext.getText().toString()));




    }
});




        pagomenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(pagotext.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput == 8) {
                    int valorprecio = Integer.parseInt(pagotext.getText().toString());
                    int valorritmo = 8;
                    int valornuevosuma = valorprecio - valorritmo;
                    pagotext.setText(valornuevosuma + "");
                } else if (getinput > 15 && getinput <= 120) {
                    int valorprecio = Integer.parseInt(pagotext.getText().toString());
                    int valorritmo = 15;
                    int valornuevosuma = valorprecio - valorritmo;
                    pagotext.setText(valornuevosuma + "");



                } else if (getinput == 15) {
                    int valorprecio = Integer.parseInt(pagotext.getText().toString());
                    int valorritmo = 7;
                    int valornuevosuma = valorprecio - valorritmo;
                    pagotext.setText(valornuevosuma + "");


                }
                sharedViewModel.setDias(pagotext.getText().toString());
                sharedViewModel.setDiasfinal(Integer.parseInt(pagotext.getText().toString()));


            }
        });
        sumarprecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int valorprecio = Integer.parseInt(precio1.getText().toString());
                int valorritmo = 50;
                int valornuevosuma = valorprecio + valorritmo;
                precio1.setText(valornuevosuma + "");



            }
        });
        restarprecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int valorprecio = Integer.parseInt(precio1.getText().toString());
                int valorritmo = 50;
                int valornuevosuma = valorprecio - valorritmo;
                precio1.setText(valornuevosuma + "");


            }
        });

        return ingresoview;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        sharedViewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                tx_producto.setText(charSequence);
            }
        });
        sharedViewModel.getCliente2().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence2) {
                tx_cliente.setText(charSequence2);
            }
        });
        sharedViewModel.getprecio().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence1) {
                precio1.setText(charSequence1);
            }
        });
        sharedViewModel.getboton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        sharedViewModel.getbotonestado().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
        sharedViewModel.getdiasfinal().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
        sharedViewModel.getdias().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("0")){
                    txtterminos.setText("El cliente se compromete a pagar en un plazo no superior a"+" "+ "contado");

                }else {
                    txtterminos.setText("El cliente se compromete a pagar en un plazo no superior a" + " " + s + " " + "días");
                }
            }
        });


    }

    public void guardarpreferencias2() {


        SharedPreferences calcpreference2 = getActivity().getSharedPreferences
                ("tipopref2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = calcpreference2.edit();
        editor
                .putInt("atributocheck2", grupotipo.indexOfChild(ingresoview.findViewById(grupotipo.getCheckedRadioButtonId())))
                .putString("diasdepago", pagotext.getText().toString());
        if(rbborrador.isChecked()) {
                editor.putString("Tipo", rbborrador.getText().toString());
        }else if(rbcompra.isChecked()){
            editor.putString("Tipo", rbcompra.getText().toString());

        }else {
            editor.putString("Tipo", rbventa.getText().toString());
        }
        editor.apply();
       // editor.commit();




    }

    public void loadRadioButtons2() {

        SharedPreferences calcpreference2 = getActivity().getSharedPreferences
                ("tipopref2", Context.MODE_PRIVATE);
        int i = calcpreference2.getInt("atributocheck2", -1);
        if (i >= 0) {
            ((RadioButton) ((RadioGroup) ingresoview.findViewById(R.id.grupotipo)).getChildAt(i)).setChecked(true);
            pagotext.setText(calcpreference2.getString("diasdepago", "0"));
        }


    }

    public void loadRadioButtons3() {

        SharedPreferences creacion = getActivity().getSharedPreferences
                ("creacionpreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = creacion.edit();
        int i = creacion.getInt("estado", -1);
        if (i >= 0) {
            ((RadioButton) ((RadioGroup) ingresoview.findViewById(R.id.grupotipo)).getChildAt(i)).setChecked(true);
            pagotext.setText(creacion.getString("diasdepago1", "0"));
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        File imgFile = new File("/storage/emulated/0/PyMESoft/Logotipo/logopng");
        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


            logomini.setImageBitmap(myBitmap);
        }
        File imgSig = new File("/storage/emulated/0/PyMESoft/Signature/signaturepng");
        if (imgSig.exists()) {

            Bitmap myBitmap2 = BitmapFactory.decodeFile(imgSig.getAbsolutePath());


            imgSignature11.setImageBitmap(myBitmap2);
        }

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onButtonAddClick(int position) {

    }

    @Override
    public void onButtonclienteClick(int position) {

    }

    @Override
    public void passingproductoClick(int position, CharSequence Producto) {

    }

    @Override
    public void passingprecio1Click(int position, CharSequence Precio) {

    }

    @Override
    public void passingcliente2Click(int position, CharSequence Cliente) {

    }

    @Override
    public void preferenciasacalculadora() {


    }

    @Override
    public void Preferenciasingreo(int estado, String dias, String Tipo) {
        
    }

    @Override
    public void passingpositionk(int position) {

    }

    @Override
    public void PassTipoDoc(int position, CharSequence tipoDoc) {

    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);






    }

    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onStart() {

        super.onStart();sharedViewModel.setDias(pagotext.getText().toString());
        sharedViewModel.setDiasfinal(Integer.parseInt(pagotext.getText().toString()));

    }
}

