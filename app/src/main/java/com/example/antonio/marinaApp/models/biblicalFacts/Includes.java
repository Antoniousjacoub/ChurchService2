
package com.example.antonio.marinaApp.models.biblicalFacts;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Includes {

    @SerializedName("Asset")
    @Expose
    private List<Asset> asset = null;

    public List<Asset> getAsset() {
        return asset;
    }

    public void setAsset(List<Asset> asset) {
        this.asset = asset;
    }

}
