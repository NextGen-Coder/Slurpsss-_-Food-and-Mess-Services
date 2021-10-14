package co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.MessAddDishActivity;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.service.DishService;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public class DishServiceImpl implements DishService {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public DishServiceImpl() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void getAllDishes( Callback<List<Dish>> finishedCallback) {
        String messId = firebaseAuth.getUid();

        DatabaseReference dishesRef = databaseReference.child( "mess").child(messId).child("dishes");
        dishesRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                List<Dish> dishes = new ArrayList<>();
                for ( DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                    Dish eachDish = dataSnapshot.getValue( Dish.class);
                    dishes.add( eachDish);
                }
                finishedCallback.callback( dishes);
            }
        });
    }

    @Override
    public void deleteDishById(Callback<Boolean> finishedCallback, String id) {
        String messId = firebaseAuth.getUid();
        DatabaseReference dishRef = databaseReference.child( "mess").child(messId).child("dishes").child( id);

        dishRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    finishedCallback.callback( true);
                } else {
                    finishedCallback.callback( false);
                }
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                finishedCallback.callback( false);
            }
        });
    }

    @Override
    public void getDishById(Callback<Dish> finishedCallback, String id) {
        String messId = firebaseAuth.getUid();
        DatabaseReference dishRef = databaseReference.child( "mess").child(messId).child("dishes").child( id);

        dishRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if( task.isSuccessful()) {
                    Dish dish = task.getResult().getValue( Dish.class);
                    finishedCallback.callback( dish);
                } else {
                    finishedCallback.callback( new Dish());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                finishedCallback.callback( new Dish());
            }
        });
    }

    @Override
    public void addDish(Callback<Boolean> finishedCallback, Dish dish) {
        String messId = firebaseAuth.getUid();

        DatabaseReference dishRef = databaseReference.child( "mess").child( messId).child( "dishes");
        String dishId = dishRef.push().getKey();

        dishRef.child( dishId).setValue( dish)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if( task.isSuccessful()) {
                            finishedCallback.callback( true);
                        } else {
                            finishedCallback.callback( false);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        finishedCallback.callback(false);
                    }
                });

    }

    @Override
    public void updateDish(Callback<Boolean> finishedCallback, Dish dish, String dishId) {
        String messId = firebaseAuth.getUid();

        DatabaseReference dishRef = databaseReference.child( "mess").child( messId).child( "dishes");
        dish.setId( dishId);

        dishRef.child( dishId).setValue( dish)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if( task.isSuccessful()) {
                        finishedCallback.callback( true);
                    } else {
                        finishedCallback.callback( false);
                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    finishedCallback.callback(false);
                }
            });
    }

}
