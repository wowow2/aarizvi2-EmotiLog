package com.example.aarizvi2_emotilog;

import android.util.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProcessAllEmoticons {
    private ArrayList<Emoticon> allEmoticons = new ArrayList<>();
    ProcessAllEmoticons(ArrayList<Emoticon> allEmoticons) {
        this.allEmoticons = allEmoticons;
    }

    public ArrayList<Pair<String, String>> processEmoticons() {
        ArrayList<Pair<String, String>> emoticonData = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        for (int i = 0; i < allEmoticons.size(); i++) {
            Emoticon currentEmoticon = allEmoticons.get(i);
            String formattedDate = formatter.format(currentEmoticon.getDate());
            Pair<String, String> newPair = new Pair<>(currentEmoticon.getEmotion(), formattedDate);
            emoticonData.add(newPair);
        }
        return emoticonData;
    }

    public ArrayList<String> getEmoticonTimes() {
        ArrayList<String> emoticonTimes = new ArrayList<>();
        for (int i = 0; i < allEmoticons.size(); i++) {
            Emoticon currentEmoticon = allEmoticons.get(i);
            emoticonTimes.add(currentEmoticon.getTime());
        }
        return emoticonTimes;
    }

    public ArrayList<String> formatEmoticons() {
        ArrayList<Pair<String, String>> emoticonPairs = processEmoticons();
        ArrayList<String> emoticonTimes = getEmoticonTimes();
        ArrayList<String> emoticonStrings = new ArrayList<>();
        for (int i = 0; i < emoticonPairs.size(); i++) {
            // Format the output string for each pair to be user-friendly
            emoticonStrings.add(emoticonPairs.get(i).first + " on " +
                    emoticonPairs.get(i).second + " at " + emoticonTimes.get(i));
        }

        return emoticonStrings;
    }
}
