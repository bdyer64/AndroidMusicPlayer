package com.bfdsoftware.musicplayer.Model;

import com.bfdsoftware.musicplayer.Services.subsonic.SubsonicMusicService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BillAdmin on 8/28/2014.
 */
public class MusicCollection
{
    private static MusicCollection _instance;
    public static MusicCollection getInstance()
    {
        if (_instance == null)
        {
            _instance = new MusicCollection();
        }

        return _instance;
    }
    public List<String> _artists = new ArrayList<String>();

    public void GetArtists(ArtistRequestListener listener)
    {
        // no caching in the model for now, let's just get this to work
        SubsonicMusicService.getInstance().GetArtists(listener);
    }

    public void GetAlbums(Artist artist,AlbumRequestListener listener)
    {
        SubsonicMusicService.getInstance().GetAlbums(artist, listener);
    }
}
