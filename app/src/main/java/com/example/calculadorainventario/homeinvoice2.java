package com.example.calculadorainventario;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadorainventario.Adapadores.BuscarAdaptador;
import com.example.calculadorainventario.Adapadores.NoteHomisAdapter;
import com.example.calculadorainventario.Adapadores.homeinvoiceadapterclass;
import com.example.calculadorainventario.Constructores.NoteHomis;
import com.example.calculadorainventario.Constructores.NoteProducto;
import com.example.calculadorainventario.Constructores.constcards;
import com.example.calculadorainventario.ViewModel.NoteHomisViewModel;
import com.example.calculadorainventario.ViewModel.SharedViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class homeinvoice2 extends AppCompatActivity implements ClickInterface1 {

    RecyclerView recyclerview1;
    DatabaseReference myDatabasehome, ref;
    SwipeRefreshLayout swipehome;
    BottomNavigationView navigationView;
    ImageView reminder_1, IMAGEHOME;
    CardView card_opciones;
    FirebaseAuth mAuth;
    SearchView edtbuscar;
    NavigationView navdrawer;
    String Firekey;
    Toolbar barhome1;
    RecyclerView RecyclerBusqueda;
    RecyclerView.Adapter madapter;
    CharSequence tipodoc, tipodoc2;
    NoteHomisViewModel noteHomisViewModel;
    SelectLanguaje selectLanguaje;
    String Lang;
    ArrayList<NoteHomis>noteHomis1;
    Context context;




    int contarventas;
    HomeNote homeNote = new HomeNote();
    List<HomeNote> listahomenotes = new ArrayList<>();
    List<NoteProducto> ListaProd1;
    ArrayList<String> ListaProductos;

    RecyclerView.LayoutManager Lmanager;
    String Start, EndDate;
    NoteProdViewModel noteProdViewModel;
    TextView title2;
    AppBarLayout appbar1;
    ArrayList<CalendarContract.Events> eventsLists, filterevents;
    DrawerLayout drawer1;
    ActionBarDrawerToggle mdrawer;
    LinearLayout gridmenu;
     SharedViewModel sharedViewModel;
     homeinvoiceadapterclass myadaptador2;
     NoteHomisAdapter myadaptador3;
    ArrayList<String> Busquedas;
    ArrayList<constcards> Listahomesql;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter, firebaseRecyclerAdapter2;

    ArrayList<String> listaproductos;
    ArrayList<String> getListaNombres;
    View cartas1;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Lang=Constants.getSP(getApplicationContext()).getLANG();
        SetLanguage(Lang);
        setContentView(R.layout.activity_home1);
        barhome1 = findViewById(R.id.barhome1);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);

        drawer1 = findViewById(R.id.drawer1);
        title2 = findViewById(R.id.title2);
        swipehome = findViewById(R.id.swipehome);
        appbar1 = findViewById(R.id.appbar1);
        edtbuscar = findViewById(R.id.edtbuscar);
        IMAGEHOME = findViewById(R.id.IMAGEHOME);
        navdrawer = findViewById(R.id.navdrawer);
        card_opciones = findViewById(R.id.card_opciones);
        RecyclerBusqueda = findViewById(R.id.RecyclerBusqueda);
        setContext1(getApplicationContext());

        PreferenceManager.setDefaultValues(this,R.xml.preference,false);
        noteProdViewModel = new ViewModelProvider(this).get(NoteProdViewModel.class);
        noteHomisViewModel=new ViewModelProvider(this).get(NoteHomisViewModel.class);
        noteProdViewModel.getAllNotes().observe(this, new Observer<List<NoteProducto>>() {
            @Override
            public void onChanged(List<NoteProducto> noteProductos) {
                ListaProd1 = noteProductos;
            }
        });




        //mdrawer = new ActionBarDrawerToggle(this, drawer1, R.string.Open, R.string.Close);
        //drawer1.setDrawerListener(mdrawer);
        //
        //getSupportActionBar().hide();//Ocultar ActivityBar anterior


        setSupportActionBar(barhome1);
       /* barhome1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP);{
                    Cir
                }
            }
        });*/


        //mdrawer.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String first = "PyMESoft";
        String secondname="<font color='#ffffff'>Facturas</font>";
        String next = getResources().getString(R.string.app_name2);
