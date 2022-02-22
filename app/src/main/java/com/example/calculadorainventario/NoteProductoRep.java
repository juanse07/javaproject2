package com.example.calculadorainventario;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteProductoRep {

        private NoteProdDao noteProdDao;
        private LiveData<List<NoteProducto>> allNotes;
        private LiveData<List<Double>>allDoubleNotes;

        private LiveData<List<NoteProducto>>LastNote;
        private LiveData<Double>getTotal;
    private LiveData<Double>getcantTotal;
    private LiveData<Double>getsumResultadoImpuesto;
        private LiveData<Double>calcPromed;
        private LiveData<Integer>CalcCount;

    private LiveData<Integer> getsumProdnom() {
        return null;
    }

    public NoteProductoRep(Application application){
            NoteProdDataBase dataBase=NoteProdDataBase.getInstance(application);
            noteProdDao=dataBase.noteProdDao();
            allNotes=noteProdDao.getallnotes();
            getTotal=noteProdDao.getTotal();
            getcantTotal=noteProdDao.getcantTotal();
            getsumResultadoImpuesto=noteProdDao.getsumResultadoImpuesto();
//            calcPromed=noteProdDao.getPromed();
//            CalcCount=noteProdDao.getCount();
           allDoubleNotes=noteProdDao.getallDoublenotes();

        }
        public void Insert(NoteProducto noteProducto){
            new NoteProductoRep.InserNotesProdAsynk(noteProdDao).execute(noteProducto);

        }
        public void Update(NoteProducto noteProducto){
            new NoteProductoRep.UpdateNotesProdAsynk(noteProdDao).execute(noteProducto);

        }
        public void  Delete(NoteProducto noteProducto){
            new com.example.calculadorainventario.NoteProductoRep.DeleteNotesProdAsynk(noteProdDao).execute(noteProducto);

        }
        public void DeleteAllNotes(){
            new com.example.calculadorainventario.NoteProductoRep.DeleteAllNotesProdAsynk(noteProdDao).execute();

        }

//        public void DeleteLastNotes(){
//            new com.example.calculadorainventario.NoteRepository.DeleteLastNotesAsynk(noteProdDao).execute();
//
//        }
//        public void getallNotes2(){
//            new com.example.calculadorainventario.NoteRepository.getallNotes2Asynk(noteProdDao).execute();
//
//        }
        public LiveData<List<NoteProducto>>getAllNotes(){
            return allNotes;
        }
        public LiveData<List<NoteProducto>>getLastNote(){
            return LastNote;
        }
        public LiveData<Double>getGetTotal(){return getTotal;}
        public LiveData<Double>getGetcantTotal(){return getcantTotal;}
        public LiveData<Double>getGetsumResultadoImpuesto(){return getsumResultadoImpuesto;}
        public LiveData<List<Double>>getAllDoubleNotes(){
            return allDoubleNotes;}
        public LiveData<Double>getCalcPromed(){return calcPromed;}
        public LiveData<Integer>getCalcCount(){return CalcCount;}

        private static class InserNotesProdAsynk extends AsyncTask<NoteProducto,Void,Void> {
            private NoteProdDao noteProdDao;
            private InserNotesProdAsynk(NoteProdDao noteProdDao){
                this.noteProdDao=noteProdDao;
            }
            @Override
            protected Void doInBackground(NoteProducto... notesProd) {
                noteProdDao.Insert(notesProd[0]);
                return null;
            }
        }
        private static class UpdateNotesProdAsynk extends AsyncTask<NoteProducto,Void,Void>{
            private NoteProdDao noteProdDao;
            private UpdateNotesProdAsynk(NoteProdDao noteProdDao){
                this.noteProdDao=noteProdDao;
            }
            @Override
            protected Void doInBackground(NoteProducto... notesProd) {
                noteProdDao.Update(notesProd[0]);
                return null;
            }
        }
        private static class DeleteNotesProdAsynk extends AsyncTask<NoteProducto,Void,Void>{
            private NoteProdDao noteProdDao;
            private DeleteNotesProdAsynk(NoteProdDao noteProdDao){
                this.noteProdDao=noteProdDao;
            }
            @Override
            protected Void doInBackground(NoteProducto... notesProd) {
                noteProdDao.Delete(notesProd[0]);
                return null;
            }
        }
        private static class DeleteAllNotesProdAsynk extends AsyncTask<Void,Void,Void>{
            private NoteProdDao noteProdDao;
            private DeleteAllNotesProdAsynk(NoteProdDao noteProdDao){
                this.noteProdDao=noteProdDao;
            }


            @Override
            protected Void doInBackground(Void... voids) {
                noteProdDao.DeleteAll();
                return null;
            }
        }
        private static class DeleteLastNotesAsynk extends AsyncTask<Void,Void,Void>{
            private NoteProdDao noteProdDao;
            private DeleteLastNotesAsynk(NoteProdDao noteProdDao){
                this.noteProdDao=noteProdDao;
            }


            @Override
            protected Void doInBackground(Void... voids) {
//                noteProdDao.DeleteLast();
                return null;
            }
        }
        private static class getallNotes2Asynk extends AsyncTask<Void,Void,Void>{
            private NoteProdDao noteProdDao;
            private getallNotes2Asynk(NoteProdDao noteProdDao){
                this.noteProdDao=noteProdDao;
            }


            @Override
            protected Void doInBackground(Void... voids) {
                noteProdDao.getallnotes();
                return null;
            }
        }
        //ya lo modifique

    }

