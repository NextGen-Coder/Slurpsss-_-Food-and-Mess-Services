package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.GpsServiceImpl;

public class UserHomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ConstraintLayout constraintLayout;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        bottomNavigationView = findViewById( R.id.bottomNav);
        constraintLayout = findViewById( R.id.userHomeLayout);
        frameLayout = findViewById( R.id.frameLayout);

        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.frameLayout, new ExploreFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment = null;

                    switch( item.getItemId()) {
                        case R.id.itemExplore:
                            fragment = new ExploreFragment();
                            break;


                        case R.id.itemOrder:
                            fragment = new OrderFragment();
                            break;


                        case R.id.itemProfile:
                            fragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace( R.id.frameLayout, fragment)
                            .commit();

                    return true;
                }
            }
        );
    }
}