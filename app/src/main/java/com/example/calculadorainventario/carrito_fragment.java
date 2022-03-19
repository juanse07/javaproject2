package com.example.calculadorainventario;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.calculadorainventario.Adapadores.ClienteAdapter;
import com.example.calculadorainventario.Constructores.NoteProducto;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link carrito_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carrito_fragment extends Fragment implements ClickInterface1  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseReference myrootDbaseref2,mydb2;
    TextView txclientebotton;
    SwipeRefreshLayout swipecliente;
    SharedPreferences sharedPreferences4;
    Button btnuevocliente2;
    RecyclerView Recyclerliente2;
    View ClientesView2;
    SearchView edtbuscarcliente;
    private ClienteAdapter myadaptador3;
    private SharedViewModel sharedViewModel;
    String pdfphone,pdfemail,pdfaddress,pdfcity,pdfcliente;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public carrito_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment carrito_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static carrito_fragment newInstance(String param1, String param2) {
        carrito_fragment fragment = new carrito_fragment();
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
        ClientesView2 = inflater.inflate(R.layout.fragment_carrito_fragment, container, false);
       // return inflater.inflate(R.layout.fragment_carrito_fragment, container, false);
        txclientebotton= ClientesView2.findViewById(R.id.txclientebotton);

        Recyclerliente2=(RecyclerView) ClientesView2.findViewById(R.id.Recyclerliente2);
        edtbuscarcliente= ClientesView2.findViewById(R.id.edtbuscarcliente);
        swipecliente=ClientesView2.findViewById(R.id.swipecliente);
        Recyclerliente2.bringToFront();

        //Recyclercliente.setHasFixedSize(true);
        Recyclerliente2.setLayoutManager(new LinearLayoutManager(Recyclerliente2.getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Recyclerliente2.getContext());
       // linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
       Recyclerliente2.setLayoutManager(linearLayoutManager);

        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        sharedViewModel.init3();
        sharedViewModel.getTaxvalue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });


        sharedViewModel.getcliente().observe(getViewLifecycleOwner(), new Observer<ArrayList<constructornom2>>() {
            @Override
            public void onChanged(ArrayList<constructornom2> constructornom2s) {
                myadaptador3.notifyDataSetChanged();
            }
        });
        myadaptador3 = new ClienteAdapter(sharedViewModel.getcliente().getValue(),this);
        Recyclerliente2.setAdapter(myadaptador3);

        edtbuscarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtbuscarcliente.setIconified(false);
            }
        });
        if(edtbuscarcliente!=null) {
            edtbuscarcliente.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {




                    myadaptador3.getFilter().filter(newText);

                    return false;

                    //newText = edtbuscar.getQuery().toString();

                    //firebaseUserSearch (newText);
                    //              return true;
                }
            });
        }
        swipecliente.hasNestedScrollingParent();
        swipecliente.setColorSchemeResources(R.color.colorAccent);
        swipecliente.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
                sharedViewModel.init3();


                sharedViewModel.getcliente().observe(getViewLifecycleOwner(), new Observer<ArrayList<constructornom2>>() {
                    @Override
                    public void onChanged(ArrayList<constructornom2> constructornom2s) {
                        myadaptador3.notifyDataSetChanged();
                    }
                });
                myadaptador3 = new ClienteAdapter(sharedViewModel.getcliente().getValue(),carrito_fragment.this);
                Recyclerliente2.setAdapter(myadaptador3);
                swipecliente.setRefreshing(false);

            }
        });



        return  ClientesView2;
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onButtonAddClick(int position) {

    }

    @Override
    public void onButtonclienteClick(int position) {

       //sharedViewModel.setTextcliente(txnombre45.getText().toString());
        sharedViewModel.drawer(String.valueOf(1));
        //sharedPreferences4=getActivity().getSharedPreferences("datosguardados4", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor= sharedPreferences4.edit();
        //editor.putString("cliente", clientes3.txnombre45.getText().toString());
        //editor.apply();

    }

    @Override
    public void passingproductoClick(int position, CharSequence Producto, CharSequence Precio, CharSequence Cantidad) {

    }



    @Override
    public void passingprecio1Click(int position, CharSequence Precio) {

    }

    @Override
    public void passingcliente2Click(int position, CharSequence Cliente, String phone, String Email, String Address, String City) {
        sharedViewModel.setCliente2(Cliente.toString());
        sharedViewModel.setTpdfemail(Email);
        sharedViewModel.setTpdfphone(phone);
        sharedViewModel.setTpdfaddress(Address);
        sharedViewModel.setTpdfcity(City);
        // ClienteL=Cliente.toString();
                pdfaddress=Address;
        pdfemail=Email;
        pdfphone=phone;
        pdfcity=City;
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




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
       sharedViewModel.getCliente2().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
           @Override
           public void onChanged(CharSequence charSequence) {
               //txclientebotton.setText(charSequence);

           }
       });
        sharedViewModel.getResultado().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String string) {
                //tx_cliente.setText(charSequence);
            }
        });

    }


}


