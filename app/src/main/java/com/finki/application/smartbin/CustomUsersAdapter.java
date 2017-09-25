package com.finki.application.smartbin;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.finki.application.smartbin.models.Firm;
import com.finki.application.smartbin.models.User;

import java.util.ArrayList;

/**
 * Created by Jona Dimovska on 10.9.2017.
 */

public class CustomUsersAdapter extends ArrayAdapter<User> {
    User user;
    ImageView image;
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
        username.setText(user.username);
        int pointsInt = (int) user.points;
        points.setText(Integer.toString(pointsInt));
        String initials[] = user.name.split(" ");
        String letters = "";
        if (initials.length == 1) {
            letters = initials[0].charAt(0)+"";
        } else if (initials.length == 0) {
            letters = "?";
        } else {
            letters = initials[0].charAt(0)+""+initials[1].charAt(0);
        }

        //TextDrawable drawable = TextDrawable.builder().buildRound(letters,Color.parseColor("#696969"));

        TextDrawable drawable = TextDrawable.builder().buildRound(letters,Color.rgb		(255, 140, 0));
        //TextDrawable drawable = TextDrawable.builder().buildRound(letters,Color.rgb(38,174,144));
        image.setImageDrawable(drawable);

    }
    void setupTextFields(View convertView) {
        username = (TextView) convertView.findViewById(R.id.Username);
        points = (TextView) convertView.findViewById(R.id.userPoints);
        image = (ImageView) convertView.findViewById(R.id.image);

    }
}
