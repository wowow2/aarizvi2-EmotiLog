package com.example.aarizvi2_emotilog;

import android.content.Context;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/*
Provides a method to create an ArrayList of table rows, with each row representing one entry in the
summary table.
 */
public class TablePopulator {
    private Context context;

    public TablePopulator(Context context) {
        this.context = context;
    }

    public ArrayList<TableRow> populate(TableLayout dataTableLayout, HashMap<String, String> summary) {
        ArrayList<TableRow> rows = new ArrayList<>(); // store all rows to enter
        // Clear any existing rows from the table, leaving the header
        if (dataTableLayout.getChildCount() > 1) {
            dataTableLayout.removeViews(1, dataTableLayout.getChildCount() - 1);
        } else if (dataTableLayout.getChildCount() == 1 && dataTableLayout.getChildAt(0) instanceof TableRow) {
            Log.w("TablePopulator", "TableLayout only contains header or is empty, cannot remove views starting from index 1.");
        }

        for (String emoticon : summary.keySet()) {
            String summaryText = summary.get(emoticon);

            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView emoticonTextView = new TextView(context);
            emoticonTextView.setText(emoticon);
            emoticonTextView.setPadding(8, 8, 8, 8);

            TextView countFrequencyTextView = new TextView(context);
            countFrequencyTextView.setText(summaryText);
            countFrequencyTextView.setPadding(8, 8, 8, 8);

            row.addView(emoticonTextView);
            row.addView(countFrequencyTextView);

            rows.add(row);
        }
        return rows;
    }
}

