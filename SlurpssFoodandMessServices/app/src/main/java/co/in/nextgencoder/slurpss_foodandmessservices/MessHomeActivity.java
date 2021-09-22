package co.in.nextgencoder.slurpss_foodandmessservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MessHomeActivity extends AppCompatActivity {


 FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_home);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void gotoEditMesss(View view) {
        Intent intent = new Intent(MessHomeActivity.this,MessEditProfileActivity.class );


        startActivity(intent);
    }

    public void gotoLogout(View view)
    {
        firebaseAuth.signOut();
        Intent intent = new Intent(MessHomeActivity.this,MainActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public void goToDish(View view) {
        Intent intent = new Intent( this, MessDishActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity( intent);
    }

    public void goToPackage(View view) {
        Intent intent = new Intent( this, MessPackageActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity( intent);
    }
}