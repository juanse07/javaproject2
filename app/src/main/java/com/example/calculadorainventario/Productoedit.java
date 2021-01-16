package com.example.calculadorainventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Productoedit extends AppCompatActivity {
    EditText cardnombre2, cardtel2,crearritmo;
    Button btactualizarprod;
    FirebaseAuth mAuth;
    ImageView btcrearmas,btcrearmenos;
    DatabaseReference ref;
    String Producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productoedit);
        cardnombre2=findViewById(R.id.cardnombre2);
        cardtel2=findViewById(R.id.cardtel2);
        btactualizarprod=findViewById(R.id.btactualizarprod);
        crearritmo=findViewById(R.id.crearritmo);
        btcrearmas=findViewById(R.id.btcrearmas);
        btcrearmenos=findViewById(R.id.btcrearmenos);
        Producto=getIntent().getStringExtra("producto");
        mAuth=FirebaseAuth.getInstance();


        cardnombre2.setText(getIntent().getStringExtra("producto"));
        crearritmo.setText("0");
        btcrearmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(crearritmo.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput >=0&&getinput <10) {
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 5;
                    int valornuevosuma = valorprecio + valorritmo;
                    crearritmo.setText(valornuevosuma + "");


                } else if (getinput >= 10&&getinput<50) {
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 40;
                    int valornuevosuma = valorprecio + valorritmo;
                    crearritmo.setText(valornuevosuma + "");

                } else if (getinput >= 50 && getinput < 100) {
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 50;
                    int valornuevosuma = valorprecio + valorritmo;
                    crearritmo.setText(valornuevosuma + "");}

                    else if (getinput >= 100 && getinput < 500) {
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 400;
                    int valornuevosuma = valorprecio + valorritmo;
                    crearritmo.setText(valornuevosuma + "");
                }else if(getinput >= 500 && getinput < 1000){
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 500;
                    int valornuevosuma = valorprecio + valorritmo;
                    crearritmo.setText(valornuevosuma + "");






                }
                // sharedViewModel.setDias(pagotext.getText().toString());
                //Constants.getSP(getContext()).setDIAS(crearritmo.getText().toString());
            }
        });
        btcrearmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getinput = Integer.parseInt(crearritmo.getText().toString());
                //shareViewModel2.setHorapdf(txactual.getText().toString());
                if (getinput>0 && getinput<= 10) {
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 5;
                    int valornuevosuma = valorprecio - valorritmo;
                    crearritmo.setText(valornuevosuma + "");
                } else if (getinput ==50) {
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 40;
                    int valornuevosuma = valorprecio - valorritmo;
                    crearritmo.setText(valornuevosuma + "");



                } else if (getinput == 100) {
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 50;
                    int valornuevosuma = valorprecio - valorritmo;
                    crearritmo.setText(valornuevosuma + "");
                }else if(getinput>100&&getinput<=500){
                    int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                    int valorritmo = 400;
                    int valornuevosuma = valorprecio - valorritmo;
                    crearritmo.setText(valornuevosuma + "");}
                    else if(getinput>500&&getinput<=1000){
                        int valorprecio = Integer.parseInt(crearritmo.getText().toString());
                        int valorritmo = 500;
                        int valornuevosuma = valorprecio - valorritmo;
                        crearritmo.setText(valornuevosuma + "");

                }



                //sharedViewModel.setDias(crearritmo.getText().toString());
                // Constants.getSP(getContext()).setDIAS(crearritmo.getText().toString());
            }
        });

        btactualizarprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatefire();
            }
        });
    }
    public void updatefire () {
        if (isProductoChanged()) {
            Toast.makeText(this, "Datos Correctamente Actualizados", Toast.LENGTH_LONG).show();
        }else{Toast.makeText(this, "No hay cambios, no es posible actualizar", Toast.LENGTH_LONG).show();}
    }
    private boolean isProductoChanged() {
        final String datakey = getIntent().getStringExtra("key");
        final String id=mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("PRODUCTOS").child(id).child(datakey);
        if(!Producto.equals(cardnombre2.getText().toString())){
            ref.child("TIPO_CUERO").setValue(cardnombre2.getText().toString());
            return true;


        }else{
            return false;
        }
    }


}
