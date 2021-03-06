package com.example.jarvus.tummybuddy;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_DINING_HALL = "com.example.jarvus.tummybuddy.DINING_HALL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Search Button
        Button searchBt = (Button) findViewById(R.id.search_page);
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                startActivity(intent);
            }
        });

        // WishList Button
        Button wishListBt = (Button) findViewById(R.id.wishList_page);
        wishListBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, WishListActivity.class);
                startActivity(intent);
            }
        });

        // Tracker Button
        Button trackerBt = (Button) findViewById(R.id.tracker);
        trackerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void sixtyFourClicked(View view) {
        int name = DisplayMenuActivity.MENU_SIXTY_FOUR;
        loadMenu(name);
    }

    public void canyonVistaClicked(View view) {
        int name = DisplayMenuActivity.MENU_CANYON_VISTA;
        loadMenu(name);
    }

    public void cafeVentanaClicked(View view) {
        int name = DisplayMenuActivity.MENU_CAFE_VENTANAS;
        loadMenu(name);
    }

    public void clubMedClicked(View view) {
        int name = DisplayMenuActivity.MENU_CLUB_MED;
        loadMenu(name);
    }

    public void foodWorxClicked(View view) {
        int name = DisplayMenuActivity.MENU_FOODWORX;
        loadMenu(name);
    }

    public void goodysClicked(View view) {
        int name = DisplayMenuActivity.MENU_GOODYS;
        loadMenu(name);
    }

    public void pinesClicked(View view) {
        int name = DisplayMenuActivity.MENU_PINES;
        loadMenu(name);
    }

    public void rootsClicked(View view) {
        int name = DisplayMenuActivity.MENU_ROOTS;
        loadMenu(name);
    }

    public void bistroClicked(View view) {
        int name = DisplayMenuActivity.MENU_BISTRO;
        loadMenu(name);
    }

    public void loadMenu(int menu) {
        Intent intent = new Intent(this, DisplayMenuActivity.class);

        intent.putExtra(EXTRA_DINING_HALL, menu);
        startActivity(intent);
    }

    public static class ParseApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();

            ParseACL defaultACL=new ParseACL();
            defaultACL.setPublicReadAccess(true);
            defaultACL.setPublicWriteAccess(true);
            ParseACL.setDefaultACL(defaultACL, true);

            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "2s0GrgbG5sY5OMVcemUzWe4TYLz86tLfRMp8ISTa", "wEjMG8yFL5GjR01PIzSLIpMlDJH5ghHXRPeoTmXm");

            ParseObject diningObj = new ParseObject("DiningHall");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("DiningHall");
            query.setLimit(1000);
            try{
                final List<ParseObject> objects = query.find();
                if(objects.size() != 0) {
                    ParseObject.deleteAllInBackground(objects);
                }
                DataBase db = new DataBase();
                db.collectData(diningObj);
            } catch(com.parse.ParseException e) {
                e.printStackTrace();
            }
        } // end of onCreate()
    } // end of ParseApplication

}