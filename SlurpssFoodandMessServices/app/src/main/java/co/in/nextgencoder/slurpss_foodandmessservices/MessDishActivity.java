package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.adapter.DishAdapter;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.service.DishService;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.DishServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public class MessDishActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DishService dishService = new DishServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_dish);

        recyclerView = findViewById( R.id.allDishRecyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager( this));

        dishService.getAllDishes(new Callback<List<Dish>>() {
            @Override
            public void callback(List<Dish> dishes) {
                recyclerView.setAdapter( new DishAdapter( dishes));
            }
        });

    }

    public void goToAddDish(View view) {
        Intent intent = new Intent( this, MessAddDishActivity.class);
        startActivity( intent);
    }
}