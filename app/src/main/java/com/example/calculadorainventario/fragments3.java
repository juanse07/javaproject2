package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
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
import android.renderscript.ScriptGroup;
import android.text.Html;
import android.view.Gravity;
import android.view.MotionEvent;
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

import java.util.ArrayList;

public class fragments3 extends AppCompatActivity implements ClickInterface1 {
    ViewPager2 viepag;
    TabLayout tabLayout;
    BadgeDrawable badgeDrawable;
    ImageView back2,arrowchange;
    Button btnproducto, btncliente,btcompra;
    MaterialButton cardprod,cardcli;
    RelativeLayout relativev,Relative2,relativigilancia;
    ConstraintLayout constlay;
    BottomNavigationView navcat;
    LinearLayout linprod, lincli;
    String PrecioL,ProductoL,ClienteL;


    TextView txclientebotton, txproductobttom,txpreciobottom,title4,textView38,textview30;
    String Radiob;
    TextView textovigilancia;
    CardView card_vigilancia;

    private ClickInterface1 listener ;
    int Estado1;
    String dias1;
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






        viepag.setAdapter(new FragmentAdapter1(this));




        final RecyclerView.Adapter adapter = viepag.getAdapter();
        String first = "PyMESoft";
        String next = "<font color='#1D2E4A'>FActuras</font>";
        title4.setText(Html.fromHtml(first + next));

        textovigilancia.setText(Constants.getSP(fragments3.this).getRBBORRADOR());
        if (textovigilancia.getText().toString().equals("Venta")) {
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
        }
        else if(textovigilancia.getText().toString().equals("Compra")){
            //card_operacion.setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorDarkRed));

        }else if(textovigilancia.getText().toString().equals("Borrador")){
            card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }


       arrowchange.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(textovigilancia.getText().toString().equals("Venta")){
                   textovigilancia.setText("Compra");
                   card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorDarkRed));
               }else  if(textovigilancia.getText().toString().equals("Compra")){
                   textovigilancia.setText("Borrador");
                   card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
               }else  if(textovigilancia.getText().toString().equals("Borrador")){
                   textovigilancia.setText("Venta");
                   card_vigilancia.setCardBackgroundColor(getResources().getColor(R.color.colorDarkBlue));
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
                    cardprod.setVisibility(View.VISIBLE);
                    cardcli.setVisibility(View.GONE);
                    textView38.setVisibility(View.VISIBLE);
                    textview30.setVisibility(View.GONE);
                    //linprod.setVisibility(View.VISIBLE);
                    //lincli.setVisibility(View.INVISIBLE);
                    //Relative2.setVisibility(View.VISIBLE);
                    btncliente.setVisibility(View.GONE);
                    btcompra.setVisibility(View.GONE);
                    btnproducto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent2=new Intent(fragments3.this,Crearproducto.class);

                            startActivity(intent2);
                        }
                    });

                }else if(position==0){
                    btncliente.bringToFront();
                    //Relative2.setVisibility(View.VISIBLE);
                    btncliente.setVisibility(View.VISIBLE);
                    cardprod.setVisibility(View.GONE);
                    cardcli.setVisibility(View.VISIBLE);
                    textView38.setVisibility(View.GONE);
                    textview30.setVisibility(View.VISIBLE);
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

                    btcompra.bringToFront();
                    btncliente.setVisibility(View.GONE);
                    btnproducto.setVisibility(View.GONE);
                    cardprod.setVisibility(View.GONE);
                    textView38.setVisibility(View.GONE);
                    textview30.setVisibility(View.GONE);
                    cardcli.setVisibility(View.GONE);
                    btcompra.setVisibility(View.VISIBLE);
                    //Relative2.setVisibility(View.GONE);
                    btcompra.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NoteViewModel noteViewModel;
                            noteViewModel = new ViewModelProvider(fragments3.this).get(NoteViewModel.class);
                           // noteViewModel.DeleteAll();

                            guardarpreferencias3();
                            Constants.getSP(fragments3.this).setDIAS(dias1);
                            Constants.getSP(fragments3.this).setRBBORRADOR(textovigilancia.getText().toString());




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
                }

            }
        });




        tabLayout = findViewById(R.id.tablayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viepag, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 1:
                        tab.setText("Catálogo");


                        break;
                    case 0:
                        tab.setText("Clientes");

                        break;
                    default:
                        tab.setText("Prefactura");
                        badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorRappi));
                        badgeDrawable.setVisible(true);

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
       sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
       // sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        sharedViewModel.init();
        sharedViewModel.getResultado().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String resultado) {
                int i=Integer.parseInt(resultado);
                if(badgeDrawable.hasNumber()){
                    badgeDrawable.setNumber(i+i);


                }else {


                    badgeDrawable.setNumber(i);
                }





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




    public void setListener(ClickInterface1 listener)
    {
        this.listener = listener ;
    }


}








