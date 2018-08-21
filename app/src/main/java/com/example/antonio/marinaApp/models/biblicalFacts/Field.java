
package com.example.antonio.marinaApp.models.biblicalFacts;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Field {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("localized")
    @Expose
    private Boolean localized;
    @SerializedName("required")
    @Expose
    private Boolean required;
    @SerializedName("validations")
    @Expose
    private List<Object> validations = null;
    @SerializedName("disabled")
    @Expose
    private Boolean disabled;
    @SerializedName("omitted")
    @Expose
    private Boolean omitted;
    @SerializedName("linkType")
    @Expose
    private String linkType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getLocalized() {
        return localized;
    }

    public void setLocalized(Boolean localized) {
        this.localized = localized;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public List<Object> getValidations() {
        return validations;
    }

    public void setValidations(List<Object> validations) {
        this.validations = validations;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getOmitted() {
        return omitted;
    }

    public void setOmitted(Boolean omitted) {
        this.omitted = omitted;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

}
