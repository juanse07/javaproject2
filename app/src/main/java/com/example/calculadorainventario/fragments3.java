package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

import com.example.calculadorainventario.Constructores.NoteProducto;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
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
import java.util.Map;

public class fragments3 extends AppCompatActivity implements ClickInterface1 {
    ViewPager2 viepag;
    TabLayout tabLayout;
    BadgeDrawable badgeDrawable, badgeDrawable2;
    ImageView back2, arrowchange;
    Button btnproducto, btncliente, btcompra, btnpdf;
    MaterialButton cardprod, cardcli, cardprod3;
    RelativeLayout relativev, Relative2, relativigilancia;
    ConstraintLayout constlay;
    BottomNavigationView navcat;
    LinearLayout linprod, lincli;
    Double ValorImp, ValorDesc,ValorImp2;
    Double valorBruto, valorNeto,valorBruto2,Valortax1,Valortax2,ValorDisc;
    String PrecioL, ProductoL, ClienteL, TaxValue, DiscountValue,TaxValue2,tax2,tax1,pdfphone,pdfemail,pdfaddress,pdfcity,pdfcliente;

    ArrayList<String> Listapdf;
    ArrayList<String> ListaProd;
    ArrayList<String> ListaDesc;
    ArrayList<Double> ListaCant;
    ArrayList<Double> ListaPre;
    ArrayList<Double> Listavalor;
    ArrayList<Double> ListaimP;
    ArrayList<Double> ListaRimp;
    ArrayList<Double> listaVal2, Listacantidades;
    Double pdfval;
    Map<String, String> datosparapdf;

    double sum;

    NoteProdViewModel noteProdViewModel;
    List<NoteProducto> ListaProd1;


    TextView txclientebotton,txSubtotal4, txproductobttom, txpreciobottom, title4, textView38, textview30, textView50, txSubtotal, txSubtotal2, txSubtotal3;
    String Radiob;
    TextView textovigilancia;
    CardView card_vigilancia;


    private ClickInterface1 listener;
    int Estado1;
    String dias1, fechafinal;
    int diasq;


    private SharedViewModel sharedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments3);


        viepag = findViewById(R.id.viepag);
        back2 = findViewById(R.id.back2);
        navcat = findViewById(R.id.navcat);
        //relativev=findViewById(R.id.relativev);
        btncliente = findViewById(R.id.btncliente);
        btnproducto = findViewById(R.id.btnproducto);
        // Relative2=findViewById(R.id.Relative2);
        title4 = findViewById(R.id.title4);
        btcompra = findViewById(R.id.btcompra);
        btnpdf = findViewById(R.id.btnpdf);
        cardprod3 = findViewById(R.id.cardprod3);
        textView50 = findViewById(R.id.textView50);
        txSubtotal = findViewById(R.id.txsubtotal);
        txSubtotal2 = findViewById(R.id.txsubtotal2);
        txSubtotal4 = findViewById(R.id.txsubtotal4);

        arrowchange = findViewById(R.id.arrowchange);
        //txproductobttom=findViewById(R.id.txproductobttom);
        relativigilancia = findViewById(R.id.relativigilancia);
        // txpreciobottom=findViewById(R.id.txpreciobottom);
        textovigilancia = findViewById(R.id.textovigilancia);
        card_vigilancia = findViewById(R.id.card_vigilancia);
        // lincli=findViewById(R.id.lincli);
        //linprod=findViewById(R.id.linprod);
        cardcli = findViewById(R.id.cardcli);
        cardprod = findViewById(R.id.cardprod);
        textView38 = findViewById(R.id.textView38);
        textview30 = findViewById(R.id.textView30);
        txSubtotal3 = findViewById(R.id.txsubtotal3);




        TaxValue = "0";
        TaxValue2 = "0";
        valorBruto = 0.0;
        valorBruto2 = 0.0;


