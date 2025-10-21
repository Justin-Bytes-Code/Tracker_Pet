package com.example.trackerpet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button LoginBtn;
    Button SignUpBtn;

    EditText EmailET;
    EditText PasswordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DataBaseHelper db = new DataBaseHelper(this);

        LoginBtn = findViewById(R.id.LoginBtn);
        SignUpBtn = findViewById(R.id.SignUpBtn);
        EmailET = findViewById(R.id.EmailET);
        PasswordET = findViewById(R.id.PasswordET);

        //TODO - Add code for it to check the Login Text boxes before allowing user to Login
        LoginBtn.setOnClickListener(v -> {

            String email = EmailET.getText().toString().trim();
            String password = PasswordET.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.validateLogin(email, password)) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });


        //TODO - Add code for it to check the Login Text boxes before allowing user to Signup
        SignUpBtn.setOnClickListener(v -> {

            String email = EmailET.getText().toString().trim();
            String password = PasswordET.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.checkUserExists(email)) {
                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
            } else {
                if (db.addUser(email, password)) {
                    Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    finish();
                } else {
                    Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}