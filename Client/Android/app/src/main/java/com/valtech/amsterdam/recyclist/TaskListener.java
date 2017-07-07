package com.jaspervz.www.recyclist;

/**
 * Describes TaskListener interface
 */

public interface TaskListener<TResult> {
    void onComplete(TResult result);
    void onError(Exception exception);
}
