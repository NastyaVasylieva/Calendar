package com.nastya.calendar.Data;

public class RegularEventTable {

    public static final String TABLE_NAME = "regular_events";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INFO = "info";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_END = "end";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_ICON = "icon";
    public static final String COLUMN_NOTIFICATIONS = "notifications";
    public static final String COLUMN_FREQUENCY = "frequency";
    public static final String COLUMN_DURATION = "duration";



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_INFO + " TEXT,"
                    + COLUMN_START + " BIGINT,"
                    + COLUMN_END + " BIGINT,"
                    + COLUMN_COLOR + " TEXT, "
                    + COLUMN_ICON + " TEXT, "
                    + COLUMN_NOTIFICATIONS + " BOOLEAN, "
                    + COLUMN_FREQUENCY + " BIGINT, "
                    + COLUMN_COLOR + " BIGINT"
                    + ")";
}
