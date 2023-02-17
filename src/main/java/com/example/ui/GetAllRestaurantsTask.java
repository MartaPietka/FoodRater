package com.example.ui;

import com.example.common.Restaurant;
import com.example.db.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class GetAllRestaurantsTask extends Task {
    @Override
    public ObservableList<Restaurant> call() {
        return FXCollections.observableArrayList(DataSource.getInstance().queryRestaurants(DataSource.ORDER_BY_ASC));
    }
}
