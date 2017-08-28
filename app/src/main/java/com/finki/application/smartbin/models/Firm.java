package com.finki.application.smartbin.models;

/**
 * Created by Jona Dimovska on 28.8.2017.
 */

public class Firm {

    public String name;
    public String email;
    public  String location;
    public  String phone ;
    public  String url ;

    public Firm(String name,String email,String location,String phone,String url){
        this.name=name;
        this.email=email;
        this.location=location;
        this.phone=phone;
        this.url=url;
    }
}
