package com.nastya.calendar.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.nastya.calendar.Data.EventTable;
import com.nastya.calendar.Data.RegularEventTable;
import com.nastya.calendar.Domain.RegularEvent;

import java.util.ArrayList;
import java.util.List;

public class RegularEventDBHelper extends SQLiteOpenHelper implements IRegularEventDBHelper {
    private static final String quote = "//~//";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "events_db";

    public RegularEventDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(RegularEventTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RegularEventTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertEvent(RegularEvent regularEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String eventName = regularEvent.getEventName().replaceAll("'", quote);
        String eventInfo = regularEvent.getEventInfo().replaceAll("'", quote);

        values.put(RegularEventTable.COLUMN_NAME, eventName);
        values.put(RegularEventTable.COLUMN_INFO, eventInfo);
        values.put(RegularEventTable.COLUMN_START, regularEvent.getEventStart());
        values.put(RegularEventTable.COLUMN_END, regularEvent.getEventFinish());
        values.put(RegularEventTable.COLUMN_COLOR, regularEvent.getEventColor());
        values.put(RegularEventTable.COLUMN_ICON, regularEvent.getEventIcon());
        values.put(RegularEventTable.COLUMN_NOTIFICATIONS, regularEvent.isEnabledNotifications());
        values.put(RegularEventTable.COLUMN_FREQUENCY, regularEvent.getEventFrequency());


        long id = db.insert(RegularEventTable.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public RegularEvent getEvent(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(RegularEventTable.TABLE_NAME,
                new String[]{RegularEventTable.COLUMN_ID, RegularEventTable.COLUMN_NAME,
                        RegularEventTable.COLUMN_INFO, RegularEventTable.COLUMN_START,
                        RegularEventTable.COLUMN_END,
                        RegularEventTable.COLUMN_COLOR, RegularEventTable.COLUMN_ICON,
                        RegularEventTable.COLUMN_NOTIFICATIONS, RegularEventTable.COLUMN_FREQUENCY},
                RegularEventTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        RegularEvent event = new RegularEvent(
                cursor.getString(cursor.getColumnIndex(RegularEventTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(RegularEventTable.COLUMN_INFO)),
                cursor.getLong(cursor.getColumnIndex(RegularEventTable.COLUMN_START)),
                cursor.getLong(cursor.getColumnIndex(RegularEventTable.COLUMN_END)),
                cursor.getString(cursor.getColumnIndex(RegularEventTable.COLUMN_COLOR)),
                cursor.getString(cursor.getColumnIndex(RegularEventTable.COLUMN_ICON)),
                cursor.getInt(cursor.getColumnIndex(RegularEventTable.COLUMN_NOTIFICATIONS)) > 0,
                cursor.getLong(cursor.getColumnIndex(RegularEventTable.COLUMN_FREQUENCY)),
                cursor.getLong(cursor.getColumnIndex(RegularEventTable.COLUMN_DURATION))
                );

        cursor.close();

        return event;
    }

    public List<RegularEvent> readAllEvents() {
        List<RegularEvent> events = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + RegularEventTable.TABLE_NAME + " ORDER BY " +
                RegularEventTable.COLUMN_NAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RegularEvent regularEvent = new RegularEvent(
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_INFO)),
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_START)),
                        cursor.getLong(cursor.getColumnIndex(EventTable.COLUMN_END)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(EventTable.COLUMN_ICON)),
                        cursor.getInt(cursor.getColumnIndex(EventTable.COLUMN_NOTIFICATIONS)) > 0,
                         cursor.getLong(cursor.getColumnIndex(RegularEventTable.COLUMN_FREQUENCY)),
                        cursor.getLong(cursor.getColumnIndex(RegularEventTable.COLUMN_DURATION))

                        );
                events.add(regularEvent);
            } while (cursor.moveToNext());
        }

        db.close();
        return events;
    }

    public int updateEvent(RegularEvent regularEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String eventName = regularEvent.getEventName().replaceAll("'", quote);
        String eventInfo = regularEvent.getEventInfo().replaceAll("'", quote);

        values.put(RegularEventTable.COLUMN_NAME, eventName);
        values.put(RegularEventTable.COLUMN_INFO, eventInfo);
        values.put(RegularEventTable.COLUMN_START, regularEvent.getEventStart());
        values.put(RegularEventTable.COLUMN_END, regularEvent.getEventFinish());
        values.put(RegularEventTable.COLUMN_COLOR, regularEvent.getEventColor());
        values.put(RegularEventTable.COLUMN_ICON, regularEvent.getEventIcon());
        values.put(RegularEventTable.COLUMN_NOTIFICATIONS, regularEvent.isEnabledNotifications());
        values.put(RegularEventTable.COLUMN_FREQUENCY, regularEvent.getEventFrequency());
        values.put(RegularEventTable.COLUMN_DURATION, regularEvent.getEventDuration());


        return db.update(EventTable.TABLE_NAME, values, EventTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(regularEvent.getEventId())});
    }

    public void deleteEvent(RegularEvent regularEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RegularEventTable.TABLE_NAME, RegularEventTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(regularEvent.getEventId())});
        db.close();
    }
}
