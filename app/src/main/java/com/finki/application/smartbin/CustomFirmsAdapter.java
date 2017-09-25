package com.finki.application.smartbin;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

import com.amulyakhare.textdrawable.TextDrawable;
import com.finki.application.smartbin.models.Firm;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jona Dimovska on 28.8.2017.
 */

public class CustomFirmsAdapter extends ArrayAdapter<Firm> {
    TextView tvName;
    TextView tvUrl;
    Firm firm;
    ImageView image;
    Context context;

    public CustomFirmsAdapter(Context context, ArrayList<Firm> firms) {
        super(context,0,firms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       firm = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_firm, parent, false);
        }
       context = parent.getContext();
        setupTextFields(convertView);
        fillUpTextFields(firm);
        tvUrl.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                loadUrl();
                return false;
            }

        });

        image = (ImageView) convertView.findViewById(R.id.image);


        String initials= tvName.getText().toString();
        String letters = "";
        if (!  initials.isEmpty()) {
            letters = initials.charAt(0)+"";
        } else {
            letters = "?";
        }

        //TextDrawable drawable = TextDrawable.builder().buildRound(letters,Color.parseColor("#696969"));

        TextDrawable drawable = TextDrawable.builder().buildRound(letters, Color.rgb		(255, 140, 0));
        //TextDrawable drawable = TextDrawable.builder().buildRound(letters,Color.rgb(38,174,144));
        image.setImageDrawable(drawable);

        return convertView;
    }
    public void loadUrl() {
        WebViewFragment webViewFragment = new WebViewFragment();
        android.app.FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args=new Bundle();
        args.putString("url", (String) tvUrl.getText());
        webViewFragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragment_firms, webViewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
