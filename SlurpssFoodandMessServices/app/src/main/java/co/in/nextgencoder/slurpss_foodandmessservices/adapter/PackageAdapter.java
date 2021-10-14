package co.in.nextgencoder.slurpss_foodandmessservices.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.MessEditDishActivity;
import co.in.nextgencoder.slurpss_foodandmessservices.R;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.DishServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.PackageServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {

    private List<Package> packages;
    public PackageAdapter( List<Package> packages) {
        this.packages = packages;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext());
        View view = layoutInflater.inflate( R.layout.item_mess_package, parent, false);
        return new PackageViewHolder( view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageAdapter.PackageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameTV.setText( packages.get(position).getName());
        holder.priceTV.setText( packages.get( position).getPrice()+" Rs");
        holder.typeTV.setText( packages.get( position).getCategory());

        if( packages.get( position).getCategory().equals( "Veg")) {
            holder.typeImage.setImageResource( R.drawable.food_type_veg);
        } else {
            holder.typeImage.setImageResource( R.drawable.food_type_nonveg);
        }

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), MessEditDishActivity.class);
                intent.putExtra( "packageId", packages.get( position).getId());
                v.getContext().startActivity( intent);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String packageId = packages.get( holder.getAdapterPosition()).getId();

                new PackageServiceImpl().deletePackageById(new Callback<Boolean>() {
                    @Override
                    public void callback(Boolean isSuccessful) {
                        if( isSuccessful) {
                            Toast.makeText(v.getContext(), packages.get( position).getName()+" removed successfully", Toast.LENGTH_SHORT).show();
                            packages.remove( position);
                            notifyItemRemoved( holder.getAdapterPosition());
                        }
                    }
                }, packageId);

            }
        });
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV, priceTV, typeTV;
        ImageView typeImage, deleteBtn, editBtn;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById( R.id.packageName);
            priceTV = itemView.findViewById( R.id.packagePrice);
            typeTV = itemView.findViewById( R.id.packageTypeText);
            typeImage = itemView.findViewById( R.id.packageTypeImage);
            deleteBtn = itemView.findViewById( R.id.packageDeleteButton);
            editBtn = itemView.findViewById( R.id.packageEditButton);
        }


    }

}

