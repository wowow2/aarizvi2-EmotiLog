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

        // Initial setup for date and emoticon list
        setupInitialData();

        // Handle set date button click
        binding.setDateButton.setOnClickListener(v -> {
            String newDate = binding.selectedDateEditText.getText().toString();
            updateSummaryForDate(newDate);
        });

        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );
    }

    private void setupInitialData() {
        // Initialize with today's date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());
        binding.selectedDateEditText.setText(formattedDate);
        binding.selectedDateTextView.setText(formattedDate);

        // Populate emoticon list
        ProcessAllEmoticons processor = new ProcessAllEmoticons(allEmoticons);
        ArrayList<String> emoticonStrings = processor.formatEmoticons();
        emoticonAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, emoticonStrings);
        binding.emoticonList.setAdapter(emoticonAdapter);

        // Update summary for the initial date
        updateSummaryForDate(formattedDate);
    }

    private void updateSummaryForDate(String date) {
        ProcessAllEmoticons processor = new ProcessAllEmoticons(allEmoticons);
        ArrayList<Pair<String, String>> emoticonProcessed = processor.processEmoticons();
        binding.selectedDateTextView.setText(date);

        DisplaySummary summaryDisplay = new DisplaySummary(emoticonProcessed);
        HashMap<String, String> summary = summaryDisplay.getSummaryForDate(date);
        Log.d("SecondFragment", "Summary for date " + date + ": " + summary.toString());

        populateTable(summary);
    }

    private void populateTable(HashMap<String, String> summary) {
        // First, clear any existing rows from the table, leaving the header
        binding.dataTableLayout.removeViews(1, binding.dataTableLayout.getChildCount() - 1);

        for (String emoticon : summary.keySet()) {
            String summaryText = summary.get(emoticon);

            TableRow row = new TableRow(requireContext());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView emoticonTextView = new TextView(requireContext());
            emoticonTextView.setText(emoticon);
            emoticonTextView.setPadding(8, 8, 8, 8);

            TextView countFrequencyTextView = new TextView(requireContext());
            countFrequencyTextView.setText(summaryText);
            countFrequencyTextView.setPadding(8, 8, 8, 8);

            row.addView(emoticonTextView);
            row.addView(countFrequencyTextView);

            binding.dataTableLayout.addView(row);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}