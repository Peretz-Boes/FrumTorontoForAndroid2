package com.boes.peretz.frumtoronto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ShulsAndTefillosActivity extends AppCompatActivity {

    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuls_and_tefillos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        button=(Button)findViewById(R.id.button40);
        button2=(Button)findViewById(R.id.button42);
        button.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDaveningSchedule(View view) {
        Intent intent = new Intent(ShulsAndTefillosActivity.this, DaveningScheduleActivity.class);
        startActivity(intent);
    }

    public void showShulListings(View view) {
        Intent intent = new Intent(ShulsAndTefillosActivity.this, ShulListingsActivity.class);
        startActivity(intent);
    }

    public void showMinchaListings(View view) {
        Intent intent = new Intent(ShulsAndTefillosActivity.this, MinchaListActivity.class);
        startActivity(intent);
    }

    public void showNewsletters(View view) {
        Intent intent = new Intent(ShulsAndTefillosActivity.this, CommunityNewslettersActivity.class);
        startActivity(intent);
    }

    public void showWeeklyPublications(View view){
        Intent intent=new Intent(ShulsAndTefillosActivity.this,WeeklyPublicationsActivity.class);
        startActivity(intent);
    }

    public void showTehillimList(View view){
        Intent intent=new Intent(ShulsAndTefillosActivity.this,TehillimListActivity.class);
        startActivity(intent);
    }

}
