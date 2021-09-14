package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Mess;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if( currentUser != null) {
            // User is logged In
            String userID = firebaseAuth.getUid();
            DatabaseReference messRef = databaseReference.child( "mess").child( userID);

            messRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    Intent intent = null;
                    if( task.isSuccessful()) {
                        Mess mess = task.getResult().getValue( Mess.class);
                        if( mess == null) {
                            firebaseAuth.signOut();
                            intent = new Intent( getApplicationContext(), MainActivity.class);
                        } else {
                            intent = new Intent( getApplicationContext(), MessHomeActivity.class);
                        }

                    } else {
                        firebaseAuth.signOut();
                        intent = new Intent( getApplicationContext(), MainActivity.class);
                    }

                    intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( intent);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            firebaseAuth.signOut();
                            Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity( intent);
                        }
                    });

        }
    }

    public void gotoUser(View view) {
    }

    public void gotoMess(View view) {
        Intent intent = new Intent(MainActivity.this, MessLoginActivity.class);
        startActivity(intent);
    }
}