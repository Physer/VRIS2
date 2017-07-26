package com.valtech.amsterdam.recyclist.modifiers;

import java.util.List;

/**
 * Created by jasper.van.zijp on 25-7-2017.
 */

public interface PositionDeterminator<TModel> {
    int getPosition(List<TModel> list, TModel objectToInsert);
}