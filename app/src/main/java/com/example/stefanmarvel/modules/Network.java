package com.example.stefanmarvel.modules;

import com.example.stefanmarvel.network.MarvelApi;
import com.example.stefanmarvel.network.MarvelInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stefanmay on 06/07/2017.
 */

@Module
public class Network {

    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/";

    private static MarvelApi marvelApi;
    private static Retrofit retrofit;
    private static OkHttpClient client;

    @Provides
    static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getMobileClient())
                    .build();
        }
        return retrofit;
    }

    @Provides
    static OkHttpClient getMobileClient() {
        if (client == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient.Builder()
                    .addInterceptor(new MarvelInterceptor())
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        }
        return client;
    }

    @Provides
    public static MarvelApi getMarvelApi() {
        if(marvelApi == null) {
            marvelApi = getRetrofit().create(MarvelApi.class);
        }
        return marvelApi;
    }
}
