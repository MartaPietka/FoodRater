//package com.example.db;
//
//import com.example.common.Restaurant;
//
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        DataSource dataSource = new DataSource();
//        if (!dataSource.open()) {
//            System.out.println("Can't open datasource");
//            return;
//        }
//
//        List<Restaurant> restaurants = dataSource.queryRestaurants(DataSource.ORDER_BY_NONE);
//        if (restaurants == null) {
//            System.out.println("No restaurants!");
//            return;
//        }
//
//        for (Restaurant restaurant : restaurants) {
//            System.out.println("ID = " + restaurant.getId() + ", Name = " + restaurant.getName());
//        }
//
//        List<String> dishesForRestaurant = dataSource.queryDishesForRestaurant("GOKO Takeaway", DataSource.ORDER_BY_ASC);
//
//        for (String dish : dishesForRestaurant) {
//            System.out.println(dish);
//        }
//
//        dataSource.close();
//    }
//}
