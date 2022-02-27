package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class fragments3 extends AppCompatActivity implements ClickInterface1 {
    ViewPager2 viepag;
    TabLayout tabLayout;
    BadgeDrawable badgeDrawable, badgeDrawable2;
    ImageView back2,arrowchange;
    Button btnproducto, btncliente,btcompra,btnpdf;
    MaterialButton cardprod,cardcli,cardprod3;
    RelativeLayout relativev,Relative2,relativigilancia;
    ConstraintLayout constlay;
    BottomNavigationView navcat;
    LinearLayout linprod, lincli;
    Double valorBruto;
    String PrecioL,ProductoL,ClienteL,TaxValue;
    ArrayList<String> Listapdf;
    ArrayList<String> ListaProd;
    ArrayList<Double> ListaCant;
    ArrayList<Double> ListaPre;
    ArrayList<Double> Listavalor;
    ArrayList<Double>ListaimP;
    ArrayList<Double>ListaRimp;
    ArrayList<Double>listaVal2,Listacantidades;
    Double pdfval;

    double sum;

    NoteProdViewModel noteProdViewModel;
    List<NoteProducto>ListaProd1;


    TextView txclientebotton, txproductobttom,txpreciobottom,title4,textView38,textview30,textView50,txSubtotal,txSubtotal2;
    String Radiob;
    TextView textovigilancia;
    CardView card_vigilancia;


    private ClickInterface1 listener ;
    int Estado1;
    String dias1,fechafinal;
    int diasq;


    private SharedViewModel sharedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments3);


        viepag = findViewById(R.id.viepag);
        back2=findViewById(R.id.back2);
        navcat=findViewById(R.id.navcat);
        //relativev=findViewById(R.id.relativev);
        btncliente=findViewById(R.id.btncliente);
        btnproducto=findViewById(R.id.btnproducto);
       // Relative2=findViewById(R.id.Relative2);
        title4=findViewById(R.id.title4);
        btcompra=findViewById(R.id.btcompra);
        btnpdf=findViewById(R.id.btnpdf);
        cardprod3=findViewById(R.id.cardprod3);
        textView50=findViewById(R.id.textView50);
        txSubtotal=findViewById(R.id.txsubtotal);
        txSubtotal2=findViewById(R.id.txsubtotal2);

        arrowchange=findViewById(R.id.arrowchange);
        //txproductobttom=findViewById(R.id.txproductobttom);
        relativigilancia=findViewById(R.id.relativigilancia);
       // txpreciobottom=findViewById(R.id.txpreciobottom);
        textovigilancia=findViewById(R.id.textovigilancia);
        card_vigilancia=findViewById(R.id.card_vigilancia);
       // lincli=findViewById(R.id.lincli);
        //linprod=findViewById(R.id.linprod);
        cardcli=findViewById(R.id.cardcli);
        cardprod=findViewById(R.id.cardprod);
        textView38=findViewById(R.id.textView38);
        textview30=findViewById(R.id.textView30);
        TaxValue="0";







//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");
//
//        final String fechacComplString = fecc.format(calendar.getTime());
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        noteProdViewModel=new ViewModelProvider(this).get(NoteProdViewModel.class);

        noteProdViewModel.getSumTotal().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                valorBruto=aDouble;

                    if(aDouble==null){
                        cardprod3.setText("0");
                        txSubtotal.setText("0");
                    }else{
                        DecimalFormat formatter = new DecimalFormat("###,###,##0");
                        String totalfac=String.valueOf(formatter.format(aDouble));


                        cardprod3.setText(totalfac);
                        txSubtotal.setText(totalfac);
                        Double imp1=Double.parseDouble(TaxValue)/100;
                        Double imp2=1+imp1;
                        Double imp3=valorBruto*imp2;
                        txSubtotal2.setText(String.valueOf(formatter.format(imp3)));
                    }
