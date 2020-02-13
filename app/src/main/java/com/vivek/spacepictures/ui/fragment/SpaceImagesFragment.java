package com.vivek.spacepictures.ui.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vivek.spacepictures.R;
import com.vivek.spacepictures.databinding.FragmentSpaceImagesBinding;
import com.vivek.spacepictures.ui.adapter.ImagesAdapter;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(null);
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
        LinearLayoutManager manager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
             manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        }else{
             manager = new LinearLayoutManager(getActivity());
        }
        binding.recyclerViewImages.setLayoutManager(manager);
        binding.recyclerViewImages.setHasFixedSize(true);
        binding.recyclerViewImages.setAdapter(adapter);

    }
}
