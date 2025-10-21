package com.example.trackerpet;

public class Animal {
    private int ID;
    private String NameOfAnimal;
    private String AnimalSpecies;
    private String DateOfBirth;
    private int CurrentWeight;
    private int GoalWeight;

    public Animal(int ID, String nameOfAnimal, String animalSpecies, String dateOfBirth, int currentWeight, int goalWeight) {
        this.ID = ID;
        NameOfAnimal = nameOfAnimal;
        AnimalSpecies = animalSpecies;
        DateOfBirth = dateOfBirth;
        CurrentWeight = currentWeight;
        GoalWeight = goalWeight;
    }

    public Animal() {
        //Just in case we need this for later it's ready
    }

    //To string is needed for Printing


    @Override
    public String toString() {
        return "Animal{" +
                "ID=" + ID +
                ", NameOfAnimal='" + NameOfAnimal + '\'' +
                ", AnimalSpecies='" + AnimalSpecies + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", CurrentWeight=" + CurrentWeight +
                ", GoalWeight=" + GoalWeight +
                '}';
    }

    //GETTERS AND SETTERS
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameOfAnimal() {
        return NameOfAnimal;
    }

    public void setNameOfAnimal(String nameOfAnimal) {
        NameOfAnimal = nameOfAnimal;
    }

    public String getAnimalSpecies() {
        return AnimalSpecies;
    }

    public void setAnimalSpecies(String animalSpecies) {
        AnimalSpecies = animalSpecies;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public int getCurrentWeight() {
        return CurrentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        CurrentWeight = currentWeight;
    }

    public int getGoalWeight() {
        return GoalWeight;
    }

    public void setGoalWeight(int goalWeight) {
        GoalWeight = goalWeight;
    }
}
