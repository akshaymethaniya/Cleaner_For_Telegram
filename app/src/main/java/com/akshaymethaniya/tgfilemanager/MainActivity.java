package com.akshaymethaniya.tgfilemanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
public class MainActivity extends AppCompatActivity {
    //    private boolean isNightMode = false;
    private FileManager fileManager;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setTitle("CLEANER FOR TELEGRAM");
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.appbar);

        fileManager = new FileManager(this);
        setOnClickListners();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        displayAd();
    }
    protected void displayAd(){
        mAdView = findViewById(R.id.adView);
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    protected void setOnClickListners() {
        RelativeLayout howtoUse = findViewById(R.id.howtoUse);
        RelativeLayout rateUs = findViewById(R.id.rateUsLayout);
        RelativeLayout images = findViewById(R.id.imagesLayout);
        RelativeLayout videos = findViewById(R.id.videosLayout);
        RelativeLayout documents = findViewById(R.id.documentsLayout);
        RelativeLayout audios = findViewById(R.id.audiosLayout);

        ArrayList<RelativeLayout> layouts = new ArrayList<RelativeLayout>() {
            {
                add(images);
                add(videos);
                add(documents);
                add(audios);
                add(howtoUse);
                add(rateUs);
            }
        };
        for (RelativeLayout layout : layouts) {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);

                    if (layout == images) {
                        Intent intent1 = new Intent(MainActivity.this, DisplayFolders.class);
                        intent1.putExtra("DIRECTORY", TELEGRAM_DIRECTORY.TELEGRAM_IMAGES);
                        startActivity(intent1);
                    } else if (layout == videos) {
                        Intent intent1 = new Intent(MainActivity.this, DisplayFolders.class);
                        intent1.putExtra("DIRECTORY", TELEGRAM_DIRECTORY.TELEGRAM_VIDEOS);
                        startActivity(intent1);
                    } else if (layout == audios) {
                        Uri folderUri = fileManager.getFolderUri(TELEGRAM_DIRECTORY.TELEGRAM_AUDIOS);
                        intent.setDataAndType(folderUri, "vnd.android.document/directory");
//                        intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                        intent.setFlags(Intent.FLAG_);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else if(layout == documents){
                        Uri folderUri = fileManager.getFolderUri(TELEGRAM_DIRECTORY.TELEGRAM_DOCUMENTS);
                        intent.setDataAndType(folderUri, "vnd.android.document/directory");
                        startActivity(intent);
                    } else if(layout == rateUs){
                        String url = getString(R.string.ratingURL);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                    else {
                        String url = getString(R.string.howToUseVideoURL);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                }
            });
        }
    }
}