package com.dddemo.sumanthmadala.androidapp.dddemo.db;

import android.provider.BaseColumns;

/**
 * Created by sumanthmadala on 3/26/17.
 */

public class DBConstants {

    public static class Favorites implements BaseColumns {
        public static final String TB_FAVORITES = "tb_favorite";
        public static final String COL_RESTAURANTID = "rid";
        public static final String COL_NAME = "name";
        public static final String COL_TRAVELTIME = "traveltime";
        public static final String COL_ADDRESS = "address";
        public static final String COL_COVER_IMAGE_URL = "cover_image_url";


        public static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + Favorites.TB_FAVORITES + " (" +
                        Favorites._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        Favorites.COL_RESTAURANTID + " TEXT NOT NULL," +
                        Favorites.COL_NAME+ " TEXT," +
                        Favorites.COL_ADDRESS + " TEXT," +
                        Favorites.COL_TRAVELTIME+ " TEXT," +
                        Favorites.COL_COVER_IMAGE_URL+ " TEXT)";

        public static final String DROP_TABLE =
                "DROP TABLE IF EXISTS " + Favorites.TB_FAVORITES;
    }
}
