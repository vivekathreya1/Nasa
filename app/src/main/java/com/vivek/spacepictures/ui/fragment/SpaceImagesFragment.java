package com.vivek.spacepictures.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivek.spacepictures.R;
import com.vivek.spacepictures.databinding.FragmentSpaceImagesBinding;
import com.vivek.spacepictures.model.Picture;
import com.vivek.spacepictures.ui.adapter.ImagesAdapter;
import com.vivek.spacepictures.utils.VerticalSpacingItemDecoration;
import com.vivek.spacepictures.viewmodel.SpaceImagesViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpaceImagesFragment extends Fragment {

    private static final String TAG = "SpaceImagesFragment";
    private SpaceImagesViewModel viewModel;
    private FragmentSpaceImagesBinding binding;
    private View rootView;
    private ImagesAdapter adapter;


    public SpaceImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_space_images, container, false);
        rootView = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(SpaceImagesViewModel.class);
        binding.setViewModel(viewModel);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        setObservers();
    }

    private void setObservers(){
      viewModel.getPictures().removeObservers(getViewLifecycleOwner());
      viewModel.getPictures().observe(getViewLifecycleOwner(), pictures -> {
          adapter.submitList(pictures);
      });

    }

    private void initRecyclerView(){
        adapter = new ImagesAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerViewImages.setLayoutManager(linearLayoutManager);
        binding.recyclerViewImages.setHasFixedSize(true);
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        binding.recyclerViewImages.addItemDecoration(itemDecoration);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerViewImages);
        binding.recyclerViewImages.setAdapter(adapter);
    }

}
