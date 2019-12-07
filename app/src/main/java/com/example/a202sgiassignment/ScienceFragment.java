package com.example.a202sgiassignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScienceFragment extends Fragment {
    String[] scTitle;
    String[] scLink;

    ScienceListAdapter mAdapter;
    RecyclerView mRV;

    public ScienceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_science, container, false);

        rootView.setBackgroundColor(getResources().getColor(R.color.sc_bgColour));

        mRV = (RecyclerView) rootView.findViewById(R.id.rv_sc);
        mRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        scTitle = this.getResources().getStringArray(R.array.scTitle);
        scLink = this.getResources().getStringArray(R.array.scLink);

        mAdapter = new ScienceListAdapter(getActivity(), scTitle, scLink);
        mRV.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

}
