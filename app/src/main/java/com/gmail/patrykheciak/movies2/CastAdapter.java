package com.gmail.patrykheciak.movies2;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.patrykheciak.lab2.movies.R;
import com.gmail.patrykheciak.movies2.entities.Actor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder> {
    private List<Actor> castList;
    private Context ctx;

    public CastAdapter(List<Actor> castList, Context ctx) {
        this.castList = castList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Actor movie = castList.get(position);
        holder.name.setText(movie.getName() + " " + movie.getSurname());
        Picasso.with(ctx).load(movie.getPhotoUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .transform(new CircleTransform())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (castList == null)
            return 0;
        else
            return castList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cast_name);
            image = (ImageView) view.findViewById(R.id.cast_img);
        }
    }
}