//
        title2.setText(Html.fromHtml(first+secondname));
//        title2.setText(getResources().getString(R.string.app_name));
//        title2.setTextColor(getResources().getColor(R.color.Turquesa));
        IMAGEHOME.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Menu menu = navdrawer.getMenu();
                MenuItem item = menu.findItem(R.id.pymename);

                SpannableString s = new SpannableString(menu.findItem(R.id.pymename).getTitle().toString());
                s.setSpan(new TextAppearanceSpan(homeinvoice2.this, R.style.titledrawer), 0, s.length(), 0);
                item.setTitle(s);
                //new ForegroundColorSpan(getResources().getColor(R.color.colorAccent))


                // open drawer here
                if (drawer1.isDrawerOpen(GravityCompat.START)) {
                    drawer1.closeDrawer(GravityCompat.START);
                } else {
                    drawer1.openDrawer(GravityCompat.START);
                }
            }
        });


        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //IMAGEHOME.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View v) {
        //  if(drawer1.isDrawerOpen(drawer1)){
        //    drawer1.closeDrawer(drawer1);
        //   }
        //}
        //});
        File imgFile = new File("/storage/emulated/0/PyMESoft/Logotipo/logopng");
        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


            IMAGEHOME.setImageBitmap(myBitmap);
        }

        myDatabasehome = FirebaseDatabase.getInstance().getReference().child("VENTAS").child(id);
        // edtbuscar.setBackgroundColor(getResources().getColor(R.color.colorBlancox));

        /*edtbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtbuscar.setIconified(false);
                edtbuscar.setQuery("",false);
            }
        });*/
        edtbuscar.setVisibility(View.INVISIBLE);

        if (edtbuscar != null) {
            edtbuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    myadaptador2.getFilter().filter(newText);
                    return false;
                    // newText = edtbuscar.getQuery().toString();


                    // firebaseUserSearch (newText);
                    // return true;
                }
            });
        }


        Busquedas = new ArrayList<>();
        String Invoice=getResources().getString(R.string.Sales);
        String Receipts=getResources().getString(R.string.Receipts);
        String Draft=getResources().getString(R.string.Draft);
        Busquedas.add(Invoice);
        Busquedas.add(Receipts);
        Busquedas.add(Draft);

//    /        Busquedas.add("Hoy");
//        Busquedas.add("Semana");
//        Busquedas.add("Enero");
//        Busquedas.add("Febrero");
//        Busquedas.add("Marzo");
//        Busquedas.add("Abril");
//        Busquedas.add("Mayo");
//        Busquedas.add("Junio");
//        Busquedas.add("Julio");
//        Busquedas.add("Agosto");
//        Busquedas.add("Septiembre");
//        Busquedas.add("Octubre");
//        Busquedas.add("Noviembre");
//        Busquedas.add("Diciembre");


        RecyclerBusqueda.setHasFixedSize(true);

        Lmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        madapter = new BuscarAdaptador(Busquedas, this);
        RecyclerBusqueda.setLayoutManager(Lmanager);
        RecyclerBusqueda.setAdapter(madapter);
        sharedViewModel = new ViewModelProvider(homeinvoice2.this).get(SharedViewModel.class);
        // sharedViewModel.DeleteAllhomeNotes();
