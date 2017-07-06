package com.example.stefanmarvel.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.stefanmarvel.MarvelApp;
import com.example.stefanmarvel.R;
import com.example.stefanmarvel.components.AppComponent;
import com.example.stefanmarvel.components.DaggerAppComponent;
import com.example.stefanmarvel.models.Comics;
import com.example.stefanmarvel.modules.MarvelModule;
import com.example.stefanmarvel.network.MarvelApi;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

        api.getComics().observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new SingleObserver<Comics>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull Comics comics) {
                            System.out.println(comics.title);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                        }
                    });
    }
}
