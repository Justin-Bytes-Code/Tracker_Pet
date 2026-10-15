
package com.example.trackerpet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;

import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Animal Table
    private static final String DATABASE_NAME = "Animals.db";
    private static final int DATABASE_VERSION = 3;

    public static final String ANIMALS_DB = "ANIMALS";
    public static final String COLUMN_ANIMAL_NAME = "ANIMAL_NAME";
    public static final String COLUMN_ANIMAL_SPECIES = "ANIMAL_SPECIES";
    public static final String COLUMN_ANIMAL_DOB = "ANIMAL_DOB";
    public static final String COLUMN_ANIMAL_CURRENT_WEIGHT = "ANIMAL_CURRENT_WEIGHT";
    public static final String COLUMN_ANIMAL_GOAL_WEIGHT = "ANIMAL_GOAL_WEIGHT";
    public static final String COLUMN_ID = "ID";

    // User Table
    public static final String USERS_DB = "USERS";
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_PASSWORD_SALT = "password_salt";

    // Hashes a password using PBKDF2 and a unique salt.
    private String hashPassword(String password, byte[] salt)
            throws Exception {

        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                600000,
                256
        );

        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.encodeToString(hash, Base64.NO_WRAP);
        } finally {
            spec.clearPassword();
        }
    }

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is first created.
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Animal Table
        String CreateTableStatement = "CREATE TABLE " + ANIMALS_DB + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " " + COLUMN_ANIMAL_NAME + " TEXT, " +
                COLUMN_ANIMAL_SPECIES + " TEXT, " +
                COLUMN_ANIMAL_DOB + " TEXT, " +
                COLUMN_ANIMAL_CURRENT_WEIGHT + " INT, " +
                COLUMN_ANIMAL_GOAL_WEIGHT + " INT)";

        db.execSQL(CreateTableStatement);

        // Users table
        String createUsersTable = "CREATE TABLE " + USERS_DB + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_PASSWORD_SALT + " TEXT)";

        db.execSQL(createUsersTable);
    }

    // Called when the database version is upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 3) {

            // Add the salt column to existing users.
            db.execSQL("ALTER TABLE " + USERS_DB +
                    " ADD COLUMN " + COLUMN_PASSWORD_SALT + " TEXT");

            //  This is for existing users who are using old passwords without hashing
            Cursor cursor = db.query(
                    USERS_DB,
                    new String[]{COLUMN_USER_ID, COLUMN_PASSWORD},
                    null, null, null, null, null
            );

            try {
                while (cursor.moveToNext()) {

                    int userId = cursor.getInt(0);
                    String oldPassword = cursor.getString(1);

                    if (oldPassword == null) {
                        continue;
                    }

                    byte[] salt = new byte[16];
                    new SecureRandom().nextBytes(salt);

                    // Hash the old passwords.
                    String hashedPassword = hashPassword(oldPassword, salt);

                    String encodedSalt =
                            Base64.encodeToString(salt, Base64.NO_WRAP);

                    // Update the user's password and salt.
                    ContentValues cv = new ContentValues();
                    cv.put(COLUMN_PASSWORD, hashedPassword);
                    cv.put(COLUMN_PASSWORD_SALT, encodedSalt);

                    db.update(
                            USERS_DB,
                            cv,
                            COLUMN_USER_ID + "=?",
                            new String[]{String.valueOf(userId)}
                    );
                }
            } catch (Exception e) {
                throw new RuntimeException("Password migration failed.", e);
            } finally {
                cursor.close();
            }
        }
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
    }

    public List<Animal> getEveryone() {

        List<Animal> returnList = new ArrayList<>();

        // Retrieve animals from the database.
        String queryString = "SELECT * FROM " + ANIMALS_DB;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // Convert each database row into an Animal object.
            do {
                int animalID = cursor.getInt(0);
                String animalName = cursor.getString(1);
                String animalSpecies = cursor.getString(2);
                String animalDOB = cursor.getString(3);
                int animalCurrentWeight = cursor.getInt(4);
                int animalGoalWeight = cursor.getInt(5);

                Animal newanimal = new Animal(
                        animalID,
                        animalName,
                        animalSpecies,
                        animalDOB,
                        animalCurrentWeight,
                        animalGoalWeight
                );

                returnList.add(newanimal);

            } while (cursor.moveToNext());
        } else {
            // Failed; do not add anything to the list.
        }

        // Closes cursor and database.
        cursor.close();
        db.close();

        return returnList;
    }

    // Updates/Edits Animals in Database
    public boolean updateAnimal(Animal animal) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ANIMAL_NAME, animal.getNameOfAnimal());
        cv.put(COLUMN_ANIMAL_SPECIES, animal.getAnimalSpecies());
        cv.put(COLUMN_ANIMAL_DOB, animal.getDateOfBirth());
        cv.put(COLUMN_ANIMAL_CURRENT_WEIGHT, animal.getCurrentWeight());
        cv.put(COLUMN_ANIMAL_GOAL_WEIGHT, animal.getGoalWeight());

        int result = db.update(
                ANIMALS_DB,
                cv,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(animal.getID())}
        );

        db.close();
        return result > 0;
    }

    public boolean deleteAnimal(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                ANIMALS_DB,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        db.close();
        return result > 0; // Returns true if a row was deleted.
    }

    // Adds a user with a hashed password and salt.
    public boolean addUser(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            // Generate a random salt for this user.
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            // Hash the password before storing it.
            String hashedPassword = hashPassword(password, salt);

            String encodedSalt =
                    Base64.encodeToString(salt, Base64.NO_WRAP);

            ContentValues cv = new ContentValues();
            cv.put(COLUMN_USERNAME, username);
            cv.put(COLUMN_PASSWORD, hashedPassword);
            cv.put(COLUMN_PASSWORD_SALT, encodedSalt);

            long insert = db.insert(USERS_DB, null, cv);

            return insert != -1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean checkUserExists(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + USERS_DB +
                        " WHERE USERNAME = ?",
                new String[]{username}
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

    // Validates a login
    public boolean validateLogin(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_PASSWORD + ", " + COLUMN_PASSWORD_SALT +
                        " FROM " + USERS_DB +
                        " WHERE " + COLUMN_USERNAME + " = ?",
                new String[]{username}
        );

        boolean valid = false;

        try {
            if (cursor.moveToFirst()) {

                String storedHash = cursor.getString(0);
                String storedSalt = cursor.getString(1);

                if (storedHash != null && storedSalt != null) {

                    byte[] salt = Base64.decode(
                            storedSalt,
                            Base64.NO_WRAP
                    );

                    // Hash the entered password using the saved salt.
                    String enteredHash = hashPassword(password, salt);


                    valid = MessageDigest.isEqual(
                            storedHash.getBytes(
                                    java.nio.charset.StandardCharsets.UTF_8),
                            enteredHash.getBytes(
                                    java.nio.charset.StandardCharsets.UTF_8)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            valid = false;
        } finally {
            cursor.close();
            db.close();
        }

        return valid;
    }
}
