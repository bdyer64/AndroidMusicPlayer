package com.bfdsoftware.musicplayer.Services.subsonic;

/**
 * Created by BillAdmin on 8/27/2014.
 */
public class SubsonicResponse
{
    private String status;
    private String version;
    private String xmlns;
    private Indexes indexes;
    private Directory directory;

    public String getStatus()
    {
        return this.status;
    }

    public String getVersion()
    {
        return this.version;
    }

    public String getXmlns()
    {
        return this.xmlns;
    }

    public Indexes getIndexes() { return this.indexes;}

    public Directory getDirectory() { return this.directory; }

   /*public MusicFolders getMusicFolders()
    {
        return musicFolders;
    }*/
}
