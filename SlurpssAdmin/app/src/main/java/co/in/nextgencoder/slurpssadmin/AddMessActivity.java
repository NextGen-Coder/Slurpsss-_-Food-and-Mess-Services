package co.in.nextgencoder.slurpssadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpssadmin.model.Mess;
import co.in.nextgencoder.slurpssadmin.util.Validator;

public class AddMessActivity extends AppCompatActivity {

    private EditText nameET, mailET, addressET, passwordET;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private Validator validator = new Validator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mess);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        nameET = findViewById( R.id.messName);
        mailET = findViewById( R.id.messEmail);
        addressET = findViewById( R.id.messAddress);
        passwordET = findViewById( R.id.messPassword);
    }

    public void saveMess(View view) {
        String name = nameET.getText().toString().trim();
        String mail = mailET.getText().toString().trim();
        String address = addressET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        String validationMsg = validator.validateMess( name, mail, address, password);

        if( validationMsg.equals("Validation Successful")) {

            firebaseAuth.createUserWithEmailAndPassword( mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful()) {
                            String id = firebaseAuth.getUid();
                            Mess mess = new Mess( id, name, mail, address);

                            DatabaseReference newMessRef = databaseReference.child( "mess").child( id);
                            newMessRef.setValue( mess);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        } else {
            Toast.makeText( this, validationMsg, Toast.LENGTH_SHORT).show();
        }


    }
}