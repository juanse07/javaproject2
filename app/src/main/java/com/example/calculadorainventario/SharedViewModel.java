package com.example.calculadorainventario;

import android.app.Application;

import com.example.calculadorainventario.Constructores.arrayconstructor;
import com.example.calculadorainventario.Constructores.constcards;
import com.example.calculadorainventario.Constructores.cuerospinner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SharedViewModel extends AndroidViewModel  {
    private Repositorio1 repositorio1;
    private MutableLiveData<CharSequence> text = new MutableLiveData<>();
    private MutableLiveData<CharSequence> precio = new MutableLiveData<>();
    private MutableLiveData<CharSequence> cliente = new MutableLiveData<>();
    private MutableLiveData<String> bottonradial = new MutableLiveData<>();
    private MutableLiveData<String> companyname = new MutableLiveData<>();
    private MutableLiveData<String> resultado;
    private MutableLiveData<String> textcliente=new MutableLiveData<>();
    private MutableLiveData<String> dias=new MutableLiveData<>();
    private MutableLiveData<String> diasfinal=new MutableLiveData<>();
    private MutableLiveData<ArrayList<cuerospinner>>obtenerproductos;
    private MutableLiveData<ArrayList<constcards>>obtenerdatos;
    private MutableLiveData<ArrayList<MatrizLista>>obtenerproductosguardados;
    private MutableLiveData<ArrayList<constructornom2>>obtenerclientes;
    private MutableLiveData<ArrayList<arrayconstructor>> UnidadesLista2;
    private ArrayList<arrayconstructor> UnidadesLista;
    private MutableLiveData<Integer> radiobtestado=new MutableLiveData<>();
    private MutableLiveData<CharSequence> searchTipo=new MutableLiveData<>();
    private LiveData<Integer>CountVentas;
    private LiveData<List<HomeNote>>allhomenotes;
    private MutableLiveData<String> Taxvalue=new MutableLiveData<>();
    private MutableLiveData<String> Discountvalue=new MutableLiveData<>();




    public SharedViewModel(@NonNull Application application){
        super(application);
        repositorio1=new Repositorio1(application);
        resultado=new MutableLiveData<>();
        CountVentas=repositorio1.getCountventas();
        allhomenotes=repositorio1.getGetallventas();
    }
    public void Insert(HomeNote homeNote){
        repositorio1.Insert(homeNote);
    }
    public void DeleteAllhomeNotes(){repositorio1.DeleteAllhomeNotes();}

    public void initguardado(){
        if(obtenerproductosguardados!=null){
            return;
        }
        obtenerproductosguardados=Repositorio1.getInstance().getProductosguardados();
    }

    public LiveData<ArrayList<MatrizLista>>getproductoguardado(){
        return obtenerproductosguardados;
    }
    public LiveData<Integer>getCountVentas (){
        return CountVentas ;

    }



public  void init4(){

        arrayconstructor array1=new arrayconstructor();

         //PopulateList();
    addMaterial(array1);

    UnidadesLista.remove(0);


        //UnidadesLista2.setValue(UnidadesLista);

    }
    public MutableLiveData<ArrayList<arrayconstructor>>getUnidadesLista2(){
        if(UnidadesLista2==null){

            UnidadesLista2 = new MutableLiveData<>();
            UnidadesLista = new ArrayList<>();}


        return UnidadesLista2;}
   public void addMaterial(arrayconstructor array1){
        //array1.setUnidadesMaterial(55.4);
       // UnidadesLista=new ArrayList<>();

        UnidadesLista.add(array1);



        UnidadesLista2.setValue(UnidadesLista);

        //Log.d("UnidadesLista", UnidadesLista.toString());

    //}
    //public void PopulateList(){
      //  arrayconstructor array1=new arrayconstructor();
        //array1.setUnidadesMaterial(54.7);
        //array1.setUnidadesMaterial(55.4);
        //UnidadesLista=new ArrayList<>();
        //UnidadesLista.add(array1);
        //UnidadesLista.add(array1);
        //Log.d("UnidadesLista", UnidadesLista.toString());

    }








  /////Controla Recyclerview Clientes/////
    public void init3(){
        if(obtenerclientes!=null){
            return;
        }
        obtenerclientes=Repositorio1.getInstance().getClientes();
    }

    public LiveData<ArrayList<constructornom2>>getcliente(){
        return obtenerclientes;
    }

/////controla Recyclerview ventas///////
    public void init2(){
        if(obtenerdatos!=null){
            return;
        }
        obtenerdatos=Repositorio1.getInstance().getdatos();
    }
    public LiveData<ArrayList<constcards>>getdato(){
        return obtenerdatos;
    }
//////controla recyclerview catalogo/////
    public void init(){
        if(obtenerproductos!=null){
            return;
        }
        obtenerproductos=Repositorio1.getInstance().getproductos();
    }

    public LiveData<ArrayList<constcards>>getdatosql(){
        return obtenerdatos;
    }
    //////controla recyclerview catalogo/////
    public void initsqlhome(){
        if(obtenerdatos!=null){
            return;
        }
        obtenerdatos=Repositorio1.getInstance().getHomesql();
    }
public LiveData<ArrayList<cuerospinner>>getproducto(){
        return obtenerproductos;
    }
    //////controla seleccionar producto////

    public void setText(CharSequence input) {
        text.setValue(input);
    }

    public LiveData<CharSequence> getText() {
        return text;
    }

    public void setPrecio(CharSequence input2) {
        precio.setValue(input2);
    }

    public LiveData<CharSequence> getprecio() {
        return precio;
    }
    public void setCliente2(CharSequence input3) {
        cliente.setValue(input3);
    }

    public LiveData<CharSequence> getCliente2() {
        return cliente;
    }

    ////controla seleccionar cliente////
    public void setTextcliente(String input2) {
        textcliente.setValue(input2);
    }

    public LiveData<String> getTextcliente() {
        return textcliente;
    }
    ///////getdrawer/////
    public LiveData<String>getDiscountValue(){return Discountvalue;}
    public void setDiscountvalue(String input11){
        Discountvalue.setValue(input11);
    }

    public LiveData<String>getResultado(){
        return resultado;
    }
    public void drawer(String datadrawer){
        resultado.setValue(obtenerdrawer.getdrawer(datadrawer));

    }
    public LiveData<List<HomeNote>>getAllNotes (){
        return allhomenotes;

    }
    public LiveData<String> getTaxvalue(){return Taxvalue;}
    public void setTaxvalue(String Input10){
        Taxvalue.setValue(Input10);
    }
    public LiveData<String> getboton() {
        return bottonradial;
    }
    public void setboton(String input5) {
        bottonradial.setValue(input5);
    }
    public LiveData<Integer> getbotonestado() {
        return radiobtestado;
    }
    public void setbotonestado(Integer input7) {
        radiobtestado.setValue(input7);
    }
    public LiveData<CharSequence> getSearchTipo() {
        return searchTipo;
    }
    public void setSearchTipo(CharSequence input9) {
        searchTipo.setValue(input9);
    }
    public LiveData<String> getdias() {
        return dias;
    }
    public void setDias(String input5) {
        dias.setValue(input5);
    }
    public LiveData<String>getcompanyname(){return companyname;}
    public void setCompanyname(String inputcompany) {
        companyname.setValue(inputcompany);
    }



    public LiveData<String> getdiasfinal() {
        return diasfinal;
    }
    public void setDiasfinal(String input7) {
        diasfinal.setValue(input7);
    }
   // public void removeMaterial() {
        //array1.setUnidadesMaterial(55.4);
        // UnidadesLista=new ArrayList<>();

     //   UnidadesLista.remove();


       // UnidadesLista2.setValue(UnidadesLista);
    //}





    }


