package com.finki.application.smartbin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finki.application.smartbin.models.Firm;

import java.util.ArrayList;

/**
 * Created by Jona Dimovska on 28.8.2017.
 */

public class CustomFirmsAdapter extends ArrayAdapter<Firm> {
    public CustomFirmsAdapter(Context context,ArrayList<Firm> firms) {
        super(context,0,firms);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Firm firm = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_firm, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvUrl);
        // Populate the data into the template view using the data object
        tvName.setText(firm.name);
        tvHome.setText(firm.url);
        // Return the completed view to render on screen
        return convertView;
    }
}
