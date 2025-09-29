package com.example.aarizvi2_emotilog;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class EmoticonViewModel extends ViewModel {
    private ArrayList<Emoticon> allEmoticons;
    private final EmoticonDatabase emoticonDatabase;

    public EmoticonViewModel() {
        allEmoticons = new ArrayList<>();
        emoticonDatabase = new EmoticonDatabase();
    }

    public void loadEmoticons(Context context) {
        if (allEmoticons.isEmpty()) {
            ArrayList<Emoticon> loadedList = emoticonDatabase.loadArrayList("database", context);
            if (loadedList != null) {
                allEmoticons = loadedList;
            }
        }
    }

    public ArrayList<Emoticon> getAllEmoticons() {
        return allEmoticons;
    }

    public void addEmoticon(Emoticon emoticon) {
        allEmoticons.add(0, emoticon);
    }

    public void clearDatabase(String filename, Context context) {
        emoticonDatabase.clearDatabase(filename, context);
        allEmoticons = new ArrayList<>();
    }
    public void saveEmoticons(Context context) {
        emoticonDatabase.saveArrayList(allEmoticons, "database", context);
    }
}
