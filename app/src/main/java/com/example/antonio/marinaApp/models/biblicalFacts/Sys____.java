
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys____ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("linkType")
    @Expose
    private String linkType;
    @SerializedName("id")
    @Expose
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