//        sharedViewModel.getdato().observe(this, new Observer<ArrayList<constcards>>() {
//            @Override
//            public void onChanged(ArrayList<constcards> constcards) {
//                Listahomesql=constcards;
//
//            }
//        });
        //  sharedViewModel.Insert(homeNote);
       /* sharedViewModel.getCountVentas().observe(home1.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
               // Toast.makeText(home1.this, String.valueOf(integer), Toast.LENGTH_SHORT).show();
            }
        });
        sharedViewModel.getAllNotes().observe(home1.this, new Observer<List<HomeNote>>() {
            @Override
            public void onChanged(List<HomeNote> homeNotes) {
                listahomenotes=homeNotes;
                listahomenotes.size();
                Toast.makeText(home1.this, String.valueOf(listahomenotes.size()), Toast.LENGTH_SHORT).show();

            }
        });*/
        sharedViewModel.getSearchTipo().observe(homeinvoice2.this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                tipodoc2 = charSequence.toString();
                //FilterData();
                CharSequence charsales=getResources().getString(R.string.Sales);

                if (charSequence.equals(getResources().getString(R.string.Receipts))||charSequence.equals(getResources().getString(R.string.Sales))||charSequence.equals(getResources().getString(R.string.Draft))) {
                    edtbuscar.setQuery(charSequence, false);



//
                } else if (charSequence.equals("Noviembre")) {
                    edtbuscar.setQuery("nov", false);
                } else if (charSequence.equals("Octubre")) {
                    edtbuscar.setQuery("oct", false);
                } else if (charSequence.equals("Diciembre")) {
                    edtbuscar.setQuery("dic", false);
                }


            }
        });
        swipehome.setColorSchemeResources(R.color.colorAccent);
        swipehome.hasNestedScrollingParent();

        swipehome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                edtbuscar.setQuery("", false);


                sharedViewModel = new ViewModelProvider(homeinvoice2.this).get(SharedViewModel.class);
                /* sharedViewModel.getSearchTipo().observe(home1.this, new Observer<CharSequence>() {
                   @Override
                   public void onChanged(CharSequence charSequence) {
                       edtbuscar.setQuery(charSequence, false);

                   }
               });*/
//
               sharedViewModel.init2();

                sharedViewModel.getdato().observe(homeinvoice2.this, new Observer<ArrayList<constcards>>() {
                    @Override
                    public void onChanged(ArrayList<constcards> Constcard) {
                        myadaptador2.notifyDataSetChanged();


                    }
                });
               myadaptador2 = new homeinvoiceadapterclass(sharedViewModel.getdato().getValue());




                recyclerview1.setAdapter(myadaptador2);

                swipehome.setRefreshing(false);
            }
        });


        //myDatabasehome.keepSynced(true);


        recyclerview1 = (RecyclerView) findViewById(R.id.recyclerview1);
        //recyclerview1.setHasFixedSize(true);
        recyclerview1.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerview1.setLayoutManager(linearLayoutManager);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
       sharedViewModel.init2();



        sharedViewModel.getdato().observe(this, new Observer<ArrayList<constcards>>() {
            @Override
            public void onChanged(ArrayList<constcards> Constcard) {
                myadaptador2.notifyDataSetChanged();}
        });
//
  noteHomisViewModel = new ViewModelProvider(this).get(NoteHomisViewModel.class);
//            }
//        });
       myadaptador2 = new homeinvoiceadapterclass(sharedViewModel.getdato().getValue());






        recyclerview1.setAdapter(myadaptador2);


        noteHomisViewModel.getSumTotal().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                Log.d("sumadehomis",String.valueOf(aDouble));
            }
        });
        noteHomisViewModel.getAllNotes().observe(this, new Observer<List<NoteHomis>>() {
            @Override
            public void onChanged(List<NoteHomis> noteHomis) {
                for(int i = 0; i<noteHomis.size(); i++) {
                    Log.d("sumadehomis", String.valueOf(noteHomis.get(i)));
                }
            }
        });


        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
