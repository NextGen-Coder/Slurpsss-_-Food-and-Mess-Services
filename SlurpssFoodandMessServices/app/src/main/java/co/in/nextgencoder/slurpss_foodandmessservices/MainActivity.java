package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoUser(View view) {
    }

    public void gotoMess(View view) {
        Intent intent = new Intent(MainActivity.this, MessLoginActivity.class);
        startActivity(intent);
    }
}