//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");
//
//        final String fechacComplString = fecc.format(calendar.getTime());
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        noteProdViewModel = new ViewModelProvider(this).get(NoteProdViewModel.class);

        noteProdViewModel.getSumTotal().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {




                if (aDouble == null) {
                    valorBruto=0.0;

                    txSubtotal.setText("0");
                    txSubtotal3.setText("0");
                    txSubtotal2.setText("0");
                    txSubtotal4.setText("0");
                    Log.d("valordevb",String.valueOf(aDouble));
                } else {
                    valorBruto = aDouble;

                   ValorImp= taxesvisual(TaxValue,valorBruto,txSubtotal);
                   ValorDesc= Discountvisor(DiscountValue,valorBruto,txSubtotal2);
                   ValorImp2= taxesvisual(TaxValue2,valorBruto,txSubtotal4);
                    DecimalFormat formatter = new DecimalFormat("###,###,##0");
                    String totalfac = String.valueOf(formatter.format(valorBruto));



                    txSubtotal3.setText(totalfac);

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
                if (aDouble == null) {
                    badgeDrawable.setVisible(false);

                } else {
                    Double sumacant = aDouble;

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
                ListaProd1 = noteProductos;
//

            }
        });


        viepag.setAdapter(new FragmentAdapter1(this));


        final RecyclerView.Adapter adapter = viepag.getAdapter();
        String first = "PyMESoft";
        String next = "<font color='#03bfa5'>FActuras</font>";
        title4.setText(Html.fromHtml(first + next));
        title4.setTextColor(getResources().getColor(R.color.colorBlancox));
        String vigilaestado = "1";

        vigilaestado = Constants.getSP(fragments3.this).getRBBORRADOR();
        Log.d("estado", vigilaestado);

        textovigilancia.setText(Constants.getSP(fragments3.this).getRBBORRADOR());
        if (vigilaestado.toString().equals("1")) {
            textovigilancia.setText(getResources().getString(R.string.Sales));
            textovigilancia.setTextColor(getResources().getColor(R.color.colorDarkBlue));
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.bluecolor));
        } else if (vigilaestado.equals("2")) {
            textovigilancia.setText(getResources().getString(R.string.Receipts));
            textovigilancia.setTextColor(getResources().getColor(R.color.purplecolor));
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.Moradoclaro));

        } else if (vigilaestado.equals("3")) {
            textovigilancia.setText(getResources().getString(R.string.Draft));
            textovigilancia.setTextColor(getResources().getColor(R.color.colorGrisoscuro));
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorGris));
        }


        arrowchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textovigilancia.getText().toString().equals(getResources().getString(R.string.Sales))) {
                    textovigilancia.setText(getResources().getString(R.string.Receipts));
                    textovigilancia.setTextColor(getResources().getColor(R.color.purplecolor));

                    card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.Moradoclaro));
                } else if (textovigilancia.getText().toString().equals(getResources().getString(R.string.Receipts))) {
                    textovigilancia.setText(getResources().getString(R.string.Draft));
                    textovigilancia.setTextColor(getResources().getColor(R.color.colorGrisoscuro));
                    card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorGris));
                } else if (textovigilancia.getText().toString().equals(getResources().getString(R.string.Draft))) {
                    textovigilancia.setText(getResources().getString(R.string.Sales));
                    textovigilancia.setTextColor(getResources().getColor(R.color.colorDarkBlue));
                    card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.bluecolor));
                }

            }
        });


        viepag.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(final int position) {
                super.onPageSelected(position);
                if (position == 1) {
                    btnproducto.bringToFront();
                    btnproducto.setVisibility(View.VISIBLE);
                    cardprod3.setVisibility(View.GONE);
                    cardprod.setVisibility(View.VISIBLE);
                    cardcli.setVisibility(View.GONE);
                    textView38.setVisibility(View.VISIBLE);
                    txSubtotal2.setVisibility(View.GONE);
                    txSubtotal.setVisibility(View.GONE);
                    txSubtotal3.setVisibility(View.GONE);
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
                            Intent intent2 = new Intent(fragments3.this, Crearproducto.class);
                            Log.d("array:", String.valueOf(ListaProd1.size()));

                            startActivity(intent2);
                        }
                    });

                } else if (position == 0) {
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
                    txSubtotal2.setVisibility(View.GONE);
                    txSubtotal.setVisibility(View.GONE);
                    txSubtotal3.setVisibility(View.GONE);

                    btnproducto.setVisibility(View.GONE);
                    btcompra.setVisibility(View.GONE);
                    btncliente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(fragments3.this, InclientActivity.class);
                            startActivity(intent);

                        }
                    });


                } else {
                    btnpdf.bringToFront();
                    txSubtotal2.bringToFront();
                    txSubtotal.bringToFront();
                    txSubtotal3.bringToFront();
                    btnpdf.setVisibility(View.VISIBLE);
//                    btcompra.bringToFront();
//                    cardprod3.bringToFront();
                    btncliente.setVisibility(View.GONE);
                    btnproducto.setVisibility(View.GONE);
                    cardprod.setVisibility(View.GONE);
                    textView38.setVisibility(View.GONE);
                    textView50.setVisibility(View.VISIBLE);
                    textview30.setVisibility(View.GONE);
                    txSubtotal2.setVisibility(View.VISIBLE);
                    txSubtotal.setVisibility(View.VISIBLE);
                    txSubtotal3.setVisibility(View.VISIBLE);
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
                            Bundle bundle = new Bundle();
                            bundle.putString("Cliente", ClienteL);
                            bundle.putString("Producto", ProductoL);
                            //bundle.putString("Precio", txpreciobottom.getText().toString());
                            bundle.putString("Precio", PrecioL);
                            bundle.putString("Diasdepago", dias1);
                            bundle.putDouble("Diasdepagoint", diasq);


                            bundle.putString("radiobuton", textovigilancia.getText().toString());

                            intent.putExtras(bundle);

                            startActivity(intent);
                        }
                    });
                    btnpdf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Constants.getSP(fragments3.this).setDIAS(dias1);
                            if (textovigilancia.getText().toString().equals(getResources().getString(R.string.Sales))) {
                                Constants.getSP(fragments3.this).setRBBORRADOR("1");
                            } else if (textovigilancia.getText().toString().equals(getResources().getString(R.string.Receipts))) {
                                Constants.getSP(fragments3.this).setRBBORRADOR("2");

                            } else if (textovigilancia.getText().toString().equals(getResources().getString(R.string.Draft))) {
                                Constants.getSP(fragments3.this).setRBBORRADOR("3");

                            }
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");


                            final String fechacComplString = fecc.format(calendar.getTime());
                            calendar.add(Calendar.DATE, diasq);
                            fechafinal = fecc.format(calendar.getTime());
                            String Pdftipo = Constants.getSP(fragments3.this).getPDFPREFERENCE();
                            int pdfposition = Constants.getSP(fragments3.this).getPDFPOSITION();
                            Log.d("valorde", Pdftipo);



                                Intent intent;


                                intent = new Intent(fragments3.this, pdfviewer3.class);
                                pdfbutton(intent);

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
                int i = Integer.parseInt(resultado);
