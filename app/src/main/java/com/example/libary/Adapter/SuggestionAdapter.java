package com.example.libary.Adapter;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class SuggestionAdapter extends ArrayAdapter<String> implements Filterable {
    private List<String> originalData; // Dữ liệu gốc
    private List<String> filteredData; // Dữ liệu sau khi lọc

    public SuggestionAdapter(Context context, List<String> data) {
        super(context, android.R.layout.simple_list_item_1, data);
        this.originalData = new ArrayList<>(data);
        this.filteredData = data;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public String getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> suggestions = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    suggestions.addAll(originalData);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (String item : originalData) {
                        if (item.toLowerCase().contains(filterPattern)) {
                            suggestions.add(item);
                        }
                    }
                }

                results.values = suggestions;
                results.count = suggestions.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredData = (List<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}

