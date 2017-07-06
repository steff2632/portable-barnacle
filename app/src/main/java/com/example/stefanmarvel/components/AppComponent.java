package com.example.stefanmarvel.components;

import com.example.stefanmarvel.activities.MainActivity;
import com.example.stefanmarvel.MarvelApp;
import com.example.stefanmarvel.modules.MarvelModule;
import com.example.stefanmarvel.presenters.ComicsListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by stefanmay on 06/07/2017.
 */

@Singleton
@Component(modules = MarvelModule.class)
public interface AppComponent {

    void inject(MarvelApp app);

    void inject(MainActivity activity);

    void inject(ComicsListPresenter comicsListPresenter);
}