//                    case R.id.listaclientes2:
//
//                        startActivity(new Intent(getApplicationContext(), ingresodat.class));
//                        overridePendingTransition(0, 0);
//                        return true;
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), homeinvoice2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_more:
                        startActivity(new Intent(getApplicationContext(), InclientActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_add:
                        startActivity(new Intent(getApplicationContext(), Crearproducto.class));
                        overridePendingTransition(0, 0);
                        return true;
                    // case R.id.action_Pdf:
                    //   startActivity(new Intent(getApplicationContext(), pdfviewer.class));
                    // overridePendingTransition(0, 0);
                    //return true;
                }
                return false;
            }
        });

        navdrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.logout1:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), loginactivity.class));
                        return true;
                    case  R.id.confempresa:
                        startActivity(new Intent(getApplicationContext(), settingsAcivity.class));
                        return true;


                }
                return false;
            }
        });


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
        Firekey=key;

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
        // tipodoc2=tipoDoc.toString();
        //FilterData();

        sharedViewModel = new ViewModelProvider(homeinvoice2.this).get(SharedViewModel.class);
        Log.d("value of the state",String.valueOf(tipoDoc));


        sharedViewModel.setSearchTipo(tipoDoc);

    }

    @Override
    public void PassnoteprodPosition(int position, String Producto, String Cantidad, String Precio, NoteProducto currentnote) {

    }


    public void iniciarhome(View view) {


        Intent i = new Intent(this, fragments3.class);
        startActivity(i);
    }

    public void reminderpage(View view) {
        Intent i = new Intent(this, reminderAc.class);
        startActivity(i);
    }


//    public void downloadpdf(Context context, String file, String fileExtension, String destinationDirectory, String url) {
//        // url = .getText().toString().trim();
//
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(url);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalFilesDir(context, destinationDirectory, file + fileExtension);
//        downloadManager.enqueue(request);
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onResume() {
        super.onResume();



//        Locale locale = Constants.getSP(homeinvoice2.this).getLANG();
//        Locale.setDefault(locale);
//        Configuration config = getBaseContext().getResources().getConfiguration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());
        File imgFile = new File("/storage/emulated/0/PyMESoft/Logotipo/logopng");
        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


            IMAGEHOME.setImageBitmap(myBitmap);

        }
        navigationView.setSelectedItemId(R.id.home);

    }

    @Override
    protected void onRestart() {

        super.onRestart();
        navigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Lang=Constants.getSP(getApplicationContext()).getLANG();
        SetLanguage(Lang);
        getMenuInflater().inflate(R.menu.bottom_menuup, menu);
        MenuItem Buscador = menu.findItem(R.id.edtbuscar2);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //int positionOfMenuItem = 0; // or whatever...


        SearchView searchView = (SearchView) menu.findItem(R.id.edtbuscar2).getActionView();
        searchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        EditText searchEditText = (EditText) searchView.findViewById(R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorGrisoscuro));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorGrisoscuro));
        searchView.setQueryHint("Quiero encontrar...");

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                return false;
            }
        });


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // this.menu = menu;  // this will copy menu values to upper defined menu so that we can change icon later akash


        // menu.clear();
        // SearchView searchView =(SearchView) MenuItemCompat.getActionView(Buscador);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myadaptador2.getFilter().filter(newText);
                return false;
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        navigationView.setSelectedItemId(R.id.home);


        return super.onCreateOptionsMenu(menu);

    }

    public void FilterData() {
        int currentyr = Calendar.getInstance().get(Calendar.YEAR);

        if (tipodoc.toString().equals("Enero"))
            Start = String.valueOf(currentyr) + "-10-01";
        EndDate = String.valueOf(currentyr) + "-10-31";


        if (!Start.equals("") || !EndDate.equals("") || !Start.equals(null) || !EndDate.equals(null)) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                Date strDate = dateFormat.parse(Start);
                Date endDate = dateFormat.parse(EndDate);
                //myadaptador2.filterDateRange(strDate,endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
    public void SetLanguage(String Lang) {

        Locale locale = new Locale(Lang);

        Locale.setDefault(locale);
        Configuration configuration = new Configuration();

        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
//        Intent refresh = new Intent(getIntent());
//        startActivity(refresh);
//        finish();

//        Constants.getSP(getApplicationContext()).setLANG(Language);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void LoadLocale(String lang) {
//        SetLanguage(lang);

    }
    public Context context1(){
        return context;
    }
    public void setContext1(Context context) {
        this.context = context;
    }
}







