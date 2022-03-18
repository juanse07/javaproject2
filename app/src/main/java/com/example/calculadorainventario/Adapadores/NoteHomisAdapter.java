package com.example.calculadorainventario.Adapadores;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calculadorainventario.Constructores.NoteHomis;
import com.example.calculadorainventario.Constructores.constcards;
import com.example.calculadorainventario.R;
import com.example.calculadorainventario.ViewModel.NoteHomisViewModel;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NoteHomisAdapter extends RecyclerView.Adapter<NoteHomisAdapter.ViewHolder> implements Filterable {
    NoteHomisViewModel noteHomisViewModel;

    ArrayList<NoteHomis> listhome;
    ArrayList<NoteHomis> listhomefull;

    public NoteHomisAdapter(ArrayList<NoteHomis> listhome) {
        this.listhome = listhome;
        listhomefull=listhome;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public NoteHomisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activityhomeinvoice2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteHomisAdapter.ViewHolder holder, final int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        holder.txinvname.setText(listhome.get(position).getCliente());
        holder.txinvdate.setText(listhome.get(position).getFecha());
        String comprobarestado;
        comprobarestado=listhome.get(position).getEstado().toString();
        if(comprobarestado.equals(holder.itemView.getResources().getString(R.string.Sales))){
            holder.txinvstate.setText(holder.itemView.getResources().getString(R.string.Sales));
            holder.cvstate.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.blueTransparent));
            holder.txinvstate.setTextColor(holder.itemView.getResources().getColor(R.color.bluecolor));
        }else if(comprobarestado.equals(holder.itemView.getResources().getString(R.string.Draft))){
            holder.txinvstate.setText(holder.itemView.getResources().getString(R.string.Draft));
            holder.cvstate.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorGrisoscurotransparent));
            holder.txinvstate.setTextColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));
        }else if(comprobarestado.equals(holder.itemView.getResources().getString(R.string.Receipts))){
            holder.txinvstate.setText(holder.itemView.getResources().getString(R.string.Receipts));
            holder.cvstate.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.purplecolotransparentr));
            holder.txinvstate.setTextColor(holder.itemView.getResources().getColor(R.color.purplecolor));
        }
//        holder.cardmedida.setText(listhome.get(position).getMedida());
//        holder.cardunidades.setText(listhome.get(position).getUnidades());
//        holder.txinvstate.setText(listhome.get(position).getEstado());
        holder.txinvtotal.setText(String.valueOf( formatter.format(Double.parseDouble(String.valueOf(listhome.get(position).getValor()))))+ " USD");
//        holder.cardproducto.setText(listhome.get(position).getProducto());
//        holder.cardprecio.setText(listhome.get(position).getPrecio());
//        holder.Fechapago.setText() listhome.get(position).getFechaparapago();
//        holder.txurl.setText(listhome.get(position).getPdfurl());


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fecc = new SimpleDateFormat("dd/MMM/yyyy");

        String inputString1 = fecc.format(calendar.getTimeInMillis());
        String inputString2 = listhome.get(position).getFechaparapago();
        String inputString3 = listhome.get(position).getFecha();





        try {
            Date date1 = fecc.parse(inputString1);
            Date date2 = fecc.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            holder.txinvdate2.setText(String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
            holder.txinvdate2.setTextColor(holder.itemView.getResources().getColor(R.color.colorverdeesmeralda));
            holder.cvdate2.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.semiTransparentverdeColor));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = Integer.parseInt(holder.txinvdate2.getText().toString());
        if (i <= 0) {
            //holder.carddias.setVisibility(View.INVISIBLE);
            holder.txinvdate2.setTextColor(holder.itemView.getResources().getColor(R.color.colorRappi));
            holder.cvdate2.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorRappiTransparent));

        }


//        if (holder.txinvstate.getText().toString().equals("Venta")) {
//            holder.cvstate.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.blueTransparent));
//            holder.txinvstate.setTextColor(holder.itemView.getResources().getColor(R.color.bluecolor));
//        } else if (holder.txinvstate.getText().toString().equals("Compra")) {
//            holder.cvstate.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.purplecolotransparentr));
//            holder.txinvstate.setTextColor(holder.itemView.getResources().getColor(R.color.purplecolor));
//        } else if (holder.txinvstate.getText().toString().equals("Borrador")) {
//            holder.cvstate.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorGrisoscurotransparent));
//            holder.txinvstate.setTextColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));
//        }//else{holder.card_operacionhome.setCardBackgroundColor(getResources().getColor(R.color.colorAccent));}
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // repositorio1.Insert(homeNote);

