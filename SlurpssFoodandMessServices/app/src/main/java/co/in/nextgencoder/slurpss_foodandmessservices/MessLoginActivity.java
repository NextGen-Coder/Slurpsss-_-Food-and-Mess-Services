package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Mess;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Validator;

public class MessLoginActivity extends AppCompatActivity {

    private EditText mailET, passET;
    private Validator validator = new Validator();
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_login);

        mailET = findViewById( R.id.messEmail);
        passET = findViewById( R.id.messPass);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void resetMessPassword(View view) {
        Intent intent = new Intent( this, MessResetPassword.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity( intent);
    }

    public void submitMessLogin(View view) {
        String mail = mailET.getText().toString().trim();
        String pass = passET.getText().toString().trim();

        String validationMsg = validator.validateMessLogin( mail, pass);

        if( validationMsg.equals( "Validation Successful")) {

            firebaseAuth.signInWithEmailAndPassword( mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful()) {

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

                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        } else {
            Toast.makeText(this, validationMsg, Toast.LENGTH_SHORT).show();
        }
    }
}