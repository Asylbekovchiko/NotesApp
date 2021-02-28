package kg.asylbekov.notesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Notes.class}, version = 1,exportSchema = false)
public abstract class NotesDataBase extends RoomDatabase {
    private static final String NAME = "db.db";
    private static NotesDataBase notesDataBase;
    private static final Object LOCK = new Object();
    public static NotesDataBase getInstance(Context context){
        synchronized (LOCK) {
            if (notesDataBase == null) {
                notesDataBase = Room.databaseBuilder(context, NotesDataBase.class, NAME)
                        .allowMainThreadQueries()
                        .build();
            }

        }
        return notesDataBase;
    }
    public abstract NotesDao notesDao();

}
