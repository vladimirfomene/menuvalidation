package com.shopify.menuvalidation;

import com.shopify.menuvalidation.BackendService.BackendService;
import com.shopify.menuvalidation.BackendService.domain.MenuPage;

import java.io.IOException;

public class MenuValidation {

    public static void main(String[] args){
        BackendService backendService = new BackendService();
        MenuPage page = null;
        try {
            page = backendService.getMenuPage(1, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(page.toString());
    }
}
