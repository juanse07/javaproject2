package com.example.calculadorainventario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.calculadorainventario.Adapadores.AdaptadorNoteProd;
import com.example.calculadorainventario.Constructores.NoteProducto;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.itextpdf.text.List;

import java.io.File;
import java.util.ArrayList;


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
    TextView txcliente1, tx_cliente, tx_producto,txdaysterm,txtaxvisor,txdiscountvisor,tximp2;
    // Spinner listaclientes, listaclientes2,listacueros,spoperacion,spmetodo;
    EditText precio1, pagotext,txtterminos;
    RecyclerView.Adapter madapter;
    NoteProdViewModel noteProdViewModel;
    ClickInterface1 clickInterface1;

    String opcion, Producto;
    ArrayList<String> ritmo = new ArrayList<String>();
    ArrayList<String> metodo = new ArrayList<String>();
    ImageView restarprecio, sumarprecio;
ArrayList<NoteProducto>notesProd;

    List lista;
    RecyclerView.LayoutManager Lmanager;


    RadioGroup grupotipo;
    RadioButton rbventa, rbcompra, rbborrador;
    ImageView logomini, pagomenos, pagomas,imagefrase,imgSignature11,imgSignature22;
    MaterialCardView increasetax,increasediscount,discount,decreasetax,decreasediscount,increaseimp2,decreaseimp2,tax1box,Discountbox,tax2box;
    CardView card_operacion;
    View ingresoview;
    DatabaseReference ref;
    private SharedViewModel sharedViewModel;
    RecyclerView RecyProd;
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
        RecyProd=(RecyclerView)ingresoview.findViewById(R.id.RecyProd);

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
        txdaysterm=ingresoview.findViewById(R.id.txdaysterm);
        txtterminos=ingresoview.findViewById(R.id.txtterminos);
        imgSignature11=ingresoview.findViewById(R.id.imgSignature11);
        imgSignature22=ingresoview.findViewById(R.id.imgSignature22);
        increasetax=ingresoview.findViewById(R.id.increasetax);
        increasediscount=ingresoview.findViewById(R.id.increasediscount);
        decreasetax=ingresoview.findViewById(R.id.decreasetax);
        decreasediscount=ingresoview.findViewById(R.id.decreasediscount);
        tximp2=ingresoview.findViewById(R.id.tximp2);
        txdiscountvisor=ingresoview.findViewById(R.id.txdiscountvisor);
        txtaxvisor=ingresoview.findViewById(R.id.txtaxvisor);
        decreaseimp2=ingresoview.findViewById(R.id.decreaseimp2);
        increaseimp2=ingresoview.findViewById(R.id.increaseimp2);
        tax1box=ingresoview.findViewById(R.id.tax1box);
        Discountbox=ingresoview.findViewById(R.id.dicountbox);
        tax2box= ingresoview.findViewById(R.id.tax2box);
        //we need to avoid null on these textviews

        txtaxvisor.setText(Constants.getSP(ingresoview.getContext()).getTAX1TX());;
        txdiscountvisor.setText(Constants.getSP(ingresoview.getContext()).getDISCOUNT());
        tximp2.setText(Constants.getSP(ingresoview.getContext()).getTAX2TX());

        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        sharedViewModel.setTaxvalue(txtaxvisor.getText().toString());
        sharedViewModel.setDiscountvalue(txdiscountvisor.getText().toString());
        sharedViewModel.setTaxvalue2(tximp2.getText().toString());


        lista = new List();
        RecyProd.setHasFixedSize(true);
        Lmanager=new LinearLayoutManager(ingresoview.getContext(),LinearLayoutManager.VERTICAL, false);
        RecyProd.setLayoutManager(Lmanager);
       final AdaptadorNoteProd adaptadorNoteProd=new AdaptadorNoteProd(ingresodatfr.this);

        RecyProd.setAdapter(adaptadorNoteProd);
        noteProdViewModel=new ViewModelProvider(getActivity()).get(NoteProdViewModel.class);
        noteProdViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<java.util.List<NoteProducto>>() {
            @Override
            public void onChanged(java.util.List<NoteProducto> noteProductos) {

                adaptadorNoteProd.setNotes(noteProductos);

            }
        });
