package firdaus.rizkika.reservasi_futsal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText email,pass;
    private Button Login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi fiirebaseauth
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.EdEmail);
        pass = findViewById(R.id.EdPass);
        Login = findViewById(R.id.bttnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            //validasi
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() && pass.getText().toString().isEmpty()){
                    email.setError("Email harus di isi!");
                    pass.setError("Password Harus di isi!");
                    return;
                }
                if (email.getText().toString().isEmpty()){
                    email.setError("Email harus di isi!");
                    return;
                }
                if (pass.getText().toString().isEmpty()){
                    pass.setError("Password harus di isi!");
                    return;
                }

                else {
                    //proses Login
                     firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(getApplicationContext(),MainMenu.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    //membuat user tetap berada di dalam MainMenu (jika sudah login)
    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainMenu.class));
            finish();
        }
    }
}