package com.valtech.amsterdam.recyclist;

import android.os.AsyncTask;
import android.util.Log;

/**
 * A class for Asynchronously executing Command objects
 */

public class AsyncCommandExecutor<TResult> extends AsyncTask<Command<TResult>, Void, TResult> {
    private TaskListener<TResult> mListener;
    private Exception mException;

    public AsyncCommandExecutor(TaskListener<TResult> mListener) {
        this.mListener = mListener;
    }

    @Override
    protected TResult doInBackground(Command<TResult>... params) {
        try {
            return params[0].execute();
        }
        catch(Exception e) {
            mException = e;
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(TResult result) {
        super.onPostExecute(result);
        if (mException == null) {
            mListener.onComplete(result);
        } else {
            mListener.onError(mException);
        }
    }
}