//        adaptadorNoteProd.setOnItemClickListener(ingresoview.getContext(),new AdaptadorNoteProd.onItemclickListener());


        //card_operacion = ingresoview.findViewById(R.id.card_operacion);
        txdaysterm.setText("0");



       txdaysterm.setText( Constants.getSP(getContext()).getDIAS());



        File imgFile2 = new File("/storage/emulated/0/PyMESoft/Frase.jpg");
        if (imgFile2.exists()) {

            Bitmap myBitmap2 = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());



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
        if(txdaysterm.getText().toString().equals("0")){
            txtterminos.setText("El cliente se compromete a pagar en un plazo no superior a"+" "+ "contado");

        }else {
            txtterminos.setText("El cliente se compromete a pagar en un plazo no superior a" + " " + txdaysterm.getText().toString() + " " + "días");
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
                        Constants.getSP(getContext()).setRBBORRADOR("3");
                        break;
                    case R.id.rbcompra:
                        sharedViewModel.setboton(rbcompra.getText().toString());
                       // getActivity().findViewById(R.id.card_vigilancia).setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
                        getActivity().findViewById(R.id.card_vigilancia).getBackground().setTint(getResources().getColor(R.color.purplecolotransparentr));
                        Constants.getSP(getContext()).setRBBORRADOR("2");
                        break;
                    case R.id.rbventa:
                        sharedViewModel.setboton(rbventa.getText().toString());
                        //getActivity().findViewById(R.id.card_vigilancia).setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
                        getActivity().findViewById(R.id.card_vigilancia).getBackground().setTint(getResources().getColor(R.color.blueTransparent));

                        Constants.getSP(getContext()).setRBBORRADOR("1");

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
increaseimp2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        increaseButton(tximp2);
        sharedViewModel.setTaxvalue2(tximp2.getText().toString());
    }
});
decreaseimp2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        decreasebutton(tximp2);
        sharedViewModel.setTaxvalue2(tximp2.getText().toString());
    }
});


increasetax.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        increaseButton(txtaxvisor);

      sharedViewModel.setTaxvalue(txtaxvisor.getText().toString());
}





});
decreasetax.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        decreasebutton(txtaxvisor);

    sharedViewModel.setTaxvalue(txtaxvisor.getText().toString());

    }
});

tax1box.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        String inputtext;
        inputtext=getResources().getString(R.string.Tax);
        int keynumber1=2;
        int keydecimal=8192;
        int inputtype=keynumber1+keydecimal;
        AlertDialog(txtaxvisor,inputtext,inputtype);

        return true;
    }
});
Discountbox.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        String inputtext;
        inputtext=getResources().getString(R.string.Discount);
        int keynumber1=2;
        int keydecimal=8192;
        int inputtype=keynumber1+keydecimal;
        AlertDialog(txdiscountvisor,inputtext,inputtype);

        return true;
    }
});

tax2box.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        String inputtext;
        inputtext=getResources().getString(R.string.Tax2);
        int keynumber1=2;
        int keydecimal=8192;
        int inputtype=keynumber1+keydecimal;
        AlertDialog(tximp2,inputtext,inputtype);
        return true;
    }
});





        increasediscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseButton(txdiscountvisor);

               sharedViewModel.setDiscountvalue(txdiscountvisor.getText().toString());
            }



        });
        decreasediscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreasebutton(txdiscountvisor);
//
                sharedViewModel.setDiscountvalue(txdiscountvisor.getText().toString());

            }
        });