//                }


            }
        });
        noteProdViewModel.getSumResutadoImpuesto().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {


                     DecimalFormat formatter = new DecimalFormat("###,###,##0");


//                     Double imp = Double.parseDouble(TaxValue);
//                     Double Imp2 = imp / 100;
//                     Double Imp3 = 1 + Imp2;
//                     Double Imp4 = aDouble * Imp3;
//
//
//                     String totalfac = String.valueOf(formatter.format(Imp4));
//
////
//                     txSubtotal2.setText(totalfac);

                 }


        });
        noteProdViewModel.getSumcantTotal().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if(aDouble==null) {
                    badgeDrawable.setVisible(false);

                } else {
                        Double sumacant=aDouble;

                    Double newData = Double.valueOf(aDouble);
                    int value = newData.intValue();

                        badgeDrawable.setVisible(true);
                        badgeDrawable.setNumber(value);


                        badgeDrawable.setBadgeTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mdtp_white));

                        badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purplecolor));
                    }


            }
        });
        noteProdViewModel.getAllNotes().observe(this, new Observer<List<NoteProducto>>() {
            @Override
            public void onChanged(List<NoteProducto> noteProductos) {
                ListaProd1=noteProductos;
//

            }
        });






        viepag.setAdapter(new FragmentAdapter1(this));




        final RecyclerView.Adapter adapter = viepag.getAdapter();
        String first = "PyMESoft";
        String next = "<font color='#03bfa5'>FActuras</font>";
        title4.setText(Html.fromHtml(first + next));
        title4.setTextColor(getResources().getColor(R.color.colorBlancox));
        String vigilaestado="1";

      vigilaestado=Constants.getSP(fragments3.this).getRBBORRADOR();
      Log.d("estado",vigilaestado);

        textovigilancia.setText(Constants.getSP(fragments3.this).getRBBORRADOR());
        if (vigilaestado.toString().equals("1")) {
            textovigilancia.setText(getResources().getString(R.string.Sales));
            textovigilancia.setTextColor(getResources().getColor(R.color.bluecolor));
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.blueTransparent));
        }
        else if(vigilaestado.equals("2")){
            textovigilancia.setText(getResources().getString(R.string.Receipts));
            textovigilancia.setTextColor(getResources().getColor(R.color.purplecolor));
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.purplecolotransparentr));

        }else if(vigilaestado.equals("3")){
            textovigilancia.setText(getResources().getString(R.string.Draft));
            textovigilancia.setTextColor(getResources().getColor(R.color.colorGrisoscuro));
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorGrisoscurotransparent));
        }


       arrowchange.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(textovigilancia.getText().toString().equals(getResources().getString(R.string.Sales))){
                   textovigilancia.setText(getResources().getString(R.string.Receipts));
                   textovigilancia.setTextColor(getResources().getColor(R.color.purplecolor));

                   card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.purplecolotransparentr));
               }else  if(textovigilancia.getText().toString().equals(getResources().getString(R.string.Receipts))){
                   textovigilancia.setText(getResources().getString(R.string.Draft));
                   textovigilancia.setTextColor(getResources().getColor(R.color.colorGrisoscuro));
                   card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorGrisoscurotransparent));
               }else  if(textovigilancia.getText().toString().equals(getResources().getString(R.string.Draft))){
                   textovigilancia.setText(getResources().getString(R.string.Sales));
                   textovigilancia.setTextColor(getResources().getColor(R.color.bluecolor));
                   card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.blueTransparent));
               }

           }
       });




        viepag.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(final int position) {
                super.onPageSelected(position);
                if(position==1){
                    btnproducto.bringToFront();
                    btnproducto.setVisibility(View.VISIBLE);
                    cardprod3.setVisibility(View.GONE);
                    cardprod.setVisibility(View.VISIBLE);
                    cardcli.setVisibility(View.GONE);
                    textView38.setVisibility(View.VISIBLE);
                    textview30.setVisibility(View.GONE);
                    textView50.setVisibility(View.GONE);
                    //linprod.setVisibility(View.VISIBLE);
                    //lincli.setVisibility(View.INVISIBLE);
                    //Relative2.setVisibility(View.VISIBLE);
                    btncliente.setVisibility(View.GONE);
                    btcompra.setVisibility(View.GONE);
                    btnproducto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent2=new Intent(fragments3.this,Crearproducto.class);
                            Log.d("array:",String.valueOf(ListaProd1.size()));

                            startActivity(intent2);
                        }
                    });

                }else if(position==0){
                    btncliente.bringToFront();
                    //Relative2.setVisibility(View.VISIBLE);
                    btncliente.setVisibility(View.VISIBLE);
                    cardprod.setVisibility(View.GONE);
                    cardcli.setVisibility(View.VISIBLE);
                    cardprod3.setVisibility(View.GONE);
                    textView38.setVisibility(View.GONE);
                    textview30.setVisibility(View.VISIBLE);
                    textView50.setVisibility(View.GONE);
                    //linprod.setVisibility(View.INVISIBLE);
                    //lincli.setVisibility(View.VISIBLE);

                    btnproducto.setVisibility(View.GONE);
                    btcompra.setVisibility(View.GONE);
                    btncliente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(fragments3.this,InclientActivity.class);
                            startActivity(intent);

                        }
                    });



                }else {
                    btnpdf.bringToFront();
                    btnpdf.setVisibility(View.VISIBLE);
//                    btcompra.bringToFront();
//                    cardprod3.bringToFront();
                    btncliente.setVisibility(View.GONE);
                    btnproducto.setVisibility(View.GONE);
                    cardprod.setVisibility(View.GONE);
                    textView38.setVisibility(View.GONE);
                    textView50.setVisibility(View.VISIBLE);
                    textview30.setVisibility(View.GONE);
                    cardcli.setVisibility(View.GONE);
                    btcompra.setVisibility(View.GONE);
                    cardprod3.setVisibility(View.GONE);
                    txSubtotal.bringToFront();
                    txSubtotal2.bringToFront();
                    //Relative2.setVisibility(View.GONE);
                    btcompra.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NoteViewModel noteViewModel;
                            noteViewModel = new ViewModelProvider(fragments3.this).get(NoteViewModel.class);
                           // noteViewModel.DeleteAll();

                            guardarpreferencias3();





                            Intent intent = new Intent(fragments3.this, preparacionas.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("Cliente", ClienteL);
                            bundle.putString("Producto", ProductoL);
                            //bundle.putString("Precio", txpreciobottom.getText().toString());
                            bundle.putString("Precio", PrecioL);
                            bundle.putString("Diasdepago",dias1);
                            bundle.putInt("Diasdepagoint",diasq);


                            bundle.putString("radiobuton",textovigilancia.getText().toString());

                            intent.putExtras(bundle);

                            startActivity(intent);
                        }
                    });
                    btnpdf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Constants.getSP(fragments3.this).setDIAS(dias1);
                            if(textovigilancia.getText().toString().equals(getResources().getString(R.string.Sales))){
                            Constants.getSP(fragments3.this).setRBBORRADOR("1");
                            }else if(textovigilancia.getText().toString().equals(getResources().getString(R.string.Receipts))){
                                Constants.getSP(fragments3.this).setRBBORRADOR("2");

                            }else if(textovigilancia.getText().toString().equals(getResources().getString(R.string.Draft))){
                                Constants.getSP(fragments3.this).setRBBORRADOR("3");

                            }
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");


                            final String fechacComplString = fecc.format(calendar.getTime());
                            calendar.add(Calendar.DATE, diasq);
                            fechafinal = fecc.format(calendar.getTime());

                            Intent intent = new Intent(fragments3.this, pdfviewer2.class);

                            //  ArrayList<Note> Lista78;
                            //Lista78=(ArrayList<Note>)allnotes3;
                            Bundle bundle = new Bundle();
                            //intent.putExtra("Lista6", (Parcelable) allnotes3);

                            bundle.putSerializable("Diasdepago2", dias1);
                            bundle.putSerializable("Fecha2", fechafinal);
