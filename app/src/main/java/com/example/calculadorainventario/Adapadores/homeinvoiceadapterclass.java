package com.example.calculadorainventario.Adapadores;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.AlertDialogos.deletedialogo;
import com.example.calculadorainventario.AlertDialogos.sionoalert;
import com.example.calculadorainventario.ClickInterface1;
import com.example.calculadorainventario.Constructores.constcards;
import com.example.calculadorainventario.Downloadpdfclass;
import com.example.calculadorainventario.HomeNote;
import com.example.calculadorainventario.R;
import com.example.calculadorainventario.Repositorio1;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class homeinvoiceadapterclass extends RecyclerView.Adapter<homeinvoiceadapterclass.ViewHolder>implements Filterable {
    private ClickInterface1 clickInterface1;
    deletedialogo deletedialogo;
    String tag1,tag2,tag3,tag4,tag5,tag6,tx1,tx2,tx3,tx4,tx5,tx6;
    sionoalert sionoalert;
    Repositorio1 repositorio1;
    //SharedViewModel sharedViewModel;
 Downloadpdfclass downloadpdfclass;
    FirebaseAuth mAuth;
 public homeinvoiceadapterclass(ClickInterface1 clickInterface1){
     this.clickInterface1=clickInterface1;

 }


    ArrayList<constcards> listhome;
    ArrayList<constcards> listhomefull;
    Double medida ;
    String cliente;
    Double valor;
    int precio;
    String producto;
    String estado;
    String pdfurl;
    int dias_plazo;
    int unidades;
    String fecha;
    String fechaparapago;

    HomeNote homeNote = new HomeNote();

  // ArrayList<constcards> listhome=new ArrayList<>();




    public homeinvoiceadapterclass(ArrayList<constcards> listhome) {
        this.listhome = listhome;
        listhomefull=listhome;


    }



    @NonNull
    @Override
    public homeinvoiceadapterclass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activityhomeinvoice2, parent, false);
        return new ViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final homeinvoiceadapterclass.ViewHolder holder, final int position) {

        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        holder.cardhomely.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deletedialogo=new deletedialogo();
                tag1=holder.itemView.getResources().getString(R.string.Customer_Name);
                tag2=holder.itemView.getResources().getString(R.string.Date);
                tag3=holder.itemView.getResources().getString(R.string.Due_Date);
                tag4=holder.itemView.getResources().getString(R.string.Draft);
                tag5=holder.itemView.getResources().getString(R.string.Total);
                tx1=listhome.get(position).getCliente();
                tx2=listhome.get(position).getFecha();
                tx3=listhome.get(position).getFechaparapago();
                tx4=listhome.get(position).getEstado();
                tx5=listhome.get(position).getValor();


                final AlertDialog dialogodelete=deletedialogo.DeleteDialogo(holder.itemView.getContext(),v,tag1,tag2,tag3,tag4,tag5,tag6,
                        tx1,tx2,tx3,tx4,tx5,tx6);
                deletedialogo.btndismiss(dialogodelete);
                deletedialogo.btnokay().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogodelete.dismiss();
                        sionoalert=new sionoalert();
                        final AlertDialog confirmdialog= sionoalert.sionoalert(holder.itemView.getContext(),v);

                        sionoalert.btncancel(confirmdialog,dialogodelete);
                        sionoalert.btnokay().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                getposicion(position);
                                confirmdialog.dismiss();

                            }
                        });





                    }
                });


                return true;
            }
        });
        holder.cardhomely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ;
               // showdialog(holder.txinvdate2, holder.itemView,position);
            }
        });

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


       // NumberFormat dc = NumberFormat.getCurrencyInstance(Locale.US);
        //dc.setMaximumFractionDigits(0);
       // DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        //symbols.setDecimalSeparator('.');

        //DecimalFormat format = new DecimalFormat();
        //format.setDecimalFormatSymbols(symbols);
        //format.setMaximumFractionDigits(1);
        //int valor2=Integer.parseInt(listhome.get(position).getValor());



        /*Double Medida = Double.parseDouble(holder.cardmedida.getText().toString());
        String Cliente = holder.cardcliente.getText().toString();
        Double Valor = Double.parseDouble(holder.cardvalor.getText().toString());
        int Precio = Integer.parseInt(holder.cardprecio.getText().toString());
        String Producto = holder.cardproducto.getText().toString();
        String Estado = holder.cardhora.getText().toString();
        String pdfurl= String.valueOf(listhome.get(position).getPdfurl());
        int dias_plazo=Integer.parseInt(listhome.get(position).getDias_plazo());
        int Unidades=Integer.parseInt(holder.cardunidades.getText().toString());
        String Fecha=holder.cardfecha.getText().toString();
        String Fechaparapago=listhome.get(position).getFechaparapago();*/
