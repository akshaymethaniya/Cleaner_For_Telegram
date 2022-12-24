package com.akshaymethaniya.tgfilemanager;

public enum TELEGRAM_DIRECTORY {
    TELEGRAM_IMAGES("Telegram Images"),
    TELEGRAM_VIDEOS("Telegram Videos"),
    TELEGRAM_AUDIOS("Telegram Audios"),
    TELEGRAM_DOCUMENTS("Telegram Documents");

    private final String value;

    public String getDirectoryName() {
        return value;
    }

    TELEGRAM_DIRECTORY(String value) {
        this.value = value;
    }
}
