package com.example.calculadorainventario;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;

    private LiveData<Double>SumTotal;
    private LiveData<List<Double>>allDoubleNotes;
    private LiveData<List<Note>>allnotes;
    private LiveData<Double>CalcPromed;
    private LiveData<Integer>CalcCount;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);

        SumTotal=noteRepository.getGetTotal();
        CalcPromed=noteRepository.getCalcPromed();
        CalcCount=noteRepository.getCalcCount();
        allnotes=noteRepository.getAllNotes();
        allDoubleNotes=noteRepository.getAllDoubleNotes();


       // LastNote=noteRepository.getLastNote();
    }
    public void Insert(Note note){
        noteRepository.Insert(note);
    }
    public void Update(Note note){
        noteRepository.Update(note);
    }
    public void Delete(Note note){
        noteRepository.Delete(note);
    }
    public void DeleteAll(){
        noteRepository.DeleteAllNotes();
    }
   public void getallnotes2(){noteRepository.getallNotes2();}
   public LiveData<List<Double>>getAllDoubleNotes(){

        return allDoubleNotes;
   }
    public LiveData<List<Note>>getAllNotes (){
        return allnotes;

    }
    public LiveData<Double>getSumTotal (){
        return SumTotal;

    }
    public LiveData<Double>getCalcPromed (){
        return CalcPromed ;

    }
    public LiveData<Integer>getCalcCount (){
        return CalcCount ;

    }
    public void DeleteLastNote(){
        noteRepository.DeleteLastNotes();
    }
   /* public void SumNotes(){

        noteRepository.SumAllNotes();
    }*/
}
