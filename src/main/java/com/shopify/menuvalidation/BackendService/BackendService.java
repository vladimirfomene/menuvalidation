/**
 * This class implements an http client to communicate
 * with the backend service using the retrofit library.
 * It uses Google's GSON library to serialize its objects
 * to JSON.
 * @author Vladimirfomene
 */


package com.shopify.menuvalidation.BackendService;

import com.shopify.menuvalidation.BackendService.api.MenuInterface;
import com.shopify.menuvalidation.domain.MenuPage;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class BackendService {

    private MenuInterface menuInterface = null;

    /**
     * Constructor initializes the retrofit
     * client using Google's GSON converter
     */
    public BackendService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend-challenge-summer-2018.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        menuInterface = retrofit.create(MenuInterface.class);

    }


    /**
     * This method takes in as parameter an id and a page
     * and then queries the backend using our http client to
     * get a particular menu page.
     * @param id url id
     * @param page corresponding to the number of page in our list of menus
     * @return a menu page
     * @throws IOException
     */
    public MenuPage getMenuPage(int id, int page) throws IOException{
        MenuPage menuPage = null;


        //Generate a getMenuPage method and execute it to get the menu page.
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
