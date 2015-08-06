package com.example.hackeru.sampleproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by hackeru on 30/07/2015.
 */
public class Person implements Serializable {

    String name;
    transient Bitmap picture;

    public Person(String name, Bitmap picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
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
