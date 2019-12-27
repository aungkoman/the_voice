package com.teamcs.voice;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.RecognizerIntent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
public class MainActivity extends AppCompatActivity {
    protected static final int PICK_CONTACT_REQUEST = 1;
    ListView listView ;
    String[] values;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        values = new String[] { "မင်္ဂလာပါ။ မိုက်ခရိုဖုန်းကို နိပ်ပြီး စကား မှ စာသားသို့ ပြောင်းလဲနိုင်ပါပြီ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "error in engine", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                try {
                    startActivityForResult(i, PICK_CONTACT_REQUEST);
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAdView = findViewById(R.id.adView);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        Toast.makeText(getApplicationContext(),"what you say is :",Toast.LENGTH_SHORT).show();
////        ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
////        Toast.makeText(getApplicationContext(),thingsYouSaid.get(0),Toast.LENGTH_SHORT).show();
//        if (requestCode==REQUEST_OK  && resultCode==RESULT_OK) {
//            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            ((TextView)findViewById(R.id.text1)).setText(thingsYouSaid.get(0));
//            Toast.makeText(getApplicationContext(),"what you say is :",Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(),thingsYouSaid.get(0),Toast.LENGTH_SHORT).show();
//        }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        //  Toast.makeText(getApplicationContext(),"result is coming back..." + requestCode+ " , "+resultCode,Toast.LENGTH_SHORT).show();
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            // this is our activity result

            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
     //           Toast.makeText(getApplicationContext(),"what you say is :",Toast.LENGTH_SHORT).show();
                // Do something with the contact here (bigger example below)
                ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //((TextView)findViewById(R.id.text1)).setText(thingsYouSaid.get(0));
//                values = new String[] { thingsYouSaid.get(0)};
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1, android.R.id.text1, values);
//                listView.setAdapter(adapter);


                int count = values.length + 1;
                int release =  values.length;
                String[] num = new String[count]; // new string array
                for (int i = 0; i < release; i++) {
                    String neki = values[i];
                    num[i] = neki;
                }
                num[release] = thingsYouSaid.get(0);
                values = num;
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, values);
                listView.setAdapter(adapter);
            }
            else{
                Toast.makeText(getApplicationContext(),"အသံဖမ်းယူရာတွင် အခက်အခဲဖြစ်နေပါသည်။ ပြန်လည် ပြောပေးပါ ",Toast.LENGTH_SHORT).show();
                //((TextView)findViewById(R.id.text1)).setText("အသံဖမ်းယူရာတွင် အခက်အခဲဖြစ်နေပါသည်။ ပြန်လည် ပြောပေးပါ ");
                values = new String[] { "အသံဖမ်းယူရာတွင် အခက်အခဲဖြစ်နေပါသည်။ ပြန်လည် ပြောပေးပါ "};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, values);
                listView.setAdapter(adapter);
            }
        }
    }
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
