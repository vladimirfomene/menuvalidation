package com.shopify.menuvalidation.BackendService.api;

import com.shopify.menuvalidation.BackendService.domain.MenuPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MenuInterface {

    @GET("challenges.json")
    Call<MenuPage> getMenuPage(@Query("id") Integer id, @Query("page") Integer page);
}
