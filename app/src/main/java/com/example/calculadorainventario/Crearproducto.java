package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Crearproducto extends AppCompatActivity {
    DatabaseReference myrootDbaseref2;

    Button btmaterialinput;
    EditText txmaterialinput,cajaprecioproducto,cajaimpuestoprod,ritmocajaprod;
    CheckBox checkimp;
    String VarProducto, VarPrecio, VarEstado, VarRitmo,VarKey,VarImpuesto;
    FirebaseAuth mAuth;
    DatabaseReference ref;
    BottomNavigationView navclientenuevo;
    FloatingActionButton floatingActionButton5;
    MaterialCardView nprodbtpr,prbtpro2,taxbtpromenos,taxbtpromas,pacebtmenos,pacebtmas,descbtpro;
    TextView nprodtxpr,prtxpro2,taxtxpro,pacetxpro,desctxpro;



    ImageView back5,impuestomenosprod,impuestomasprod,ritmomenosprod,ritmomasprod;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearproducto);
        nprodbtpr=findViewById(R.id.nprobtpr);
        prbtpro2=findViewById(R.id.prbtpro2);
        taxbtpromas=findViewById(R.id.taxbtpromas);
        taxbtpromenos=findViewById(R.id.taxbtpromenos);
        pacebtmenos=findViewById(R.id.pacebtmenos);
        pacebtmas=findViewById(R.id.pacebtmas);
        nprodtxpr=findViewById(R.id.nprodtxpr);
        prtxpro2=findViewById(R.id.prtxpro2);
        taxtxpro=findViewById(R.id.taxtxpro);
        pacetxpro=findViewById(R.id.pacetxpro);
        descbtpro=findViewById(R.id.descbtpro);
        desctxpro=findViewById(R.id.desctxpro);


        btmaterialinput = (Button) findViewById(R.id.btmaterialinput);
        navclientenuevo=findViewById(R.id.navclientenuevo);
//        back5 = findViewById(R.id.back5);
        txmaterialinput = findViewById(R.id.txmaterialinput);
        cajaprecioproducto = findViewById(R.id.cajaprecioproducto);
        cajaimpuestoprod = findViewById(R.id.cajaimpuestoprod);
        ritmocajaprod = findViewById(R.id.ritmocajaprod);
        impuestomasprod = findViewById(R.id.impuestomasprod);
        impuestomenosprod = findViewById(R.id.impuestomenosprod);
        floatingActionButton5=findViewById(R.id.floatingActionButton5);
        ritmomenosprod = findViewById(R.id.ritmomenosprod);
        ritmomasprod = findViewById(R.id.ritmomasprod);
        checkimp = findViewById(R.id.checkimp);

        mAuth = FirebaseAuth.getInstance();


        myrootDbaseref2 = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        pacetxpro.setText("1");

        taxtxpro.setText("20");
        Recibirintents();
        navclientenuevo.setSelectedItemId(R.id.action_add);
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Crearproducto.this, fragments3.class);
                startActivity(i);
            }
        });
        descbtpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Description);
                int inputtype=1;
                AlertDialog(desctxpro,inputtext,inputtype);
            }
        });
        nprodbtpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Product_Name);
                int inputtype=1;
                AlertDialog(nprodtxpr,inputtext,inputtype);
            }
        });
        prbtpro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Price);
                int inputtype=2;
                AlertDialog(prtxpro2,inputtext,inputtype);

            }
        });
        navclientenuevo.setSelectedItemId(R.id.action_add);
        navclientenuevo.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
