package com.example.db;


import com.example.common.Dish;
import com.example.common.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.db.Constants.*;

public class DataSource {
    public static final String DB_NAME = "RestaurantDataBase.db";
    public static
    final String CONNECTION_STRING = ("jdbc:sqlite:/Users/martapietka/Desktop/Projekty/IdeaProjects/RestaurantDataBase.db");
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    private PreparedStatement queryDishesByRestaurantId;
    private PreparedStatement updateRestaurantName;

    private Connection connection;
    private static DataSource instance = new DataSource();

    private DataSource() {

    }

    public static DataSource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            queryDishesByRestaurantId = connection.prepareStatement(QUERY_DISHES_BY_RESTAURANT_ID);
            updateRestaurantName = connection.prepareStatement(UPDATE_ARTIST_NAME);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }
    public void close() {
        try {
            if (queryDishesByRestaurantId != null) {
                queryDishesByRestaurantId.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (updateRestaurantName != null) {
                updateRestaurantName.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }
    public List<Restaurant> queryRestaurants(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_RESTAURANTS);

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_RESTAURANTS_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Restaurant> restaurants = new ArrayList<>();
            while (results.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(results.getInt(INDEX_RESTAURANT_ID));
                restaurant.setName(results.getString(INDEX_RESTAURANT_NAME));
                restaurants.add(restaurant);
            }

            return restaurants;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Dish> queryDishesForRestaurantId(int id) {
        try {
            queryDishesByRestaurantId.setInt(1, id);
            ResultSet results = queryDishesByRestaurantId.executeQuery();

            List<Dish> dishes = new ArrayList<>();
            while (results.next()) {
                Dish dish = new Dish();
                dish.setId(results.getInt(1));
                dish.setName(results.getString(2));
                dish.setRestaurantId(id);
                dishes.add(dish);
            }
            return dishes;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public boolean updateRestaurantName(int id, String newName) {
        try {
            updateRestaurantName.setString(1, newName);
            updateRestaurantName.setInt(2, id);
            int affectedRecords = updateRestaurantName.executeUpdate();

            return affectedRecords == 1;
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    public List<String> queryDishesForRestaurant(String restaurantName, int sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_DISHES_BY_RESTAURANT_START);
        sb.append(restaurantName);
        sb.append("\"");


        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_DISHES_BY_RESTAURANT_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        System.out.println("SQL statement = " + sb.toString());

        try (Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sb.toString())) {

            List<String> dishes = new ArrayList<>();
            while (results.next()) {
                dishes.add(results.getString(1));
            }

            return dishes;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
