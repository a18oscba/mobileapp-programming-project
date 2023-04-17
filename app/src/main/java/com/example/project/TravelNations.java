package com.example.project;

public class TravelNations {
    private String ID;
    private String name;
    private String location;
    private String category;
    public TravelNations(String ID, String name, String location, String category){
        ID = ID;
        this.name = name;
        this.location = location;
        this.category = category;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }
    @Override
    public String toString(){
        return name;
    }
}