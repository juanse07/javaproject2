package com.example.calculadorainventario;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridView;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;



public class MainActivity extends AppCompatActivity {

    ArrayList<Double> ListaCuero = new ArrayList<Double>();
    ArrayList<String> milista = new ArrayList<String>();


    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btcero, btpunto, btadd, btborrar, btcalcular;
    GridView grid3;
    TextView valor_a, valor_u, txcuenta, txultimo, txsuma, txprecio, txvalor, txactual;
    ListView Lista1;
    BottomNavigationView navigationView;
    Switch dcmbutton;
    Vibrator vibrator;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menuup, menu);
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principalui);
       // getSupportActionBar().setIcon(R.mipmap.ic_launcher);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        btcero = (Button) findViewById(R.id.btcero);
        btborrar = (Button) findViewById(R.id.btborrar);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);
        btpunto = (Button) findViewById(R.id.btpunto);
        btadd = (Button) findViewById(R.id.btadd);
        txsuma = (TextView) findViewById(R.id.txsuma);
        txcuenta = (TextView) findViewById(R.id.txcuenta);
        txactual = (TextView) findViewById(R.id.txactual);
        txultimo = (TextView) findViewById(R.id.txultimo);
        valor_a = (TextView) findViewById(R.id.txactual);
        //Lista1=(ListView)  findViewById(R.id.lista1);
        btcalcular = (Button) findViewById(R.id.btcalcular);
        grid3 = (GridView) findViewById(R.id.grid3);
        txprecio = (TextView) findViewById(R.id.txprecio);
        txvalor = (TextView) findViewById(R.id.txvalor);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        dcmbutton = (Switch) findViewById(R.id.dcmbutton);

        String precio1 = getIntent().getStringExtra("preciouno");
        txprecio.setText(precio1);


        btcero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 0);

            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 3);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 4);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 5);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 6);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 7);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 8);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + 9);
            }
        });
        btpunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                valor_a = (TextView) findViewById(R.id.txactual);
                txactual.setText(valor_a.getText().toString() + ".");
            }
        });


        btadd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                txactual = (TextView) findViewById(R.id.txactual);
                if (txactual.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();
                } else {
                    if (dcmbutton.isChecked()) {
                        double getinput = Double.parseDouble(txactual.getText().toString());
                        if (getinput > 75 && getinput < 3000) {
                            /////SUMAR////

                            ListaCuero.add(getinput);
                            //Collections.reverse(ListaCuero);
                            ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(MainActivity.this, android.R.layout.simple_selectable_list_item, ListaCuero);
                            grid3.setAdapter(adapter);
                            ((TextView) findViewById(R.id.txactual)).setText("");
                            grid3.getFirstVisiblePosition();

                            txsuma = (TextView) findViewById(R.id.txsuma);
                            txprecio = (TextView) findViewById(R.id.txprecio);
                            txvalor = (TextView) findViewById(R.id.txvalor);
                            double promed;

                            double sum = 0;
                            DecimalFormat df = new DecimalFormat("##.0");
                           //Currency dc=new Currency("$###.###.###");
                            NumberFormat cformat= NumberFormat.getCurrencyInstance(Locale.getDefault());
                            cformat.setCurrency(Currency.getInstance("US"));



                            for (int i = 0; i < ListaCuero.size(); i++) {

                                sum += ListaCuero.get(i);
                                promed = sum / ListaCuero.size();


                                txsuma.setText(Double.toString(Double.parseDouble(df.format(sum))));
                                txcuenta.setText(Integer.toString(ListaCuero.size()));
                                txultimo.setText(Double.toString(Double.parseDouble(df.format(promed))));
                                double pre1 = Double.parseDouble(txprecio.getText().toString());
                                double val1 = Double.parseDouble(txsuma.getText().toString());
                                double valfinal = pre1 * val1;

                                txvalor.setText(cformat.format(valfinal));
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "El valor está fuera de rango", Toast.LENGTH_SHORT).show();


                        }

                    } else {

                        double getinput = Double.parseDouble(txactual.getText().toString());
                        if (getinput > 10 && getinput < 99) {
                            /////SUMAR////


                            ListaCuero.add(getinput);
                            //Collections.reverse(ListaCuero);
                            ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(MainActivity.this, android.R.layout.simple_selectable_list_item, ListaCuero);
                            grid3.setAdapter(adapter);
                            ((TextView) findViewById(R.id.txactual)).setText("");
                            grid3.getFirstVisiblePosition();

                            txsuma = (TextView) findViewById(R.id.txsuma);
                            txprecio = (TextView) findViewById(R.id.txprecio);
                            txvalor = (TextView) findViewById(R.id.txvalor);
                            double promed;

                            double sum = 0;
                            DecimalFormat df = new DecimalFormat("##.#");
                          // DecimalFormat dc = new DecimalFormat("###,####.###");
                            NumberFormat dc = NumberFormat.getCurrencyInstance(Locale.US);
                            dc.setMaximumFractionDigits(0);
                           // String currency = format.format(number);


                            for (int i = 0; i < ListaCuero.size(); i++) {

                                sum += ListaCuero.get(i);
                                promed = sum / ListaCuero.size();


                                txsuma.setText(Double.toString(Double.parseDouble(df.format(sum))));
                                txcuenta.setText(Integer.toString(ListaCuero.size()));
                                txultimo.setText(Double.toString(Double.parseDouble(df.format(promed))));
                                double pre1 = Double.parseDouble(txprecio.getText().toString());
                                double val1 = Double.parseDouble(txsuma.getText().toString());
                                double valfinal = pre1 * val1;

                                txvalor.setText(dc.format(valfinal));
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "El valor está fuera de rango", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        btcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                ((TextView) findViewById(R.id.txactual)).setText("");

            }

        });


        btborrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vibrator.vibrate(20);
                if (ListaCuero.isEmpty()) {
                    Toast.makeText(MainActivity.this, "NO HAY DATOS", Toast.LENGTH_SHORT).show();
                } else {
                    ListaCuero.remove(ListaCuero.size() - 1);
                    grid3.invalidateViews();

                    txsuma = (TextView) findViewById(R.id.txsuma);
                    double promed;

                    double sum = 0;
                    DecimalFormat df = new DecimalFormat("#.0");
                    for (int i = 0; i < ListaCuero.size(); i++) {

                        sum += ListaCuero.get(i);
                        promed = sum / ListaCuero.size();


                        txsuma.setText(Double.toString(Double.parseDouble(df.format(sum))));
                        txcuenta.setText(Integer.toString(ListaCuero.size()));
                        txultimo.setText(Double.toString(Double.parseDouble(df.format(promed))));
                        double pre1 = Double.parseDouble(txprecio.getText().toString());
                        double val1 = Double.parseDouble(txsuma.getText().toString());
                        double valfinal = pre1 * val1;
                        txvalor.setText(Double.toString(valfinal));

                        //
                    }
                }
            }
        });
        grid3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int position1 = position + 1;
                Toast.makeText(MainActivity.this, "" + position1, Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void Enviardatos(View view) {


        Intent i = new Intent(this, VistaAA.class);

        i.putExtra("WLTP_list", ListaCuero);
        i.putExtra("sumatotal2", txsuma.getText().toString());
        i.putExtra("uds2", txcuenta.getText().toString());
        i.putExtra("precio2", txprecio.getText().toString());
        i.putExtra("valortotal", txvalor.getText().toString());
        //i.putExtra("opcion",opcion);

        startActivity(i);


    }





    }



