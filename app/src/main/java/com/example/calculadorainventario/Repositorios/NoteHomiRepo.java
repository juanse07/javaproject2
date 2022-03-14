package com.example.calculadorainventario.Repositorios;

import android.app.Application;
import android.os.AsyncTask;

import com.example.calculadorainventario.Constructores.NoteHomis;
import com.example.calculadorainventario.Dao.NoteHomisDao;
import com.example.calculadorainventario.DataBases.NoteHomisDataBase;
import com.example.calculadorainventario.NoteProdDao;
import com.example.calculadorainventario.NoteProducto;
import com.example.calculadorainventario.NoteProductoRep;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteHomiRepo {
    private NoteHomisDao noteHomisDao;
    private LiveData<List<NoteHomis>> allNotes;
    private LiveData<List<Double>>allDoubleNotes;
    private LiveData<Double>getTotal;
    private LiveData<Double>getcantTotal;

    public NoteHomiRepo(Application application){
        NoteHomisDataBase noteHomisDataBase=NoteHomisDataBase.getInstance(application);
        noteHomisDao=noteHomisDataBase.noteHomisDao();
        allNotes=noteHomisDao.getallnotes();
        getTotal=noteHomisDao.getTotal();
        getcantTotal=noteHomisDao.getcantTotal();

    }
    public void Insert(NoteHomis noteHomis){
        new NoteHomiRepo.InserNotesProdAsynk(noteHomisDao).execute(noteHomis);

    }
    public void Update(NoteHomis noteHomis){
        new NoteHomiRepo.UpdateNotesProdAsynk(noteHomisDao).execute(noteHomis);

    }
    public void  Delete(NoteHomis noteHomis){
        new com.example.calculadorainventario.Repositorios.NoteHomiRepo.DeleteNotesProdAsynk(noteHomisDao).execute(noteHomis);

    }
    public void DeleteAllNotes(){
        new NoteHomiRepo.DeleteAllNotesProdAsynk(noteHomisDao).execute();

    }
    public LiveData<List<NoteHomis>>getAllNotes(){
        return allNotes;
    }

    public LiveData<Double>getGetTotal(){return getTotal;}
    public LiveData<Double>getGetcantTotal(){return getcantTotal;}

    public LiveData<List<Double>>getAllDoubleNotes(){
        return allDoubleNotes;}

    private static class InserNotesProdAsynk extends AsyncTask<NoteHomis,Void,Void> {
        private NoteHomisDao noteHomisDao;
        private InserNotesProdAsynk(NoteHomisDao noteHomisDao){
            this.noteHomisDao=noteHomisDao;
        }
        @Override
        protected Void doInBackground(NoteHomis... notesHomis) {
            noteHomisDao.Insert(notesHomis[0]);
            return null;
        }
    }
    private static class UpdateNotesProdAsynk extends AsyncTask<NoteHomis,Void,Void>{
        private NoteHomisDao noteHomisDao;
        private UpdateNotesProdAsynk(NoteHomisDao noteHomisDao){
            this.noteHomisDao=noteHomisDao;
        }
        @Override
        protected Void doInBackground(NoteHomis... notesHomis) {
            noteHomisDao.Update(notesHomis[0]);
            return null;
        }
    }
    private static class DeleteNotesProdAsynk extends AsyncTask<NoteHomis,Void,Void>{
        private NoteHomisDao noteHomisDao;
        private DeleteNotesProdAsynk(NoteHomisDao noteHomisDao){
            this.noteHomisDao=noteHomisDao;
        }
        @Override
        protected Void doInBackground(NoteHomis... notesHomis) {
            noteHomisDao.Delete(notesHomis[0]);
            return null;
        }
    }
    private static class DeleteAllNotesProdAsynk extends AsyncTask<Void,Void,Void>{
        private NoteHomisDao noteHomisDao;
        private DeleteAllNotesProdAsynk(NoteHomisDao noteHomisDao){
            this.noteHomisDao=noteHomisDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteHomisDao.DeleteAll();
            return null;
        }
    }
    private static class DeleteLastNotesAsynk extends AsyncTask<Void,Void,Void>{
        private NoteHomisDao noteHomisDao;
        private DeleteLastNotesAsynk(NoteHomisDao noteHomisDao){
            this.noteHomisDao=noteHomisDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
//                noteHomisDao.DeleteLast();
            return null;
        }
    }
    private static class getallNotes2Asynk extends AsyncTask<Void,Void,Void>{
        private NoteHomisDao noteHomisDao;
        private getallNotes2Asynk(NoteHomisDao noteHomisDao){
            this.noteHomisDao=noteHomisDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteHomisDao.getallnotes();
            return null;
        }
    }

}
