package com.example.common;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Dish {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty restaurantId;

    public Dish() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.restaurantId = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getRestaurantId() {
        return restaurantId.get();
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId.set(restaurantId);
    }
}
