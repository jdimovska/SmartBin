package com.finki.application.smartbin;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.finki.application.smartbin.models.Firm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FirmsFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    FragmentActivity context;
    ArrayList<Firm> firmsList;
    CustomFirmsAdapter adapter;

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_firms, container, false);

        firmsList=new ArrayList<>();

        adapter=new CustomFirmsAdapter(getActivity(),firmsList);
        ListView viewList=(ListView)view.findViewById (R.id.list);
        viewList.setAdapter(adapter);

        DownloadTask task=new DownloadTask();
        task.execute("https://jonadimovska.000webhostapp.com/firms.php");
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
    public class DownloadTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try{
                url=new URL(urls[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while(data!=-1){
                    char current=(char) data;
                    result+=current;
                    data=reader.read();
                }

                JSONArray obj=new JSONArray(result);
                for(int i=0;i<obj.length();i++){
                    JSONObject objJson=obj.getJSONObject(i);
                    String id = objJson.getString("id");
                    String name = objJson.getString("name");
                    String email = objJson.getString("email");
                    String location = objJson.getString("location");
                    String phone = objJson.getString("phone");
                    String urlFirm = objJson.getString("url");
                    Firm firm=new Firm(name,email,location,phone,urlFirm);
                    firmsList.add(firm);
                }
                    Log.i("json: ",result);

            }catch(Exception e ){
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter.notifyDataSetChanged();
        }
    }
}