//                            bundle.putSerializable("Unidades1",gcantidad.getText().toString());
//                            bundle.putSerializable("Medida1",gmedida.getText().toString());
//                            bundle.putSerializable("Total1",txtotalbottom.getText().toString());
                            bundle.putSerializable("Fecha1", fechacComplString.toString());
//                        bundle.putSerializable("Hora1",ghora.getText().toString());
                            //                           bundle.putSerializable("Precio1", PrecioL);
                            bundle.putSerializable("Nombre1", ClienteL);
                            bundle.putSerializable("Producto1", ProductoL);

                            String comprobarestado = textovigilancia.getText().toString();
                            if (comprobarestado.equals(getResources().getString(R.string.Receipts))) {
                                bundle.putSerializable("Estado1", "2");
                            } else if (comprobarestado.equals(getResources().getString(R.string.Sales))) {
                                bundle.putSerializable("Estado1", "1");

                            } else if (comprobarestado.equals(getResources().getString(R.string.Draft))) {
                                bundle.putSerializable("Estado1", "3");

                            }



                            ListaProd=new ArrayList<>();
                            ListaCant=new ArrayList<>();
                            ListaPre=new ArrayList<>();
                            Listavalor=new ArrayList<Double>();
                            listaVal2=new ArrayList<>();
                            ListaRimp=new ArrayList<>();
                            ListaimP=new ArrayList<>();
