package com.example.trackerpet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditAnimalActivity extends AppCompatActivity {

    Button EditBTN;
    Button DeleteBTN;
    EditText EditNameOfAnimalET,  EditAnimalSpeciesET, EditDateOfBirthET, EditCurrentWeightET, EditGoalWeightET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditBTN = findViewById(R.id.EditBTN);
        DeleteBTN = findViewById(R.id.DeleteBTN);
        EditNameOfAnimalET = findViewById(R.id.EditNameOfAnimalET);
        EditAnimalSpeciesET = findViewById(R.id.EditAnimalSpeciesET);
        EditDateOfBirthET = findViewById(R.id.EditDateOfBirthET);
        EditCurrentWeightET = findViewById(R.id.EditCurrentWeightET);
        EditGoalWeightET = findViewById(R.id.EditGoalWeightET);

        // Gets Data from selected Animal
        int id = getIntent().getIntExtra("ID", -1); // default -1 if not found
        String name = getIntent().getStringExtra("Name");
        String species = getIntent().getStringExtra("Species");
        String dob = getIntent().getStringExtra("DOB");
        int currentWeight = getIntent().getIntExtra("CurrentWeight", 0);
        int goalWeight = getIntent().getIntExtra("GoalWeight", 0);

        EditNameOfAnimalET.setText(name);
        EditAnimalSpeciesET.setText(species);
        EditDateOfBirthET.setText(dob);
        EditCurrentWeightET.setText(String.valueOf(currentWeight));
        EditGoalWeightET.setText(String.valueOf(goalWeight));

        EditBTN.setOnClickListener(v -> {
            DataBaseHelper db = new DataBaseHelper(EditAnimalActivity.this);

            Animal updatedAnimal = new Animal(
                    id,
                    EditNameOfAnimalET.getText().toString(),
                    EditAnimalSpeciesET.getText().toString(),
                    EditDateOfBirthET.getText().toString(),
                    Integer.parseInt(EditCurrentWeightET.getText().toString()),
                    Integer.parseInt(EditGoalWeightET.getText().toString())
            );

            boolean success = db.updateAnimal(updatedAnimal);

            if (success) {
                Toast.makeText(EditAnimalActivity.this, "Animal updated!", Toast.LENGTH_SHORT).show();
                finish(); // Go back to Welcome Activity
            } else {
                Toast.makeText(EditAnimalActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
            }
        });

        DeleteBTN.setOnClickListener(v -> {

            DataBaseHelper db = new DataBaseHelper(EditAnimalActivity.this);
            boolean success = db.deleteAnimal(id);

            if (success) {
                Toast.makeText(EditAnimalActivity.this, name + " Deleted!", Toast.LENGTH_SHORT).show();
                finish(); // Go back to Welcome Activity
            } else {
                Toast.makeText(EditAnimalActivity.this, name + " Delete failed!", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
