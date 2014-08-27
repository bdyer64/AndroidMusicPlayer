package com.bfdsoftware.musicplayer.Services.subsonic;


import android.app.Application;

import com.bfdsoftware.musicplayer.Services.MusicService;
import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;

/**
 * Created by BillAdmin on 8/25/2014.
 */
public class SubsonicMusicService extends SpringAndroidSpiceService implements MusicService {

    String _server = null;
    long _port = 80;

    public void SetServer(String url,long port) {
        _server = url;
        _port = port;
    }

    public boolean Authenticate(String username,String password) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }


/**
 * SpringAndroidSpiceServiceInterface
 */

    public CacheManager createCacheManager(Application application)
    {
        CacheManager cacheManager = new CacheManager();

    }

}
