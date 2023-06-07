package com.alekhin.museys.fragments.museum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alekhin.museys.databinding.FragmentMuseumBinding;
import com.alekhin.museys.room.Museum;
import com.squareup.picasso.Picasso;

public class MuseumFragment extends Fragment {
    private Museum museum;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMuseumBinding binding = FragmentMuseumBinding.inflate(inflater);

        if (getArguments() != null) museum = MuseumFragmentArgs.fromBundle(getArguments()).getCurrentMuseum();

        Picasso.get().load(museum.museumImage).resize(256, 256).centerCrop().into(binding.museumImage);
        binding.museumTitle.setText(museum.museumTitle);
        binding.museumDescription.setText(museum.museumDescription);
        binding.museumAddress.setText(museum.museumAddress);
        binding.museumNumber.setText(museum.museumNumber);
        binding.museumSite.setText(museum.museumSite);

        binding.museumAddressCard.setOnClickListener(this::getAddress);
        binding.museumNumberCard.setOnClickListener(this::makeCall);
        binding.museumSiteCard.setOnClickListener(this::openSite);

        return binding.getRoot();
    }

    private void getAddress(View v) {
        Intent addressIntent = new Intent(Intent.ACTION_VIEW);
        addressIntent.setData(Uri.parse("https://www.google.com/maps/search/?api=1&query=" + museum.museumAddress));
        addressIntent.setPackage("com.google.android.apps.maps");

        if (addressIntent.resolveActivity(requireActivity().getPackageManager()) != null) startActivity(addressIntent);

        Toast.makeText(requireContext(), "ADDRESS IS: " + museum.museumAddress + " PACKAGE IS: " + addressIntent.getPackage(), Toast.LENGTH_LONG).show();
    }

    private void makeCall(View v) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + museum.museumNumber));
        startActivity(callIntent);
    }

    private void openSite(View v) {
        Intent siteIntent = new Intent(Intent.ACTION_VIEW);
        siteIntent.setData(Uri.parse(museum.museumSite));
        startActivity(siteIntent);
    }
}