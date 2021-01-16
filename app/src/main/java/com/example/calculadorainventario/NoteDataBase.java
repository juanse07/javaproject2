package com.example.calculadorainventario;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Note.class},version = 5)

public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;
    public abstract NoteDao noteDao();
    public static synchronized NoteDataBase  getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDataBase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomcallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatedbAsynkTask(instance).execute();
        }
    };
    public static class PopulatedbAsynkTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private PopulatedbAsynkTask(NoteDataBase db){
            noteDao=db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //noteDao.Insert(new Note(String.valueOf(47)));
            //noteDao.Insert(new Note(String.valueOf(89.9)));

            return null;
        }
    }
}
