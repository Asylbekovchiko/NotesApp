package kg.asylbekov.notesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notes")
    LiveData<List<Notes>> getData();

    @Insert
    void insertData(Notes notes);

    @Delete
    void deleteData(Notes notes);

    @Query("DELETE FROM notes")
    void deleteAll();
}
