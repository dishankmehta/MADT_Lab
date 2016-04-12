package com.example.dishank.madt_lab;

/**
 * Created by Dishank on 4/1/2016.
 */
public class DataProvider {

    private String Name;
    private String Match;
    private String Goal;

    public DataProvider(String name, String match, String goal){
        this.Name = name;
        this.Match = match;
        this.Goal = goal;
    }

    public String getName(){
        return Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getMatch(){
        return Match;
    }

    public void setMatch(String Match){
        this.Match = Match;
    }

    public String getGoal(){
        return Goal;
    }

    public void setGoal(String Goal){
        this.Goal = Goal;
    }
}
