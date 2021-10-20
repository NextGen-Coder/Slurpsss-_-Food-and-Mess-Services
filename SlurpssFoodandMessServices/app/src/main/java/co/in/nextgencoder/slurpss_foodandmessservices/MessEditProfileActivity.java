package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Mess;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.service.MessService;
import co.in.nextgencoder.slurpss_foodandmessservices.service.PackageService;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.MessServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.PackageServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Validator;

public class MessEditProfileActivity extends AppCompatActivity {

    private EditText addressURLET, messDescriptionET , messSafetyMeasuresET;
    private String id;

    private Validator validator = new Validator();
    private MessService messService = new MessServiceImpl();

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_edit_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();


        addressURLET = findViewById( R.id.messAddressURL);
        messDescriptionET = findViewById( R.id.messDescription);
        messSafetyMeasuresET = findViewById( R.id.messSafety);

        id = firebaseAuth.getUid();

        messService.getMessById(new Callback<Mess>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void callback(Mess mess) {
                addressURLET.setText( mess.getAddressURL());
                messDescriptionET.setText( mess.getDescription());
                messSafetyMeasuresET.setText( mess.getSafetyMeasures());

            }
        }, id);


    }

    public void gotoEditImage(View view) {

    }

    public void submitEditedMess(View view) {
        String addressURL = addressURLET.getText().toString().trim();
        String messDescription = messDescriptionET.getText().toString().trim();
        String messSafetyMeasures = messSafetyMeasuresET.getText().toString().trim();


        String validationMsg = validator.validateUpdateMess( addressURL, messDescription, messSafetyMeasures);

        if( validationMsg.equals("Validation Successful")) {
            Mess mess = new Mess( addressURL, messDescription, messSafetyMeasures );

            messService.updateMessProfile(new Callback<Boolean>() {
                @Override
                public void callback(Boolean isSuccessful) {
                    if( isSuccessful) {
                        Toast.makeText(getApplicationContext(), "Mess edited successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent( getApplicationContext(), MessHomeActivity.class);
                        startActivity( intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Mess editing failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }, addressURL, messDescription, messSafetyMeasures);

        } else {
            Toast.makeText( this, validationMsg, Toast.LENGTH_SHORT).show();
        }
    }
}