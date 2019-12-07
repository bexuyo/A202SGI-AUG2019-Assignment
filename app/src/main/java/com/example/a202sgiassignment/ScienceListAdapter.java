package com.example.a202sgiassignment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScienceListAdapter extends RecyclerView.Adapter<ScienceListAdapter.ViewHolder> {
    private String[] mScTitle;
    private String[] mScLink;
    private LayoutInflater mInflater;

    public ScienceListAdapter(Context c, String[] scTitle, String[] scList)
    {
        mInflater = LayoutInflater.from(c);
        mScTitle = scTitle;
        mScLink = scList;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView mTitle;
        public final TextView mLink;
        final ScienceListAdapter mAdapter;

        ViewHolder(View viewItem, ScienceListAdapter adapter)
        {
            super(viewItem);
            mTitle = (TextView) viewItem.findViewById(R.id.sc_title);
            mLink = (TextView) viewItem.findViewById(R.id.sc_link);
            mAdapter = adapter;

            viewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String currentScLink = mScLink[position];

            Uri video = Uri.parse(currentScLink);
            Intent i = new Intent(Intent.ACTION_VIEW, video);

            if(i.resolveActivity(v.getContext().getPackageManager()) != null)
                v.getContext().startActivity(i);
            else
                Log.d("ScienceIntent", "Can\'t handle intent");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.sciencelist_item, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String currentTitle = mScTitle[i];
        String currentLink = mScLink[i];

        viewHolder.mTitle.setText(currentTitle);
        viewHolder.mLink.setText(currentLink);
    }

    @Override
    public int getItemCount() {
        return mScTitle.length;
    }
}
