package com.bfdsoftware.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.bfdsoftware.musicplayer.Model.Album;
import com.bfdsoftware.musicplayer.Model.AlbumRequestListener;
import com.bfdsoftware.musicplayer.Model.Artist;
import com.bfdsoftware.musicplayer.Model.MusicCollection;
import com.bfdsoftware.musicplayer.Services.subsonic.SubsonicMusicService;
import com.bfdsoftware.musicplayer.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link AlbumRequestListener}
 * interface.
 */
public class AlbumListFragment extends Fragment implements AbsListView.OnItemClickListener,AlbumRequestListener {

    private static final String ARG_NAME = "artist";
    private static final String ARG_ID = "id";

    private String mName;
    private String mId;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;
    private Artist mArtist;

    public static AlbumListFragment newInstance(Artist artist) {
        AlbumListFragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, artist.getName());
        args.putString(ARG_ID, artist.getId());
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlbumListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
            mId = getArguments().getString(ARG_ID);
        }


        MusicCollection.getInstance().GetAlbums(new Artist(mName, mId),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albumlist, container, false);

        // Set the adapter
        if (mAdapter != null) {
            mListView = (AbsListView) view.findViewById(android.R.id.list);
            mListView.setAdapter(mAdapter);
        }

        // Set OnItemClickListener so we can be notified on item clicks
        //mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
      /*  try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onRequestFailure(Exception e) {

    }

    @Override
    public void onRequestSuccess(List<Album> response)
    {
        mAdapter = new AlbumListAdapter(getActivity(),R.layout.album_row, response);

        mListView = (AbsListView) getView().findViewById(android.R.id.list);
        if (mAdapter != null) {
            mListView.setAdapter(mAdapter);
        }
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
