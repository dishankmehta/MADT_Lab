package com.example.dishank.madt_lab;

/**
 * Created by Dishank on 4/11/2016.
 */
public class DataProviderContent {

    private int image;
    private String name;
    private String goal;

    public DataProviderContent(int image, String name, String goal){
        this.image = image;
        this.name = name;
        this.goal = goal;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getGoal(){
        return goal;
    }

    public void setGoal(String goal){
        this.goal = goal;
    }
}
