package com.valtech.amsterdam.vris.model;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

public class Person {
    private int mId;
    private String mName;

    public Person(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
