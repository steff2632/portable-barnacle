package com.example.stefanmarvel.network;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by stefanmay on 06/07/2017.
 */

public class MarvelInterceptor implements Interceptor {

    private final String privateKey = "5de1fabcda2ea08912bd8b09bca4321f50563655";
    private final String publicKey = "54306733de0f5cd1418aa05a85fa062a";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl original = request.url();

        String timeStamp = Long.toString(System.currentTimeMillis());

        try {

            Request.Builder apiRequestBuilder = request.newBuilder();

            HttpUrl.Builder builder = new HttpUrl.Builder();
            builder.host(original.host());
            builder.encodedPath(original.encodedPath());
            builder.scheme(original.scheme());

            builder.addQueryParameter("ts", timeStamp);
            builder.addQueryParameter("apikey", publicKey);
            builder.addQueryParameter("hash", getApiKey(timeStamp));

            apiRequestBuilder.url(builder.build());

            return chain.proceed(apiRequestBuilder.build());

        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }

    private String getApiKey(String timeStamp) throws NoSuchAlgorithmException {
        return hashString(timeStamp + privateKey + publicKey);
    }


    private String hashString(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
