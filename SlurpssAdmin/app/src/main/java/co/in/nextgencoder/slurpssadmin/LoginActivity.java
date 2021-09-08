package co.in.nextgencoder.slurpssadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.slurpssadmin.util.AESUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText userIdEdit, userPassEdit;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIdEdit = findViewById( R.id.userId);
        userPassEdit = findViewById( R.id.userPass);
        databaseReference = FirebaseDatabase.getInstance().getReference( "super_admin");
    }

    public void submitLogin(View view) {

        try {
            String userId = userIdEdit.getText().toString();
            String userPass = userPassEdit.getText().toString();


            databaseReference.get()
                .addOnCompleteListener( task -> {
                    String id = task.getResult().child("user_id").getValue().toString();
                    String pass = task.getResult().child("password").getValue().toString();
                    try {
                        String dbId = AESUtils.decrypt( id);
                        String dbPass = AESUtils.decrypt( pass);
                        if(dbId.equals(userId) && dbPass.equals(userPass)) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        } catch( Exception ignored) { }

    }
}