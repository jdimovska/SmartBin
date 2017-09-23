package com.finki.application.smartbin.models;

/**
 * Created by Jona Dimovska on 30.8.2017.
 */

public class Container
{
    public int id;
    public double longitude;
    public double latitude;
    public double capacity;
    public double maxCapacity;
    public int id_ttn;
    public int category;

    public Container(int id, double longitude, double latitude, double capacity, double maxCapacity, int id_ttn,int category) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.id_ttn = id_ttn;
        this.category=category;
    }
}
