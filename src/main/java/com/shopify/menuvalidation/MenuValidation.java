package com.shopify.menuvalidation;

import com.google.gson.Gson;
import com.shopify.menuvalidation.BackendService.BackendService;
import com.shopify.menuvalidation.domain.*;
import java.io.IOException;
import java.util.*;

public class MenuValidation{

    private static final int MAX_DEPTH = 4;

    public static void main(String[] args){

        //Create json -> pojo converter object
        Gson gson = new Gson();

        //Regular Challenge
        List<MenuItem> menuItems = getAllMenuItems(1);
        ValidInvalidMenuList validatedMenuList = extractMenusFromList(menuItems);
        System.out.println(gson.toJson(validatedMenuList));


        //Extra Challenge
        List<MenuItem> extraMenuItems = getAllMenuItems(2);
        ValidInvalidMenuList extraValidatedMenuList = extractMenusFromList(extraMenuItems);
        System.out.println(gson.toJson(extraValidatedMenuList));

    }

    public static List<MenuItem> getAllMenuItems(int id){
        //Initialize the api service
        BackendService backendService = new BackendService();

        //Create a container for menu items
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        //Initialize page counter
        int pageCounter = 1;

        MenuPage page = null;

        do{

            try {
                page = backendService.getMenuPage(id, pageCounter);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Add new items to our list of menu items
            menuItems.addAll(page.getMenus());

           //increment page counter
           pageCounter++;
        }while(page.getMenus().size() != 0);

        return menuItems;
    }

    /**
     * This methods iterates through the list of menu items to extract and
     * return the list of valid and invalid menus.
     * @param menuList
     * @return
     */
    public static ValidInvalidMenuList extractMenusFromList(List<MenuItem> menuList){

        //Initialize object to store all valid and invalids menus.
        ValidInvalidMenuList validatedMenuList = new ValidInvalidMenuList();

        //Create a hash map to track all the menu items visited.
        HashMap<Integer, Boolean> visitedMenuItems = new HashMap<>();

        //Iterate over all menuItems
        for(int i = 0; i < menuList.size(); i++){

            //Skip over menuItems which I have already processed.
             if(visitedMenuItems.containsKey(menuList.get(i).getId())){
                 continue;
             }

             //Create a new menu object and the menu root item
             Menu currentMenu = new Menu();
             currentMenu.setRootId(menuList.get(i).getId());

             //traverse the tree hierarchy from the menu root item
             extractMenus(visitedMenuItems, menuList.get(i), validatedMenuList, menuList, currentMenu);

        }
        return validatedMenuList;
    }

    /**
     *
     * @param visitedMenuItems
     * @param rootItem
     * @param validatedMenuList
     * @param menuItems
     * @param currentMenu
     */
    public static void extractMenus(HashMap<Integer, Boolean> visitedMenuItems, MenuItem rootItem,
                                    ValidInvalidMenuList validatedMenuList, List<MenuItem> menuItems, Menu currentMenu){

        //Mark this item as seen
        visitedMenuItems.put(rootItem.getId(), true);

        /* If were are at a leaf, add the current node to the children list then
        test for the depth of the menu, if greater than 4, add menu to invalid menus
        otherwise add menu to valid menus
         */
        if(rootItem.getChildIds().isEmpty()){

            //Add node to the children list
            currentMenu.getChildren().add(rootItem.getId());

            //if Menu depth is greater than the max depth, make menu invalid
            if(currentMenu.getChildren().size() > MAX_DEPTH){

                //add menu to invalid menus
                validatedMenuList.getValidMenus().add(createMenuCopy(currentMenu));
            }else {

                //Add menu to valid menus
                validatedMenuList.getValidMenus().add(createMenuCopy(currentMenu));
            }

            return;
        }

        //If you have found a cycle, add the menu to invalid menus
        if(rootItem.getId().equals(currentMenu.getRootId()) && !currentMenu.getChildren().isEmpty()){

            //add an invalid menu to a menu list
            validatedMenuList.getInvalidMenus().add(createMenuCopy(currentMenu));

            return;
        }

        //Add the current menu item as a child
        if(!rootItem.getId().equals(currentMenu.getRootId()))
            currentMenu.getChildren().add(rootItem.getId());

        //recursive visit all the children of the current node and then recursive visit the childrens of these childrens
        for(int i = 0; i < rootItem.getChildIds().size(); i++){

            extractMenus(visitedMenuItems, menuItems.get(rootItem.getChildIds().get(i) - 1), validatedMenuList, menuItems, currentMenu);

            //Pop a child from a traversal node
            if(!currentMenu.getChildren().isEmpty())
                currentMenu.getChildren().remove(currentMenu.getChildren().size() - 1);
        }
    }


    /**
     * This methods prints all the menuItems which where collected
     * over the network from the backend endpoint.
     * @param menuItems
     */
    public static void printMenuItems(List<MenuItem> menuItems){

        for(int i = 0; i < menuItems.size(); i++){
            System.out.println(menuItems.get(i).toString());
        }
    }

    /**
     * This method creates and returns a copy of the current menu
     * @param menu
     * @return
     */
    public static Menu createMenuCopy(Menu menu){
        Menu copy = new Menu();
        copy.setRootId(menu.getRootId());
        copy.setChildren(new ArrayList<>(menu.getChildren()));
        return copy;
    }
}
