package com.example.calculadorainventario.AlertDialogos;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.sax.Element;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calculadorainventario.R;
import com.google.android.material.button.MaterialButton;

import java.io.File;

import androidx.constraintlayout.widget.ConstraintLayout;

public class deletedialogo {
    View view2;
    AlertDialog.Builder builder1;

    public AlertDialog DeleteDialogo(Context context, View v, String stag1, String stag2, String stag3, String stag4,
                                     String stag5, String stag6, String stx1, String stx2, String stx3, String stx4, String stx5,
                                     String stx6) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert);
        setBuilder(builder);
        TextView txnombre, txdate1, txdate2, txestado, txdias, txtotal, tag1, tag2, tag3, tag4, tag5, tag6, tag7;
        LinearLayout Dialogdeletely;
        ImageView Deleteicon;
        MaterialButton mtbdelete, mtbdismiss;
        final View view = LayoutInflater.from(context).inflate(R.layout.deleteandoptionsrecycler, (ConstraintLayout) v.findViewById(R.id.opconstraint));
        builder.setView(view);
        setview(view);


        final AlertDialog alertDialog = builder.create();
        txnombre = view.findViewById(R.id.dialognametx);
        txdate1 = view.findViewById(R.id.dialogdatetx);
        txdate2 = view.findViewById(R.id.dialogddatetx);
        txestado = view.findViewById(R.id.dialogtipetx);
        txdias = view.findViewById(R.id.dialogdaystx);
        txtotal = view.findViewById(R.id.dialogtotaltx);
        mtbdelete = view.findViewById(R.id.mtbdelete);
        tag1 = view.findViewById(R.id.tag1);
        tag2 = view.findViewById(R.id.tag2);
        tag3 = view.findViewById(R.id.tag3);
        tag4 = view.findViewById(R.id.tag4);
        tag5 = view.findViewById(R.id.tag5);
        tag6 = view.findViewById(R.id.tag6);

        mtbdismiss = view.findViewById(R.id.mtbdismiss);

        tag1.setText(stag1);
        tag2.setText(stag2);
        tag3.setText(stag3);
        tag4.setText(stag4);
        tag5.setText(stag5);
        tag6.setText(stag6);
        txnombre.setText(stx1);
        txdate1.setText(stx2);
        txdate2.setText(stx3);
        txestado.setText(stx4);
        txdias.setText(stx5);
        txtotal.setText(stx6);

        if (alertDialog.getWindow() != null) {
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

    public MaterialButton btndismiss(final AlertDialog alertDialog) {
        MaterialButton btndismiss;
        View view = getiew();
        btndismiss = view.findViewById(R.id.mtbdismiss);
        btndismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        return btndismiss;
    }
    public MaterialButton btnokay() {
        MaterialButton btnokay;
        View view = getiew();
        btnokay = view.findViewById(R.id.mtbdelete);

        return btnokay;
    }






}
