package com.finki.application.smartbin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finki.application.smartbin.models.Firm;

import java.util.ArrayList;

/**
 * Created by Jona Dimovska on 28.8.2017.
 */

public class CustomFirmsAdapter extends ArrayAdapter<Firm>  {
    TextView tvName;
    TextView tvUrl;
    Firm firm;

    public CustomFirmsAdapter(Context context, ArrayList<Firm> firms) {
        super(context,0,firms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       firm = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_firm, parent, false);
        }
        setupTextFields(convertView);
        fillUpTextFields(firm);
        return convertView;
    }

    void fillUpTextFields(Firm firm) {
        tvName.setText(firm.name);
        tvUrl.setText(firm.url);

    }
    void setupTextFields(View convertView) {
         tvName = (TextView) convertView.findViewById(R.id.tvName);
         tvUrl = (TextView) convertView.findViewById(R.id.tvUrl);

    }


}
