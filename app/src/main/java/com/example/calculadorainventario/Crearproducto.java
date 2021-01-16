package com.example.calculadorainventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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



    ImageView back5,impuestomenosprod,impuestomasprod,ritmomenosprod,ritmomasprod;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearproducto);


        btmaterialinput = (Button) findViewById(R.id.btmaterialinput);
        back5 = findViewById(R.id.back5);
        txmaterialinput = findViewById(R.id.txmaterialinput);
        cajaprecioproducto = findViewById(R.id.cajaprecioproducto);
        cajaimpuestoprod = findViewById(R.id.cajaimpuestoprod);
        ritmocajaprod = findViewById(R.id.ritmocajaprod);
        impuestomasprod = findViewById(R.id.impuestomasprod);
        impuestomenosprod = findViewById(R.id.impuestomenosprod);
        ritmomenosprod = findViewById(R.id.ritmomenosprod);
        ritmomasprod = findViewById(R.id.ritmomasprod);
        checkimp = findViewById(R.id.checkimp);
        mAuth = FirebaseAuth.getInstance();


        myrootDbaseref2 = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        ritmocajaprod.setText("0");

        cajaimpuestoprod.setText("0");
        Recibirintents();
        if (VarProducto == null) {
            txmaterialinput.setText("".trim());
            cajaprecioproducto.setText("".trim());
            ritmocajaprod.setText("5");
            cajaimpuestoprod.setText("0");
            checkimp.setChecked(false);
        } else {
            txmaterialinput.setText(VarProducto);
            cajaprecioproducto.setText(VarPrecio);
            ritmocajaprod.setText(VarRitmo);
            cajaimpuestoprod.setText(VarImpuesto);
            if (VarEstado.equals("SI") ) {
                checkimp.setChecked(true);
            } else {
                checkimp.setChecked(false);
            }
        }
        impuestomasprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(cajaimpuestoprod.getText().toString());
                int valorprecio = Integer.parseInt(cajaimpuestoprod.getText().toString());
                int valorritmo = 1;
                int valornuevosuma = valorprecio + valorritmo;
                cajaimpuestoprod.setText(valornuevosuma + "");

            }
        });
        impuestomenosprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(cajaimpuestoprod.getText().toString());
                int valorprecio = Integer.parseInt(cajaimpuestoprod.getText().toString());
                int valorritmo = 1;
                int valornuevosuma = valorprecio - valorritmo;
                cajaimpuestoprod.setText(valornuevosuma + "");

            }
        });
        ritmomasprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(ritmocajaprod.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput >=0&&getinput <10) {
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 5;
                    int valornuevosuma = valorprecio + valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");


                } else if (getinput >= 10&&getinput<50) {
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 40;
                    int valornuevosuma = valorprecio + valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");

                } else if (getinput >= 50 && getinput < 100) {
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 50;
                    int valornuevosuma = valorprecio + valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");}

                else if (getinput >= 100 && getinput < 500) {
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 400;
                    int valornuevosuma = valorprecio + valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");
                }else if(getinput >= 500 && getinput < 1000){
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 500;
                    int valornuevosuma = valorprecio + valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");






                }
                // sharedViewModel.setDias(pagotext.getText().toString());
                //Constants.getSP(getContext()).setDIAS(crearritmo.getText().toString());
            }
        });
        ritmomenosprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(ritmocajaprod.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput>0 && getinput<= 10) {
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 5;
                    int valornuevosuma = valorprecio - valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");
                } else if (getinput ==50) {
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 40;
                    int valornuevosuma = valorprecio - valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");



                } else if (getinput == 100) {
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 50;
                    int valornuevosuma = valorprecio - valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");
                }else if(getinput>100&&getinput<=500){
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 400;
                    int valornuevosuma = valorprecio - valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");}
                else if(getinput>500&&getinput<=1000){
                    int valorprecio = Integer.parseInt(ritmocajaprod.getText().toString());
                    int valorritmo = 500;
                    int valornuevosuma = valorprecio - valorritmo;
                    ritmocajaprod.setText(valornuevosuma + "");

                }



                //sharedViewModel.setDias(ritmocajaprod.getText().toString());
                // Constants.getSP(getContext()).setDIAS(ritmocajaprod.getText().toString());
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
                    String Material = txmaterialinput.getText().toString();
                    String id = mAuth.getCurrentUser().getUid();
                    DatabaseReference myrootbd2 = myrootDbaseref2.child("PRODUCTOS").child(id).push();

                    Map<String, Object> datosmaterialnuevo = new HashMap<>();
                    datosmaterialnuevo.put("TIPO_CUERO", Material);
                    datosmaterialnuevo.put("Key", myrootbd2.getKey());
                    datosmaterialnuevo.put("Impuesto", cajaimpuestoprod.getText().toString());
                    datosmaterialnuevo.put("Precio", cajaprecioproducto.getText().toString());
                    datosmaterialnuevo.put("Ritmo", ritmocajaprod.getText().toString());
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
                    ref.child("Precio").setValue(cajaprecioproducto.getText().toString());
                    if(checkimp.isChecked()){
                        ref.child("Estado_Imp").setValue("SI");}else { ref.child("Estado_Imp").setValue("NO");}
                    ref.child("Impuesto").setValue(cajaimpuestoprod.getText().toString());
                    ref.child("Ritmo").setValue(ritmocajaprod.getText().toString());
                    ref.child("TIPO_CUERO").setValue(txmaterialinput.getText().toString());
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
