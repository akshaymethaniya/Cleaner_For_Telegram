package com.akshaymethaniya.tgfilemanager;

public enum TELEGRAM_URI_DETAILS {
//    MEDIA_TELEGRAM_IMAGES_DOC_ID("/tree/primary:android/media/org.telegram.messenger/Telegram"),
    MEDIA_URI_STRING("content://com.android.externalstorage.documents/document/primary%3Aandroid%2Fmedia%2Forg.telegram.messenger%2FTelegram"),
    DATA_TELEGRAM_URI_STRING("content://com.android.externalstorage.documents/document/primary%3Aandroid%2Fdata%2Forg.telegram.messenger%2Ffiles%2FTelegram"),
//    MEDIA_TELEGRAM_IMAGES_URI_STRING("content://com.android.externalstorage.documents/document/primary%3Aandroid%2Fmedia%2Forg.telegram.messenger%2FTelegram%2FTelegram%20Images"),
//    MEDIA_TELEGRAM_VIDEOS_URI_STRING("content://com.android.externalstorage.documents/document/primary%3Aandroid%2Fmedia%2Forg.telegram.messenger%2FTelegram%2FTelegram%20Video")
    ;
    private final String value;
    TELEGRAM_URI_DETAILS(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
