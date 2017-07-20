package com.valtech.amsterdam.vris.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by jasper.van.zijp on 20-7-2017.
 */

public class AuthenticatorService extends Service {
    private Authenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
