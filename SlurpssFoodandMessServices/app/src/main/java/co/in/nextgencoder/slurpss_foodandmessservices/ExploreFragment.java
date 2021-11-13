package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.in.nextgencoder.slurpss_foodandmessservices.service.GpsService;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.GpsServiceImpl;

public class ExploreFragment extends Fragment {

    private ExploreViewModel mViewModel;

    double currentLatitude = 0.0;
    double currentLongitude = 0.0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.explore_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ExploreViewModel.class);
        setupLocation();
        return root;
    }

    private void setupLocation() {
        GpsService gps = new GpsServiceImpl(getContext());
        if(gps.canGetLocation()) {
            currentLatitude = gps.getLatitude();
            currentLongitude = gps.getLongitude();

            if (currentLatitude == 0.0 && currentLongitude == 0.0) {
                currentLatitude = 22.22;
                currentLongitude = 22.22;
            }
        } else {
            gps.showSettingsAlert();
        }
    }
}