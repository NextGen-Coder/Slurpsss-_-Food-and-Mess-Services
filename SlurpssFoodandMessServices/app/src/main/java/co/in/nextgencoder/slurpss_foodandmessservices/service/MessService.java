package co.in.nextgencoder.slurpss_foodandmessservices.service;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Mess;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public interface MessService {

    public void getMessById(Callback<Mess> finishedCallback, String id);

    public void updateMessProfile(Callback<Boolean> finishedCallback, String addressURL, String messDescription, String messSafety);

}
