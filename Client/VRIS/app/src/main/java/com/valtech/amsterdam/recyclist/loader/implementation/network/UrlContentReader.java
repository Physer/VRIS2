package com.valtech.amsterdam.recyclist.loader.implementation.network;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by jasper.van.zijp on 18-4-2017.
 */

public interface UrlContentReader {
    String readContent(HttpURLConnection urlConnection) throws IOException;
}