package com.example.douglassmith.smityfinal;

public class Restaurant {
    private String restaurant;
    private String restaurantURL;
    private void setRestaurantInfo(String pizzaCrust, Boolean gluten){
        Integer choice= 0;
        if(pizzaCrust == "thin"){
            choice = 2;
        }else {
            choice= 1;
        }
        if(gluten){
            choice= 0;
        }
        switch (choice){
            case 0:
                restaurant="Boss Lady Pizza";
                restaurantURL="https://bossladypizza.com/";
                break;
            case 1:
                restaurant="Backcountry Pizza";
                restaurantURL="https://backcountrypizzaandtaphouse.info/";
                break;
            case 2:
                restaurant="Pizzeria Locale";
                restaurantURL="https://localeboulder.com/";
                break;

            default:
                restaurant = "none";
                restaurantURL = "http://www.google.com";
        }
    }

    public void setRestaurant(String pizzaCrust,Boolean gluten){
        setRestaurantInfo(pizzaCrust, gluten);

    }
    public void setRestaurantURL(String pizzaCrust, Boolean gluten){
        setRestaurantInfo(pizzaCrust, gluten);

    }

    public String getRestaurant(){
        return restaurant;
    }


    public String getRestaurantURL(){
        return restaurantURL;
    }
}