pagomas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int getinput = Integer.parseInt(txdaysterm.getText().toString());
        //shareViewModel2.setHorapdf(txactual.getText().toString());
        if (getinput == 0) {
            Integer valorprecio = Integer.parseInt(txdaysterm.getText().toString());
           Integer valorritmo = 8;
            Integer valornuevosuma = valorprecio + valorritmo;
            txdaysterm.setText(valornuevosuma + "");



        } else if (getinput == 8) {
            Integer valorprecio = Integer.parseInt(txdaysterm.getText().toString());
            Integer valorritmo = 7;
            Integer valornuevosuma = valorprecio + valorritmo;
            txdaysterm.setText(valornuevosuma + "");
        } else if (getinput >= 15 && getinput < 120) {
            Integer valorprecio =Integer.parseInt(txdaysterm.getText().toString());
            Integer valorritmo = 15;
            Integer valornuevosuma = valorprecio + valorritmo;
            txdaysterm.setText(valornuevosuma + "");




        }
        sharedViewModel.setDias(txdaysterm.getText().toString());
        Constants.getSP(ingresoview.getContext()).setDIAS(txdaysterm.getText().toString());
        sharedViewModel.setDiasfinal(txdaysterm.getText().toString());




    }
});




        pagomenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(txdaysterm.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput == 8) {
                    Integer valorprecio = Integer.parseInt(txdaysterm.getText().toString());
                    Integer valorritmo = 8;
                    Integer valornuevosuma = valorprecio - valorritmo;
                    txdaysterm.setText(valornuevosuma + "");
                } else if (getinput > 15 && getinput <= 120) {
                    Integer valorprecio = Integer.parseInt(txdaysterm.getText().toString());
                    Integer valorritmo = 15;
                    Integer valornuevosuma = valorprecio - valorritmo;
                    txdaysterm.setText(valornuevosuma + "");



                } else if (getinput == 15) {
                    Integer valorprecio = Integer.parseInt(txdaysterm.getText().toString());
                    Integer valorritmo = 7;
                    Integer valornuevosuma = valorprecio - valorritmo;
                    txdaysterm.setText(valornuevosuma + "");


                }
                sharedViewModel.setDias(txdaysterm.getText().toString());
                Constants.getSP(ingresoview.getContext()).setDIAS(txdaysterm.getText().toString());

                sharedViewModel.setDiasfinal(txdaysterm.getText().toString());


            }
        });


        return ingresoview;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        sharedViewModel.getTaxvalue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
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
     sharedViewModel.getdiasfinal().observe(getViewLifecycleOwner(), new Observer<String>() {
         @Override
         public void onChanged(String s) {

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
                .putString("diasdepago", txdaysterm.getText().toString());
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
            txdaysterm.setText(calcpreference2.getString("diasdepago", "0"));
        }


    }

    public void loadRadioButtons3() {

        SharedPreferences creacion = getActivity().getSharedPreferences
                ("creacionpreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = creacion.edit();
        int i = creacion.getInt("estado", -1);
        if (i >= 0) {
            ((RadioButton) ((RadioGroup) ingresoview.findViewById(R.id.grupotipo)).getChildAt(i)).setChecked(true);
            txdaysterm.setText(creacion.getString("diasdepago1", "0"));
        }


    }
    private void AlertDialog(final TextView textView, String inputtext1,int inputtype){
        AlertDialog.Builder builder=new AlertDialog.Builder(ingresoview.getContext(),R.style.Theme_MaterialComponents_Dialog_Alert);
        ;
        final View view= LayoutInflater.from(ingresoview.getContext()).inflate(R.layout.edittextdialog,(ConstraintLayout)ingresoview.findViewById(R.id.parentconstrait));
        builder.setView(view);
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setInputType(inputtype);
//                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setText(holder.txcatprice.getText().toString());
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).requestFocus();
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHint(inputtext1);
        ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHintTextColor(view.getResources().getColor(R.color.colorGrisoscuro));
        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
        ((MaterialButton) view.findViewById(R.id.editdialogbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ((MaterialButton) view.findViewById(R.id.aceptedtx)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).getText().toString());

                alertDialog.dismiss();

            }

        });

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
    public void passingproductoClick(int position, CharSequence Producto, CharSequence Precio, CharSequence Cantidad) {

    }



    @Override
    public void passingprecio1Click(int position, CharSequence Precio) {

    }

    @Override
    public void passingcliente2Click(int position, CharSequence Cliente, String phone, String Email, String Address, String City) {

    }



    @Override
    public void passfirebasekey(String key) {

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
    public void PassnoteprodPosition(int position, String Producto, String Cantidad, String Precio, NoteProducto currentnote) {

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

        super.onStart();sharedViewModel.setDias(txdaysterm.getText().toString());
        sharedViewModel.setDiasfinal(txdaysterm.getText().toString());

    }

    public void increaseButton(TextView textView){
        int valorprecio = Integer.parseInt(textView.getText().toString());
        int valorritmo = 1;
        int valornuevosuma = valorprecio + valorritmo;
        String valorsum=String.valueOf(valornuevosuma);
        String valorpercent=valorsum+" "+"%";
//        txtaxvisor.setText(valorpercent);
        textView.setText(valornuevosuma + "");
//        sharedViewModel.setTaxvalue(valorsum);


//        sharedViewModel.setTaxvalue(textView.getText().toString());


    }
    public void decreasebutton(TextView textView){
        int getinput = Integer.parseInt(textView.getText().toString());

        int valorprecio = Integer.parseInt(textView.getText().toString());
        int valorritmo = 1;
        int valornuevosuma = valorprecio - valorritmo;
        String valorsum=String.valueOf(valornuevosuma);
        String valorpercent=valorsum+" ";
//        txtaxvisor.setText(valorpercent);
        textView.setText(valornuevosuma + "");
//

    }
    public void IncreasePrice(TextView textView, Double Pace){
        Double valorprecio = Double.parseDouble(precio1.getText().toString());
        Double valorritmo = 50.0;
        Double valornuevosuma = valorprecio + valorritmo;
        precio1.setText(valornuevosuma + "");

    }
    public void DecreasePrice(TextView textView,Double Pace){
        Double valorprecio = Double.parseDouble(precio1.getText().toString());
        Double valorritmo = 50.0;
        Double valornuevosuma = valorprecio - valorritmo;
        precio1.setText(valornuevosuma + "");

    }
}

