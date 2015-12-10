package com.example.harsh.new_;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class MainActivity extends AppCompatActivity {
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ed = (EditText)findViewById(R.id.search);
        final ImageView image1 = (ImageView) findViewById(R.id.imageView);
        final String URL = "https://www.irctc.co.in";

        new Thread(new Runnable() {
            @Override
            public void run() {
                String str="Attr: ";

                int  s=0;
                String url=null;
                try {
                    File f =new File("cap.png");
                    Document doc = Jsoup.connect(URL).get();
                    Log.e("Status","connection estabished");
                    //username
                    Element hidden = doc.select("input[name=j_username]").first();
                    hidden.attr("value","kevalo");
                    str+=hidden.attr("value");
                    //password
                    Element hidden2 = doc.select("input[name=j_password]").first();
                    hidden2.attr("value","mypass");
                    str+=hidden2.attr("value");

                    Element hidden3 = doc.select("input[name=j_captcha]").first();
                    //hidden.attr("value","kevalo");
                   // str+=hidden3.attr("value");
                    Element image = doc.select("img[id=cimage]").first();
                    str+=image.attr("src");
                    Element image2=image;
                    url = image2.absUrl(image.attr("src"));
                    Element login =doc.select("input#loginbutton").first();
                    Document doc1 = Jsoup.parse(f, "binary", url);




                } catch (Exception e) {
                    str=e.getMessage();
                }
                final String finalStr = str;
                ed.post(new Runnable() {
                    @Override
                    public void run() {
                        ed.setText(finalStr);
                    }
                });
                final String finalUrl = url;
                image1.post(new Runnable() {
                    @Override
                    public void run() {
//                        Picasso.with(getApplicationContext()).load(finalUrl).into(image1);


                        image1.setImageBitmap(BitmapFactory.decodeFile(finalUrl));
                        image1.setImageDrawable(Drawable.createFromPath(finalUrl));
                    }
                });
            }
        }).start();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    /*publicvoid open(View view) {



        //- See more at: http://www.survivingwithandroid.com/2014/04/parsing-html-in-android-with-jsoup.html#sthash.Q80tzqZN.dpuf
        //Elements topicList = doc.select("h2.topic");

        //Intent i = new Intent(Intent.ACTION_VIEW);
        //i.setData(Uri.parse(URL));
        //startActivity(i);
    }

*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
