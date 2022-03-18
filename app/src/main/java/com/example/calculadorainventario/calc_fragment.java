package com.example.calculadorainventario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Environment;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.Constructores.NoteProducto;
import com.example.calculadorainventario.Constructores.arrayconstructor;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.VIBRATOR_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calc_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calc_fragment extends Fragment implements ClickInterface1 {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Double> ListaCuero = new ArrayList<Double>();
    ArrayList<String> milista = new ArrayList<String>();
    private static final String EXTRA_MEDIDA="com.example.calculadorainventario.EXTRA_MEDIDA";


    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btcero, btpunto, btadd, btborrar, btcalcular,btpdf,dcmbutton;

    TextView valor_a, valor_u, txcuenta, txultimo, txprecio, txvalor, txactual;
    ListView Lista1;
    BottomNavigationView navigationView;
    //Switch dcmbutton;
    Vibrator vibrator;
    View ClientesView2;
    CharSequence sumacompartir;
    SharedViewModel sharedViewModel;
    ShareViewModel2 shareViewModel2;
    NoteViewModel noteViewModel;
    ArrayList<Note>ListaPdf;
    arrayconstructor const2=new arrayconstructor();
    //ArrayList<Double>ListaPdf;
    int position2;
    RadioButton Pies,Decimetros;
    String Cuentacompartir,Promediocompartir,Preciocompartir,Valorcompartir;
    RadioGroup grupo;
    SharedPreferences sharedPreferences;
    Repositorio1 repositorio1;
    private List<Double> UnidadesLista;
    private ArrayList<Double> UnidadesLista2;
    private File pdfFile;
    private Paragraph paragraph;
    private com.itextpdf.text.List lista;
    Document mDoc = new Document(PageSize.LETTER);
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());
    String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";
    String ingreso;
    Uri pdfUri;
    ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public calc_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calc_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static calc_fragment newInstance(String param1, String param2) {
        calc_fragment fragment = new calc_fragment();
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
        ClientesView2 = inflater.inflate(R.layout.fragment_calc_fragment, container, false);
        //return inflater.inflate(R.layout.fragment_calc_fragment, container, false);
        btcero = (Button) ClientesView2.findViewById(R.id.btcero);
        btborrar = (Button) ClientesView2.findViewById(R.id.btborrar);
        bt1 = (Button) ClientesView2.findViewById(R.id.bt1);
        bt2 = (Button) ClientesView2.findViewById(R.id.bt2);
        bt3 = (Button) ClientesView2.findViewById(R.id.bt3);
        bt4 = (Button) ClientesView2.findViewById(R.id.bt4);
        bt5 = (Button) ClientesView2.findViewById(R.id.bt5);
        bt6 = (Button) ClientesView2.findViewById(R.id.bt6);
        bt7 = (Button) ClientesView2.findViewById(R.id.bt7);
        bt8 = (Button) ClientesView2.findViewById(R.id.bt8);
        bt9 = (Button) ClientesView2.findViewById(R.id.bt9);
        dcmbutton=ClientesView2.findViewById(R.id.dcmbutton);
       // btpdf= ClientesView2.findViewById(R.id.btpdf);
        btpunto = (Button) ClientesView2.findViewById(R.id.btpunto);
        btadd = (Button) ClientesView2.findViewById(R.id.btadd);

        txcuenta = (TextView) ClientesView2.findViewById(R.id.txcuenta);

        txultimo = (TextView) ClientesView2.findViewById(R.id.txultimo);
        Pies=ClientesView2.findViewById(R.id.Pies);
        Decimetros=ClientesView2.findViewById(R.id.Decimetros);
        grupo=ClientesView2.findViewById(R.id.grupo);
        //String Path1=mFilepath;


        btcalcular = (Button) ClientesView2.findViewById(R.id.btcalcular);

        txprecio = (TextView) ClientesView2.findViewById(R.id.txprecio);


        txvalor = (TextView) ClientesView2.findViewById(R.id.txvalor);
        vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        noteViewModel=new ViewModelProvider(getActivity()).get(NoteViewModel.class);
        // dcmbutton = (Switch)ClientesView2.findViewById(R.id.dcmbutton);


        //String precio1 = getActivity().getIntent().getStringExtra("preciouno");

        //txprecio.setText(precio1);


        loadRadioButtons();


dcmbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        noteViewModel.DeleteAll();
    }
});
        btcero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);


                String Val0;


                if(ingreso==null){
                    Val0="0";

                }else{

                    Val0=ingreso+0;}


                shareViewModel2.setHorapdf(Val0);

            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val1;


                if(ingreso==null){
                    Val1="1";

                }else{

                    Val1=ingreso+1;}


                shareViewModel2.setHorapdf(Val1);

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                String Val2;


                if(ingreso==null){
                   Val2="2";

                }else{

                 Val2=ingreso+2;}


                    shareViewModel2.setHorapdf(Val2);
                //shareViewModel2.setHorapdf(valor_a.getText().toString() );

            }

        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val3;


                if(ingreso==null){
                    Val3="3";

                }else{

                    Val3=ingreso+3;}


                shareViewModel2.setHorapdf(Val3);

            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val4;


                if(ingreso==null){
                    Val4="4";

                }else{

                    Val4=ingreso+4;}


                shareViewModel2.setHorapdf(Val4);

            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val5;


                if(ingreso==null){
                    Val5="5";

                }else{

                    Val5=ingreso+5;}


                shareViewModel2.setHorapdf(Val5);

            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val6;


                if(ingreso==null){
                    Val6="6";

                }else{

                    Val6=ingreso+6;}


                shareViewModel2.setHorapdf(Val6);

            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val7;


                if(ingreso==null){
                    Val7="7";

                }else{

                    Val7=ingreso+7;}


                shareViewModel2.setHorapdf(Val7);

            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val8;


                if(ingreso==null){
                    Val8="8";

                }else{

                    Val8=ingreso+8;}


                shareViewModel2.setHorapdf(Val8);

            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Val9;


                if(ingreso==null){
                    Val9="9";

                }else{

                    Val9=ingreso+9;}


                shareViewModel2.setHorapdf(Val9);

            }
        });
        btpunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);

                String Valp;


                if(ingreso==null){
                    Valp=".";

                }else{

                    Valp=ingreso+".";}


                shareViewModel2.setHorapdf(Valp);

            }
        });



        btadd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                guardarpreferencias();
                arrayconstructor array1 = new arrayconstructor();
                 Interface2 interface2 = null;



                if (ingreso.isEmpty()) {
                    vibrator.vibrate(120);
                    Toast.makeText(ClientesView2.getContext(), "NO HAY DATOS", Toast.LENGTH_SHORT).show();

                } else {
                    //if (dcmbutton.isChecked()) {
                    double getinput = Double.parseDouble(ingreso);
                    //shareViewModel2.setHorapdf(txactual.getText().toString());

                    if (getinput > 10 && getinput < 99) {
                        vibrator.vibrate(20);
                        array1.setUnidadesMaterial(getinput);
                        sharedViewModel.addMaterial(array1);


                        ListaCuero.add(getinput);//HALLAR PARA PDF
                        Double Valor_Medida=Double.parseDouble(ingreso);
                        Note note=new Note(Valor_Medida);
                        //note.setValor_Medida(Valor_Medida);
                       noteViewModel.Insert(note);



//                       ListaPdf=new ArrayList<>();
//                       ListaPdf.add(note);





                        shareViewModel2.setHorapdf("");
                       // shareViewModel2.setINGRESAR_DATOS("");


                        /*double promed;

                        double sum = 0;
                        DecimalFormat df = new DecimalFormat("##.#");

                        NumberFormat dc = NumberFormat.getCurrencyInstance(Locale.US);
                        dc.setMaximumFractionDigits(0);*/


                      /*  for (int i = 0; i < ListaCuero.size(); i++) {

                            sum += ListaCuero.get(i);
                            promed = sum / ListaCuero.size();

                            //txsuma.setText((df.format(sum)));




                           // txsuma.setText(Double.toString(Double.parseDouble(df.format(sum).trim())));




                              //sumacompartir =Double.toString(Double.parseDouble(df.format(sum).trim()));
                            //if(sumacompartir.toString().trim().length()!=0)
                               // shareViewModel2.setMedidapdf(sumacompartir.toString());
                           // double compartir=(Double.parseDouble(df.format(sum)));
                           // shareViewModel2.setMedidasuma(compartir);

                            Cuentacompartir=Integer.toString(ListaCuero.size());




                            //txcuenta.setText(Integer.toString(ListaCuero.size()));
                            shareViewModel2.setUnidadespdf(Cuentacompartir);

                            Promediocompartir=Double.toString(Double.parseDouble(df.format(promed))).trim();

                           //txultimo.setText(Double.toString(Double.parseDouble(df.format(promed))).trim());





                        }*/

                    } else {
                        vibrator.vibrate(120);
                        Toast.makeText(ClientesView2.getContext(), "El valor está fuera de rango", Toast.LENGTH_SHORT).show();
                    }
                }

               

                //interface2.outputClick(ListaCuero);
                shareViewModel2.setProductopdf(Promediocompartir);


               shareViewModel2.setPath(ListaPdf);

                //ConexionBD conexbd=new ConexionBD(ClientesView2.getContext(),"bd_medida",null,1);
               // String VarMedida=ingreso;





                //ArrayList<String> inputArray=new ArrayList<String>();

               // String inputString= gson.toJson(ListaCuero);
               /* SQLiteDatabase db= conexbd.getWritableDatabase();
                ContentValues values=new ContentValues();
               values.put("medida",ingreso);
                //values.put(Utilities.Medida,inputString);
                Long idResultado=db.insert("MatrizLista","medida",values);
                Toast.makeText(ClientesView2.getContext(),"Resultado"+idResultado,Toast.LENGTH_SHORT).show();

               // db.delete("MatrizLista", null, null);
                db.close();'*/

               // shareViewModel2.setFechapdf(outputStream);




            }

        });
        btcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                //((TextView) ClientesView2.findViewById(R.id.txactual)).setText("");
                shareViewModel2.setHorapdf("");

            }

        });


        btborrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                arrayconstructor array2 = new arrayconstructor();
                vibrator.vibrate(20);
               //noteViewModel.DeleteAll();
                noteViewModel.DeleteLastNote();

                   // array2.setUnidadesMaterial(removableitem);




                    //txsuma = (TextView) ClientesView2.findViewById(R.id.txsuma);
                    double promed;

                    double sum = 0;
                    DecimalFormat df = new DecimalFormat("#.0");
                    for (int i = 0; i < ListaCuero.size(); i++) {

                        sum += ListaCuero.get(i);
                        promed = sum / ListaCuero.size();


                       // txsuma.setText(Double.toString(Double.parseDouble(df.format(sum))));
                        shareViewModel2.setMedidapdf(Double.toString(Double.parseDouble(df.format(sum))));
                        Cuentacompartir=Integer.toString(ListaCuero.size());
                        shareViewModel2.setUnidadespdf(Cuentacompartir);
                        Promediocompartir=Double.toString(Double.parseDouble(df.format(promed)));

                        double pre1 = Double.parseDouble(Preciocompartir);
                       // double val1 = Double.parseDouble(txsuma.getText().toString());
                      //  double valfinal = pre1 * val1;
                       // txvalor.setText(Double.toString(valfinal));
                        shareViewModel2.setValorpdf(Valorcompartir);




                        //
                    }

            }

        });
       // btpdf.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {



             //   Intent intent = new Intent(getActivity(), pdfviewer.class);
               // Bundle bundle = new Bundle();
               // bundle.putSerializable("Lista_5",ListaCuero);

               // intent.putExtras(bundle);

               // startActivity(intent);
            //}
        //});


        return ClientesView2;
    }

    public void Enviardatos(View view) {


        Intent i = new Intent(ClientesView2.getContext(), pdfviewer.class);

        i.putExtra("WLTP_list", ListaCuero);
        i.putExtra("sumatotal2", sumacompartir);
        i.putExtra("uds2", Cuentacompartir);
        i.putExtra("precio2", Preciocompartir);
        i.putExtra("valortotal", Valorcompartir);
        //i.putExtra("opcion",opcion);

        startActivity(i);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shareViewModel2 = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(ShareViewModel2.class);
        shareViewModel2.getPath().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

            }
        });
        shareViewModel2.getNombrepdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String nombrepdf) {
            }
        });
        shareViewModel2.getProductopdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String productopdf) {
            }
        });
        shareViewModel2.getMedidaepdf().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
                    @Override
                    public void onChanged(CharSequence charSequence) {

                    }
                });
                shareViewModel2.getUnidadespdf().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String unidadespdf) {
                    }
                });
        shareViewModel2.getValorpdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String valorpdf) {
            }
        });
        shareViewModel2.getFechapdf().observe(getViewLifecycleOwner(), new Observer<ByteArrayOutputStream>() {
            @Override
            public void onChanged(ByteArrayOutputStream byteArrayOutputStream) {

            }


        });
        shareViewModel2.getHorapdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String horapdf) {

            }
        });
        shareViewModel2.getPreciopdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String preciopdf) {

            }
        });
        shareViewModel2.getPrecio2().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String precio2) {
                //txprecio.setText(precio2);
                Preciocompartir=precio2;

            }
        });
        shareViewModel2.getMedidasuma().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {

            }
        });

        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        sharedViewModel.getUnidadesLista2().observe(getViewLifecycleOwner(), new Observer<ArrayList<arrayconstructor>>() {


            @Override
            public void onChanged(ArrayList<arrayconstructor> arrayconstructors) {

            }
        });
        shareViewModel2.getINGRESAR_DATOS().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                ingreso=charSequence.toString();

            }
        });
        noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);
        LiveData<Double> s =noteViewModel.getSumTotal();
        s.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                //gmedida.setText(String.valueOf(aDouble));
                sumacompartir=String.valueOf(aDouble);
            }
        });


    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {





                    Toast.makeText(ClientesView2.getContext(), "Permiso concedido", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(ClientesView2.getContext(), "Permiso negado", Toast.LENGTH_SHORT).show();
                }


                // case REQUEST_CODE_ASK_PERMISSIONS_2:{
                //   if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //     uploadpdf(pdfUri);


                //} else {
                //  Toast.makeText(PdfFragmentView.getContext(), "Permiso negado", Toast.LENGTH_SHORT).show();
            }

        }
    }
    private void guardarpreferencias(){
        SharedPreferences calcpreference=getContext().getSharedPreferences
                ("medidapref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= calcpreference.edit();
        editor.putInt("atributocheck", grupo.indexOfChild(ClientesView2.findViewById(grupo.getCheckedRadioButtonId())));
        editor.apply();
       // editor.putBoolean("piesChecked", Pies.isChecked());
       //editor.putBoolean("decimetrosChecked", Decimetros.isChecked());
        editor.commit();
    }
    public void loadRadioButtons(){
        SharedPreferences calcpreference=getContext().getSharedPreferences
                ("medidapref",Context.MODE_PRIVATE);
        int i = calcpreference.getInt("atributocheck",-1);
        if( i >= 0){
            ((RadioButton) ((RadioGroup)ClientesView2.findViewById(R.id.grupo)).getChildAt(i)).setChecked(true);
        }
        //Pies.setChecked(calcpreference.getBoolean("piesChecked", false));
        //Decimetros.setChecked(calcpreference.getBoolean("decimetroschecked", false));
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
    public void passingcliente2Click(int position, CharSequence Cliente) {

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
        position2=position;

    }

    @Override
    public void PassTipoDoc(int position, CharSequence tipoDoc) {

    }

    @Override
    public void PassnoteprodPosition(int position, String Producto, String Cantidad, String Precio, NoteProducto currentnote) {

    }



}
