package com.vivek.spacepictures.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.spacepictures.R;
import com.vivek.spacepictures.databinding.ImageRowBinding;
import com.vivek.spacepictures.model.Picture;

public class ImagesAdapter extends PagedListAdapter<Picture, ImagesAdapter.ImagesViewHolder> {

    private static final String TAG = ImagesAdapter.class.getSimpleName();

    public ImagesAdapter(Context context) {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.image_row, parent, false);
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
        ImageRowBinding binding;

        private ImagesViewHolder(ImageRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.picsCard.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putInt("clickedPos", this.getAdapterPosition());
                Navigation.findNavController(view).navigate(R.id.imagesListToDetailFragment, bundle);
            });
        }

        private void bind(Picture picture) {
            binding.setPicture(picture);
            binding.executePendingBindings();
        }

    }
}
