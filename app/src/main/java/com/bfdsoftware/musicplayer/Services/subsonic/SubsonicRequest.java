package com.bfdsoftware.musicplayer.Services.subsonic;

import com.bfdsoftware.musicplayer.MainActivity;
import com.octo.android.robospice.request.simple.BitmapRequest;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import java.io.File;

/**
 * Created by BillAdmin on 8/27/2014.
 */
public class SubsonicRequest extends SpringAndroidSpiceRequest<SubsonicJsonResponse> {
    public SubsonicRequest() {
        super(SubsonicJsonResponse.class);
    }

    private String url = "http://www.chezdyer.net:4041/rest/getIndexes.view?u=admin&p=beagle&v=1.10.2&f=json&c=bfd";

    public static BitmapRequest getAlbumArtRequest(String id)
    {
        // TODO - This is a super hack, figure out a way to fix it
        File cacheFile = new File(MainActivity.getMyApplication().getCacheDir(),id );
        String url = String.format("http://www.chezdyer.net:4041/rest/getCoverArt.view?id=%s&u=admin&p=beagle&v=1.10.2&f=json&c=bfd",id);
        return new BitmapRequest(url, cacheFile );
    }

    public static SubsonicRequest getIndexInstance() {
        SubsonicRequest req = new SubsonicRequest();
        req.url = "http://www.chezdyer.net:4041/rest/getIndexes.view?u=admin&p=beagle&v=1.10.2&f=json&c=bfd";
        return req;
    }

    public static SubsonicRequest getAlbumListInstance(String artist,String id)
    {
        SubsonicRequest req = new SubsonicRequest();
        req.url = String.format("http://www.chezdyer.net:4041/rest/getMusicDirectory.view?id=%s&u=admin&p=beagle&v=1.10.2&f=json&c=bfd",id);
        return req;
    }

    @Override
    public SubsonicJsonResponse loadDataFromNetwork() throws Exception
    {
        return getRestTemplate().getForObject(url,SubsonicJsonResponse.class);
    }

    public String createCacheKey()
    {
        return Integer.toString(url.hashCode());
    }
}
