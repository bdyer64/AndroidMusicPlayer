package com.bfdsoftware.musicplayer.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bfdsoftware.musicplayer.MainActivity;
import com.bfdsoftware.musicplayer.R;
import com.bfdsoftware.musicplayer.Services.subsonic.SubsonicMusicService;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by BillAdmin on 9/3/2014.
 */
public class Album implements CoverArtRequestListener {

    private String genre;
    private String id;
    private String title;
    private String created;
    private String album;
    private String parent;
    private String year;
    private String isDir;
    private String artist;
    private String coverArt;

    @JsonIgnore
    private Bitmap coverArtBitmap = null;
    @JsonIgnore
    private boolean coverArtLoaded = false;
    @JsonIgnore
    private AlbumChangedListener mListener;

    public String getGenre() {
        return genre;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated() {
        return created;
    }

    public String getParent() {
        return parent;
    }

    public String getAlbum() { return album; }

    public String getYear() {
        return year;
    }

    public String getIsDir() {
        return isDir;
    }

    public String getArtist() {
        return artist;
    }

    public String getCoverArt() {
        return coverArt;
    }

    @JsonIgnore
    public void SetAlbumChangedListener(AlbumChangedListener listener)
    {
        mListener = listener;
    }

    @JsonIgnore
    public Bitmap getCoverArtBitmap()
    {
        if (coverArtBitmap == null)
        {
            Drawable blank = MainActivity.getMyApplication().getResources().getDrawable(R.drawable.blankart);
            coverArtBitmap = ((BitmapDrawable)blank).getBitmap();
        }
        if (!coverArtLoaded)
        {
            SubsonicMusicService.getInstance().GetCoverArt(this,this);
        }

        return coverArtBitmap;
    }

    @Override
    public String toString() {
        return album;
    }

    public interface AlbumChangedListener
    {
        public void AlbumArtChanged(Album album);
    }

    @Override
    public void onRequestFailure(Exception e) {

    }

    @Override
    public void onRequestSuccess(Bitmap coverArt) {
        coverArtBitmap = coverArt;
        coverArtLoaded = true;
        if (mListener != null)
        {
            mListener.AlbumArtChanged(this);
        }
    }
}
