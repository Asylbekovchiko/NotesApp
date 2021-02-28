package kg.asylbekov.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private ViewModel viewModel;
    /*
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase database;*/

   /* private NotesDataBase dataBase;*/

    private final  ArrayList<Notes> notes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rec);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
     /*   dataBase = NotesDataBase.getInstance(this);*/
    /*    sqLiteHelper = new SQLiteHelper(this);
        database = sqLiteHelper.getWritableDatabase();
    */ /*  if(notes.isEmpty()) {
            notes.add(new Notes("Зубной", "Сходить", "Понеделник", 2));
            notes.add(new Notes("Ужин", "С Роналду", "Суббота", 3));
        }*/



       /* for(Notes note : notes){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBContacts.DBContactsEntry.COLUMN_TITLE, note.getTitle());
            contentValues.put(DBContacts.DBContactsEntry.COLUMN_DESCRIPTION, note.getDescription());
            contentValues.put(DBContacts.DBContactsEntry.COLUMN_DAY_OF_WEEK, note.getDayOfWeek());
            contentValues.put(DBContacts.DBContactsEntry.COLUMN_PRIORITY, note.getPriority());
            database.insert(DBContacts.DBContactsEntry.TABLE_NAME, null, contentValues);
        }
*/


/*
        getData();
*/







        adapter = new NoteAdapter(notes);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            getData();
            recyclerView.setAdapter(adapter);
            adapter.setOnNoteClickListener(new NoteAdapter.OnNoteClickListener() {
                @Override
                public void onClickListener(int position) {
                Toast.makeText(getApplicationContext(), "Свайпни влево или вправо", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onLongClickListener(int position) {
                    Toast.makeText(getApplicationContext(), "Свайпни влево или вправо", Toast.LENGTH_LONG).show();
                }
            });
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
                }
            });
            itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void addNewNotes(View view) {
        Intent intent = new Intent(this, AddNotes.class);
        startActivity(intent);

    }


    public void remove(int position){
        Notes note = notes.get(position);
        viewModel.DeleteData(note);
        /*
        dataBase.notesDao().deleteData(note);*/


        /*
        int id = notes.get(position).getId();
*/
/*
        String whereFrom = DBContacts.DBContactsEntry._ID + " = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        database.delete(DBContacts.DBContactsEntry.TABLE_NAME,whereFrom, whereArgs);
*/
/*
        getData();
*/
/*
        adapter.notifyDataSetChanged();
*/
    }


/*
    public void getData(){
      notes.clear();
        Cursor cursor = database.query(DBContacts.DBContactsEntry.TABLE_NAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DBContacts.DBContactsEntry._ID));
            String title = cursor.getString(cursor.getColumnIndex(DBContacts.DBContactsEntry.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DBContacts.DBContactsEntry.COLUMN_DESCRIPTION));
            String day = cursor.getString(cursor.getColumnIndex(DBContacts.DBContactsEntry.COLUMN_DAY_OF_WEEK));
            int priority = cursor.getInt(cursor.getColumnIndex(DBContacts.DBContactsEntry.COLUMN_PRIORITY));
            Notes notess = new Notes(id,title, description, day, priority);
            notes.add(notess);
        }
        cursor.close();
    }*/
    public void getData(){

        LiveData<List<Notes>> arrayListFromDB = viewModel.getNotesFromViewModel();
        arrayListFromDB.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notesFromLivedata) {
                notes.clear();
                notes.addAll(notesFromLivedata);
                adapter.notifyDataSetChanged();

            }
        });
    }
}