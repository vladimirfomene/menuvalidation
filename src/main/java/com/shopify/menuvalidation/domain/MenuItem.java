/**
 * This class represents of the items
 * in our menu with their attributes.
 * @author vladimirfomene
 */

package com.shopify.menuvalidation.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MenuItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("child_ids")
    @Expose
    private List<Integer> childIds = null;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Integer> getChildIds() {
        return childIds;
    }

    public void setChildIds(List<Integer> childIds) {
        this.childIds = childIds;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", childIds=" + childIds +
                ", parentId=" + parentId +
                '}';
    }
}
