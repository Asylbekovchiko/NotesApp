package kg.asylbekov.notesapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private static NotesDataBase dataBase;
    private LiveData<List<Notes>> notesFromViewModel;
    public ViewModel(@NonNull Application application) {
        super(application);
        dataBase = NotesDataBase.getInstance(getApplication());
        notesFromViewModel = dataBase.notesDao().getData();
    }

    public LiveData<List<Notes>> getNotesFromViewModel() {
        return notesFromViewModel;
    }

    public void insertData(Notes note){
        new InsertTask().execute(note);
    }
    public void DeleteData(Notes note){
        new DeleteTask().execute(note);
    }

    public static class InsertTask extends AsyncTask<Notes, Void, Void>{

        @Override
        protected Void doInBackground(Notes... notes) {
        if(notes != null && notes.length>0){
            dataBase.notesDao().insertData(notes[0]);
        }
        return null;
        }
    }
    public static class DeleteTask extends AsyncTask<Notes, Void, Void>{

        @Override
        protected Void doInBackground(Notes... notes) {
            if(notes != null && notes.length>0){
                dataBase.notesDao().deleteData(notes[0]);
            }
            return null;
        }
    }

}
