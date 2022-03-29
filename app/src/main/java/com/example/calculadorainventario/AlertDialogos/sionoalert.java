package com.example.calculadorainventario.AlertDialogos;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.calculadorainventario.R;
import com.google.android.material.button.MaterialButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class sionoalert {
    AlertDialog.Builder builder1;
    View view2;
    TextView txtittle,txcontent;
    MaterialButton btyes,btno;

    public AlertDialog sionoalert(Context context,View v){


        AlertDialog.Builder builder=new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert);
        setBuilder(builder);

        View view= LayoutInflater.from(context).inflate(R.layout.dialoghome,(ConstraintLayout)v.findViewById(R.id.yesnoconstraint));
        builder.setView(view);
        setview(view);


        final AlertDialog alertDialog=builder.create();

        txtittle=view.findViewById(R.id.txtittle);
        txcontent=view.findViewById(R.id.txcontent);
        btno=view.findViewById(R.id.boton_si);
        btyes=view.findViewById(R.id.boton_no);


        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();


        return alertDialog;
    }


    public View getiew() {
        return view2;
    }

    public void setview(View view2) {
        this.view2 = view2;
    }

    public AlertDialog.Builder getBuilder() {
        return builder1;
    }

    public void setBuilder(AlertDialog.Builder builder1) {
        this.builder1 = builder1;
    }

    public MaterialButton btncancel(final AlertDialog alertDialog, final AlertDialog alertDialog2) {
        MaterialButton btndismiss;
        View view = getiew();
        btndismiss = view.findViewById(R.id.boton_no);
        btndismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                alertDialog2.show();

            }
        });
        return btndismiss;
    }
    public MaterialButton btnokay() {
        MaterialButton btnokay;
        View view = getiew();
        btnokay = view.findViewById(R.id.boton_si);

        return btnokay;
    }



}
