package co.in.nextgencoder.slurpss_foodandmessservices.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.R;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private List<Dish> dishes;
    public DishAdapter( List<Dish> dishes) {
        this.dishes = dishes;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext());
        View view = layoutInflater.inflate( R.layout.item_mess_dish, parent, false);
        return new DishViewHolder( view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        holder.nameTV.setText( dishes.get(position).getName());
        holder.priceTV.setText( dishes.get( position).getPrice()+" Rs");
        holder.typeTV.setText( dishes.get( position).getCategory());

        if( dishes.get( position).getCategory().equals( "Veg")) {
            holder.typeImage.setImageResource( R.drawable.food_type_veg);
        } else {
            holder.typeImage.setImageResource( R.drawable.food_type_nonveg);
        }
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public class DishViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV, priceTV, typeTV;
        ImageView typeImage;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById( R.id.dishName);
            priceTV = itemView.findViewById( R.id.dishPrice);
            typeTV = itemView.findViewById( R.id.dishTypeText);
            typeImage = itemView.findViewById( R.id.dishTypeImage);
        }
    }

}
