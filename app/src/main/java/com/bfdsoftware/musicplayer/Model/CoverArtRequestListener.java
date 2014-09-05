package com.bfdsoftware.musicplayer.Model;

import android.graphics.Bitmap;

/**
 * Created by BillAdmin on 9/4/2014.
 */
public interface CoverArtRequestListener {
    public void onRequestFailure(Exception e);
    public void onRequestSuccess(Bitmap ccverArt);
}
