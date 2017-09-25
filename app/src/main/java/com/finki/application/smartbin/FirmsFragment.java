package com.finki.application.smartbin;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.finki.application.smartbin.helper.AppDatabaseHelper;
import com.finki.application.smartbin.helper.FirmsDatabaseHelper;
import com.finki.application.smartbin.models.Firm;
import com.finki.application.smartbin.models.User;

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
    AppDatabaseHelper helper;

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_firms, container, false);
        firmsList=new ArrayList<>();
        helper=new AppDatabaseHelper(getActivity().getApplicationContext());

        adapter=new CustomFirmsAdapter(getActivity(),firmsList);
        ListView viewList=(ListView)view.findViewById (R.id.list);
        viewList.setAdapter(adapter);
        updateListView();
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

                SQLiteDatabase dbDelete = helper.getWritableDatabase();
                dbDelete.execSQL("Delete from firms");

                JSONArray jsonArray=new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    String location = jsonObject.getString("location");
                    String phone = jsonObject.getString("phone");
                    String urlFirm = jsonObject.getString("url");
                    Firm firm=new Firm(name,email,location,phone,urlFirm);
                    firmsList.add(firm);


                    // 2. create ContentValues to add key "column"/value
                    String sql="INSERT INTO firms(name,email,location,phone,url) VALUES(?,?,?,?,?)";
                    SQLiteStatement statement=dbDelete.compileStatement(sql);

                    statement.bindString(1,name);
                    statement.bindString(2,email);
                    statement.bindString(3,location);
                    statement.bindString(4,phone);
                    statement.bindString(5,urlFirm);
                    statement.execute();

                }


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
    public void updateListView(){
        String query = "SELECT  * FROM firms";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Firm firm = null;
        if (cursor.moveToFirst()) {
            do {

                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String location = cursor.getString(3);
                String phone = cursor.getString(4);
                String urlFirm = cursor.getString(5);
                firm=new Firm(name,email,location,phone,urlFirm);
                firmsList.add(firm);
            } while (cursor.moveToNext());

            adapter.notifyDataSetChanged();
        }
    }
}
