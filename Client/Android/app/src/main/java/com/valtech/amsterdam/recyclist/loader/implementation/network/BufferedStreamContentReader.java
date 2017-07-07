package com.jaspervz.www.recyclist.loader.implementation.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.inject.Inject;

/**
 * Reads String content from a HttpURLConnection by Buffering reading
 */

public class BufferedStreamContentReader implements UrlContentReader {
    @Inject
    public BufferedStreamContentReader() {}

    @Override
    public String readContent(HttpURLConnection urlConnection) throws IOException {
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        } catch (Exception e) {
            throw new IOException(e);
        }

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
