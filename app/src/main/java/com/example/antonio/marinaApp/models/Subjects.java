package com.example.antonio.marinaApp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by antonio on 7/27/18.
 */

public class Subjects {

     class CreatedBy {

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


    class Environment {

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


     class Field {

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

    }

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


    public class Space {

        @SerializedName("sys")
        @Expose
        private Sys_ sys;

        public Sys_ getSys() {
            return sys;
        }

        public void setSys(Sys_ sys) {
            this.sys = sys;
        }

    }

    public class SubjectModel {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("displayField")
        @Expose
        private String displayField;
        @SerializedName("fields")
        @Expose
        private List<Field> fields = null;
        @SerializedName("sys")
        @Expose
        private Sys sys;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisplayField() {
            return displayField;
        }

        public void setDisplayField(String displayField) {
            this.displayField = displayField;
        }

        public List<Field> getFields() {
            return fields;
        }

        public void setFields(List<Field> fields) {
            this.fields = fields;
        }

        public Sys getSys() {
            return sys;
        }

        public void setSys(Sys sys) {
            this.sys = sys;
        }

    }

    public class Sys {

        @SerializedName("space")
        @Expose
        private Space space;
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
        private Environment environment;
        @SerializedName("createdBy")
        @Expose
        private CreatedBy createdBy;
        @SerializedName("updatedBy")
        @Expose
        private UpdatedBy updatedBy;
        @SerializedName("publishedCounter")
        @Expose
        private Integer publishedCounter;
        @SerializedName("version")
        @Expose
        private Integer version;
        @SerializedName("publishedBy")
        @Expose
        private PublishedBy publishedBy;
        @SerializedName("publishedVersion")
        @Expose
        private Integer publishedVersion;
        @SerializedName("firstPublishedAt")
        @Expose
        private String firstPublishedAt;
        @SerializedName("publishedAt")
        @Expose
        private String publishedAt;

        public Space getSpace() {
            return space;
        }

        public void setSpace(Space space) {
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

        public Environment getEnvironment() {
            return environment;
        }

        public void setEnvironment(Environment environment) {
            this.environment = environment;
        }

        public CreatedBy getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(CreatedBy createdBy) {
            this.createdBy = createdBy;
        }

        public UpdatedBy getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(UpdatedBy updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Integer getPublishedCounter() {
            return publishedCounter;
        }

        public void setPublishedCounter(Integer publishedCounter) {
            this.publishedCounter = publishedCounter;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public PublishedBy getPublishedBy() {
            return publishedBy;
        }

        public void setPublishedBy(PublishedBy publishedBy) {
            this.publishedBy = publishedBy;
        }

        public Integer getPublishedVersion() {
            return publishedVersion;
        }

        public void setPublishedVersion(Integer publishedVersion) {
            this.publishedVersion = publishedVersion;
        }

        public String getFirstPublishedAt() {
            return firstPublishedAt;
        }

        public void setFirstPublishedAt(String firstPublishedAt) {
            this.firstPublishedAt = firstPublishedAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

    }

    public class Sys_ {

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

    public class Sys__ {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("linkType")
        @Expose
        private String linkType;

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

        public String getLinkType() {
            return linkType;
        }

        public void setLinkType(String linkType) {
            this.linkType = linkType;
        }

    }

    public class Sys___ {

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

    public class Sys_____ {

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

}
