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
import com.google.firebase.auth.FirebaseAuth;

public class MessResetPassword extends AppCompatActivity {

    private EditText mailET;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_reset_password);

        mailET = findViewById( R.id.messEmail);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void resetPassword(View view) {

        String mail = mailET.getText().toString().trim();

        firebaseAuth.sendPasswordResetEmail( mail)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if( task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Reset Link sent to your email. Kindly Check it to reset password.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent( getApplicationContext(), MessLoginActivity.class);
                        startActivity( intent);
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

    }
}