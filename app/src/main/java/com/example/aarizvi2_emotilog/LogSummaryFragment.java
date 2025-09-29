package com.example.aarizvi2_emotilog;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.aarizvi2_emotilog.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class LogSummaryFragment extends Fragment {

    private FragmentSecondBinding binding;
    private EmoticonViewModel viewModel;
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
        viewModel = new ViewModelProvider(requireActivity()).get(EmoticonViewModel.class);
        emoticonDataProcessor = new ProcessAllEmoticons(viewModel.getAllEmoticons());

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
                NavHostFragment.findNavController(LogSummaryFragment.this)
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
        binding.selectedDateTextView.setText(date);

        // Get the latest emoticon data from the ViewModel each time.
        ArrayList<Emoticon> allEmoticons = viewModel.getAllEmoticons();
        ProcessAllEmoticons emoticonDataProcessor = new ProcessAllEmoticons(allEmoticons);

        // Clear existing views from the table to prevent duplicates.
        binding.dataTableLayout.removeAllViews();

        ArrayList<Pair<String, String>> emoticonProcessed = emoticonDataProcessor.processEmoticons();
        DisplaySummary summaryDisplay = new DisplaySummary(emoticonProcessed);
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