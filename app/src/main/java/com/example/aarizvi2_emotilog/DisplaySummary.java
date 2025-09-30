package com.example.aarizvi2_emotilog;

import android.util.Pair;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aarizvi2_emotilog.databinding.FragmentSecondBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/*
DisplaySummary computes the summary statistics for a target date (the total count and frequency),
the class has the full list of all emoticons logged, and the getSummaryForDate method takes in a date
and iterates over log to identify emoticons on that date. It returns a hash map of each emoticon mapped
to a count and frequency.
*/
public class DisplaySummary {

    private ArrayList<Pair<String, String>> allEmoticons;

    public DisplaySummary(ArrayList<Pair<String, String>> allEmoticons) {
        this.allEmoticons = allEmoticons;
    }

    public HashMap<String, String> getSummaryForDate(String targetDate) {
        HashMap<String, Integer> counts = new HashMap<>();
        int totalForDate = 0;

        // Count emoticons for the target date
        for (Pair<String, String> emoticonEntry : allEmoticons) {
            String emoticon = emoticonEntry.first; // The emoticon is the first element of the pair
            String date = emoticonEntry.second; // The date is the second element

            if (date.equals(targetDate)) {
                totalForDate++;
                counts.put(emoticon, counts.getOrDefault(emoticon, 0) + 1);
            }
        }

        // Calculate frequencies and format the results
        HashMap<String, String> summary = new HashMap<>();
        if (totalForDate > 0) {
            for (String emoticon : counts.keySet()) {
                int count = counts.get(emoticon);
                double frequency = (double) count / totalForDate;
                summary.put(emoticon, count + " (" + String.format(Locale.getDefault(), "%.1f", frequency * 100) + "%)");
            }
            summary.put("Total Count", Integer.toString(totalForDate));
        }
        else {
            summary.put("Total Count", Integer.toString(0));
        }
        return summary;
    }
}
