package com.bfdsoftware.musicplayer.Services.subsonic;

import com.bfdsoftware.musicplayer.Model.Artist;

/**
 * Created by BillAdmin on 8/28/2014.
 */
public class Index {
    String name;

    // This is using the Model artist which will work for now,
    // later we should separate subsonic artist from Model artist
    Artist[] artist;

    public String getName() { return name;}
    public Artist[] getArtist() {return artist; }
}