//
//


            }
        });
        sharedViewModel.getDiscountValue().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                DiscountValue = s;

                  ValorDesc=Discountvisor(DiscountValue,valorBruto,txSubtotal2);



            }



        });
        sharedViewModel.getTaxvalue().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TaxValue = s;

               ValorImp= taxesvisual(s,valorBruto,txSubtotal);


            }

//                TaxValue=s;


        });
        sharedViewModel.getTaxvalue2().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TaxValue2=s;

                ValorImp2= taxesvisual(TaxValue2,valorBruto,txSubtotal4);



//
            }

        });
        sharedViewModel.getCliente2().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                ClienteL = charSequence.toString();
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
                PrecioL = charSequence.toString();
            }
        });
        sharedViewModel.getText().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                ProductoL = charSequence.toString();
                cardprod.setText(charSequence);
                cardprod.setTextSize(11);
                cardprod.setTextColor(getResources().getColor(R.color.colorNegrobrillante));
                cardprod.setIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_forward_24));
                cardprod.setIconTint(ColorStateList.valueOf(getResources().getColor(R.color.colorNegrobrillante)));
                cardprod.setIconGravity(MaterialButton.ICON_GRAVITY_END);

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

                Estado1 = integer;

            }
        });
        sharedViewModel.getdias().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dias1 = s;


            }
        });
        sharedViewModel.getpdfaddress().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                pdfaddress=s;
            }
        });
        sharedViewModel.getpdfemail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                pdfemail=s;
            }
        });
        sharedViewModel.getpdfphone().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                pdfphone=s;

            }
        });
        sharedViewModel.getpdfcity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                pdfcity=s;
            }
        });

        sharedViewModel.getdiasfinal().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                diasq = Integer.parseInt(s);
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

    public double sumarRe() {
        sum = 0;
        for (int i = 0; i < ListaRimp.size(); i++)
            sum += ListaRimp.get(i);
        Log.d("TT:", String.valueOf(sum));
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

        Log.d("values:", String.valueOf(position));
        Log.d("values:", String.valueOf(currentnote));
        Log.d("values:", String.valueOf(Producto));
        Log.d("values:", String.valueOf(Cantidad));
        Log.d("values:", String.valueOf(Precio));
    }


    public void setListener(ClickInterface1 listener) {
        this.listener = listener;
    }

    public void pdfbutton(Intent intent) {
        Constants.getSP(this).setDISCOUNT(DiscountValue);
        Constants.getSP(this).setTAX1TX(TaxValue);
        Constants.getSP(this).setTAX2TX(TaxValue2);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");

        final String fechacComplString = fecc.format(calendar.getTime());
        calendar.add(Calendar.DATE, diasq);
        fechafinal = fecc.format(calendar.getTime());


        //  ArrayList<Note> Lista78;
        //Lista78=(ArrayList<Note>)allnotes3;
        Bundle bundle = new Bundle();
        //intent.putExtra("Lista6", (Parcelable) allnotes3);

        bundle.putSerializable("Diasdepago2", dias1);
        bundle.putSerializable("Fecha2", fechafinal);
//
        bundle.putSerializable("Fecha1", fechacComplString.toString());
//
        bundle.putSerializable("Nombre1", ClienteL);
        bundle.putSerializable("Producto1", ProductoL);
        bundle.putSerializable("pdfcity",pdfcity);
        bundle.putSerializable("pdfaddress",pdfaddress);
        bundle.putSerializable("pdfphone",pdfphone);
        bundle.putSerializable("pdfemail",pdfemail);
        Double valorbr;
        Double valornet;

        valorbr = noteProdViewModel.getSumTotal().getValue();
        valornet = valorbr - ValorDesc + ValorImp+ValorImp2;

        Log.d("valoto", String.valueOf(valornet));
        bundle.putSerializable("valorneto", valornet);
        bundle.putSerializable("valorbruto", valorbr);
        bundle.putSerializable("valorimp", ValorImp);
        bundle.putSerializable("valordesc", ValorDesc);
        bundle.putSerializable("valorimp2",ValorImp2);
        bundle.putSerializable("impuestopercent", Double.parseDouble(TaxValue));
        bundle.putSerializable("descpercent", Double.parseDouble(DiscountValue));
        bundle.putSerializable("impuesto2percent", Double.parseDouble(TaxValue2));


        String comprobarestado = textovigilancia.getText().toString();
//        if (comprobarestado.equals(getResources().getString(R.string.Receipts))) {
//            bundle.putSerializable("Estado1", "2");
//        } else if (comprobarestado.equals(getResources().getString(R.string.Sales))) {
//            bundle.putSerializable("Estado1", "1");
//
//        } else if (comprobarestado.equals(getResources().getString(R.string.Draft))) {
//            bundle.putSerializable("Estado1", "3");
//
//        }
        if (comprobarestado.equals(getResources().getString(R.string.Receipts))) {
            bundle.putSerializable("Estado1",getResources().getString(R.string.Receipts));
        } else if (comprobarestado.equals(getResources().getString(R.string.Sales))) {
            bundle.putSerializable("Estado1", getResources().getString(R.string.Sales));

        } else if (comprobarestado.equals(getResources().getString(R.string.Draft))) {
            bundle.putSerializable("Estado1", getResources().getString(R.string.Draft));

        }


        ListaProd = new ArrayList<>();
        ListaCant = new ArrayList<>();
        ListaPre = new ArrayList<>();
        Listavalor = new ArrayList<Double>();
        listaVal2 = new ArrayList<>();
        ListaRimp = new ArrayList<>();
        ListaimP = new ArrayList<>();
        ListaDesc = new ArrayList<>();
//                            double sum = 0;
//                            for(int i = 0; i < m.size(); i++)
//                                sum += m.get(i);
//                            return sum;


        for (int i = 0; i < ListaProd1.size(); i++) {


//                   Log.d("value is" , Listadobles2.get(i).valor_Medida.toString());}
//                   Listapdf.add(Listadobles2.get(i).getValor_Medida().toString());
            String pdfprod1 = ListaProd1.get(i).Nombre_prod;
            Double pdfcant1 = ListaProd1.get(i).Cant_prod;
            Double pdfpre1 = ListaProd1.get(i).Precio_prod;
            Double pdfimp = ListaProd1.get(i).Impuesto;
            Double pdfRimp = ListaProd1.get(i).Resultado_Impuesto;
            pdfval = ListaProd1.get(i).Resultado_valor;
            String pdfDesc = ListaProd1.get(i).getDescripcion();
            String pdfpre2;
            String pdfcant2;
            String pdfnom2;


            ListaCant.add(pdfcant1);
            ListaProd.add(pdfprod1);
            ListaPre.add(pdfpre1);
            Listavalor.add(pdfval);
            listaVal2.add(pdfval);
            ListaimP.add(pdfimp);
            ListaRimp.add(pdfRimp);
            ListaDesc.add(pdfDesc);
            Log.d("values", String.valueOf(ListaimP));
            Log.d("v2", String.valueOf(ListaRimp));
//                               datosparapdf=new HashMap<>();
//                                datosparapdf.put("Nombre_prod",ListaProd1.get(i).Nombre_prod);
//                                datosparapdf.put("Cant_prod",String.valueOf(ListaProd1.get(i).Cant_prod));
//                                datosparapdf.put("Precio_prod",String.valueOf(ListaProd1.get(i).Precio_prod));
//                                datosparapdf.put("Descripcion",ListaProd1.get(i).Descripcion);

        }
        sumarRe();

        String sumaResultado = String.valueOf(sum);

//
//
//
//
        bundle.putSerializable("Total1", sumaResultado);
        bundle.putSerializable("listaProd1", ListaProd);
        bundle.putSerializable("listaCant1", ListaCant);
        bundle.putSerializable("listaPre1", ListaPre);
        bundle.putSerializable("listaResultado", Listavalor);
        bundle.putSerializable("ListaImp", ListaimP);
        bundle.putSerializable("ListaRimp", ListaRimp);
        bundle.putSerializable("ListaDesc", ListaDesc);

//
//         bundle.putSerializable("Listapdf",Listapasar);
        // bundle.putByteArray("wpa",outputStream.toByteArray());

        intent.putExtras(bundle);





    }
    public Double taxesvisual(String s,Double valorBruto, TextView textView) {


    Double ValorImp;
    ValorImp = 0.0;


    if (s != null) {

        DecimalFormat formatter = new DecimalFormat("###,###,##0");



        Double imp = Double.parseDouble(s);
        Double Imp2 = imp / 100;
        Double Imp3 = 1 + Imp2;
        Double Imp4 = valorBruto * Imp3;
        ValorImp = valorBruto * Imp2;
        String tax = String.valueOf(formatter.format(ValorImp));




//
        textView.setText(tax);

    }

return ValorImp;


    }
    public Double Discountvisor(String s,Double valorBruto, TextView textview){
        if (s != null) {


            DecimalFormat formatter = new DecimalFormat("###,###,##0");


            Double disc = Double.parseDouble(s);
            Double disc2 = disc / 100;
            ValorDesc = valorBruto * disc2;


            String totalfac = String.valueOf(formatter.format(ValorDesc));


//
            textview.setText(totalfac);
        }
        return ValorDesc;

    }
    public void traerpreferences1(){

    }
}









