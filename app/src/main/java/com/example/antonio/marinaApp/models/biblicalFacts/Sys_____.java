
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys_____ {

    @SerializedName("space")
    @Expose
    private Space_ space;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("environment")
    @Expose
    private Environment_ environment;
    @SerializedName("revision")
    @Expose
    private Integer revision;
    @SerializedName("locale")
    @Expose
    private String locale;

    public Space_ getSpace() {
        return space;
    }

    public void setSpace(Space_ space) {
        this.space = space;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Environment_ getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment_ environment) {
        this.environment = environment;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
