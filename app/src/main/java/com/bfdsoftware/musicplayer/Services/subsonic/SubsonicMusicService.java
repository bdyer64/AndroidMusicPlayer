package com.bfdsoftware.musicplayer.Services.subsonic;


import android.app.Application;
import android.graphics.Bitmap;

import com.bfdsoftware.musicplayer.Model.Album;
import com.bfdsoftware.musicplayer.Model.AlbumRequestListener;
import com.bfdsoftware.musicplayer.Model.Artist;
import com.bfdsoftware.musicplayer.Model.ArtistRequestListener;

import com.bfdsoftware.musicplayer.Model.CoverArtRequestListener;
import com.bfdsoftware.musicplayer.Services.MusicService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.binary.InFileBitmapObjectPersister;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.persistence.springandroid.json.jackson.JacksonObjectPersisterFactory;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.BitmapRequest;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import org.codehaus.jackson.map.DeserializationConfig;

import java.util.Arrays;
import java.util.List;

/**
 * Created by BillAdmin on 8/25/2014.
 */
public class SubsonicMusicService extends SpringAndroidSpiceService implements MusicService {

    private static SubsonicMusicService _instance = null;

    String _server = null;
    long _port = 80;
    //only one outstanding request at a time, if you make a second things will go bad
    //this can be fixed later

    private SpiceManager _subsonicManager;

    public static SubsonicMusicService getInstance()
    {
        if (_instance == null)
        {
            _instance = new SubsonicMusicService();
        }

        return _instance;
    }

    public void SetServer(String url,long port) {
        _server = url;
        _port = port;
    }

    public void SetSpiceManager(SpiceManager spiceManager)
    {
        _subsonicManager = spiceManager;
    }

    public boolean Authenticate(String username,String password) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void GetArtists(ArtistRequestListener listener)
    {
        // put this in a common function eventually
        SubsonicRequest request = SubsonicRequest.getIndexInstance();
        String lastRequestCacheKey = request.createCacheKey();

        _subsonicManager.execute(request,lastRequestCacheKey, DurationInMillis.ONE_MINUTE,
                    new SubsonicRequestListener(listener));
    }


    public void GetAlbums(Artist artist, AlbumRequestListener listener)
    {
        // put this in a common function eventually
        SubsonicRequest request = SubsonicRequest.getAlbumListInstance(artist.getName(),artist.getId());
        String lastRequestCacheKey = request.createCacheKey();

        _subsonicManager.execute(request,lastRequestCacheKey, DurationInMillis.ONE_MINUTE,
                new SubsonicRequestListener(listener));
    }

    public void GetCoverArt(Album album,CoverArtRequestListener listener)
    {
        BitmapRequest request = SubsonicRequest.getAlbumArtRequest((album.getCoverArt()));
        //TODO add a real cache key for this request
        String lastRequestCacheKey = album.getCoverArt();

        _subsonicManager.execute(request,lastRequestCacheKey, DurationInMillis.ONE_MINUTE,
                new SubsonicImageListener(listener));
    }
/**
 * SpringAndroidSpiceServiceInterface
 */

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException
    {
        CacheManager cacheManager = new CacheManager();
        JacksonObjectPersisterFactory jacksonObjectPersisterFactory = new JacksonObjectPersisterFactory(application);
        cacheManager.addPersister(jacksonObjectPersisterFactory);
        InFileBitmapObjectPersister inFileBitmapObjectPersister = new InFileBitmapObjectPersister(application);
        cacheManager.addPersister(inFileBitmapObjectPersister);
        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();

        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
        jsonConverter.getObjectMapper().configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final List<HttpMessageConverter< ? >> listHttpMessageConverters = restTemplate.getMessageConverters();
        listHttpMessageConverters.add(jsonConverter);
        listHttpMessageConverters.add(formHttpMessageConverter);
        listHttpMessageConverters.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(listHttpMessageConverters);
        return restTemplate;
    }

    @Override
    public int getThreadCount() {
        return 10;
    }

    private class SubsonicImageListener implements RequestListener<Bitmap>
    {
        CoverArtRequestListener _listener;
        SubsonicImageListener(CoverArtRequestListener listener)
        {
            _listener = listener;
        }

        @Override
        public void onRequestFailure(SpiceException e)
        {
            int y = 10;
        }

        @Override
        public void onRequestSuccess(Bitmap response)
        {
            _listener.onRequestSuccess(response);
            _listener = null;
        }
    }

    private class SubsonicRequestListener implements RequestListener<SubsonicJsonResponse>
    {
        private ArtistRequestListener _artistListener;
        private AlbumRequestListener _albumListener;

        public SubsonicRequestListener(ArtistRequestListener artistListener)
        {
            _artistListener = artistListener;
        }

        public SubsonicRequestListener(AlbumRequestListener albumListener)
        {
            _albumListener = albumListener;
        }

        @Override
        public void onRequestFailure(SpiceException e)
        {
            int y = 10;
        }

        @Override
        public void onRequestSuccess(SubsonicJsonResponse response)
        {
            if (_artistListener != null)
            {
                Index i = response.getSubsonic_response().getIndexes().getIndex()[18];
                _artistListener.onRequestSuccess(Arrays.asList(i.getArtist()));
                _artistListener = null;
            }

            if (_albumListener != null)
            {
                _albumListener.onRequestSuccess(Arrays.asList(response.getSubsonic_response().getDirectory().getAlbums()));
                _albumListener = null;
            }
        }
    }
}
