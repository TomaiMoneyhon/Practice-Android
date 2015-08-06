package com.example.hackeru.contactmanageapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by hackeru on 06/08/2015.
 */
public class Contact implements Serializable {

    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String webSite;
    private String birthDate;
    private String timeToCall;
    private String days;
    transient private Bitmap picture;

    public Contact(String name, String phoneNumber, String email, String address, String webSite, String birthDate, String timeToCall, String days, Bitmap picture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.webSite = webSite;
        this.birthDate = birthDate;
        this.timeToCall = timeToCall;
        this.days = days;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getTimeToCall() {
        return timeToCall;
    }

    public String getDays() {
        return days;
    }

    public Bitmap getPicture() {
        return picture;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        picture.compress(Bitmap.CompressFormat.JPEG,100,oos);
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException {
        picture = BitmapFactory.decodeStream(ois);
        ois.defaultReadObject();
    }
}
