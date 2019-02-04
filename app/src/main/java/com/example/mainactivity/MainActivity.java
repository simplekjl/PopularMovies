/*
 * Develop by Jose L Crisostomo S. on 2/3/19 4:08 PM
 * Last modified 2/3/19 4:08 PM.
 * Copyright (c) 2019. All rights reserved.
 *
 *
 */

package com.example.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_most_popular:
                //TODO(1) make a service call to sort the movies by most popular
                return true;
            case R.id.action_highest_rated:
                //TODO(2) make a service call to sort the movies by highest rated
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
