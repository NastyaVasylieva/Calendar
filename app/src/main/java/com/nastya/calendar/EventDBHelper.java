package com.nastya.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nastya.calendar.Model.Event;

import java.util.ArrayList;
import java.util.List;


public class EventDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "events_db";


    public EventDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Event.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Event.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Event.COLUMN_NAME, event.getEventName());
        values.put(Event.COLUMN_INFO, event.getEventInfo());
        values.put(Event.COLUMN_DATE, event.getEventDate());
        values.put(Event.COLUMN_COLOR, event.getEventColor());
        values.put(Event.COLUMN_ICON, event.getEventIcon());
        values.put(Event.COLUMN_NOTIFICATIONS, event.isEnabledNotifications());

        long id = db.insert(Event.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public Event getEvent(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Event.TABLE_NAME,
                new String[]{Event.COLUMN_ID, Event.COLUMN_NAME, Event.COLUMN_INFO, Event.COLUMN_DATE,
                        Event.COLUMN_COLOR, Event.COLUMN_ICON, Event.COLUMN_NOTIFICATIONS},
                Event.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Event event = new Event(
                cursor.getLong(cursor.getColumnIndex(Event.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Event.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Event.COLUMN_INFO)),
                cursor.getLong(cursor.getColumnIndex(Event.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(Event.COLUMN_COLOR)),
                cursor.getString(cursor.getColumnIndex(Event.COLUMN_ICON)),
                cursor.getInt(cursor.getColumnIndex(Event.COLUMN_NOTIFICATIONS)) > 0
        );

        cursor.close();

        return event;
    }

    public List<Event> readAllEvents() {
        List<Event> events = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Event.TABLE_NAME + " ORDER BY " +
                Event.COLUMN_NAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(
                        cursor.getLong(cursor.getColumnIndex(Event.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Event.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(Event.COLUMN_INFO)),
                        cursor.getLong(cursor.getColumnIndex(Event.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(Event.COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(Event.COLUMN_ICON)),
                        cursor.getInt(cursor.getColumnIndex(Event.COLUMN_NOTIFICATIONS)) > 0
                );
                events.add(event);
            } while (cursor.moveToNext());
        }

        db.close();
        return events;
    }

    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Event.COLUMN_NAME, event.getEventName());
        values.put(Event.COLUMN_INFO, event.getEventInfo());
        values.put(Event.COLUMN_DATE, event.getEventDate());
        values.put(Event.COLUMN_COLOR, event.getEventColor());
        values.put(Event.COLUMN_ICON, event.getEventIcon());
        values.put(Event.COLUMN_NOTIFICATIONS, event.isEnabledNotifications());

        return db.update(Event.TABLE_NAME, values, Event.COLUMN_ID + " = ?",
                new String[]{String.valueOf(event.getEventId())});
    }

    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Event.TABLE_NAME, Event.COLUMN_ID + " = ?",
                new String[]{String.valueOf(event.getEventId())});
        db.close();
    }
}
