package com.memaro.courseroomrx.usecases;

import com.memaro.courseroomrx.database.NoteDao;
import com.memaro.courseroomrx.database.NoteDatabase;
import com.memaro.courseroomrx.entities.Note;
import com.memaro.courseroomrx.presentation.NoteApplication;

import java.util.List;

public class NoteRepository {

    private NoteDatabase database;
    private NoteDao dao;

    public NoteRepository() {
        database = NoteDatabase
                .getDatabase(NoteApplication.getAppContext());
        dao = database.noteDao();
    }

    public void insertNote(Note note) {
        dao.insertNote(note);
    }

    public List<Note> getNotes() {
        return dao.getNotes();
    }

}
