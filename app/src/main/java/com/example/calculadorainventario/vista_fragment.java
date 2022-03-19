package com.example.calculadorainventario;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.calculadorainventario.Adapadores.AdaptadorProductoGuardado;
import com.example.calculadorainventario.Constructores.NoteProducto;
import com.example.calculadorainventario.Constructores.arrayconstructor;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.itextpdf.text.Document;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link vista_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class vista_fragment extends Fragment implements ClickInterface1 {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView gdate, gprecio, gvalor, gmedida, gcantidad, gnombre, gproducto, ghora,gopcion;

    GridView gridcueros2;
    CardView card_view;
    View ClientesView2;


    private File pdfFile;
    private Paragraph paragraph;
    ShareViewModel2 shareViewModel2;
    private List lista;
    Document mDoc = new Document(PageSize.LETTER);
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());
    String mFilepath = Environment.getExternalStorageDirectory() + "/" + mFilename + ".pdf";
    BottomNavigationView navigationView;
    int position1;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;
    ImageButton btnshare;
    Calendar mcalendar;
    Intent shareintent;
    DatabaseReference myrootDbaseref5;

    RecyclerView recyclerArray;
    RecyclerView.Adapter mAdapter;
    NoteViewModel noteViewModel;
    SharedViewModel sharedViewModel;
    private java.util.List<arrayconstructor> UnidadesLista;
    private  ArrayList<arrayconstructor>UnidadesLista2;
    FirebaseStorage mystorage;
    FirebaseAuth mAuth;
    Uri pdfUri;
    vista_fragment context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public vista_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment vista_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static vista_fragment newInstance(String param1, String param2) {
        vista_fragment fragment = new vista_fragment();
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
        ClientesView2 = inflater.inflate(R.layout.fragment_vista_fragment, container, false);
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_vista_fragment, container, false);
        Date d = new Date();


       recyclerArray = (RecyclerView) ClientesView2.findViewById(R.id.recyclerArray);


        lista = new List();
        recyclerArray.setHasFixedSize(true);
       // recyclerArray.setLayoutManager(new GridLayoutManager(ClientesView2.getContext(), 6));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ClientesView2.getContext(),6);


        recyclerArray.setLayoutManager(gridLayoutManager);


        myrootDbaseref5 = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mystorage = FirebaseStorage.getInstance();
        final AdaptadorProductoGuardado adapter1=new AdaptadorProductoGuardado();
        recyclerArray.setAdapter(adapter1);

        context = this;
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(SharedViewModel.class);
        noteViewModel=new ViewModelProvider(getActivity()).get(NoteViewModel.class);
       noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<java.util.List<Note>>() {
           @Override
           public void onChanged(java.util.List<Note> notes) {
               adapter1.setNotes(notes);
           }
       });

    /*   sharedViewModel.getUnidadesLista2().observe(getViewLifecycleOwner(), new Observer<ArrayList<arrayconstructor>>() {
            @Override
            public void onChanged(ArrayList<arrayconstructor> arrayconstructors) {
               mAdapter.notifyDataSetChanged();







            }


        });

        sharedViewModel.init4();
         mAdapter = new arrayadapterclass(sharedViewModel.getUnidadesLista2().getValue());
        mAdapter = new arrayadapterclass(sharedViewModel.getUnidadesLista2().getValue());
        recyclerArray.setAdapter(mAdapter);*/



        return ClientesView2;

    }















    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shareViewModel2 = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(ShareViewModel2.class);
        shareViewModel2.getNombrepdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String nombrepdf) {
            }
        });
        shareViewModel2.getProductopdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String productopdf) {
            }
        });
        shareViewModel2.getMedidaepdf().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {

            }
        });
        shareViewModel2.getUnidadespdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String unidadespdf) {
            }
        });
        shareViewModel2.getValorpdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String valorpdf) {
            }
        });
        shareViewModel2.getFechapdf().observe(getViewLifecycleOwner(), new Observer<ByteArrayOutputStream>() {

            @Override
            public void onChanged(ByteArrayOutputStream byteArrayOutputStream) {

            }
        });
        shareViewModel2.getHorapdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String horapdf) {
            }
        });
        shareViewModel2.getPreciopdf().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String preciopdf) {
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);



        }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onButtonAddClick(int position) {

    }

    @Override
    public void onButtonclienteClick(int position) {

    }

    @Override
    public void passingproductoClick(int position, CharSequence Producto, CharSequence Precio, CharSequence Cantidad) {

    }



    @Override
    public void passingprecio1Click(int position, CharSequence Precio) {

    }

    @Override
    public void passingcliente2Click(int position, CharSequence Cliente, String phone, String Email, String Address, String City) {

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
        position1=position;

    }

    @Override
    public void PassTipoDoc(int position, CharSequence tipoDoc) {

    }

    @Override
    public void PassnoteprodPosition(int position, String Producto, String Cantidad, String Precio, NoteProducto currentnote) {

    }




}





