package co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Mess;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.service.MessService;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public class MessServiceImpl implements MessService {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public MessServiceImpl() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void getMessById(Callback<Mess> finishedCallback, String id) {

        DatabaseReference messRef = databaseReference.child( "mess").child(id);
         messRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if( task.isSuccessful()) {
                    Mess mess = task.getResult().getValue( Mess.class);
                    finishedCallback.callback( mess);
                } else {
                    finishedCallback.callback( new Mess());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                finishedCallback.callback( new Mess());
            }
        });
    }

    @Override
    public void updateMessProfile(Callback<Boolean> finishedCallback, String addressURL, String messDescription, String messSafety) {

        String messId = firebaseAuth.getUid();
        DatabaseReference messRef = databaseReference.child( "mess").child( messId);

        messRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if( task.isSuccessful()) {
                    Mess mess = task.getResult().getValue( Mess.class);

                    mess.setAddressURL( addressURL);
                    mess.setDescription( messDescription);
                    mess.setSafetyMeasures( messSafety);

                    messRef.setValue( mess)
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

                } else {
                    finishedCallback.callback( false);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                finishedCallback.callback( false);
            }
        });
    }
}
