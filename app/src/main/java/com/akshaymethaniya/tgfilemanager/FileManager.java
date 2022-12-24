package com.akshaymethaniya.tgfilemanager;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class FileManager {
    private Uri telegramRootFolderURI;
    private Uri telegramMediaFolderURI;
    private Activity activity;
    private Uri uri;

    //    private String mediaTelegramImageFolderId = "primary:android/media/org.telegram.messenger/Telegram/Telegram Images";
    public FileManager(Activity activity) {
        this.activity = activity;
    }

    public Uri getFolderUri(TELEGRAM_DIRECTORY telegramDirectory, FOLDER_OPTION folderOption) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return folderOption.compareTo(FOLDER_OPTION.MEDIA) == 0 ?
                    Uri.parse(TELEGRAM_URI_DETAILS.MEDIA_URI_STRING.getValue()) :
                    Uri.parse(TELEGRAM_URI_DETAILS.DATA_TELEGRAM_URI_STRING.getValue());
        }
        Uri folderUri = null;
        switch (telegramDirectory){
            case TELEGRAM_IMAGES:
                folderUri = folderOption.compareTo(FOLDER_OPTION.MEDIA) == 0 ?
                        Uri.parse(TELEGRAM_URI_DETAILS.MEDIA_URI_STRING.getValue()+"%2FTelegram%20Images") :
                        Uri.parse(TELEGRAM_URI_DETAILS.DATA_TELEGRAM_URI_STRING.getValue()+"%2FTelegram%20Images")
                        ;
                break;
            case TELEGRAM_VIDEOS:
                folderUri = folderOption.compareTo(FOLDER_OPTION.MEDIA) == 0 ?
                        Uri.parse(TELEGRAM_URI_DETAILS.MEDIA_URI_STRING.getValue()+"%2FTelegram%20Video") :
                        Uri.parse(TELEGRAM_URI_DETAILS.DATA_TELEGRAM_URI_STRING.getValue()+"%2FTelegram%20Video")
                ;
                break;
        }
        return folderUri;
    }
    public Uri getFolderUri(TELEGRAM_DIRECTORY telegramDirectory) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return Uri.parse(TELEGRAM_URI_DETAILS.MEDIA_URI_STRING.getValue());
        }
        Uri folderUri = null;
        switch (telegramDirectory) {

            case TELEGRAM_IMAGES:
                folderUri = Uri.parse(TELEGRAM_URI_DETAILS.MEDIA_URI_STRING.getValue()+"%2FTelegram%20Images");
                break;
            case TELEGRAM_VIDEOS:
                folderUri = Uri.parse(TELEGRAM_URI_DETAILS.DATA_TELEGRAM_URI_STRING.getValue()+"%2FTelegram%20Video");
                break;
            case TELEGRAM_AUDIOS:
                folderUri = Uri.parse(TELEGRAM_URI_DETAILS.DATA_TELEGRAM_URI_STRING.getValue()+"%2FTelegram%20Audio");
                break;
            case TELEGRAM_DOCUMENTS:
                folderUri = Uri.parse(TELEGRAM_URI_DETAILS.DATA_TELEGRAM_URI_STRING.getValue()+"%2FTelegram%20Documents");
                break;
        }
        return folderUri;
    }
}
