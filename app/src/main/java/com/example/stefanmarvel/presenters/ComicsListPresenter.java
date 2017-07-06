package com.example.stefanmarvel.presenters;

import com.example.stefanmarvel.components.AppComponent;
import com.example.stefanmarvel.components.DaggerAppComponent;
import com.example.stefanmarvel.models.ComicDataWrapper;
import com.example.stefanmarvel.modules.MarvelModule;
import com.example.stefanmarvel.network.MarvelApi;
import com.example.stefanmarvel.views.ComicsListView;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by stefanmay on 06/07/2017.
 */

public class ComicsListPresenter {

    @Inject
    MarvelApi api;

    private ComicsListView view;

    public ComicsListPresenter(ComicsListView view, MarvelModule module) {
        AppComponent appComponent = DaggerAppComponent.builder()
                .marvelModule(module)
                .build();

        appComponent.inject(this);

        this.view = view;
    }

    public void getComicsList() {
        api.getComics()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<ComicDataWrapper>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ComicDataWrapper cdw) {
                        if(cdw.data != null && cdw.data.results != null) {
                            view.setComics(cdw.data.results);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
