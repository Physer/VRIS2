package com.valtech.amsterdam.vris.model;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.recyclist.annotation.ApiInfo;
import com.valtech.amsterdam.recyclist.modifiers.IHasId;
import com.valtech.amsterdam.vris.R;

/**
 * A person returned by the API
 */
@ApiInfo(methodName = "person")
public final class Person implements IHasId {
    @SerializedName("Id") private int mId;
    @SerializedName("Name") private String mName;

    /**
     * Initiate a new person
     * @param id
     * @param name
     */
    public Person(int id, String name) {
        mId = id;
        mName = name;
    }

    /**
     * Get the Id of this person
     * @return
     */
    public int getId() {
        return mId;
    }

    /**
     * Get the name of this person
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * Get the email of this person
     * @return todo auto generated, needs to be implemented in constructor
     */
    public String getEmail() {
        return mName + "@email.later.implemented";
    }

    /**
     * Get the profile picture
     * @return todo auto generated, needs to be implemented in constructor
     */
    public DrawableCompat getProfilePicture() {
        return null;
    }
}
