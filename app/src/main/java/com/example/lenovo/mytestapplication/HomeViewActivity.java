package com.example.lenovo.mytestapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomeViewActivity extends AppCompatActivity  implements CountryAdapter.ItemClickListener {
    CountryAdapter adapter;
    public ArrayList<Country> countriesArr = new ArrayList<Country>();
    private String TAG = MainActivity.class.getSimpleName();

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profileView:
                Intent i = new Intent(this, ProfileViewActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        new GetCountries().execute();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryAdapter(this, countriesArr);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Clicked " + adapter.getItem(position) + " Row number " + position, Toast.LENGTH_SHORT).show();

    }

    private class GetCountries extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String url = "http://services.groupkt.com/country/get/all";
            String jsonStr = "";
            try {
                // Making a request to url and getting response
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                HttpResponse response = client.execute(request);
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONObject result1 = jsonObj.getJSONObject("RestResponse");
                    JSONArray result2=result1.getJSONArray("result");

                    // looping through All Country
                    for (int i = 0; i < result2.length(); i++) {
                        JSONObject pObj = result2.getJSONObject(i);

                        Country country = new Country();
                        country.setName(pObj.getString("name"));
                        country.setAlpha2Code(pObj.getString("alpha2_code"));
                        country.setAlpha3Code(pObj.getString("alpha3_code"));
                        countriesArr.add(country);

                    }
                    return null;
                } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                Toast.makeText(getApplicationContext(),
                        "Couldn't get json from server. Check LogCat for possible errors!",
                        Toast.LENGTH_LONG).show();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            adapter.notifyDataSetChanged();
        }
    }
}