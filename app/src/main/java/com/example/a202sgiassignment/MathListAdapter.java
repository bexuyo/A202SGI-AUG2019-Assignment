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

public class MathListAdapter extends RecyclerView.Adapter<MathListAdapter.ViewHolder> {

    private String[] mMathTitle;
    private String[] mMathLink;
    private LayoutInflater mInflater;

    public MathListAdapter(Context c, String[] mathTitle, String[] mathLink)
    {
        mInflater = LayoutInflater.from(c);
        mMathTitle = mathTitle;
        mMathLink = mathLink;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView mTitle;
        public final TextView mLink;
        final MathListAdapter mAdapter;

        ViewHolder(View viewItem, MathListAdapter adapter)
        {
            super(viewItem);
            mTitle = (TextView) viewItem.findViewById(R.id.math_title);
            mLink = (TextView) viewItem.findViewById(R.id.math_link);
            mAdapter = adapter;

            viewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String currentMathLink = mMathLink[position];

            Uri video = Uri.parse(currentMathLink);
            Intent i = new Intent(Intent.ACTION_VIEW, video);

            if(i.resolveActivity(v.getContext().getPackageManager()) != null)
                v.getContext().startActivity(i);
            else
                Log.d("MathImplicit", "Can\'t handle intent");

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.mathlist_item, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String currentTitle = mMathTitle[i];
        String currentLink = mMathLink[i];

        viewHolder.mTitle.setText(currentTitle);
        viewHolder.mLink.setText(currentLink);
    }

    @Override
    public int getItemCount() {
        return mMathTitle.length;
    }
}
