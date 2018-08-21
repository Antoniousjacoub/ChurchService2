
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Space {

    @SerializedName("sys")
    @Expose
    private Sys__ sys;

    public Sys__ getSys() {
        return sys;
    }

    public void setSys(Sys__ sys) {
        this.sys = sys;
    }

}
