package com.bfdsoftware.musicplayer.api.subsonic;

import android.test.AndroidTestCase;

import com.bfdsoftware.musicplayer.Services.subsonic.SubsonicMusicService;

import junit.framework.Assert;


/**
 * Created by BFD on 8/22/2014.
 */

public class SubsonicAPITests extends AndroidTestCase {

    public void testSomething() throws Throwable {
        Assert.assertTrue(1 + 1 == 2);
    }

    public void testAuthenticateMusicService() {
        SubsonicMusicService sut = new SubsonicMusicService();

        String username = "admin";
        String password = "beagle";

        sut.SetServer("http://chezdyer.net",4041);

        Assert.assertTrue(sut.Authenticate(username, password));
    }
}
