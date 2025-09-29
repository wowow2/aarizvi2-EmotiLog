package com.example.aarizvi2_emotilog;

import java.io.Serializable;
import java.util.Date;

public class Emoticon implements Serializable {
    private String emotion;
    private Date date;

    public Emoticon(String emotion, Date date) {
        this.emotion = emotion;
        this.date = date;
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
}