//         homeNote.setMedida(Double.parseDouble(holder.cardmedida.getText().toString()));
//      homeNote.setCliente(holder.cardcliente.getText().toString());
////      homeNote.setValor(Double.parseDouble(holder.cardvalor.getText().toString()));
//        homeNote.setPrecio(Integer.parseInt(holder.cardprecio.getText().toString()));
//      homeNote.setProducto(holder.cardproducto.getText().toString());
//        homeNote.setEstado(holder.cardhora.getText().toString());
//        homeNote.setPdfurl(listhome.get(position).getPdfurl());
//       homeNote.setDias_plazo(Integer.parseInt(listhome.get(position).getDias_plazo()));
//        homeNote.setUnidades(Integer.parseInt(listhome.get(position).getUnidades()));
//        homeNote.setFecha(listhome.get(position).getFecha());
//       homeNote.setFechaparapago(listhome.get(position).getFechaparapago());





        //note.setValor_Medida(Valor_Medida);
        //sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(SharedViewModel.class);
        //sharedViewModel.Insert(homeNote);



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
                listhome.get(position).getKey_fire();




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
                pdfurl=String.valueOf(listhome.get(position).getPdfurl());


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
//  return listhome != null ? listhome.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return Homefilter;
    }
    private Filter Homefilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

           ArrayList<constcards> filteredHomeList=new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredHomeList.addAll(listhomefull);

            }else{
                String filterHomePattern= constraint.toString().toLowerCase().trim();
                for(constcards item:listhomefull){
                    if(item.getCliente().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
                            item.getProducto().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
                            item.getValor().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
                            item.getMedida().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
//                            item.getUnidades().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
//                            item.getPrecio().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
                            item.getFecha().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
                            item.getEstado().toLowerCase().contains(constraint.toString().toLowerCase().trim())

                    )
                    {
                        filteredHomeList.add(item);

                    }


                    }
               // notifyDataSetChanged();

                }


            FilterResults homeresults=new FilterResults();
            homeresults.values=filteredHomeList;
            return homeresults;
        }




        @Override
        protected void publishResults(CharSequence constraint, FilterResults homeresults) {
          //  listhome.clear();
            //listhome=((ArrayList<constcards>)homeresults.values);
            listhome=((ArrayList)homeresults.values);

            notifyDataSetChanged();


        }


    };
    /*public void filterDateRange(Date charText,Date charText1) {
        filteredHomeList.clear();
        if (charText.equals("")||charText.equals(null)) {
            filteredHomeList.addAll(listhomefull);
        } else {
            for (constcards wp : listhomefull) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date strDate = sdf.parse(wp.Fecha);
                    if (charText1.after(strDate)&&charText.before(strDate)) {
                        listhomefull.add(wp);
                    }else{

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        notifyDataSetChanged();
    }*/


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txinvname,txinvdate,txinvdate2,txinvtotal,txinvstate,txurl;
        ImageView imgvpdf;
        CardView cvdate2,cvstate;
        String Fechapago,Fechaventa,tipodoc;
        MaterialCardView mtpdf;
        LinearLayout cardhomely;




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
            cardhomely=itemView.findViewById(R.id.cardhomely);

//            imgvpdf=itemView.findViewById(R.id.imgvpdf);
            mtpdf=itemView.findViewById(R.id.mtpdf);









        }
    }
    public void getposicion(int position){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref;
        listhome.get(position);


        String key=listhome.get(position).getKey_fire();
        final String id = mAuth.getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id).child(key);
        ref.removeValue();

    }





}




