package com.memaro.courseroomrx.presentation.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.memaro.courseroomrx.database.NoteDao;
import com.memaro.courseroomrx.database.NoteDatabase;
import com.memaro.courseroomrx.R;
import com.memaro.courseroomrx.entities.Note;
import com.memaro.courseroomrx.presentation.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText noteEditText, descEditText, finishedByEditText;
    private NoteDatabase datebase;
    private NoteDao dao;

    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEditText = findViewById(R.id.note_edit_text);
        descEditText = findViewById(R.id.desc_edit_text);
        finishedByEditText = findViewById(R.id.finished_by_edit_text);
//        datebase = NoteDatabase.getDatabase(this);
//        dao = datebase.noteDao();

        noteViewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);

        noteViewModel.isInserted.observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("CheckResult")
    private void insertNote() {
        String noteName = noteEditText.getText().toString();
        String desc = descEditText.getText().toString();
        String finishedBy = finishedByEditText.getText().toString();
        if (noteName.isEmpty() || desc.isEmpty() || finishedBy.isEmpty()) {
            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
            return;
        }
        final Note note = new Note();
        note.setNote(noteName);
        note.setDesc(desc);
        note.setFinishedBy(finishedBy);
        noteViewModel.insertNote(note);
//        Single.fromCallable((Callable<Object>) () -> {
//            dao.insertNote(note);
//            return true;
//        }).
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(value -> Log.e("success", "success" ),
//                        error -> Log.e("error", error.getMessage()));
//    }

    }

    public void save(View view) {
        insertNote();
    }
}