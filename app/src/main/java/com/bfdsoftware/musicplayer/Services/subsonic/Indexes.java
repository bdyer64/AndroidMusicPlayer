package com.bfdsoftware.musicplayer.Services.subsonic;

/**
 * Created by BillAdmin on 8/28/2014.
 */
public class Indexes {
    private Index[] index;
    private String ignoredArticles;
    private long lastModified;

    public Index[] getIndex() { return index;}
    public String getIgnoredArticles() {return ignoredArticles;}
    public long getLastModified() { return lastModified; }
}