//                            double sum = 0;
//                            for(int i = 0; i < m.size(); i++)
//                                sum += m.get(i);
//                            return sum;


                            for (int i = 0 ; i <ListaProd1.size() ; i++) {

//                   Log.d("value is" , Listadobles2.get(i).valor_Medida.toString());}
//                   Listapdf.add(Listadobles2.get(i).getValor_Medida().toString());
                             String pdfprod1 = ListaProd1.get(i).Nombre_prod;
                                Double pdfcant1 = ListaProd1.get(i).Cant_prod;
                                Double pdfpre1 = ListaProd1.get(i).Precio_prod;
                                Double pdfimp=ListaProd1.get(i).Impuesto;
                                Double pdfRimp=ListaProd1.get(i).Resultado_Impuesto;
                                pdfval = ListaProd1.get(i).Resultado_valor;


                                ListaCant.add(pdfcant1);
                                ListaProd.add(pdfprod1);
                                ListaPre.add(pdfpre1);
                                Listavalor.add(pdfval);
                                listaVal2.add(pdfval);
                                ListaimP.add(pdfimp);
                                ListaRimp.add(pdfRimp);
                                Log.d("values",String.valueOf(ListaimP));
                                Log.d("v2",String.valueOf(ListaRimp));
                            }
                            sumarRe();

                            String sumaResultado= String.valueOf(sum);

//
//
//
//
                            bundle.putSerializable("Total1",sumaResultado);
                            bundle.putSerializable("listaProd1",ListaProd);
                            bundle.putSerializable("listaCant1",ListaCant);
                            bundle.putSerializable("listaPre1",ListaPre);
                            bundle.putSerializable("listaResultado",Listavalor);
                            bundle.putSerializable("ListaImp",ListaimP);
                            bundle.putSerializable("ListaRimp",ListaRimp);

//
//         bundle.putSerializable("Listapdf",Listapasar);
                            // bundle.putByteArray("wpa",outputStream.toByteArray());


                            intent.putExtras(bundle);
                            // pdfviewer pdfviewer2=new pdfviewer();





                            startActivity(intent);









                        }
                    });
                }

            }
        });




        tabLayout = findViewById(R.id.tablayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viepag, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 1:
                        tab.setText(getResources().getString(R.string.Products));



                        break;
                    case 0:
                        tab.setText(getResources().getString(R.string.Costumers));

                        break;
                    default:
                        tab.setText(getResources().getString(R.string.Pre_order));
                        badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorRappi));
                        badgeDrawable.setVisible(false);


                        //badgeDrawable.setNumber();
                        break;
                }
            }
        });

        tabLayoutMediator.attach();
        cardcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viepag.setCurrentItem(1);
            }
        });
        cardprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viepag.setCurrentItem(2);
            }
        });

        sharedViewModel.init();
        sharedViewModel.getResultado().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String resultado) {
                int i=Integer.parseInt(resultado);
//                if(badgeDrawable.hasNumber()) {
//
//                    badgeDrawable.setVisible(true);
//                    badgeDrawable.setNumber(ListaProd.size());
//                    badgeDrawable.setBadgeTextColor(ContextCompat.getColor(getApplicationContext(), R.color.mdtp_white));
//
//                    badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purplecolor));
//
//
//               }else {
//
//                    badgeDrawable.setVisible(false);
////                    badgeDrawable.setNumber(i);
////                    badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purplecolotransparentr));
////                    badgeDrawable.setBadgeTextColor(ContextCompat.getColor(getApplicationContext(), R.color.purplecolotransparentr));
//             }
//




            }
        });
          sharedViewModel.getTaxvalue().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                DecimalFormat formatter = new DecimalFormat("###,###,##0");
              TaxValue=s;

                Double imp = Double.parseDouble(s);
                Double Imp2 = imp / 100;
                Double Imp3 = 1 + Imp2;
                Double Imp4 = valorBruto* Imp3;


                String totalfac = String.valueOf(formatter.format(Imp4));

