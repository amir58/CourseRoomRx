package com.memaro.courseroomrx.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.memaro.courseroomrx.entities.Note;
import com.memaro.courseroomrx.usecases.NoteUsecase;

import java.util.List;

public class NoteViewModel extends ViewModel {

    public MutableLiveData<Boolean> isInserted;
    public MutableLiveData<List<Note>> listMutableLiveData;
    NoteUsecase noteUsecase;

    public NoteViewModel() {
        isInserted = new MutableLiveData<>();
        listMutableLiveData = new MutableLiveData<>();
        noteUsecase = new NoteUsecase(isInserted, listMutableLiveData);
    }

    public void insertNote(Note note) {
        noteUsecase.insertNote(note);
    }

    public void getNotes() {
        noteUsecase.getNotes();
    }
}
