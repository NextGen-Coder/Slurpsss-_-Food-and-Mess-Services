package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.model.Package;
import co.in.nextgencoder.slurpss_foodandmessservices.service.DishService;
import co.in.nextgencoder.slurpss_foodandmessservices.service.PackageService;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.DishServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.PackageServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Validator;

public class MessAddPackageActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText packageName, packagePrice, packageDescription;
    private String [] foodTypes = { "Select Category", "Veg", "Non-Veg", "Egg" };

    private Validator validator = new Validator();
    private PackageService packageService = new PackageServiceImpl();

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_add_package);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        spinner = findViewById( R.id.foodTypeSpinner);
        packageName = findViewById( R.id.packageName);
        packagePrice = findViewById( R.id.packagePrice);
        packageDescription = findViewById( R.id.packageDescription);

        String [] foodTypes = { "Select Category", "Veg", "Non-Veg", "Egg" };


        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_dropdown_item, foodTypes);
        spinner.setAdapter( typeAdapter);
    }

    public void submitPackage(View view) {
        String name = packageName.getText().toString().trim();
        String price = packagePrice.getText().toString().trim();
        String description = packageDescription.getText().toString().trim();
        String foodType = spinner.getSelectedItem().toString();

        String validationMsg = validator.validateAddPackage( name, price, description);

        if( validationMsg.equals("Validation Successful")) {
            if( foodType.equals( foodTypes[0])) {
                Toast.makeText( this, "Select food type", Toast.LENGTH_SHORT).show();
            } else {
                Package packageObj = new Package( name, null, Double.parseDouble( price), description, foodType);

                packageService.addPackage(new Callback<Boolean>() {
                    @Override
                    public void callback(Boolean isSuccessful) {
                        if( isSuccessful) {
                            Toast.makeText(getApplicationContext(), "Package saved successfully", Toast.LENGTH_SHORT).show();
                            packageName.setText( "");
                            packageDescription.setText("");
                            packagePrice.setText("");
                            spinner.setSelection( 0);
                        } else {
                            Toast.makeText(getApplicationContext(), "Package saving failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, packageObj);
            }
        } else {
            Toast.makeText( this, validationMsg, Toast.LENGTH_SHORT).show();
        }
    }
}