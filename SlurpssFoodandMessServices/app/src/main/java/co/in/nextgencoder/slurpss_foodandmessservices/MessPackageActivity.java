package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.adapter.PackageAdapter;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.service.PackageService;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.PackageServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public class MessPackageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PackageService packageService = new PackageServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_package);

        recyclerView = findViewById( R.id.allPackageRecyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager( this));

        packageService.getAllPackages(new Callback<List<Package>>() {
            @Override
            public void callback(List<Package> packages) {
                recyclerView.setAdapter(new PackageAdapter(packages));
            }
        });
    }

    public void goToAddPackage(View view) {
        Intent intent = new Intent( this, MessAddPackageActivity.class);
        startActivity( intent);
    }
}