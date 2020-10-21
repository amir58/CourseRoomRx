package com.memaro.courseroomrx.presentation.core;

import android.app.Application;
import android.content.Context;

public class NoteApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
