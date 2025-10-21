package com.example.trackerpet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    Button RegisterBTN;
    EditText NameOfAnimalET,  AnimalSpeciesET, DateOfBirthET, CurrentWeightET, GoalWeightET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RegisterBTN = findViewById(R.id.RegisterBTN);
        NameOfAnimalET = findViewById(R.id.NameOfAnimalET);
        AnimalSpeciesET = findViewById(R.id.AnimalSpeciesET);
        DateOfBirthET = findViewById(R.id.DateOfBirthET);
        CurrentWeightET = findViewById(R.id.CurrentWeightET);
        GoalWeightET = findViewById(R.id.GoalWeightET);
        //CurrentDateET = findViewById(R.id.CurrentDateET);




        RegisterBTN.setOnClickListener(v -> {


            Animal animal;

            try {
                animal = new Animal(
                        -1,
                        NameOfAnimalET.getText().toString(),
                        AnimalSpeciesET.getText().toString(),
                        DateOfBirthET.getText().toString(),
                        Integer.parseInt(CurrentWeightET.getText().toString()),
                        Integer.parseInt(GoalWeightET.getText().toString())
                );
                //Toast.makeText(MainActivity3.this, animal.toString(), Toast.LENGTH_SHORT).show();
                Log.d("AnimalInfo", animal.toString());
            }
            catch (NumberFormatException e1) {
                Toast.makeText(MainActivity3.this, "Please Fill Out The Fields!!", Toast.LENGTH_SHORT).show();
                animal = new Animal(-1, "Error", "Error", "Error", 1, 1);
            }
            catch (Exception e2){
                Toast.makeText(MainActivity3.this, "Error Found!", Toast.LENGTH_SHORT).show();
                animal = new Animal(-1, "Error", "Error", "Error", 1, 1);
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity3.this);

            boolean success = dataBaseHelper.AddOne(animal);

            Toast.makeText(MainActivity3.this, "Animal Added!", Toast.LENGTH_SHORT).show();

            //Toast.makeText(MainActivity3.this, animal.toString(), Toast.LENGTH_SHORT).show();


            //Logo Cat Debug
            // Log.d("AnimalInfo", animal.toString());


        });




    }
}