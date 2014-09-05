package com.bfdsoftware.musicplayer.Services.subsonic;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by BillAdmin on 8/27/2014.
 */
public class SubsonicJsonResponse
{
    private SubsonicResponse subsonic_response;

    @JsonProperty("subsonic-response")
    public SubsonicResponse getSubsonic_response()
        {
            return this.subsonic_response;
        }
}
