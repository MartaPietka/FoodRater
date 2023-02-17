package com.example.ui;

import com.example.common.Dish;
import com.example.common.Restaurant;
import com.example.db.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private TableView restaurantsTable;

    @FXML
    public void listRestaurants() {
        Task<ObservableList<Restaurant>> task = new GetAllRestaurantsTask();
        restaurantsTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void listDishesForRestaurant() {
        final Restaurant restaurant = (Restaurant) restaurantsTable.getSelectionModel().getSelectedItem();
        if (restaurant == null) {
            System.out.println("No restaurant selected");
            return;
        }
        Task<ObservableList<Dish>> task = new Task<>() {
            @Override
            protected ObservableList<Dish> call() throws Exception {
                return FXCollections.observableArrayList(DataSource.getInstance().queryDishesForRestaurantId(restaurant.getId()));
            }
        };
        restaurantsTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void updateRestaurant() {
        // Jak będę robić dialog, żeby móc wpisać updatewaną nazwę, to wtedy ten skomentowany kod, zamiast tego poniżej
        // final Restaurant restaurant = (Restaurant) restaurantTable.getSelectionModel().getSelectedItem();
        final Restaurant restaurant = (Restaurant) restaurantsTable.getItems().get(2);

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return DataSource.getInstance().updateRestaurantName(restaurant.getId(), "Ziomek");
            }
        };

        task.setOnSucceeded(e -> {
            if(task.valueProperty().get()) {
                restaurant.setName("Ziomek");
                restaurantsTable.refresh();
            }
        });

        new Thread(task).start();
    }
}