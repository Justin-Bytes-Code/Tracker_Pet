package com.example.trackerpet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    Button AddAnimalBtn;
    Button DeleteAnimalBtn;
    RecyclerView AnimalListRV;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch SMSSwitch;

    private RecycleViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final int SMS_PERMISSION_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AddAnimalBtn = findViewById(R.id.AddAnimalBtn);
        DeleteAnimalBtn = findViewById(R.id.DeleteAnimalBtn);
        AnimalListRV = findViewById(R.id.AnimalListRV);
        SMSSwitch = findViewById(R.id.SMSSwitch);

        // Load animals from the database and display them in the RecyclerView.
        DataBaseHelper dataBaseHelper = new DataBaseHelper(WelcomeActivity.this);
        List<Animal> everyone = dataBaseHelper.getEveryone();

        AnimalListRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        AnimalListRV.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(this, everyone);
        AnimalListRV.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(animal -> {
            Intent intent = new Intent(WelcomeActivity.this, EditAnimalActivity.class);

            // Pass animal data
            intent.putExtra("ID", animal.getID());
            intent.putExtra("Name", animal.getNameOfAnimal());
            intent.putExtra("Species", animal.getAnimalSpecies());
            intent.putExtra("DOB", animal.getDateOfBirth());
            intent.putExtra("CurrentWeight", animal.getCurrentWeight());
            intent.putExtra("GoalWeight", animal.getGoalWeight());

            // Start EditAnimalActivity
            startActivity(intent);
        });

        SMSSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {

                // TODO: Allow the user to enter their own phone number instead of using a hardcoded value.
                String phoneNumber = "+15551234567"; // Test Phone Number 555-123-4567
                String message = "Check back in often to update your information about your pet!";
                checkSmsPermission(phoneNumber, message);
            }
        });

        DeleteAnimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(WelcomeActivity.this);
                List<Animal> everyone = dataBaseHelper.getEveryone();

                // Refresh the RecyclerView with the latest database data.
                mAdapter = new RecycleViewAdapter(WelcomeActivity.this, everyone);
                AnimalListRV.setAdapter(mAdapter);

                Log.d("AnimalInfo", everyone.toString());
            }
        });

        AddAnimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent SecondScreen = new Intent(WelcomeActivity.this, RegisterAnimalActivity.class);
                startActivity(SecondScreen);

            }
        });
    }

    // Load the latest animals from the database when going back to screen
    @Override
    protected void onResume() {
        super.onResume();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        List<Animal> everyone = dataBaseHelper.getEveryone();

        mAdapter = new RecycleViewAdapter(this, everyone);
        AnimalListRV.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(animal -> {
            Intent intent = new Intent(WelcomeActivity.this, EditAnimalActivity.class);
            intent.putExtra("ID", animal.getID());
            intent.putExtra("Name", animal.getNameOfAnimal());
            intent.putExtra("Species", animal.getAnimalSpecies());
            intent.putExtra("DOB", animal.getDateOfBirth());
            intent.putExtra("CurrentWeight", animal.getCurrentWeight());
            intent.putExtra("GoalWeight", animal.getGoalWeight());
            startActivity(intent);
        });

        Log.d("AnimalInfo", "RecyclerView refreshed with these values: " + everyone.toString());

    }
    // Check if SMS permission is granted
    private void checkSmsPermission(String phoneNumber, String message) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        } else {
            sendSms(phoneNumber, message);
        }
    }

    // Send SMS
    private void sendSms(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent!", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            Toast.makeText(this, "SMS permission was denied.", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Invalid phone number or message.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Handle SMS permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
