package com.finki.application.smartbin.models;

/**
 * Created by Jona Dimovska on 10.9.2017.
 */

public class User {
    public String name;
    public String username;
    public double points;

    public User(String name, String username, double points) {
        this.name = name;
        this.username = username;
        this.points = points;
    }
}
