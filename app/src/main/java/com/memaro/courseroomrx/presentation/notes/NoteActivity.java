package com.memaro.courseroomrx.presentation.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.memaro.courseroomrx.usecases.database.NoteDao;
import com.memaro.courseroomrx.usecases.database.NoteDatabase;
import com.memaro.courseroomrx.R;
import com.memaro.courseroomrx.entities.Note;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NoteActivity extends AppCompatActivity {
    private NoteDatabase database;
    private NoteDao dao;
    private TextView resultTextView;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private static final String NAME = "name";
    private static final String PASSWARD = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        database = NoteDatabase.getDatabase(this);
        dao = database.noteDao();
        getNotes();

        //        SharedPreferences sharedPreferences=getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("name", "Elena");
//        editor.putInt("idName", 12);
//        editor.apply();


    }

    @SuppressLint("CheckResult")
    private void getNotes() {
        Single.fromCallable(new Callable<List<Note>>() {
            @Override
            public List<Note> call() throws Exception {
                return dao.getNotes();
            }
        })
                .subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<Note>>() {
                              @Override
                              public void accept(List<Note> value) throws Exception {
                                  resultTextView.setText(value.get(0).getDesc() + value.get(0).getNote());
                              }
                          },
                        error -> Log.e("error", error.getMessage()));
    }

}