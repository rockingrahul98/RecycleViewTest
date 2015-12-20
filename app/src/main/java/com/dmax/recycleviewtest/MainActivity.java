package com.dmax.recycleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<AdapterItem> quoteItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            quoteItems = new ArrayList<AdapterItem>();
            for (int i = 0; i < 50; i++) {
                AdapterItem item = new AdapterItem();
                /*Add your name and age here*/

                quoteItems.add(item);
            }


            getSupportFragmentManager().beginTransaction().add(R.id.container, new RecycleFragment(), "First Frag").commit();
        }
    }


}
