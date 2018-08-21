
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Asset {

    @SerializedName("sys")
    @Expose
    private Sys_____ sys;
    @SerializedName("fields")
    @Expose
    private Fields_ fields;

    public Sys_____ getSys() {
        return sys;
    }

    public void setSys(Sys_____ sys) {
        this.sys = sys;
    }

    public Fields_ getFields() {
        return fields;
    }

    public void setFields(Fields_ fields) {
        this.fields = fields;
    }

}
