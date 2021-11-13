package co.in.nextgencoder.slurpss_foodandmessservices.service;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public interface GpsService {

    public Location getLocation();

    public void stopUsingGPS();

    public double getLatitude();

    public double getLongitude();

    public boolean canGetLocation();

    public void showSettingsAlert();
}

