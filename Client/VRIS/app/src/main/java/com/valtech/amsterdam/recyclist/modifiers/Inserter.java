package com.valtech.amsterdam.recyclist.modifiers;

import java.util.List;

/**
 * Created by jasper.van.zijp on 25-7-2017.
 */

public interface Inserter<TModel> {
    boolean insert(List<TModel> list, TModel objectToInsert, int position);
}
