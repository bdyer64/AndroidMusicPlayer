package com.bfdsoftware.musicplayer.Services.subsonic;


import android.app.Application;

import com.bfdsoftware.musicplayer.Services.MusicService;
import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson.JacksonObjectPersisterFactory;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException
    {
        CacheManager cacheManager = new CacheManager();
        JacksonObjectPersisterFactory jacksonObjectPersisterFactory = new JacksonObjectPersisterFactory(application);
        cacheManager.addPersister(jacksonObjectPersisterFactory);
        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();

        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final List<HttpMessageConverter< ? >> listHttpMessageConverters = restTemplate.getMessageConverters();
        listHttpMessageConverters.add(jsonConverter);
        listHttpMessageConverters.add(formHttpMessageConverter);
        restTemplate.setMessageConverters(listHttpMessageConverters);
        return restTemplate;
    }
}
