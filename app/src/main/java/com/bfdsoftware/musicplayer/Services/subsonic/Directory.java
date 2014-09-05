package com.bfdsoftware.musicplayer.Services.subsonic;

import com.bfdsoftware.musicplayer.Model.Album;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by BillAdmin on 9/3/2014.
 */
public class Directory {

    @JsonProperty("child")
    private Album[] child;
    private String id;
    private String name;

    @JsonProperty("child")
    public Album[] getAlbums()
    {
        return child;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
