package com.gmail.patrykheciak.movies2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.gmail.patrykheciak.lab2.movies.R;
import com.squareup.picasso.Picasso;


public class ImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;

    private String[] imageUrls;

    public ImageListAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.gridview_image, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gridview_image, parent, false);
        }

        Picasso
                .with(context)
                .load(imageUrls[position])
                .fit()
                .centerCrop()
                .into((ImageView) convertView);

        return convertView;
    }
}
