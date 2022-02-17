package com.example.calculadorainventario;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {NoteProducto.class},version = 3)

public abstract class NoteProdDataBase extends RoomDatabase {
    @NonNull
    private static NoteProdDataBase instance;
    public abstract NoteProdDao noteProdDao();
    public static synchronized NoteProdDataBase  getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteProdDataBase.class,"note_Proddatabase")
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
            new NoteProdDataBase.PopulatedbAsynkTask(instance).execute();
        }
    };
    public static class PopulatedbAsynkTask extends AsyncTask<Void,Void,Void> {
        private NoteProdDao noteProdDao;
        private PopulatedbAsynkTask(NoteProdDataBase db){
            noteProdDao=db.noteProdDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //noteDao.Insert(new Note(String.valueOf(47)));
            //noteDao.Insert(new Note(String.valueOf(89.9)));

            return null;
        }
    }
    //Listo ya lo modifique!!
}
