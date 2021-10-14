package co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.service.DishService;
import co.in.nextgencoder.slurpss_foodandmessservices.service.PackageService;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;

public class PackageServiceImpl implements PackageService {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public PackageServiceImpl() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void getAllPackages( Callback<List<Package>> finishedCallback) {
        String messId = firebaseAuth.getUid();

        DatabaseReference packagesRef = databaseReference.child( "mess").child(messId).child("packages");
        packagesRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                List<Package> packages = new ArrayList<>();
                for ( DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                    Package eachPackage = dataSnapshot.getValue(Package.class);
                    packages.add( eachPackage);
                }
                finishedCallback.callback( packages);
            }
        });
    }

    @Override
    public void deletePackageById(Callback<Boolean> finishedCallback, String id) {
        String messId = firebaseAuth.getUid();
        DatabaseReference packageRef = databaseReference.child( "mess").child(messId).child("packages").child( id);

        packageRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    finishedCallback.callback( true);
                } else {
                    finishedCallback.callback( false);
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        finishedCallback.callback( false);
                    }
                });
    }

    @Override
    public void getPackageById(Callback<Package> finishedCallback, String id) {
        String messId = firebaseAuth.getUid();
        DatabaseReference packageRef = databaseReference.child( "mess").child(messId).child("packages").child( id);

        packageRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if( task.isSuccessful()) {
                    Package packageobj = task.getResult().getValue( Package.class);
                    finishedCallback.callback( packageobj);
                } else {
                    finishedCallback.callback( new Package());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                finishedCallback.callback( new Package());
            }
        });
    }

    @Override
    public void addPackage(Callback<Boolean> finishedCallback, Package packageobj) {
        String messId = firebaseAuth.getUid();

        DatabaseReference packageRef = databaseReference.child( "mess").child( messId).child( "packages");
        String packageId = packageRef.push().getKey();

        packageRef.child( packageId).setValue( packageobj)
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

    }

    @Override
    public void updatePackage(Callback<Boolean> finishedCallback, Package packageobj, String packageId) {
        String messId = firebaseAuth.getUid();

        DatabaseReference packageRef = databaseReference.child( "mess").child( messId).child( "packages");
        packageobj.setId( packageId);

        packageRef.child( packageId).setValue( packageobj)
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
    }

}

