package com.example.calculadorainventario;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.calculadorainventario.Constructores.NoteHomis;
import com.example.calculadorainventario.Constructores.constcards;
import com.example.calculadorainventario.Constructores.cuerospinner;
import com.example.calculadorainventario.Dao.NoteHomisDao;
import com.example.calculadorainventario.DataBases.NoteHomisDataBase;
import com.example.calculadorainventario.Repositorios.NoteHomiRepo;
import com.example.calculadorainventario.ViewModel.NoteHomisViewModel;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class Repositorio1 {
    HomeNoteDao homeNoteDao;
    homeinvoice2 homeinvoice2;
    DatabaseReference reference, referenceoffline;
    SharedViewModel sharedViewModel;
    NoteHomisViewModel noteHomisViewModel;
    NoteHomis noteHomis=new NoteHomis();
    FirebaseAuth mAuth;
    Context context;
    private NoteHomisDao noteHomisDao;
    private LiveData<List<NoteHomis>> allNotes;
    private LiveData<List<Double>>allDoubleNotes;
    private LiveData<Double>getTotal;
    private LiveData<Double>getcantTotal;


    static Repositorio1 instance;
    private ArrayList<cuerospinner> productos = new ArrayList<>();
    private MutableLiveData<ArrayList<cuerospinner>> productos1 = new MutableLiveData<>();
    private ArrayList<constcards> datos = new ArrayList<>();
    private MutableLiveData<ArrayList<constcards>> datos1 = new MutableLiveData<>();
    private ArrayList<NoteHomis> datoshomesql = new ArrayList<>();
    private MutableLiveData<ArrayList<NoteHomis>> datos1homesql = new MutableLiveData<>();
    private ArrayList<constructornom2> clientes = new ArrayList<constructornom2>();
    private MutableLiveData<ArrayList<constructornom2>> clientes1 = new MutableLiveData<ArrayList<constructornom2>>();
    private ArrayList<Double> Unidadesnuevas = new ArrayList<Double>();
    private MutableLiveData<ArrayList<Double>> Unidadesnuevas1 = new MutableLiveData<ArrayList<Double>>();
    private ArrayList<MatrizLista> productosguardados = new ArrayList<MatrizLista>();
    private MutableLiveData<ArrayList<MatrizLista>> productosguardados1 = new MutableLiveData<ArrayList<MatrizLista>>();
    private LiveData<List<HomeNote>>getallventas;
    private LiveData<Integer> countVentas;
    Double getinput;




    public Repositorio1() {

    }




    public Repositorio1(Application application) {

        NoteHomeDataBase homeDataBase = NoteHomeDataBase.getInstance(application);
        homeNoteDao = homeDataBase.HomenoteDao();
        getallventas=homeNoteDao.getallventas();
        NoteHomisDataBase noteHomisDataBase=NoteHomisDataBase.getInstance(application);
        noteHomisDao=noteHomisDataBase.noteHomisDao();
        allNotes=noteHomisDao.getallnotes();
        getTotal=noteHomisDao.getTotal();
        getcantTotal=noteHomisDao.getcantTotal();
       // countVentas=homeNoteDao.getCountVenta();

    }


    public static Repositorio1 getInstance() {

        if (instance == null) {
            instance = new Repositorio1();
        }

        return instance;
    }

    public MutableLiveData<ArrayList<cuerospinner>> getproductos() {
        // if(productos.size()==-1) {
        loadProductos();
        //}


        productos1.setValue(productos);

        return productos1;


    }
    public LiveData<List<HomeNote>>getGetallventas(){
        return getallventas ;
    }
    public LiveData<Integer>getCountventas(){return countVentas;}
    public void Insert(NoteHomis noteHomis){
        new Repositorio1.InserNotesProdAsynk(noteHomisDao).execute(noteHomis);

    }

    private static class InserNotesProdAsynk extends AsyncTask<NoteHomis,Void,Void> {
        private NoteHomisDao noteHomisDao;

        private InserNotesProdAsynk(NoteHomisDao noteHomisDao) {
            this.noteHomisDao = noteHomisDao;
        }

        @Override
        protected Void doInBackground(NoteHomis... notesHomis) {
            noteHomisDao.Insert(notesHomis[0]);
            return null;
        }
    }


    private void loadProductos() {
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        referenceoffline = FirebaseDatabase.getInstance().getReference("PersistenciaDatosOffline");
        reference = FirebaseDatabase.getInstance().getReference().child("PRODUCTOS").child(id);
        referenceoffline.keepSynced(true);

        //Query query=reference.child("PRODUCTOS");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (productos.size() != 0) {
                        productos.clear();

                    }

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        productos.add(snapshot.getValue(cuerospinner.class));


                    }
                    productos1.postValue(productos);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public MutableLiveData<ArrayList<constcards>> getdatos() {


        // if(productos.size()==-1) {
        loadDatos();



        //}


        datos1.setValue(datos);

        return datos1;


    }

    private void loadDatos() {
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();

        referenceoffline = FirebaseDatabase.getInstance().getReference("PersistenciaDatosOffline");

        reference = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id);
        referenceoffline.keepSynced(true);

        //Query query=reference.child("PRODUCTOS");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (datos.size() != 0) {
                        datos.clear();

                    }

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                        datos.add(snapshot.getValue(constcards.class));


                    }
                    datos1.postValue(datos);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private  void loadDatossql() {
        homeinvoice2=new homeinvoice2();

    //  noteHomisViewModel=new ViewModelProvider((ViewModelStoreOwner) homeinvoice2.context1()).get(NoteHomisViewModel.class);

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();

        referenceoffline = FirebaseDatabase.getInstance().getReference("PersistenciaDatosOffline");

        reference = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id);
        referenceoffline.keepSynced(true);

        //Query query=reference.child("PRODUCTOS");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (datoshomesql.size() != 0) {
                        datoshomesql.clear();

                    }

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {



                        datoshomesql.add(snapshot.getValue(NoteHomis.class));

                       }


                    }
                    datos1homesql.postValue(datoshomesql);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public MutableLiveData<ArrayList<NoteHomis>> getdatossql() {


        // if(productos.size()==-1) {
        loadDatossql();


        //}


        datos1homesql.setValue(datoshomesql);

        return datos1homesql;


    }

    public MutableLiveData<ArrayList<constructornom2>> getClientes() {
        loadClientes();
        //}


        clientes1.setValue(clientes);

        return clientes1;


    }

    private void loadClientes() {
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        referenceoffline = FirebaseDatabase.getInstance().getReference("PersistenciaDatosOffline");
        reference = FirebaseDatabase.getInstance().getReference().child("CLIENTE").child(id);
        referenceoffline.keepSynced(true);

        //Query query=reference.child("PRODUCTOS");
        reference.orderByChild("Nombre").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (clientes.size() != 0) {
                        clientes.clear();

                    }

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        clientes.add(snapshot.getValue(constructornom2.class));


                    }
                    clientes1.postValue(clientes);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


    }

    public MutableLiveData<ArrayList<MatrizLista>> getProductosguardados() {

        loadProductosguardados(context);
        //}


        productosguardados1.setValue(productosguardados);

        return productosguardados1;


    }

    private void loadProductosguardados(Context context) {
        ConexionBD conexionBD;
        conexionBD = new ConexionBD(context, "bd_medida", null, 1);

        SQLiteDatabase db = conexionBD.getReadableDatabase();
        String[] ConsultaMedida = {Utilities.Medida};
        MatrizLista matriz = null;
        Cursor cursor = db.rawQuery("SELECT * FROM MatrizLista", null);
        while (cursor.moveToNext()) {
            matriz = new MatrizLista();
            matriz.setKEY_ID(cursor.getInt(0));
            matriz.setMedida(cursor.getString(1));
            productosguardados.add(matriz);
        }


    }


    private static class InserHomeNotesAsynk extends AsyncTask<HomeNote, Void, Void> {
        private HomeNoteDao homeNoteDao;

        private InserHomeNotesAsynk(HomeNoteDao homeNoteDao) {
            this.homeNoteDao = homeNoteDao;
        }


        @Override
        protected Void doInBackground(HomeNote... homeNotes) {
            homeNoteDao.Insert(homeNotes[0]);
            return null;
        }


    }

    public void Insert(HomeNote homeNote) {
        new InserHomeNotesAsynk(homeNoteDao).execute(homeNote);

    }

    private static class DeleteAllhomeNotes extends AsyncTask<Void, Void, Void> {
        private HomeNoteDao homeNoteDao;

        private DeleteAllhomeNotes(HomeNoteDao homeNoteDao) {
            this.homeNoteDao = homeNoteDao;
        }




        @Override
        protected Void doInBackground(Void... voids) {
            homeNoteDao.deleteallHome();
            return null;
        }
    }
    public void DeleteAllhomeNotes() {
        new Repositorio1.DeleteAllhomeNotes(homeNoteDao).execute();

    }

    }


