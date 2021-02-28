package kg.asylbekov.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNotes extends AppCompatActivity {
    private EditText editTitle;
    private EditText editDescription;
    private Spinner spinner;
    private RadioGroup radioGroup;/*
    private NotesDataBase notesDataBase;*/
    private ViewModel viewModel;
    /*
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase database;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);/*
        notesDataBase = NotesDataBase.getInstance(this);*/
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
    /*    sqLiteHelper = new SQLiteHelper(this);
        database = sqLiteHelper.getWritableDatabase();
    */}

    public void addAllNotes(View view) {
        String title= editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        String spinnerNotes = spinner.getSelectedItem().toString();
        int checkButton = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkButton);
        int priority = Integer.parseInt(radioButton.getText().toString());
        if(isFilled(title, description)){
            Notes notes = new Notes(title, description, spinnerNotes, priority);
            viewModel.insertData(notes);
            /*notesDataBase.notesDao().insertData(notes);*/
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    private boolean isFilled(String title, String description){
        return !title.isEmpty() && !description.isEmpty();

    }
}