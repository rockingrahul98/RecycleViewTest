package com.dmax.recycleviewtest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rockingrahul98 on 08-12-2015.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private ArrayList<String> quoteItems;

    public RecycleAdapter(ArrayList<QuoteItem> quoteItems) {
        this.quoteItems = new ArrayList<String>();
        this.quoteItems = quoteItems.get(0).quotes;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtext, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.im.setBackgroundResource(R.mipmap.ic_launcher);
        holder.name.setText("My name is" + position);
        holder.age.setText("My age is" + position);

    }

    @Override
    public int getItemCount() {
        return quoteItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView im;
        public TextView name, age;

        public ViewHolder(CardView cv) {
            super(cv);
            im = (ImageView) cv.findViewById(R.id.im);
            name = (TextView) cv.findViewById(R.id.name);
            age = (TextView) cv.findViewById(R.id.age);
        }
    }
}
