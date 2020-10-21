package com.memaro.courseroomrx.usecases;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.memaro.courseroomrx.entities.Note;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NoteUsecase {

    NoteRepository repository;
    MutableLiveData<Boolean> isInserted;
    MutableLiveData<List<Note>> listMutableLiveData;

    public NoteUsecase(MutableLiveData<Boolean> isInserted,
                       MutableLiveData<List<Note>> listMutableLiveData) {

        this.repository = new NoteRepository();
        this.isInserted = isInserted;
        this.listMutableLiveData = listMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void insertNote(final Note note) {
        Single.fromCallable((Callable<Object>) () -> {
            repository.insertNote(note);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(value -> {
                    Log.e("success", "success");
                    isInserted.postValue(true);
                }, error -> {
                    Log.e("error", error.getMessage());
                    isInserted.postValue(false);
                });
    }

    @SuppressLint("CheckResult")
    public void getNotes() {
        Single.fromCallable(() -> repository.getNotes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(value -> listMutableLiveData.postValue(value),
                        error -> Log.e("error", error.getMessage()));
    }

}
