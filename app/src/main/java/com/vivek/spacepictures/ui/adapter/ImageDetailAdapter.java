package com.vivek.spacepictures.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.spacepictures.R;
import com.vivek.spacepictures.databinding.ImageDetailRowBinding;
import com.vivek.spacepictures.model.Picture;

public class ImageDetailAdapter extends PagedListAdapter<Picture, ImageDetailAdapter.ImagesViewHolder> {

    private static final String TAG = ImageDetailAdapter.class.getSimpleName();
    final Context context;

    public ImageDetailAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ImageDetailAdapter.ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageDetailRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.image_detail_row, parent, false);
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
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        ImageDetailRowBinding binding;

        private ImagesViewHolder(ImageDetailRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Picture picture) {
            binding.setPicture(picture);
            binding.executePendingBindings();
        }

    }

}