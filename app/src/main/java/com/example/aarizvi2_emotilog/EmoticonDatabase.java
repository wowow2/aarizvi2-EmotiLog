package com.example.aarizvi2_emotilog;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
To allow for user to see summaries of previous days in a user friendly way, this class provides methods
to save the ArrayList containing all emoticons to a file, load all emoticons from the file, and clear
the saved emoticons.
 */
public class EmoticonDatabase {
    public void saveArrayList(ArrayList<Emoticon> list, String filename, Context context) {
        try {
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Emoticon> loadArrayList(String filename, Context context) {
        ArrayList<Emoticon> list = new ArrayList<>();
        try {
            File file = new File(context.getFilesDir(), filename);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<Emoticon>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void clearDatabase(String filename, Context context) {
        File file = new File(context.getFilesDir(), filename);
        file.delete();

    }
}
