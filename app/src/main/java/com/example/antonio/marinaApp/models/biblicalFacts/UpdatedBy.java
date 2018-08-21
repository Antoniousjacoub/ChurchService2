
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatedBy {

    @SerializedName("sys")
    @Expose
    private Sys____ sys;

    public Sys____ getSys() {
        return sys;
    }

    public void setSys(Sys____ sys) {
        this.sys = sys;
    }

}
