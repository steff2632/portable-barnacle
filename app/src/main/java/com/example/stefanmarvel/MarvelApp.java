package com.example.stefanmarvel;

import android.app.Application;

import com.example.stefanmarvel.components.AppComponent;
import com.example.stefanmarvel.components.DaggerAppComponent;
import com.example.stefanmarvel.modules.MarvelModule;

/**
 * Created by stefanmay on 06/07/2017.
 */

public class MarvelApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.builder()
                .marvelModule(new MarvelModule(this))
                .build();
    }
}
