package com.example.stefanmarvel.modules;

import com.example.stefanmarvel.network.MarvelApi;
import com.example.stefanmarvel.MarvelApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stefanmay on 06/07/2017.
 */
@Module
public class MarvelModule {
    private MarvelApp application;

    public MarvelModule(MarvelApp application) {
        this.application = application;
    }


    /**
     * Expose the application to the graph.
     */
    @Provides
    @Singleton
    MarvelApp providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    MarvelApi providesApi() {return Network.getMarvelApi();}
}
