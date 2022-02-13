package com.example.calculadorainventario;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

public class homeadapterclass extends RecyclerView.Adapter<homeadapterclass.ViewHolder>implements Filterable {

    Repositorio1 repositorio1;
    //SharedViewModel sharedViewModel;


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




    public homeadapterclass(ArrayList<constcards> listhome) {
        this.listhome = listhome;
        listhomefull=listhome;


    }



    @NonNull
    @Override
    public homeadapterclass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vistatar2, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final homeadapterclass.ViewHolder holder, final int position) {


        holder.cardcliente.setText(listhome.get(position).getCliente());
        holder.cardfecha.setText(listhome.get(position).getFecha());
        holder.cardhora.setText(listhome.get(position).getEstado());
        holder.cardmedida.setText(listhome.get(position).getMedida());
        holder.cardunidades.setText(listhome.get(position).getUnidades());
        holder.cardvalor.setText(listhome.get(position).getValor());
        holder.cardproducto.setText(listhome.get(position).getProducto());
        holder.cardprecio.setText(listhome.get(position).getPrecio());
        holder.Fechapago = listhome.get(position).getFechaparapago();


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
         homeNote.setMedida(Double.parseDouble(holder.cardmedida.getText().toString()));
      homeNote.setCliente(holder.cardcliente.getText().toString());
//      homeNote.setValor(Double.parseDouble(holder.cardvalor.getText().toString()));
        homeNote.setPrecio(Integer.parseInt(holder.cardprecio.getText().toString()));
      homeNote.setProducto(holder.cardproducto.getText().toString());
        homeNote.setEstado(holder.cardhora.getText().toString());
        homeNote.setPdfurl(listhome.get(position).getPdfurl());
       homeNote.setDias_plazo(Integer.parseInt(listhome.get(position).getDias_plazo()));
        homeNote.setUnidades(Integer.parseInt(listhome.get(position).getUnidades()));
        homeNote.setFecha(listhome.get(position).getFecha());
       homeNote.setFechaparapago(listhome.get(position).getFechaparapago());





        //note.setValor_Medida(Valor_Medida);
        //sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(SharedViewModel.class);
        //sharedViewModel.Insert(homeNote);



        try {
            Date date1 = fecc.parse(inputString1);
            Date date2 = fecc.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            holder.txdias.setText(String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
            holder.txdias.setTextColor(holder.itemView.getResources().getColor(R.color.colorverdeesmeralda));
            holder.carddias.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.semiTransparentverdeColor));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = Integer.parseInt(holder.txdias.getText().toString());
        if (i <= 0) {
            //holder.carddias.setVisibility(View.INVISIBLE);
            holder.txdias.setTextColor(holder.itemView.getResources().getColor(R.color.colorRappi));
            holder.carddias.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorRappiTransparent));

        }


        if (holder.cardhora.getText().toString().equals("Venta")) {
            holder.card_operacionhome.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.blueTransparent));
            holder.cardhora.setTextColor(holder.itemView.getResources().getColor(R.color.bluecolor));
        } else if (holder.cardhora.getText().toString().equals("Compra")) {
            holder.card_operacionhome.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.purplecolotransparentr));
            holder.cardhora.setTextColor(holder.itemView.getResources().getColor(R.color.purplecolor));
        } else if (holder.cardhora.getText().toString().equals("Borrador")) {
            holder.card_operacionhome.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorGrisoscurotransparent));
            holder.cardhora.setTextColor(holder.itemView.getResources().getColor(R.color.colorGrisoscuro));
        }//else{holder.card_operacionhome.setCardBackgroundColor(getResources().getColor(R.color.colorAccent));}
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // repositorio1.Insert(homeNote);

                Intent intent = new Intent(v.getContext(), reminderAc.class);
                intent.putExtra("cliente", holder.cardcliente.getText().toString());
                intent.putExtra("producto", holder.cardproducto.getText().toString());
                intent.putExtra("key", listhome.get(position).getKey());
                intent.putExtra("precio", holder.cardprecio.getText().toString());
                intent.putExtra("medida", holder.cardmedida.getText().toString());
                intent.putExtra("valor", holder.cardvalor.getText().toString());
                intent.putExtra("unidades", holder.cardunidades.getText().toString());
                intent.putExtra("fecha", holder.cardfecha.getText().toString());
                intent.putExtra("estado", holder.cardhora.getText().toString());
                intent.putExtra("pdfurl", listhome.get(position).getPdfurl());

                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listhome.size();
       //return listhome != null ? listhome.size() : 0;
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
                            item.getUnidades().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
                            item.getPrecio().toLowerCase().contains(constraint.toString().toLowerCase().trim())||
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
            //listhome.clear();
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
        TextView cardcliente, cardproducto, cardmedida, cardfecha, cardhora, cardvalor, cardunidades, cardprecio,txdias;
        ImageView deleteicon;
        CardView card_operacionhome,carddias;
        String Fechapago,Fechaventa,tipodoc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txdias=itemView.findViewById(R.id.txdias);
            cardcliente = itemView.findViewById(R.id.cardcliente);
            cardproducto = itemView.findViewById(R.id.cardproducto);
            cardmedida = itemView.findViewById(R.id.cardmedida);
            cardfecha = itemView.findViewById(R.id.cardfecha);
            cardhora = itemView.findViewById(R.id.cardhora);
            cardvalor= itemView.findViewById(R.id.cardvalor);
            cardunidades = itemView.findViewById(R.id.cardunidades);
            cardprecio = itemView.findViewById(R.id.cardprecio);
            card_operacionhome=itemView.findViewById(R.id.card_operacionhome);
            carddias=itemView.findViewById(R.id.carddias);

        }
    }

}




