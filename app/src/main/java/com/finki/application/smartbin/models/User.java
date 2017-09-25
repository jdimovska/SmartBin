package com.finki.application.smartbin.models;

/**
 * Created by Jona Dimovska on 10.9.2017.
 */

public class User {
    public String name;
    public String email;
    public String username;
    public double points;

    public User(String name,String email, String username, double points) {
        this.name = name;
        this.email=email;
        this.username = username;
        this.points = points;
    }
}
