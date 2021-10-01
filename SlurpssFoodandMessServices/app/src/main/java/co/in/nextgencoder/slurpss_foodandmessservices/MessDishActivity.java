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

public class MessDishActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_dish);

        recyclerView = findViewById( R.id.allDishRecyclerView);

        Dish dish1 = new Dish( "Paneer", 20.0, "Curry", "Veg");
        Dish dish2 = new Dish( "Chicken", 40.0, "Fry", "Non-Veg");

        List<Dish> dishes = new ArrayList<>();
        dishes.add( dish1);
        dishes.add( dish2);

        recyclerView.setLayoutManager( new LinearLayoutManager( this));
        recyclerView.setAdapter( new DishAdapter( dishes));
    }

    public void goToAddDish(View view) {
        Intent intent = new Intent( this, MessAddDishActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity( intent);
    }
}