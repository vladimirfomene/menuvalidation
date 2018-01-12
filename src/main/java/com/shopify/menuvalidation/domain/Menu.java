/**
 * This class represents both the valid menus
 * the invalids menus and is mostly used by
 * the JSON to JAVA object serializer and deserializer.
 * @author vladimirfomene
 */

package com.shopify.menuvalidation.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Menu{

    @SerializedName("root_id")
    @Expose
    private Integer rootId;
    @SerializedName("children")
    @Expose
    private List<Integer> children = new ArrayList<>();

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public List<Integer> getChildren() {
        return children;
    }

    public void setChildren(List<Integer> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "rootId=" + rootId +
                ", children=" + children +
                '}';
    }
}