//
                txSubtotal2.setText(totalfac);


//                TaxValue=s;

            }
        });
        sharedViewModel.getCliente2().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                ClienteL=charSequence.toString();
                cardcli.setText(charSequence);
                cardcli.setTextSize(11);
                cardcli.setTextColor(getResources().getColor(R.color.colorNegrobrillante));
                cardcli.setIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_forward_24));
                cardcli.setIconTint(ColorStateList.valueOf(getResources().getColor(R.color.colorNegrobrillante)));
                cardcli.setIconGravity(MaterialButton.ICON_GRAVITY_END);
            }
        });
        sharedViewModel.getResultado().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String string) {

                //txproductobottom.setText(string);
            }
        });
        sharedViewModel.getprecio().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                PrecioL=charSequence.toString();
            }
        });
        sharedViewModel.getText().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                ProductoL=charSequence.toString();
                cardprod.setText(charSequence);
                cardprod.setTextSize(11);
                cardprod.setTextColor(getResources().getColor(R.color.colorNegrobrillante));
                cardprod.setIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_forward_24));
                cardprod.setIconTint(ColorStateList.valueOf(getResources().getColor(R.color.colorNegrobrillante)));
                cardprod.setIconGravity(MaterialButton.ICON_GRAVITY_END);
                //txproductobttom.setTextSize(14);
                //txproductobttom.setText(charSequence);
                //txproductobttom.setTextColor(getResources().getColor(R.color.colorNegrobrillante));
               // cardprod.setCardBackgroundColor(getResources().getColor(R.color.colorverdeesmeralda));
            }
        });
        sharedViewModel.getboton().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textovigilancia.setText(s);

            }
        });
        sharedViewModel.getbotonestado().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

           Estado1 =integer;

            }
        });
        sharedViewModel.getdias().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dias1=s;



            }
        });
        sharedViewModel.getdiasfinal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                diasq=integer;

            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);


    }
    public void guardarpreferencias3() {


        SharedPreferences creacion = getSharedPreferences
                ("creacionpreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = creacion.edit();
        editor
                .putInt("estado", Estado1)
                .putString("diasdepago1", dias1);
        //if(rbborrador.isChecked()) {
          //  editor.putString("Tipo", rbborrador.getText().toString());
        //}else if(rbcompra.isChecked()){
          //  editor.putString("Tipo", rbcompra.getText().toString());

        //}else {
          //  editor.putString("Tipo", rbventa.getText().toString());
       // }
        editor.apply();
        // editor.commit();




    }
    public double sumarRe()
    {
         sum = 0;
        for(int i = 0; i < ListaRimp.size(); i++)
            sum += ListaRimp.get(i);
        Log.d("TT:",String.valueOf(sum));
        return sum;

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
        //sharedViewModel.setCliente2(Cliente);

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

        Log.d("values:",String.valueOf(position));
        Log.d("values:",String.valueOf(currentnote));
        Log.d("values:",String.valueOf(Producto));
        Log.d("values:",String.valueOf(Cantidad));
        Log.d("values:",String.valueOf(Precio));
    }




    public void setListener(ClickInterface1 listener)
    {
        this.listener = listener ;
    }


}









