package com.nastya.calendar.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nastya.calendar.Data.EventTable;
import com.nastya.calendar.Domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EventDBHelper extends SQLiteOpenHelper implements IEventDBHelper{
    private static final String quote = "//~//";

    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME;

    public EventDBHelper(Context context, long millis) {
        super(context, ("database_"+ (new Date(millis).getMonth()) + "_" + new Date(millis).getYear()), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EventTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EventTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public long insertEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String eventName = event.getEventName().replaceAll("'", "\\~\\");
        String eventInfo = event.getEventInfo().replaceAll("'", "\\~\\");

        values.put(EventTable.COLUMN_NAME, eventName);
        values.put(EventTable.COLUMN_INFO, eventInfo);
        values.put(EventTable.COLUMN_START, event.getEventStart());
        values.put(EventTable.COLUMN_END, event.getEventFinish());
        values.put(EventTable.COLUMN_COLOR, event.getEventColor());
        values.put(EventTable.COLUMN_ICON, event.getEventIcon());
        values.put(EventTable.COLUMN_NOTIFICATIONS, event.isEnabledNotifications());

        long id = db.insert(EventTable.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public Event getEvent(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(EventTable.TABLE_NAME,
                new String[]{EventTable.COLUMN_ID, EventTable.COLUMN_NAME,
                        EventTable.COLUMN_INFO, EventTable.COLUMN_START,
                        EventTable.COLUMN_END,
                        EventTable.COLUMN_COLOR, EventTable.COLUMN_ICON,
                        EventTable.COLUMN_NOTIFICATIONS},
                EventTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Event event = new Event(
                cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_INFO)),
                cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_START)),
                cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_END)),
                cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_COLOR)),
                cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_ICON)),
                cursor.getInt(cursor.getColumnIndex(EventTable.COLUMN_NOTIFICATIONS)) > 0
        );

        cursor.close();

        return event;
    }

    public List<Event> readAllEvents() {
        List<Event> events = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + EventTable.TABLE_NAME + " ORDER BY " +
                EventTable.COLUMN_NAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Event event = new Event(
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_INFO)),
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_START)),
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_END)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_ICON)),
                        cursor.getInt(cursor.getColumnIndex(EventTable.COLUMN_NOTIFICATIONS)) > 0
                );
                events.add(event);
            } while (cursor.moveToNext());
        }

        db.close();
        return events;
    }

    public ArrayList<Event> readByDay(long date) {
        ArrayList<Event> events = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + EventTable.TABLE_NAME + " WHERE "+ EventTable.COLUMN_START + " = "+ date;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Event event = new Event(
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_INFO)),
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_START)),
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_END)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_ICON)),
                        cursor.getInt(cursor.getColumnIndex(EventTable.COLUMN_NOTIFICATIONS)) > 0
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

        String eventName = event.getEventName().replaceAll("'", "\\~\\");
        String eventInfo = event.getEventInfo().replaceAll("'", "\\~\\");

        values.put(EventTable.COLUMN_NAME, eventName);
        values.put(EventTable.COLUMN_INFO, eventInfo);
        values.put(EventTable.COLUMN_START, event.getEventStart());
        values.put(EventTable.COLUMN_END, event.getEventFinish());
        values.put(EventTable.COLUMN_COLOR, event.getEventColor());
        values.put(EventTable.COLUMN_ICON, event.getEventIcon());
        values.put(EventTable.COLUMN_NOTIFICATIONS, event.isEnabledNotifications());

        return db.update(EventTable.TABLE_NAME, values, EventTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(event.getEventId())});
    }

    public void deleteEvent(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EventTable.TABLE_NAME, EventTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
