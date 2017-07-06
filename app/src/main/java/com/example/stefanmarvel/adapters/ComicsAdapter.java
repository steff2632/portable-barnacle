package com.example.stefanmarvel.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stefanmarvel.R;
import com.example.stefanmarvel.models.Comics;
import com.example.stefanmarvel.models.Images;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stefanmay on 06/07/2017.
 */

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {

    private Comics[] comics;

    public ComicsAdapter() {
        comics = new Comics[0];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTitle.setText(comics[position].title);
        String thumbUrl = Images.getPathAndExt(comics[position].thumbnail);
        if(thumbUrl != null)
            Picasso.with(holder.imgThumb.getContext()).load(thumbUrl).into(holder.imgThumb);
        else
            Picasso.with(holder.imgThumb.getContext()).load(R.mipmap.ic_launcher).into(holder.imgThumb);
    }

    @Override
    public int getItemCount() {
        return comics.length;
    }

    public void setComics(Comics[] comics) {
        this.comics = comics;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView txtTitle;

        @BindView(R.id.img_thumb)
        ImageView imgThumb;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
