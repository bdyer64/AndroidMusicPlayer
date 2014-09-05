package com.bfdsoftware.musicplayer.Model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by BillAdmin on 8/28/2014.
 */
public class Artist {
    private String id;
    private String name;

    public Artist()
    {
        id = null;
        name = null;
    }

    public Artist(String name,String id)
    {
        this.name = name;
        this.id = id;
    }

    @JsonProperty("id")
    public String getId() { return id; }
    @JsonProperty("name")
    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }
}
