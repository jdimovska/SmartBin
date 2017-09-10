package com.finki.application.smartbin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finki.application.smartbin.models.Firm;
import com.finki.application.smartbin.models.User;

import java.util.ArrayList;

/**
 * Created by Jona Dimovska on 10.9.2017.
 */

public class CustomUsersAdapter extends ArrayAdapter<User> {
    User user;
    TextView name;
    TextView username;
    TextView points;

    public CustomUsersAdapter(Context context, ArrayList<User> users) {
        super(context,0,users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        user=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_user, parent, false);
        }
        setupTextFields(convertView);
        fillUpTextFields(user);
        return convertView;
    }
    void fillUpTextFields(User user) {
        name.setText(user.name);
        username.setText(user.username);
        points.setText(Double.toString(user.points));

    }
    void setupTextFields(View convertView) {
        name = (TextView) convertView.findViewById(R.id.User);
        username = (TextView) convertView.findViewById(R.id.Username);
        points = (TextView) convertView.findViewById(R.id.userPoints);

    }
}
