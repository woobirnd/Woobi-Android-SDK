package com.woobi.woobitest152;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.woobi.Woobi;
import com.woobi.WoobiError;
import com.woobi.WoobiEventListener;
import com.woobi.WoobiVidgetAd;
import com.woobi.model.WoobiCacheListener;

public class MainActivity extends AppCompatActivity {

    private WoobiVidgetAd woobiVidgetAd;
    private String appId = "l6WBP-bwHgCJTIPXoIvimL3v27qXGpV8bJ2iNRUzBxovPL1safX1flGWV7BHT9VH"; // set your token here
    private ToggleButton toggleNonRewarded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toggle button for non rewarded ad
        toggleNonRewarded = (ToggleButton)findViewById(R.id.toggleNonRewarded);
        toggleNonRewarded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                woobiVidgetAd.requestNonIncentVideo(isChecked, appId);
            }
        });

//        Woobi.staging = true;
        Woobi.init(this, appId, new WoobiEventListener() {
            @Override
            public void onInitialized() {
                Toast.makeText(getApplicationContext(), "init", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(WoobiError woobiError) {
                Toast.makeText(getApplicationContext(), woobiError.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShowVidget() {

            }

            @Override
            public void onCloseVidget() {

            }
        });

        woobiVidgetAd = Woobi.getWoobiVidgetAd(this, appId);
        woobiVidgetAd.setWoobiCacheListener(new WoobiCacheListener() {
            @Override
            public void onReady() {
                Toast.makeText(getApplicationContext(), "vidget ready", Toast.LENGTH_SHORT).show();
            }
        });
        woobiVidgetAd.setClientId(""); // optional
    }

    public void getAndShow(View view) {
        woobiVidgetAd.getAndShow();
    }

    public void get(View view) {
        woobiVidgetAd.get();
    }

    public void show(View view) {
        woobiVidgetAd.show();
    }
}
