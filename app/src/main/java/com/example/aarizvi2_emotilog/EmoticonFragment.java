package com.example.aarizvi2_emotilog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aarizvi2_emotilog.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.Date;


public class EmoticonFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EmoticonViewModel allEmoticons;
    private Date today;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        today = new Date();

        allEmoticons = new ViewModelProvider(requireActivity()).get(EmoticonViewModel.class);
        // Load data when the fragment is created.
        allEmoticons.loadEmoticons(requireContext());


        binding.buttonFirst.setOnClickListener(v ->
            NavHostFragment.findNavController(EmoticonFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

        binding.SadButton.setOnClickListener(v -> {
            Emoticon sadEmotion = new Emoticon("Sad", today);
            allEmoticons.addEmoticon(sadEmotion);
        });

        binding.GratefulButton.setOnClickListener(v -> {
            Emoticon gratefulEmotion = new Emoticon("Grateful", today);
            allEmoticons.addEmoticon(gratefulEmotion);
        });

        binding.AngryButton.setOnClickListener(v -> {
            Emoticon angryEmotion = new Emoticon("Angry", today);
            allEmoticons.addEmoticon(angryEmotion);
        });

        binding.ExcitedButton.setOnClickListener(v -> {
            Emoticon excitedEmotion = new Emoticon("Excited", today);
            allEmoticons.addEmoticon(excitedEmotion);
        });

        binding.FearButton.setOnClickListener(v -> {
            Emoticon FearEmotion = new Emoticon("Fear", today);
            allEmoticons.addEmoticon(FearEmotion);
        });

        binding.HappyButton.setOnClickListener(v -> {
            Emoticon happyEmoticon = new Emoticon("Happy", today);
            allEmoticons.addEmoticon(happyEmoticon);
        });

        binding.clearData.setOnClickListener(v -> {
            allEmoticons.clearDatabase("database", requireContext());
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        allEmoticons.saveEmoticons(requireContext());
        binding = null;
    }

}