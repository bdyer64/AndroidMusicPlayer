package com.bfdsoftware.musicplayer.Model;

import java.util.List;

/**
 * Created by BillAdmin on 8/28/2014.
 */
public interface ArtistRequestListener {
    public void onRequestFailure(Exception e);
    public void onRequestSuccess(List<Artist> response);
}
