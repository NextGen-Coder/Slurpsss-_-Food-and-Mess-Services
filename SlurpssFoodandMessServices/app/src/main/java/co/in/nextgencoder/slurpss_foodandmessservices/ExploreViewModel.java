package co.in.nextgencoder.slurpss_foodandmessservices;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Mess;
import co.in.nextgencoder.slurpss_foodandmessservices.service.MessService;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.MessServiceImpl;

public class ExploreViewModel extends ViewModel{

    private MutableLiveData<ArrayList<Mess>> allMesses;
    private MessService messService;

    public ExploreViewModel() {
        messService = new MessServiceImpl();
    }

}