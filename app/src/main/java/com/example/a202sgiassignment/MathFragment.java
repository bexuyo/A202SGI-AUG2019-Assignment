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
public class MathFragment extends Fragment {
    String[] mathTitle;
    String[] mathLink;

    MathListAdapter mAdapter;
    RecyclerView mRV;

    public MathFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_math, container, false);

        rootView.setBackgroundColor(getResources().getColor(R.color.math_bgColour));

        mRV = (RecyclerView) rootView.findViewById(R.id.rv_math);
        mRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        mathTitle = this.getResources().getStringArray(R.array.mathTitle);
        mathLink = this.getResources().getStringArray(R.array.mathLink);

        mAdapter = new MathListAdapter(getActivity(), mathTitle, mathLink);
        mRV.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

}
