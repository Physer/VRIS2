package com.valtech.amsterdam.vris.model;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.recyclist.annotation.ApiInfo;
import com.valtech.amsterdam.recyclist.modifiers.IHasId;

/**
 * A room registered in the Office namespace
 * Todo: unused
 */
@ApiInfo(methodName = "room")
public class Room implements IHasId {
    @SerializedName("Id") private int mId;
    @SerializedName("Name")private String mName;

    /**
     * Initiate a new room
     * @param id
     * @param name
     */
    public Room(int id, String name) {
        mId = id;
        mName = name;
    }

    /**
     * Get the id of the room
     * @return
     */
    public int getId() {
        return mId;
    }

    /**
     * Get the rooms name
     * @return
     */
    public String getName() { return mName; }
}
