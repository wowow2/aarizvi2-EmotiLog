package com.example.aarizvi2_emotilog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aarizvi2_emotilog.databinding.FragmentFirstBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private static ArrayList<Emoticon> allEmoticons = new ArrayList<>();
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

        binding.buttonFirst.setOnClickListener(v ->
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

        binding.SadButton.setOnClickListener(v -> {
            Emoticon sadEmotion = new Emoticon("Sad", today);
            allEmoticons.add(0,sadEmotion);
        });

        binding.GratefulButton.setOnClickListener(v -> {
            Emoticon gratefulEmotion = new Emoticon("Grateful", today);
            allEmoticons.add(0,gratefulEmotion);
        });

        binding.AngryButton.setOnClickListener(v -> {
            Emoticon angryEmotion = new Emoticon("Angry", today);
            allEmoticons.add(0,angryEmotion);
        });

        binding.ExcitedButton.setOnClickListener(v -> {
            Emoticon excitedEmotion = new Emoticon("Excited", today);
            allEmoticons.add(0,excitedEmotion);
        });

        binding.FearButton.setOnClickListener(v -> {
            Emoticon FearEmotion = new Emoticon("Fear", today);
            allEmoticons.add(0,FearEmotion);
        });

        binding.HappyButton.setOnClickListener(v -> {
            Emoticon happyEmoticon = new Emoticon("Happy", today);
            allEmoticons.add(0,happyEmoticon);
        });

    }

    public static ArrayList<Emoticon> getAllEmoticons() {
        return allEmoticons;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}