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

public class ClienteEdit extends AppCompatActivity {
    EditText cardtel2,cardnombrecliente2;
    Button btactualizarcli;
    FirebaseAuth mAuth;
    DatabaseReference ref;

    String Nombre, Tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_edit);
        cardtel2=findViewById(R.id.cardtel2);
        cardnombrecliente2=findViewById(R.id.cardnombrecliente2);
        btactualizarcli=findViewById(R.id.btactualizarcli);
        mAuth=FirebaseAuth.getInstance();


        cardnombrecliente2.setText(getIntent().getStringExtra("cliente"));
        cardtel2.setText(getIntent().getStringExtra("tel"));
        Nombre=getIntent().getStringExtra("cliente");
        Tel=getIntent().getStringExtra("tel");
        btactualizarcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatefire();
            }
        });
    }

    public void updatefire () {
        if (isNombreChanged()||isTelChanged()) {
            Toast.makeText(this, "Datos Correctamente Actualizados", Toast.LENGTH_LONG).show();
        }else{Toast.makeText(this, "No hay cambios, no es posible actualizar", Toast.LENGTH_LONG).show();}
    }

    private boolean isTelChanged() {
        final String datakey = getIntent().getStringExtra("key");
        final String id=mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("CLIENTE").child(id).child(datakey);
        if(!Tel.equals(cardtel2.getText().toString())){
            ref.child("Tel").setValue(cardtel2.getText().toString());
            return true;


        }else{
            return false;
        }
    }


    private boolean isNombreChanged() {
        final String datakey = getIntent().getStringExtra("key");
        final String id=mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("CLIENTE").child(id).child(datakey);
        if(!Nombre.equals(cardnombrecliente2.getText().toString())){
            ref.child("Nombre").setValue(cardnombrecliente2.getText().toString());
            return true;


        }else{
            return false;
        }
    }
    }

