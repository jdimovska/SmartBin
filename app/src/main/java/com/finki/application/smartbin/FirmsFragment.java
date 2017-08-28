package com.finki.application.smartbin;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.finki.application.smartbin.models.Firm;

import java.util.ArrayList;

public class FirmsFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    FragmentActivity context;
    ArrayList<Firm> firmsList;

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_firms, container, false);

        firmsList=new ArrayList<>();
        firmsList.add(new Firm("Pakomak","pakomak@gmail.com","Aerodrom","070111222","www.gmail.com"));
        firmsList.add(new Firm("Komuna","www.komuna.com","Centar","0752223333","kom@kom.com"));
        firmsList.add(new Firm("Komuna2","www.komuna.com","Centar","0752223333","kom@kom.com"));
        firmsList.add(new Firm("Komuna3","www.komuna.com","Centar","0752223333","kom@kom.com"));
        firmsList.add(new Firm("Komuna4","www.komuna.com","Centar","0752223333","kom@kom.com"));
        firmsList.add(new Firm("Komuna5","www.komuna.com","Centar","0752223333","kom@kom.com"));

        CustomFirmsAdapter adapter=new CustomFirmsAdapter(getActivity(),firmsList);
        ListView viewList=(ListView)view.findViewById (R.id.list);
        viewList.setAdapter(adapter);

        viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <? > arg0, View arg1, int arg2,
                                    long arg3) {

                Bundle args=new Bundle();

                args.putString("name",firmsList.get(arg2).name);
                args.putString("location",firmsList.get(arg2).location);
                args.putString("url",firmsList.get(arg2).url);
                args.putString("phone",firmsList.get(arg2).phone);
                args.putString("email",firmsList.get(arg2).email);

                FirmDetailsFragment secondFragment = new FirmDetailsFragment();
                secondFragment.setArguments(args);
                android.app.FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_firms, secondFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });
        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        context=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Firm item = firmsList.get(i);

        FirmDetailsFragment secondFragment = new FirmDetailsFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_firms, secondFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
