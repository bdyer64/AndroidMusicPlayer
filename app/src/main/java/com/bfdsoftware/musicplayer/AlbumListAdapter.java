package com.bfdsoftware.musicplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bfdsoftware.musicplayer.Model.Album;
import com.bfdsoftware.musicplayer.Model.CoverArtRequestListener;
import com.bfdsoftware.musicplayer.Services.MusicService;
import com.bfdsoftware.musicplayer.Services.subsonic.SubsonicMusicService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BillAdmin on 9/4/2014.
 */
public class AlbumListAdapter extends ArrayAdapter<Album> {

    private Context context;

    public AlbumListAdapter(Context context, int resource, List<Album> items) {
        super(context, resource, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.album_row, null);
            holder = new ViewHolder();
            view.setTag(holder);
            holder.mAlbumArt = (ImageView)view.findViewById(R.id.album_art);
            holder.mAlbumArtist = (TextView)view.findViewById(R.id.album_artist);
            holder.mAlbumName = (TextView)view.findViewById(R.id.album);
        }
        else
        {
            holder = (ViewHolder)view.getTag();
        }

        holder.mAlbum = getItem(position);
        holder.setViews();
        return view;
    }

    private class ViewHolder implements Album.AlbumChangedListener
    {
        public Album mAlbum;
        public TextView mAlbumName;
        public TextView mAlbumArtist;
        public ImageView mAlbumArt;
        private int imageHeight;
        private int imageWidth;

        public ViewHolder()
        {
            Drawable blank = MainActivity.getMyApplication().getResources().getDrawable(R.drawable.blankart);
            Bitmap blankBitmap = ((BitmapDrawable)blank).getBitmap();
            imageHeight = blankBitmap.getHeight();
            imageWidth = blankBitmap.getWidth();
        }

        public void setViews()
        {
            Bitmap scaled = Bitmap.createScaledBitmap(mAlbum.getCoverArtBitmap(),imageWidth,imageWidth,false);
            mAlbumArt.setImageBitmap(scaled);

            mAlbumName.setText(mAlbum.getAlbum());
            mAlbumArtist.setText(mAlbum.getArtist());
            mAlbum.SetAlbumChangedListener(this);
        }

        public void AlbumArtChanged(Album album)
        {
            if (album == mAlbum)
            {
                setViews();
            }
        }
    }
}