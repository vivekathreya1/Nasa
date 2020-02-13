package com.vivek.spacepictures.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.vivek.spacepictures.R;

public class BindingAdapters {

    @BindingAdapter("nasaImage")
    public static void loadImage(ImageView view, String imageUrl) {

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(view.getContext());
        circularProgressDrawable.setStrokeWidth( 15f);
        circularProgressDrawable.setCenterRadius(40f);
        circularProgressDrawable.start();
        Glide.with(view.getContext())
                .load(imageUrl)
                .error(R.drawable.ic_warning_black_24dp).dontTransform()
                .placeholder(circularProgressDrawable)
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        circularProgressDrawable.stop();
                        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        circularProgressDrawable.stop();
                        return false;
                    }
                })
                .into(view);
    }
}
