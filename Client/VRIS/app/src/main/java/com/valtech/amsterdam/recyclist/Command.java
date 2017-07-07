package com.valtech.amsterdam.recyclist;

import java.io.IOException;

/**
 * A command which returns TResult object on execute()
 */

public interface Command<TResult> {
    TResult execute() throws IOException;
}
