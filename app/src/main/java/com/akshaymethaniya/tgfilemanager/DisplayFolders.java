package com.akshaymethaniya.tgfilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DisplayFolders extends AppCompatActivity {
    TELEGRAM_DIRECTORY telegramDirectory;
    ImageView mediaIcon,dataFolderIcon;
    TextView folder1,folder2;
    private FileManager fileManager = new FileManager(this);
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_folders);

        getSupportActionBar().setHomeButtonEnabled(true);
        //Extract Directory Name
        Bundle extras = getIntent().getExtras();
        telegramDirectory = (TELEGRAM_DIRECTORY) extras.get("DIRECTORY");
//        Log.d("TEST", telegramDirectory.getDirectoryName());

        mediaIcon = (ImageView) findViewById(R.id.mediaIcon);
        dataFolderIcon = (ImageView) findViewById(R.id.dataFolderIcon);

        folder1 = (TextView) findViewById(R.id.folder1);
        folder2 = (TextView) findViewById(R.id.folder2);

        updateView(telegramDirectory);
        displayAd();
        setOnClickListners(telegramDirectory);
    }
    protected void displayAd(){
        mAdView = findViewById(R.id.adView);
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    private void updateView(TELEGRAM_DIRECTORY telegram_directory){
        getSupportActionBar().setTitle(telegram_directory.getDirectoryName().toUpperCase());
        switch (telegram_directory){
            case TELEGRAM_IMAGES:
                mediaIcon.setImageResource(R.drawable.images_icon);
                dataFolderIcon.setImageResource(R.drawable.images_icon);
                folder1.setText("Media/Images");
                folder2.setText("Data/Images");
                break;
            case TELEGRAM_VIDEOS:
                mediaIcon.setImageResource(R.drawable.ic_videos_icon);
                dataFolderIcon.setImageResource(R.drawable.ic_videos_icon);
                folder1.setText("Media/Videos");
                folder2.setText("Data/Videos");
                break;
        }
    }
    private void setOnClickListners(TELEGRAM_DIRECTORY telegramDirectory){
        RelativeLayout mediaFolderLayout = findViewById(R.id.mediaFolderLayout);
        RelativeLayout dataFolderLayout = findViewById(R.id.dataFolderLayout);

        mediaFolderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri folderUri = fileManager.getFolderUri(telegramDirectory,FOLDER_OPTION.MEDIA);
                intent.setDataAndType(folderUri, "vnd.android.document/directory");
                startActivity(intent);
            }
        });
        dataFolderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri folderUri = fileManager.getFolderUri(telegramDirectory,FOLDER_OPTION.DATA);
                intent.setDataAndType(folderUri, "vnd.android.document/directory");
                startActivity(intent);
            }
        });
    }
}