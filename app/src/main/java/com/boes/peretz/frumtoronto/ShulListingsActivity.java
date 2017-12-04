package com.boes.peretz.frumtoronto;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ShulListingsActivity extends AppCompatActivity {

    public static final String LOG_TAG = ShulListingsActivity.class.getSimpleName();
    WebView webView;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shul_listings);
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
        webView = (WebView) findViewById(R.id.shul_listings_web_view);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (isInternetServiceAvailable()) {
            ParseShulListingsWebPage parseShulListingsWebPage=new ParseShulListingsWebPage();
            parseShulListingsWebPage.execute();
        }else {
            Toast.makeText(getApplicationContext(), R.string.internet_connection_error_message, Toast.LENGTH_LONG).show();
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

        public class ParseShulListingsWebPage extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... strings) {
                Document document;
                try {
                    document = Jsoup.connect("http://www.frumtoronto.com/DirectoryListing.asp?CategoryID=234").get();
                    Log.d(LOG_TAG, document.toString());
                    result = document.toString();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                return "Executed";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                webView.loadData(result, "text/html", null);
            }
        }

    public boolean isInternetServiceAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (connectivityManager.getActiveNetworkInfo()==null){
            return false;
        }
        return networkInfo.isConnected()||networkInfo.isConnectedOrConnecting();
    }

}
