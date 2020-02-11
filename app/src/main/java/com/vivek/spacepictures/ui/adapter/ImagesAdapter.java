package com.vivek.spacepictures.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.navigation.NavigationView;
import com.vivek.spacepictures.R;
import com.vivek.spacepictures.databinding.CardViewRowPicsBinding;
import com.vivek.spacepictures.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends PagedListAdapter<Picture, ImagesAdapter.ImagesViewHolder> {

    private List<Picture> picturesList = new ArrayList<>();
    private Context context;
    private static final String TAG = "ImagesAdapter";

    public ImagesAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardViewRowPicsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_view_row_pics, parent, false);
        return new ImagesViewHolder(binding);
    }

    public Picture getPicture(int position) {
        return getItem(position);
    }

    private static final DiffUtil.ItemCallback<Picture> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Picture>() {
                @Override
                public boolean areItemsTheSame(@NonNull Picture oldItem, @NonNull Picture newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Picture oldItem, @NonNull Picture newItem) {
                    return oldItem.getId() == newItem.getId() && oldItem.getTitle().equalsIgnoreCase(newItem.getTitle());
                }
            };

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        holder.bind(getPicture(position));
        holder.binding.setPicture(getPicture(position));
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        CardViewRowPicsBinding binding;

        private ImagesViewHolder(CardViewRowPicsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.picsCard.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putInt("id", binding.getPicture().getId());
                Navigation.findNavController(view).navigate(R.id.imagesListToDetailFragment, bundle);
            });
        }

        private void bind(Picture picture) {
            binding.picTitleTv.setText(picture.getTitle());
            loadImage(picture);
        }

        private void loadImage(Picture picture) {
            Glide.with(context)
                    .load(picture.getUrl())
                    .placeholder(R.drawable.placeholder)
                    .fitCenter()
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.picIv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.picIv.setScaleType(ImageView.ScaleType.FIT_XY);
                            return false;
                        }
                    })
                    .into(binding.picIv);
        }
    }
}
