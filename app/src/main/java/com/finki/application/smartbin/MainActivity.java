package com.finki.application.smartbin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.finki.application.smartbin.models.Firm;
import com.finki.application.smartbin.models.User;
import com.finki.application.smartbin.rest_services.AppConfig;
import com.finki.application.smartbin.rest_services.AppController;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public Button btnScan;
    private IntentIntegrator qrScan;
    private SessionManager session;
    private Context mContext;
    private static FragmentManager mManager;
    Fragment fragment = null;
    ArrayList<User> usersList;
    CustomUsersAdapter adapter;
    ListView viewListUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersList=new ArrayList<>();
        adapter=new CustomUsersAdapter(this,usersList);

        viewListUsers=(ListView) findViewById (R.id.userAdapterList);
        viewListUsers.setAdapter(adapter);

        DownloadTask task=new DownloadTask();
        task.execute("https://jonadimovska.000webhostapp.com/best.php");

        session = new SessionManager(getApplicationContext());
       // setContentView(R.layout.activity_main);
        btnScan = (Button) findViewById(R.id.btnScan);
        qrScan = new IntentIntegrator(this);
        setupDrawer();

    }


    public void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //TextView headerName = (TextView) navigationView.findViewById(R.id.header_fullname);
       // headerName.setText(session.getFullname());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                try {
                    JSONObject obj = new JSONObject(result.getContents());
                    StringBuilder sb = new StringBuilder();
                    sb.append("\n"+"Name: "+obj.getString("name")+"\n"+"\n");
                    sb.append("Points: "+obj.getString("points")+"\n"+"\n");
                    sb.append("Location: "+obj.getString("address")+"\n"+"\n");

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(sb)
                            .setTitle(R.string.congrats_title)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    StringRequest strReq = new StringRequest(Request.Method.POST,
                                            AppConfig.URL_UPDATE_USER_POINTS, new Response.Listener<String>() {

                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(getApplicationContext(),"Successfully updated!",Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }) {

                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put("email", session.getEmail());
                                            String points=session.getPoints();
                                            Double doublePoints=Double.parseDouble(points);
                                            doublePoints+=100;
                                            params.put("points",doublePoints+"");

                                            return params;
                                        }


                                    };
                                    AppController.getInstance().addToRequestQueue(strReq);












                                    dialog.cancel();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void scanClick(View view){
            qrScan.setPrompt("");
        qrScan.setOrientationLocked(false);
        qrScan.initiateScan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_map) {
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_firms) {
            Intent intent = new Intent(getApplicationContext(), BaseFirmActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_scan) {
            qrScan.setPrompt("");
            qrScan.setOrientationLocked(false);
                qrScan.initiateScan();

        } else if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_log_out) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            finish();
            startActivity(intent);
            session.setLogin(false);

        } else if (id == R.id.nav_user) {
            Intent intent = new Intent(getApplicationContext(),UpdateAccountActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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



                JSONArray jsonArray=new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);

                    String name = jsonObject.getString("name");
                    String username = jsonObject.getString("username");
                    String points = jsonObject.getString("points");

                    User user=new User(name,username,Double.parseDouble(points));
                    usersList.add(user);
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

}
