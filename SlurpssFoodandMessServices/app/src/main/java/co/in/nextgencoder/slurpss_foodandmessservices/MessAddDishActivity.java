package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MessAddDishActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_add_dish);

        spinner = findViewById( R.id.foodTypeSpinner);

        String [] foodTypes = { "Select Category", "Veg", "Non-Veg", "Egg" };


        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_dropdown_item, foodTypes);
        spinner.setAdapter( typeAdapter);
    }
}