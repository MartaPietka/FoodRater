package com.example.db;

public class Constants {
    public static final String TABLE_RESTAURANTS = "Restaurants";
    public static final String COLUMN_RESTAURANT_ID = "_id";
    public static final String COLUMN_RESTAURANT_NAME = "name";
    public static final int INDEX_RESTAURANT_ID = 1;
    public static final int INDEX_RESTAURANT_NAME = 2;

    public static final String TABLE_DISHES = "Dishes";
    public static final String COLUMN_DISH_ID = "_id";
    public static final String COLUMN_DISH_NAME = "name";
    public static final String COLUMN_DISH_RESTAURANT = "restaurant";
    public static final int INDEX_DISH_ID = 1;
    public static final int INDEX_DISH_NAME = 2;
    public static final int INDEX_DISH_RESTAURANT = 3;

    public static final String QUERY_DISHES_BY_RESTAURANT_START =
                    "SELECT " + TABLE_DISHES + "." + COLUMN_DISH_NAME + " FROM " + TABLE_DISHES +
                    " INNER JOIN " + TABLE_RESTAURANTS + " ON " + TABLE_DISHES + "." + COLUMN_DISH_RESTAURANT +
                    " = " + TABLE_RESTAURANTS + "." + COLUMN_RESTAURANT_ID +
                    " WHERE " + TABLE_RESTAURANTS + "." + COLUMN_RESTAURANT_NAME + " = \"";

    public static final String QUERY_DISHES_BY_RESTAURANT_SORT =
                    " ORDER BY " + TABLE_DISHES + "." + COLUMN_DISH_NAME + " COLLATE NOCASE ";

    public static final String QUERY_RESTAURANTS_SORT =
                    " ORDER BY " + COLUMN_RESTAURANT_NAME + " COLLATE NOCASE ";

    public static final String QUERY_DISHES_BY_RESTAURANT_ID = "SELECT * FROM " + TABLE_DISHES +
            " WHERE " + COLUMN_DISH_RESTAURANT + " = ? ORDER BY " + COLUMN_DISH_NAME + " COLLATE NOCASE";

    public static final String UPDATE_ARTIST_NAME = "UPDATE " + TABLE_RESTAURANTS + " SET " +
            COLUMN_RESTAURANT_NAME + " = ? WHERE " + COLUMN_RESTAURANT_ID + " = ?";
}
