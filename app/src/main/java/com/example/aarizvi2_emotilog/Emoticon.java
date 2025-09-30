package com.example.aarizvi2_emotilog;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
Represents an emoticon, it holds the kind of emoticon, the date and timestamp. The timestamp is
derived from the date.
*/
public class Emoticon implements Serializable {
    private String emotion;
    private Date date;
    private String timestring;

    public Emoticon(String emotion, Date date) {
        this.emotion = emotion;
        this.date = date;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        this.timestring = timeFormat.format(date);
    }

    public String getEmotion() {
        return emotion;
    }

    public Date getDate() {
        return date;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {return timestring;}
}
