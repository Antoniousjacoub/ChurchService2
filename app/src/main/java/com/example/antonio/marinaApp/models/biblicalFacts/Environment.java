
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Environment {

    @SerializedName("sys")
    @Expose
    private Sys___ sys;

    public Sys___ getSys() {
        return sys;
    }

    public void setSys(Sys___ sys) {
        this.sys = sys;
    }

}
