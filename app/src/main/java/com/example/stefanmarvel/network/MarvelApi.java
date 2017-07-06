package com.example.stefanmarvel.network;

import com.example.stefanmarvel.models.Comics;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by stefanmay on 06/07/2017.
 */

public interface MarvelApi {

    @GET("comics")
    Single<Comics> getComics();

    @GET("comics/{comicId}")
    Observable getComics(@Path("comicId") int comicId);
}
