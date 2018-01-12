/**
 * This class represent the list of
 * menus sorted into a valid set and
 * a invalid set.
 * @author VladimirFomene
 */

package com.shopify.menuvalidation.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ValidInvalidMenuList {

    @SerializedName("valid_menus")
    @Expose
    private List<Menu> validMenus = new ArrayList<>();
    @SerializedName("invalid_menus")
    @Expose
    private List<Menu> invalidMenus = new ArrayList<>();

    public List<Menu> getValidMenus() {
        return validMenus;
    }

    public void setValidMenus(List<Menu> validMenus) {
        this.validMenus = validMenus;
    }

    public List<Menu> getInvalidMenus() {
        return invalidMenus;
    }

    public void setInvalidMenus(List<Menu> invalidMenus) {
        this.invalidMenus = invalidMenus;
    }
}
