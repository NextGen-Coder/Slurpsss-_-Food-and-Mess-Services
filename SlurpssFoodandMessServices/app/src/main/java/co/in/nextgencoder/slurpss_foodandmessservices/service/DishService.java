package co.in.nextgencoder.slurpss_foodandmessservices.service;

import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public interface DishService {

    public void getAllDishes(Callback<List<Dish>> finishedCallback);

    public void deleteDishById(Callback<Boolean> finishedCallback, String id);

    public void getDishById(Callback<Dish> finishedCallback, String id);

    public void addDish(Callback<Boolean> finishedCallback, Dish dish);

    public void updateDish(Callback<Boolean> finishedCallback, Dish dish, String id);

}
