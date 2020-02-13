package com.vivek.spacepictures.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.vivek.spacepictures.R;
import com.vivek.spacepictures.databinding.FragmentImageDetailBinding;
import com.vivek.spacepictures.model.Picture;
import com.vivek.spacepictures.ui.adapter.ImageDetailAdapter;
import com.vivek.spacepictures.viewmodel.ImageDetailViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailFragment extends Fragment  {

    private static final String TAG = ImageDetailFragment.class.getSimpleName();
    private ImageDetailViewModel viewModel;
    private FragmentImageDetailBinding binding;
    private View rootView;
    private ImageDetailAdapter adapter;
    private static int firstVisibleInListview;
    private List<Picture> pictures;

    public ImageDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            firstVisibleInListview = getArguments().getInt("clickedPos");
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_detail, container, false);
        rootView = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(ImageDetailViewModel.class);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        setObservers();
    }

    private void setObservers() {
        viewModel.getPictures().removeObservers(getViewLifecycleOwner());
        viewModel.getPictures().observe(getViewLifecycleOwner(), pictures -> {
            this.pictures = pictures;
            adapter.submitList(pictures);
            setTitle(firstVisibleInListview);
            binding.recyclerViewDetail.getLayoutManager().scrollToPosition(firstVisibleInListview);
        });

    }

    private void initRecyclerView() {
        adapter = new ImageDetailAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.recyclerViewDetail.setLayoutManager(linearLayoutManager);
        binding.recyclerViewDetail.setHasFixedSize(true);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerViewDetail);
        binding.recyclerViewDetail.setAdapter(adapter);
        binding.recyclerViewDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentVisible = linearLayoutManager.findFirstVisibleItemPosition();
                if (currentVisible != firstVisibleInListview) {
                    setTitle(currentVisible);
                }
                firstVisibleInListview = currentVisible;
            }
        });

    }

    private void setTitle(int visibleItem) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(pictures.get(visibleItem).getTitle());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(pictures.get(visibleItem).getDate());
    }

}
