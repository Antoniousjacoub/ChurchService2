
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublishedBy {

    @SerializedName("sys")
    @Expose
    private Sys_____ sys;

    public Sys_____ getSys() {
        return sys;
    }

    public void setSys(Sys_____ sys) {
        this.sys = sys;
    }

}
