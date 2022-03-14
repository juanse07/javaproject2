package com.example.calculadorainventario.ViewModel;

import android.app.Application;

import com.example.calculadorainventario.Constructores.NoteHomis;
import com.example.calculadorainventario.NoteProducto;
import com.example.calculadorainventario.NoteProductoRep;
import com.example.calculadorainventario.Repositorios.NoteHomiRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteHomisViewModel extends AndroidViewModel {
    private NoteHomiRepo noteHomiRepo;

    private LiveData<Double> SumTotal;
    private LiveData<Double> SumcantTotal;
    private LiveData<Double>SumResutadoImpuesto;

    private LiveData<List<Double>>allDoubleNotes;
    private LiveData<List<NoteHomis>>allnotes;
    private LiveData<Double>CalcPromed;
    private LiveData<Integer>CalcCount;
    public NoteHomisViewModel(@NonNull Application application) {
        super(application);
        NoteHomiRepo noteHomiRepo=new NoteHomiRepo(application);
        SumTotal=noteHomiRepo.getGetTotal();

        SumcantTotal=noteHomiRepo.getGetcantTotal();

        allnotes=noteHomiRepo.getAllNotes();
        allDoubleNotes=noteHomiRepo.getAllDoubleNotes();
    }
    public void Insert(NoteHomis noteHomis){
        noteHomiRepo.Insert(noteHomis);
    }
    public void Update(NoteHomis noteHomis){ noteHomiRepo.Update(noteHomis);
    }
    public void Delete(NoteHomis noteHomis){
        noteHomiRepo.Delete(noteHomis);
    }
    public void DeleteAll(){
        noteHomiRepo.DeleteAllNotes();
    }
    //        public void getallnotes2(){noteHomisRep.getallNotes2();}
    public LiveData<List<Double>>getAllDoubleNotes(){

        return allDoubleNotes;
    }
    public LiveData<List<NoteHomis>>getAllNotes (){
        return allnotes;

    }
    public LiveData<Double>getSumTotal (){
        return SumTotal;

    }
    public LiveData<Double>getSumcantTotal (){
        return SumcantTotal;

    }
    public LiveData<Double>getSumResutadoImpuesto(){
        return SumResutadoImpuesto;
    }
    public LiveData<Double>getCalcPromed (){
        return CalcPromed ;

    }
    public LiveData<Integer>getCalcCount (){
        return CalcCount ;

    }
}
