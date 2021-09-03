package co.in.nextgencoder.slurpssadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);
    }

    public void addMess(View view) {
        Intent intent = new Intent(this, AddMessActivity.class);
        startActivity(intent);
    }
}