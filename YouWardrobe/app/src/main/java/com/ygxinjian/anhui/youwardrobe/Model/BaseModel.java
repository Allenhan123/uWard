package com.ygxinjian.anhui.youwardrobe.Model;

import com.google.gson.Gson;
import com.litesuits.orm.db.annotation.Ignore;

import java.io.Serializable;

/**
 * Created by handongqiang on 17/1/6.
 */

public class BaseModel implements Serializable {
    @Ignore
    private static Gson gson = new Gson();

    public BaseModel() {
    }

    public String toString() {
        return getGson().toJson(this);
    }

    public static Gson getGson() {
        return gson;
    }
}
