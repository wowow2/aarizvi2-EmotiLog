package com.example.aarizvi2_emotilog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aarizvi2_emotilog.databinding.FragmentSecondBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ArrayList<Emoticon> allEmoticons = FirstFragment.getAllEmoticons();
    private ArrayAdapter<String> emoticonAdapter;
    private DateFormatter dateFormatter;
    private TablePopulator tablePopulator;
    private ProcessAllEmoticons emoticonDataProcessor;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateFormatter = new DateFormatter("yyyy/MM/dd", Locale.getDefault());
        tablePopulator = new TablePopulator(requireContext());
        allEmoticons = FirstFragment.getAllEmoticons();
        emoticonDataProcessor = new ProcessAllEmoticons(allEmoticons);

        setupUI();
        setupListeners();
        initializeData();
    }

    private void setupUI() {
        // Populate emoticon list
        ArrayList<String> emoticonStrings = emoticonDataProcessor.formatEmoticons();
        emoticonAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, emoticonStrings);
        binding.emoticonList.setAdapter(emoticonAdapter);
    }

    private void setupListeners() {
        // Handle set date button click to use the typed date
        binding.setDateButton.setOnClickListener(v -> {
            String newDate = binding.selectedDateEditText.getText().toString();
            updateSummaryForDate(newDate);
        });

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    private void initializeData() {
        // Initialize with today's date and set it to the EditText/TextView
        String todayDate = dateFormatter.formatDate(Calendar.getInstance().getTime());
        binding.selectedDateEditText.setText(todayDate);
        binding.selectedDateTextView.setText(todayDate);

        // Update summary for the initial date
        updateSummaryForDate(todayDate);
    }

    private void updateSummaryForDate(String date) {
        binding.selectedDateTextView.setText(date); // Update the displayed date

        // Process emoticon array into strings
        ArrayList<Pair<String, String>> emoticonProcessed = emoticonDataProcessor.processEmoticons();
        DisplaySummary summaryDisplay = new DisplaySummary(emoticonProcessed);
        // get summary stats for target date
        HashMap<String, String> summary = summaryDisplay.getSummaryForDate(date);

        ArrayList<TableRow> rows = tablePopulator.populate(binding.dataTableLayout, summary);

        for (TableRow row : rows) {
            binding.dataTableLayout.addView(row);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}