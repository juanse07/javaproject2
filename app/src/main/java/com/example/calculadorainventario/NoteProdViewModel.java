package com.example.calculadorainventario;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteProdViewModel extends AndroidViewModel {

        private NoteProductoRep noteProductoRep;

        private LiveData<Double> SumTotal;
    private LiveData<Double> SumcantTotal;

        private LiveData<List<Double>>allDoubleNotes;
        private LiveData<List<NoteProducto>>allnotes;
        private LiveData<Double>CalcPromed;
        private LiveData<Integer>CalcCount;
        public NoteProdViewModel(@NonNull Application application) {
            super(application);
            noteProductoRep=new NoteProductoRep(application);

            SumTotal=noteProductoRep.getGetTotal();
            SumcantTotal=noteProductoRep.getGetcantTotal();
            CalcPromed=noteProductoRep.getCalcPromed();
            CalcCount=noteProductoRep.getCalcCount();
            allnotes=noteProductoRep.getAllNotes();
            allDoubleNotes=noteProductoRep.getAllDoubleNotes();


            // LastNote=noteProductoRep.getLastNote();
        }
        public void Insert(NoteProducto noteProducto){
            noteProductoRep.Insert(noteProducto);
        }
        public void Update(NoteProducto noteProducto){ noteProductoRep.Update(noteProducto);
        }
        public void Delete(NoteProducto noteProducto){
            noteProductoRep.Delete(noteProducto);
        }
        public void DeleteAll(){
            noteProductoRep.DeleteAllNotes();
        }
//        public void getallnotes2(){noteProductoRep.getallNotes2();}
        public LiveData<List<Double>>getAllDoubleNotes(){

            return allDoubleNotes;
        }
        public LiveData<List<NoteProducto>>getAllNotes (){
            return allnotes;

        }
        public LiveData<Double>getSumTotal (){
            return SumTotal;

        }
    public LiveData<Double>getSumcantTotal (){
        return SumcantTotal;

    }
        public LiveData<Double>getCalcPromed (){
            return CalcPromed ;

        }
        public LiveData<Integer>getCalcCount (){
            return CalcCount ;

        }
//        public void DeleteLastNote(){
//            noteProductoRep.DeleteLastNotes();
//        }

    }
