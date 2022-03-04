package com.example.calculadorainventario;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.Adapadores.adaptadorcatalogo;
import com.example.calculadorainventario.Constructores.cuerospinner;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link catalogo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class catalogo extends Fragment implements ClickInterface1 {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseReference myrootDbaseref2, mydb2;
    Button btproduct, btncatalogo;
    SwipeRefreshLayout swipecatalogo;
    TextView txproductobottom;
    SearchView edtbuscarproducto;
    Context ctx;
    NoteProdViewModel noteProdViewModel;
    NoteProducto noteProducto;
    String Nombre_Prod;
    String Cant_Prod;
    String Precio_prod;


    RecyclerView Recyclercatalogo;
    View CatalogoView;
    SharedPreferences sharedPreferences1;

    private SharedViewModel sharedViewModel;
    private adaptadorcatalogo myadaptador;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public catalogo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment catalogo.
     */
    // TODO: Rename and change types and number of parameters
    public static catalogo newInstance(String param1, String param2) {
        catalogo fragment = new catalogo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CatalogoView = inflater.inflate(R.layout.fragment_catalogo, container, false);
        //return //inflater.inflate(R.layout.fragment_catalogo, container, false);
        // txproductobottom=CatalogoView.findViewById(R.id.txproductobottom);
        edtbuscarproducto = CatalogoView.findViewById(R.id.edtbuscarproducto);
        swipecatalogo=CatalogoView.findViewById(R.id.swipecatalogo);
        // btproduct=CatalogoView.findViewById(R.id.btproduct);
        Recyclercatalogo = (RecyclerView) CatalogoView.findViewById(R.id.Recyclercatalogo);
        ViewPager2 vp=(ViewPager2) getActivity().findViewById(R.id.viepag);
        Recyclercatalogo.bringToFront();
        //Recyclercliente.setHasFixedSize(true);


        Recyclercatalogo.setLayoutManager(new LinearLayoutManager(Recyclercatalogo.getContext()));
        //Recyclercatalogo.setLayoutManager(new GridLayoutManager(Recyclercatalogo.getContext(), 3));

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(Recyclercatalogo.getContext(), 3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Recyclercatalogo.getContext());

        Recyclercatalogo.setLayoutManager(linearLayoutManager);


        noteProdViewModel=new ViewModelProvider(getActivity()).get(NoteProdViewModel.class);

        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        sharedViewModel.init();


        sharedViewModel.getproducto().observe(getViewLifecycleOwner(), new Observer<ArrayList<cuerospinner>>() {
            @Override
            public void onChanged(ArrayList<cuerospinner> cuerospinners) {
                myadaptador.notifyDataSetChanged();
            }
        });
        myadaptador = new adaptadorcatalogo(sharedViewModel.getproducto().getValue(), this);
        Recyclercatalogo.setAdapter(myadaptador);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.UP) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                myadaptador.getpos(viewHolder.getAdapterPosition());
//                final String id = mAuth.getCurrentUser().getUid();
//                ref = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id).child(Firekey);
//                ref.removeValue();
                Toast.makeText(CatalogoView.getContext(),"Deleted",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(Recyclercatalogo);



        /*btproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Crearproducto.class);
                startActivity(intent);
            }
        });*/

        edtbuscarproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtbuscarproducto.setIconified(false);
            }
        });
        if (edtbuscarproducto != null) {
            edtbuscarproducto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {


                    myadaptador.getFilter().filter(newText);

                    return false;

                    //newText = edtbuscar.getQuery().toString();

                    //firebaseUserSearch (newText);
                    //              return true;
                }
            });
        }
        swipecatalogo.setColorSchemeResources(R.color.colorAccent);
        swipecatalogo.hasNestedScrollingParent();
        swipecatalogo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
                sharedViewModel.init();
                sharedViewModel.getTaxvalue().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {

                    }
                });

                sharedViewModel.getproducto().observe(getViewLifecycleOwner(), new Observer<ArrayList<cuerospinner>>() {
                    @Override
                    public void onChanged(ArrayList<cuerospinner> cuerospinners) {
                        myadaptador.notifyDataSetChanged();
                    }
                });
                myadaptador = new adaptadorcatalogo(sharedViewModel.getproducto().getValue(), catalogo.this);
                Recyclercatalogo.setAdapter(myadaptador);
                swipecatalogo.setRefreshing(false);



            }
        });


        // mydb2 = FirebaseDatabase.getInstance().getReference().child("PRODUCTOS");


        return CatalogoView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);


        sharedViewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
               // txproductobottom.setText(charSequence);
            }
        });
        sharedViewModel.getResultado().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String string) {
                //tx_cliente.setText(charSequence);
            }
        });
        sharedViewModel.getprecio().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                //precio1.setText(charSequence);
            }
        });


        //  sharedViewModel.getproducto().observe(getViewLifecycleOwner(), new Observer<ArrayList<cuerospinner>>() {
        //    @Override
        //  public void onChanged(ArrayList<cuerospinner> cuerospinners) {
        //}
        //});


    }

    @Override
    public void onItemClick(int position) {


    }

    @Override
    public void onButtonAddClick(int position) {
        sharedViewModel.drawer(String.valueOf(1));

    }

    @Override
    public void onButtonclienteClick(int position) {

    }

    @Override
    public void passingproductoClick(int position, CharSequence Producto, CharSequence Precio, CharSequence Cantidad) {
        sharedViewModel.setText(Producto);
//        String Nombre_Prod=Producto.toString();
//       Double Cant_Prod=Cantidad;
//        String  Precio_prod=Precio.toString();
//        Double price = Double.parseDouble(Cant_Prod);
//        Double qt = Double.parseDouble(Precio_prod);
////                int valorritmo = 1;
//        Double valornuevosuma = price * qt;
//        Double Resultado_valor=valornuevosuma;
//
//
//        noteProducto=new NoteProducto(Nombre_Prod,Cant_Prod,Precio_prod,Resultado_valor);
//        noteProdViewModel.Insert(noteProducto);
//        Log.d("el valor es", String.valueOf(noteProducto.Nombre_prod+noteProducto.Precio_prod+noteProducto.Cant_prod));
//

    }



    @Override
    public void passingprecio1Click(int position, CharSequence Precio) {
       sharedViewModel.setPrecio(Precio);

    }



    @Override
    public void passingcliente2Click(int position, CharSequence Cliente) {

    }

    @Override
    public void passfirebasekey(String key) {

    }

    @Override
    public void preferenciasacalculadora() {

    }

    @Override
    public void Preferenciasingreo(int estado, String dias, String Tipo) {

    }

    @Override
    public void passingpositionk(int position) {

    }

    @Override
    public void PassTipoDoc(int position, CharSequence tipoDoc) {

    }

    @Override
    public void PassnoteprodPosition(int position, String Producto, String Cantidad, String Precio, NoteProducto currentnote) {

    }




}
