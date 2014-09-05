package com.bfdsoftware.musicplayer.Model;

import java.util.List;

/**
 * Created by BillAdmin on 8/28/2014.
 */
public interface AlbumRequestListener {
    public void onRequestFailure(Exception e);
    public void onRequestSuccess(List<Album> response);
}
