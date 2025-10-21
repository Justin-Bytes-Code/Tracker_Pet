package com.example.trackerpet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Animal Table
    private static final String DATABASE_NAME = "Animals.db";
    private static final int DATABASE_VERSION = 2;
    public static final String ANIMALS_DB = "ANIMALS";
    public static final String COLUMN_ANIMAL_NAME = "ANIMAL_NAME";
    public static final String COLUMN_ANIMAL_SPECIES = "ANIMAL_SPECIES";
    public static final String COLUMN_ANIMAL_DOB = "ANIMAL_DOB";
    public static final String COLUMN_ANIMAL_CURRENT_WEIGHT = "ANIMAL_CURRENT_WEIGHT";
    public static final String COLUMN_ANIMAL_GOAL_WEIGHT = "ANIMAL_GOAL_WEIGHT";
    public static final String COLUMN_ID = "ID";


    //User Table
    public static final String USERS_DB = "USERS";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";



    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //First time database is accessed. Create new databases here
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Animal Table
        String CreateTableStatement = "CREATE TABLE " + ANIMALS_DB + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " " + COLUMN_ANIMAL_NAME
                + " TEXT, " + COLUMN_ANIMAL_SPECIES
                + " TEXT, " + COLUMN_ANIMAL_DOB
                + " TEXT, " + COLUMN_ANIMAL_CURRENT_WEIGHT
                + " INT, " + COLUMN_ANIMAL_GOAL_WEIGHT
                + " INT)";

        db.execSQL(CreateTableStatement);

        //Users table
        String createUsersTable = "CREATE TABLE " + USERS_DB + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);




    }

    //This is called if the database version changes number and prevents the app from breaking
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean AddOne(Animal animal) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ANIMAL_NAME, animal.getNameOfAnimal());
        cv.put(COLUMN_ANIMAL_SPECIES, animal.getAnimalSpecies());
        cv.put(COLUMN_ANIMAL_DOB, animal.getDateOfBirth());
        cv.put(COLUMN_ANIMAL_CURRENT_WEIGHT, animal.getCurrentWeight());
        cv.put(COLUMN_ANIMAL_GOAL_WEIGHT, animal.getGoalWeight());

        long insert = db.insert(ANIMALS_DB, null, cv);
        return insert != -1;



        //return true;
    }
    public List<Animal> getEveryone() {

        List<Animal>  returnList = new ArrayList<>();

        //Get Data from Database

        String queryString = "SELECT * FROM " + ANIMALS_DB;
        
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);


        if (cursor.moveToFirst()) {
            //Loop though results and create new customer objects
            do {
                int animalID = cursor.getInt(0);
                String animalName = cursor.getString(1);
                String animalSpecies = cursor.getString(2);
                String animalDOB = cursor.getString(3);
                int animalCurrentWeight = cursor.getInt(4);
                int animalGoalWeight = cursor.getInt(5);

                Animal newanimal = new Animal(animalID, animalName, animalSpecies, animalDOB, animalCurrentWeight, animalGoalWeight);
                        returnList.add(newanimal);


            } while (cursor.moveToNext());


        } else {
            //failed do not add anything to the list

        }

        //Closes cursor and databases
        cursor.close();
        db.close();

        return returnList;

    }

    //Edits Animals
    public boolean updateAnimal(Animal animal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ANIMAL_NAME, animal.getNameOfAnimal());
        cv.put(COLUMN_ANIMAL_SPECIES, animal.getAnimalSpecies());
        cv.put(COLUMN_ANIMAL_DOB, animal.getDateOfBirth());
        cv.put(COLUMN_ANIMAL_CURRENT_WEIGHT, animal.getCurrentWeight());
        cv.put(COLUMN_ANIMAL_GOAL_WEIGHT, animal.getGoalWeight());

        int result = db.update(ANIMALS_DB, cv, COLUMN_ID + "=?", new String[]{String.valueOf(animal.getID())});
        db.close();
        return result > 0;
    }

    //Deletes Animal
    public boolean deleteAnimal(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(ANIMALS_DB, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0; // returns true if a row was deleted
    }

    // Add a new user
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password);

        long insert = db.insert(USERS_DB, null, cv);
        db.close();
        return insert != -1;
    }

    // Check if username exists
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_DB + " WHERE USERNAME = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Validate login
    public boolean validateLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_DB + " WHERE USERNAME = ? AND PASSWORD = ?", new String[]{username, password});
        boolean valid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return valid;
    }




}
