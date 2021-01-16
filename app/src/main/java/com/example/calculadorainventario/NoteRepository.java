package com.example.calculadorainventario;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>>allNotes;
    private LiveData<List<Double>>allDoubleNotes;

    private LiveData<List<Note>>LastNote;
    private LiveData<Double>getTotal;
    private LiveData<Double>calcPromed;
    private LiveData<Integer>CalcCount;

    public NoteRepository(Application application){
        NoteDataBase dataBase=NoteDataBase.getInstance(application);
        noteDao=dataBase.noteDao();
        allNotes=noteDao.getallnotes();
        getTotal=noteDao.getTotal();
        calcPromed=noteDao.getPromed();
        CalcCount=noteDao.getCount();
        allDoubleNotes=noteDao.getallDoublenotes();

    }
    public void Insert(Note note){
        new InserNotesAsynk(noteDao).execute(note);

    }
    public void Update(Note note){
        new UpdateNotesAsynk(noteDao).execute(note);

    }
    public void  Delete(Note note){
        new DeleteNotesAsynk(noteDao).execute(note);

    }
    public void DeleteAllNotes(){
        new DeleteAllNotesAsynk(noteDao).execute();

    }
   /* public void SumAllNotes(){
        new SumNotesAsynk(noteDao).execute();

    }*/
    public void DeleteLastNotes(){
        new DeleteLastNotesAsynk(noteDao).execute();

    }
    public void getallNotes2(){
        new getallNotes2Asynk(noteDao).execute();

    }
    public LiveData<List<Note>>getAllNotes(){
        return allNotes;
    }
    public LiveData<List<Note>>getLastNote(){
        return LastNote;
    }
    public LiveData<Double>getGetTotal(){return getTotal;}
    public LiveData<List<Double>>getAllDoubleNotes(){
        return allDoubleNotes;}
    public LiveData<Double>getCalcPromed(){return calcPromed;}
    public LiveData<Integer>getCalcCount(){return CalcCount;}

    private static class InserNotesAsynk extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private InserNotesAsynk(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNotesAsynk extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private UpdateNotesAsynk(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Update(notes[0]);
            return null;
        }
    }
    private static class DeleteNotesAsynk extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private DeleteNotesAsynk(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsynk extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private DeleteAllNotesAsynk(NoteDao noteDao){
            this.noteDao=noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.DeleteAll();
            return null;
        }
    }
    private static class DeleteLastNotesAsynk extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private DeleteLastNotesAsynk(NoteDao noteDao){
            this.noteDao=noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.DeleteLast();
            return null;
        }
    }
    private static class getallNotes2Asynk extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private getallNotes2Asynk(NoteDao noteDao){
            this.noteDao=noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.getallnotes();
            return null;
        }
    }

}
