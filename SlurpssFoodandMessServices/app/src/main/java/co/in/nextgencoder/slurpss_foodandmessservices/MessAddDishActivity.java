package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpss_foodandmessservices.model.Dish;
import co.in.nextgencoder.slurpss_foodandmessservices.service.DishService;
import co.in.nextgencoder.slurpss_foodandmessservices.serviceimpl.DishServiceImpl;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Callback;
import co.in.nextgencoder.slurpss_foodandmessservices.util.Validator;

public class MessAddDishActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText dishName, dishPrice, dishDescription;
    private String [] foodTypes = { "Select Category", "Veg", "Non-Veg", "Egg" };

    private Validator validator = new Validator();
    private DishService dishService = new DishServiceImpl();

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_add_dish);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        spinner = findViewById( R.id.foodTypeSpinner);
        dishName = findViewById( R.id.dishName);
        dishPrice = findViewById( R.id.dishPrice);
        dishDescription = findViewById( R.id.dishDescription);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_dropdown_item, foodTypes);
        spinner.setAdapter( typeAdapter);
    }

    public void submitDish(View view) {
        String name = dishName.getText().toString().trim();
        String price = dishPrice.getText().toString().trim();
        String description = dishDescription.getText().toString().trim();
        String foodType = spinner.getSelectedItem().toString();

        String validationMsg = validator.validateAddDish( name, price, description);

        if( validationMsg.equals("Validation Successful")) {
            if( foodType.equals( foodTypes[0])) {
                Toast.makeText( this, "Select food type", Toast.LENGTH_SHORT).show();
            } else {
                Dish dish = new Dish(  name, null, Double.parseDouble( price), description, foodType);

                dishService.addDish(new Callback<Boolean>() {
                    @Override
                    public void callback(Boolean isSuccessful) {
                        if( isSuccessful) {
                            Toast.makeText(getApplicationContext(), "Dish saved successfully", Toast.LENGTH_SHORT).show();
                            dishName.setText( "");
                            dishDescription.setText("");
                            dishPrice.setText("");
                            spinner.setSelection( 0);
                        } else {
                            Toast.makeText(getApplicationContext(), "Dish saving failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, dish);
            }
        } else {
            Toast.makeText( this, validationMsg, Toast.LENGTH_SHORT).show();
        }
    }
}