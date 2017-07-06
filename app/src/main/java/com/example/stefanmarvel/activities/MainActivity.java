package com.example.stefanmarvel.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.stefanmarvel.MarvelApp;
import com.example.stefanmarvel.R;
import com.example.stefanmarvel.components.AppComponent;
import com.example.stefanmarvel.components.DaggerAppComponent;
import com.example.stefanmarvel.fragments.ComicListFragment;
import com.example.stefanmarvel.modules.MarvelModule;
import com.example.stefanmarvel.network.MarvelApi;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MarvelApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppComponent appComponent = DaggerAppComponent.builder()
                .marvelModule(new MarvelModule((MarvelApp) getApplication()))
                .build();
        appComponent.inject(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container,
                ComicListFragment.getInstance(new MarvelModule((MarvelApp) getApplication())),
                ComicListFragment.class.getSimpleName())
                .commit();
    }
}
