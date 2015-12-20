package com.dmax.recycleviewtest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<QuoteItem> quoteItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FindUrl().execute();
        if (null == savedInstanceState) {
            quoteItems = new ArrayList<QuoteItem>();
        }
    }
    class FindUrl extends AsyncTask<Void, Void, ArrayList<QuoteItem>> {
        ProgressDialog dialog;

        public FindUrl() {
            this.dialog = new ProgressDialog(MainActivity.this);
            this.dialog.setMessage("Loading data..");
            this.dialog.setCancelable(false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.show();
        }

        @Override
        protected ArrayList<QuoteItem> doInBackground(Void... params) {
            return getItems();
        }

        public ArrayList<QuoteItem> getItems() {
            BufferedReader in = null;
            StringBuffer buffer = null;
            String str;
            try {
                URL url = new URL(
                        "https://www.dropbox.com/s/92n93fl0xo1oqdp/shayari.txt?dl=1");
                in = new BufferedReader(new InputStreamReader(
                        url.openStream()));
                buffer = new StringBuffer();
                while ((str = in.readLine()) != null) {
                    buffer = buffer.append(str);
                }
                in.close();
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }

            if (buffer != null) {
                try {
                    JSONObject jsonObject = new JSONObject(buffer.toString());
                    JSONArray catagoryArrayNames = jsonObject.names();
                    for (int i = 0; i < catagoryArrayNames.length(); i++) {
                        JSONArray catagoryArray = jsonObject
                                .getJSONArray(catagoryArrayNames.getString(i));
                        QuoteItem quoteItem = new QuoteItem();
                        quoteItem.catagoryName = catagoryArrayNames
                                .getString(i);
                        quoteItem.quotes = new ArrayList<String>();
                        for (int j = 0; j < catagoryArray.length(); j++) {
                            String quotes = catagoryArray.getString(j);
                            quoteItem.quotes.add(quotes);
                        }
                        quoteItems.add(quoteItem);
                    }
                } catch (Exception e) {
                }
            }
            return new ArrayList<QuoteItem>();

        }

        @Override
        protected void onPostExecute(ArrayList<QuoteItem> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();
            getSupportFragmentManager().beginTransaction().add(R.id.container, new RecycleFragment(), "RecycleFrag").commit();
        }
    }


}
