package co.in.nextgencoder.slurpssadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpssadmin.util.AESUtils;

public class MainActivity extends AppCompatActivity {

    private EditText userIdEdit, userPassEdit;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIdEdit = findViewById( R.id.userId);
        userPassEdit = findViewById( R.id.userPass);
        databaseReference = FirebaseDatabase.getInstance().getReference( "super_admin");
    }

    public void submitLogin(View view) {

        try {
            String userId = userIdEdit.getText().toString();
            String userPass = userPassEdit.getText().toString();

            System.out.println( "======> "+userId);
            System.out.println( "======> "+userPass);

            String encryptedUserId = AESUtils.decrypt( userId);
            String encryptedUserPass = AESUtils.decrypt( userPass);

            databaseReference.get()
                .addOnCompleteListener( task -> {
                    String id = task.getResult().child("user_id").getValue().toString();
                    String pass = task.getResult().child("password").getValue().toString();
                    System.out.println( "ID -> " + id);
                    System.out.println( "Pass -> " + pass);
                });

        } catch( Exception e) { }

    }
}