//                    case R.id.listaclientes2:
//
//                        startActivity(new Intent(getApplicationContext(), ingresodat.class));
//                        overridePendingTransition(0, 0);
//                        return true;
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), homeinvoice2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_more:
                        startActivity(new Intent(getApplicationContext(), InclientActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_add:
                        startActivity(new Intent(getApplicationContext(), Crearproducto.class));
                        overridePendingTransition(0, 0);
                        return true;
                    // case R.id.action_Pdf:
                    //   startActivity(new Intent(getApplicationContext(), pdfviewer.class));
                    // overridePendingTransition(0, 0);
                    //return true;
                }
                return false;
            }
        });

        if (VarProducto == null) {
            nprodtxpr.setText(getResources().getString(R.string.Product_Name).trim());
            prtxpro2.setText(getResources().getString(R.string.Price).trim());
            pacetxpro.setText("5");
            cajaimpuestoprod.setText("0");
            checkimp.setChecked(false);
        } else {
            nprodtxpr.setText(VarProducto);
            prtxpro2.setText(VarPrecio);
            pacetxpro.setText(VarRitmo);
            cajaimpuestoprod.setText(VarImpuesto);
            if (VarEstado.equals("SI") ) {
                checkimp.setChecked(true);
            } else {
                checkimp.setChecked(false);
            }
        }
        taxbtpromas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double getinput = Double.parseDouble(taxtxpro.getText().toString());
                Double valorprecio = Double.parseDouble(taxtxpro.getText().toString());
                Double valorritmo = 1.0;
                Double valornuevosuma = valorprecio + valorritmo;
                taxtxpro.setText(valornuevosuma + "");

            }
        });
        impuestomenosprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double getinput = Double.parseDouble(taxtxpro.getText().toString());
               Double valorprecio = Double.parseDouble(taxtxpro.getText().toString());
                Double valorritmo = 1.0;
                Double valornuevosuma = valorprecio - valorritmo;
                taxtxpro.setText(valornuevosuma + "");

            }
        });
        ritmomasprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double getinput = Double.parseDouble(pacetxpro.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput >=0&&getinput <10) {
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 5.0;
                    Double valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");


                } else if (getinput >= 10&&getinput<50) {
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 40.0;
                    Double valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");

                } else if (getinput >= 50 && getinput < 100) {
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 50.0;
                    Double valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");}

                else if (getinput >= 100 && getinput < 500) {
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 400.0;
                    Double valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");
                }else if(getinput >= 500 && getinput < 1000){
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 500.0;
                    Double valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");






                }
                // sharedViewModel.setDias(pagotext.getText().toString());
                //Constants.getSP(getContext()).setDIAS(crearritmo.getText().toString());
            }
        });
        ritmomenosprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double getinput = Double.parseDouble(pacetxpro.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput>0 && getinput<= 10) {
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 5.0;
                    Double valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");
                } else if (getinput ==50) {
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 40.0;
                    Double valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");



                } else if (getinput == 100) {
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 50.0;
                    Double valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");
                }else if(getinput>100&&getinput<=500){
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 400.0;
                    Double valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");}
                else if(getinput>500&&getinput<=1000){
                    Double valorprecio = Double.parseDouble(pacetxpro.getText().toString());
                    Double valorritmo = 500.0;
                    Double valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");

                }




                //sharedViewModel.setDias(pacetxpro.getText().toString());
                // Constants.getSP(getContext()).setDIAS(pacetxpro.getText().toString());
            }
        });
        pacetxpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Ritmo);
                int keynumber1=2;
                int keydecimal=8192;
                int inputtype=keynumber1+keydecimal;
                AlertDialog(pacetxpro,inputtext,inputtype);

            }
        });

        taxtxpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputtext;
                inputtext=getResources().getString(R.string.Tax);
                int keynumber1=2;
                int keydecimal=8192;
                int inputtype=keynumber1+keydecimal;
                AlertDialog(taxtxpro,inputtext,inputtype);
            }
        });




//        back5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        btmaterialinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////New Product////////
                if(VarKey==null){
                    String Material = nprodtxpr.getText().toString();
                    String id = mAuth.getCurrentUser().getUid();
                    DatabaseReference myrootbd2 = myrootDbaseref2.child("PRODUCTOS").child(id).push();

                    Map<String, Object> datosmaterialnuevo = new HashMap<>();
                    datosmaterialnuevo.put("TIPO_CUERO", Material);
                    datosmaterialnuevo.put("Key", myrootbd2.getKey());
                    datosmaterialnuevo.put("Impuesto", "0");
                    datosmaterialnuevo.put("Precio", prtxpro2.getText().toString());
                    datosmaterialnuevo.put("Ritmo", pacetxpro.getText().toString());
                    datosmaterialnuevo.put("Descripcion",desctxpro.getText().toString());

                    if (checkimp.isChecked()) {
                        datosmaterialnuevo.put("Estado_Imp", "SI");
                    } else {
                        datosmaterialnuevo.put("Estado_Imp", "NO");
                    }


                    myrootbd2.setValue(datosmaterialnuevo);

                    Toast.makeText(Crearproducto.this,"Producto Creado",Toast.LENGTH_SHORT).show();
                    ////////New Product////////
                    //////Actualizar Producto//////

                }else if(VarKey!=null) {
                    String id = mAuth.getCurrentUser().getUid();
                    ref = FirebaseDatabase.getInstance().getReference().child("PRODUCTOS").child(id).child(VarKey);
                    ref.child("Precio").setValue(prtxpro2.getText().toString());
                    if(checkimp.isChecked()){
                        ref.child("Estado_Imp").setValue("SI");}else { ref.child("Estado_Imp").setValue("NO");}
                    ref.child("Impuesto").setValue(taxtxpro.getText().toString());
                    ref.child("Ritmo").setValue(pacetxpro.getText().toString());
                    ref.child("TIPO_CUERO").setValue(nprodtxpr.getText().toString());
                    ref.child("Descripcion").setValue(desctxpro.getText().toString());
                    Toast.makeText(Crearproducto.this,"Producto Actualizado",Toast.LENGTH_SHORT).show();



                }
                onBackPressed();
                //////Actualizar Producto//////


            }
        });
    }
    private void Recibirintents(){

            VarProducto=getIntent().getStringExtra("producto");
            VarPrecio=getIntent().getStringExtra("precio");
            VarEstado=getIntent().getStringExtra("estado");
            VarRitmo=getIntent().getStringExtra("ritmo");
            VarKey=getIntent().getStringExtra("key");
            VarImpuesto=getIntent().getStringExtra("impuesto");


    }

    private void AlertDialog(final TextView textView, String inputtext1,int inputtype){
        AlertDialog.Builder builder=new AlertDialog.Builder(Crearproducto.this,R.style.Theme_MaterialComponents_Dialog_Alert);
        ;
        final View view= LayoutInflater.from(Crearproducto.this).inflate(R.layout.edittextdialog,(ConstraintLayout)Crearproducto.this.findViewById(R.id.parentconstrait));
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

    }}
