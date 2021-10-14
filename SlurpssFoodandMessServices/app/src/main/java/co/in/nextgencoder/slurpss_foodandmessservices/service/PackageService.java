package co.in.nextgencoder.slurpss_foodandmessservices.service;

import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public interface PackageService {

    public void getAllPackages(Callback<List<Package>> finishedCallback);

    public void deletePackageById(Callback<Boolean> finishedCallback, String id);

    public void getPackageById(Callback<Package> finishedCallback, String id);

    public void addPackage(Callback<Boolean> finishedCallback, Package packageObj);

    public void updatePackage(Callback<Boolean> finishedCallback, Package packageObj, String id);

}
