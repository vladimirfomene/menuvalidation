package com.shopify.menuvalidation.BackendService;

import com.shopify.menuvalidation.BackendService.api.MenuInterface;
import com.shopify.menuvalidation.BackendService.domain.MenuPage;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class BackendService {

    private MenuInterface menuInterface = null;

    public BackendService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend-challenge-summer-2018.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        menuInterface = retrofit.create(MenuInterface.class);

    }


    public MenuPage getMenuPage(int id, int page) throws IOException{
        MenuPage menuPage = null;

        Call<MenuPage> call = menuInterface.getMenuPage(id, page);
        Response<MenuPage> menuPageResponse = call.execute();
        boolean isSuccessful = menuPageResponse.isSuccessful();

        int code = menuPageResponse.code();
        if (isSuccessful) {
            menuPage = menuPageResponse.body();
            if (menuPage != null) {
                System.out.println("- getMenuPage({},{}) :Response [isSuccessful: {" + isSuccessful + "}, code: {"+ code + "}]");
            }
        } else {
            ResponseBody errorResponse = menuPageResponse.errorBody();
            if (errorResponse != null) {
                System.out.println("- getMenuPage({},{},{}) :Response [isSuccessful: {" + isSuccessful + "}, code: {" + code + "}, error: {" + errorResponse.string() + "}]");
            }
        }
        return menuPage;

    }
}
