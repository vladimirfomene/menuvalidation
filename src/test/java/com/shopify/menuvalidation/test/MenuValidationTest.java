package com.shopify.menuvalidation.test;

import com.google.gson.Gson;
import com.shopify.menuvalidation.BackendService.BackendService;
import com.shopify.menuvalidation.domain.MenuPage;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class MenuValidationTest {

    @Test
    public void getMenuPageTest(){
        //Initialize the api service
        BackendService backendService = new BackendService();

        //Initialize GSON json to java serializer
        Gson gson = new Gson();



        //First page of menus
        String firstMenuPage = "{\"menus\":[{\"id\":1,\"data\":\"House\",\"child_ids\":[3]},{\"id\":2,\"data\":\"Company\",\"child_ids\":[4,5,8]},{\"id\":3,\"data\":\"Living Room\",\"parent_id\":1,\"child_ids\":[7]},{\"id\":4,\"data\":\"Meeting Rooms\",\"parent_id\":2,\"child_ids\":[]},{\"id\":5,\"data\":\"Kitchen\",\"parent_id\":2,\"child_ids\":[6]}],\"pagination\":{\"current_page\":1,\"per_page\":5,\"total\":15}}";

        MenuPage page = null;

        try {
            page = backendService.getMenuPage(1, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String actualResult = gson.toJson(page);
        MenuPage firstPage = gson.fromJson(firstMenuPage, MenuPage.class);
        //System.out.println(page.toString());
        System.out.println(actualResult);

        //assertEquals(firstPage, , false);

    }

    @Test
    public void extractMenusFromListTest(){
        //
    }
}
