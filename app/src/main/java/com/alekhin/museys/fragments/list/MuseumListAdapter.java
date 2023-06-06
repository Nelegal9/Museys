package com.alekhin.museys.fragments.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.alekhin.museys.R;
import com.alekhin.museys.room.Museum;
import com.alekhin.museys.databinding.CardMuseumBinding;

import java.util.Collections;
import java.util.List;

public class MuseumListAdapter extends RecyclerView.Adapter<MuseumListAdapter.MuseumListViewHolder> {
    private List<Museum> museumList = Collections.emptyList();

    protected static class MuseumListViewHolder extends RecyclerView.ViewHolder {
        CardMuseumBinding binding;

        private MuseumListViewHolder(@NonNull CardMuseumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Museum museum) {
            binding.museumImage.setImageResource(R.drawable.logo);
            binding.museumTitle.setText(museum.museumTitle);
            binding.museumCard.setOnClickListener(v -> {
                NavDirections action = MuseumListFragmentDirections.actionMuseumListFragmentToMuseumFragment(museum);
                Navigation.findNavController(v).navigate(action);
            });
        }
    }

    @NonNull
    @Override
    public MuseumListAdapter.MuseumListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MuseumListViewHolder(CardMuseumBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumListAdapter.MuseumListViewHolder holder, int position) {
        holder.bind(museumList.get(position));
    }

    @Override
    public int getItemCount() {
        return museumList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    void setData(List<Museum> museumList) {
        this.museumList = museumList;
        notifyDataSetChanged();
    }
}