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
    MaterialCardView nprodbtpr,prbtpro2,taxbtpromenos,taxbtpromas,pacebtmenos,pacebtmas;
    TextView nprodtxpr,prtxpro2,taxtxpro,pacetxpro;



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


        btmaterialinput = (Button) findViewById(R.id.btmaterialinput);
        navclientenuevo=findViewById(R.id.navclientenuevo);
        back5 = findViewById(R.id.back5);
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
        nprodbtpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext(),R.style.Theme_MaterialComponents_Dialog_Alert);
                ;
                final View view= LayoutInflater.from(v.getContext()).inflate(R.layout.edittextdialog,(ConstraintLayout)v.findViewById(R.id.parentconstrait));
                builder.setView(view);
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setInputType(InputType.TYPE_CLASS_TEXT);
//                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setText(holder.txcatprice.getText().toString());
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).requestFocus();
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHint(view.getResources().getString(R.string.Product_Name));
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

                        nprodtxpr.setText( ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).getText().toString());

                        alertDialog.dismiss();

                    }
                });

            }
        });
        prbtpro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext(),R.style.Theme_MaterialComponents_Dialog_Alert);
                ;
                final View view= LayoutInflater.from(v.getContext()).inflate(R.layout.edittextdialog,(ConstraintLayout)v.findViewById(R.id.parentconstrait));
                builder.setView(view);
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setInputType(InputType.TYPE_CLASS_TEXT);
//                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setText(holder.txcatprice.getText().toString());
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).requestFocus();
                ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).setHint(view.getResources().getString(R.string.Price));
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

                        prtxpro2.setText( ((TextInputEditText) view.findViewById(R.id.edtxeditdialog)).getText().toString());

                        alertDialog.dismiss();

                    }
                });

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
            nprodtxpr.setText("".trim());
            prtxpro2.setText("".trim());
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
                int getinput = Integer.parseInt(taxtxpro.getText().toString());
                int valorprecio = Integer.parseInt(taxtxpro.getText().toString());
                int valorritmo = 1;
                int valornuevosuma = valorprecio + valorritmo;
                taxtxpro.setText(valornuevosuma + "");

            }
        });
        impuestomenosprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(taxtxpro.getText().toString());
                int valorprecio = Integer.parseInt(taxtxpro.getText().toString());
                int valorritmo = 1;
                int valornuevosuma = valorprecio - valorritmo;
                taxtxpro.setText(valornuevosuma + "");

            }
        });
        ritmomasprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(pacetxpro.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput >=0&&getinput <10) {
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 5;
                    int valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");


                } else if (getinput >= 10&&getinput<50) {
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 40;
                    int valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");

                } else if (getinput >= 50 && getinput < 100) {
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 50;
                    int valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");}

                else if (getinput >= 100 && getinput < 500) {
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 400;
                    int valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");
                }else if(getinput >= 500 && getinput < 1000){
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 500;
                    int valornuevosuma = valorprecio + valorritmo;
                    pacetxpro.setText(valornuevosuma + "");






                }
                // sharedViewModel.setDias(pagotext.getText().toString());
                //Constants.getSP(getContext()).setDIAS(crearritmo.getText().toString());
            }
        });
        ritmomenosprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(pacetxpro.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput>0 && getinput<= 10) {
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 5;
                    int valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");
                } else if (getinput ==50) {
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 40;
                    int valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");



                } else if (getinput == 100) {
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 50;
                    int valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");
                }else if(getinput>100&&getinput<=500){
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 400;
                    int valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");}
                else if(getinput>500&&getinput<=1000){
                    int valorprecio = Integer.parseInt(pacetxpro.getText().toString());
                    int valorritmo = 500;
                    int valornuevosuma = valorprecio - valorritmo;
                    pacetxpro.setText(valornuevosuma + "");

                }



                //sharedViewModel.setDias(pacetxpro.getText().toString());
                // Constants.getSP(getContext()).setDIAS(pacetxpro.getText().toString());
            }
        });




        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
                    datosmaterialnuevo.put("Impuesto", taxtxpro.getText().toString());
                    datosmaterialnuevo.put("Precio", prtxpro2.getText().toString());
                    datosmaterialnuevo.put("Ritmo", pacetxpro.getText().toString());
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
}
