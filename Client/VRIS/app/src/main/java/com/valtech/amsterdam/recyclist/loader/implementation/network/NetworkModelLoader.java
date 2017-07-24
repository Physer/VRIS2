package com.valtech.amsterdam.recyclist.loader.implementation.network;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.valtech.amsterdam.recyclist.annotation.ApiInfo;
import com.valtech.amsterdam.recyclist.loader.ModelLoader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A Model loader which loads models from a Network connection
 */

public class NetworkModelLoader<TModel> implements ModelLoader<TModel> {
    private UrlContentReader mUrlContentReader;
    private Desynchronizer<TModel> mDesynchronizer;
    private Class<TModel> mClassType; //Need to store the class variable because getting the class of a generic type is not possible in compile time
    private String mBaseUrl;

    public NetworkModelLoader(UrlContentReader urlContentReader, Desynchronizer<TModel> desynchronizer, Class<TModel> classType, String baseUrl) {
        this.mUrlContentReader = urlContentReader;
        this.mDesynchronizer = desynchronizer;
        mClassType = classType;
        mBaseUrl = baseUrl;
    }

    @Override
    public ObservableList<TModel> getList() throws IOException {
        ApiInfo annotation = mClassType.getAnnotation(ApiInfo.class);
        URL url = new URL(mBaseUrl + annotation.methodName());

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        String content = mUrlContentReader.readContent(urlConnection);
        return mDesynchronizer.getList(content);
    }
}