//                Intent intent = new Intent(v.getContext(), reminderAc.class);
//                intent.putExtra("cliente", holder.txinvname.getText().toString());
//                intent.putExtra("producto", holder.cardproducto.getText().toString());
//                intent.putExtra("key", listhome.get(position).getKey());
//                intent.putExtra("precio", holder.cardprecio.getText().toString());
//                intent.putExtra("medida", holder.cardmedida.getText().toString());
//                intent.putExtra("valor", holder.cardvalor.getText().toString());
//                intent.putExtra("unidades", holder.cardunidades.getText().toString());
//                intent.putExtra("fecha", holder.cardfecha.getText().toString());
//                intent.putExtra("estado", holder.cardhora.getText().toString());
//                intent.putExtra("pdfurl", listhome.get(position).getPdfurl());

//                v.getContext().startActivity(intent);
            }
        });



        holder.mtpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listhome.get(position).getKey();




                if (Build.VERSION.SDK_INT >= 24) {

                    try {

                        //For API's > 24, runtime exception occurs when a URI is exposed BEYOND this particular app that you are writing (AKA when user attempts to open in device/emulator

                        Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");

                        m.invoke(null);

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                }


                String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
                String mFilename = simpleDateFormat.format(System.currentTimeMillis());
                File file;

              //  mFilename.toString().replaceAll(":",".")
                String pdfurl = String.valueOf(listhome.get(position).getPdfurl());


                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat hora1 = new SimpleDateFormat("HH:mm:ss");
                String hora2 = hora1.format(calendar.getTime());
                String mFilepath = Environment.getExternalStorageDirectory() +   File.separator+ "PyMESoft"+
                        File.separator+"down"+mFilename.toString().replaceAll(":",".");
                File filepath2;
                File file2;

                file = new File(mFilepath);
                if (!file.exists()) {
                    file.mkdirs();

                }
                filepath2= new File(file.getAbsolutePath());
                filepath2.mkdir();
                file2=new File(filepath2,"inv-"+holder.txinvname.getText().toString()+".pdf");




//                    String mFilename2 = holder.txinvname.getText().toString() + System.currentTimeMillis();







                    // File desf = new File(mFilepath);
                    DownloadManager downloadManager = (DownloadManager) v.getContext().getSystemService(v.getContext().DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(pdfurl);
//                Log.d("a:",String.valueOf(uri));
                    DownloadManager.Request request = new DownloadManager.Request(uri);

                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    //request.setDestinationUri(Uri.fromFile(new File(mFilepath)));
                    request.setDestinationUri(Uri.fromFile(file2));
                    //request.setDestinationInExternalFilesDir(context,destinationDirectory,file+fileExtension);
                    // request.setDestinationUri(Uri.parse(mFilepath));
                downloadManager.enqueue(request);


    Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file2),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);


        Intent intent = Intent.createChooser(target, "Open File");
        try {
            v.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {

        }








            }
        });





    }

    @Override
    public int getItemCount() {
        return listhome.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txinvname,txinvdate,txinvdate2,txinvtotal,txinvstate,txurl;
        ImageView imgvpdf;
        CardView cvdate2,cvstate;
        String Fechapago,Fechaventa,tipodoc;
        MaterialCardView mtpdf;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            txurl=itemView.findViewById(R.id.textView58);
            txinvname=itemView.findViewById(R.id.txinvname);
            txinvdate=itemView.findViewById(R.id.txinvdate);
            txinvdate2=itemView.findViewById(R.id.txinvdate2);
            txinvtotal=itemView.findViewById(R.id.txinvtotal);
            txinvstate=itemView.findViewById(R.id.txinvstate);
            cvdate2=itemView.findViewById(R.id.cvdate2);
            cvstate=itemView.findViewById(R.id.cvstate);
//            imgvpdf=itemView.findViewById(R.id.imgvpdf);
            mtpdf=itemView.findViewById(R.id.mtpdf);









        }
    }

}

