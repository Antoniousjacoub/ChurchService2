
package com.example.antonio.marinaApp.models.biblicalFacts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields_ {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("file")
    @Expose
    private File file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
