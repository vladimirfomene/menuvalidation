package com.shopify.menuvalidation.BackendService.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuPage {
    @SerializedName("menus")
    @Expose
    private List<MenuItem> menus = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<MenuItem> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuItem> menus) {
        this.menus = menus;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "MenuPage{" +
                "menus=" + menus.toString() +
                ", pagination=" + pagination.toString() +
                '}';
    }
}
