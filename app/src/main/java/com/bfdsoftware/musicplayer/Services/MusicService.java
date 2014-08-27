package com.bfdsoftware.musicplayer.Services;

/**
 * Created by BillAdmin on 8/25/2014.
 */
public interface MusicService {
    public void SetServer(String url, long port);
    public boolean Authenticate(String username,String password);
}
