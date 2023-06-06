package com.alekhin.museys.fragments.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alekhin.museys.databinding.FragmentMuseumListBinding;
import com.alekhin.museys.room.MuseumViewModel;

public class MuseumListFragment extends Fragment {
    private FragmentMuseumListBinding binding;

    private MuseumListAdapter museumListAdapter;

    private MuseumViewModel museumViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMuseumListBinding.inflate(inflater);

        setRecyclerView();

        binding.museumSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query != null) searchDatabase(query);
                return false;
            }
        });

        return binding.getRoot();
    }

    private void setRecyclerView() {
        museumListAdapter = new MuseumListAdapter();
        binding.newsList.setAdapter(museumListAdapter);
        binding.newsList.setLayoutManager(new LinearLayoutManager(requireContext()));

        museumViewModel = new ViewModelProvider(this).get(MuseumViewModel.class);
        museumViewModel.readAllData.observe(getViewLifecycleOwner(), museumListAdapter::setData);
    }

    private void searchDatabase(String query) {
        String searchQuery = "%" + query + "%";
        museumViewModel.searchDatabase(searchQuery).observe(this, museums -> museumListAdapter.setData(museums));
    }
}