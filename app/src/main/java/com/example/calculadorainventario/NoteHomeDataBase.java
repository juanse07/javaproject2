package com.example.calculadorainventario;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = HomeNote.class,version = 2)

public abstract class NoteHomeDataBase extends RoomDatabase {
    private static NoteHomeDataBase instance;
    public abstract HomeNoteDao HomenoteDao();
    public static synchronized NoteHomeDataBase  getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteHomeDataBase.class,"homenote_database")
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
            new NoteHomeDataBase.PopulatehomedbAsynkTask(instance).execute();
        }
    };
    public static class PopulatehomedbAsynkTask extends AsyncTask<Void,Void,Void> {
        private HomeNoteDao homenoteDao;
        private PopulatehomedbAsynkTask(NoteHomeDataBase db){
            homenoteDao=db.HomenoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //noteDao.Insert(new Note(String.valueOf(47)));
            //noteDao.Insert(new Note(String.valueOf(89.9)));

            return null;
        }
    }
}
