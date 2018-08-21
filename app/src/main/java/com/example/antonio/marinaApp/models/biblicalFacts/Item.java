
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("sys")
    @Expose
    private Sys_ sys;
    @SerializedName("fields")
    @Expose
    private Fields fields;

    public Sys_ getSys() {
        return sys;
    }

    public void setSys(Sys_ sys) {
        this.sys = sys;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

}
