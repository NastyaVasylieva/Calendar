package com.nastya.calendar.Model;

public class Event {
    public static final String TABLE_NAME = "events";

    public Event(long eventId, String eventName, String eventInfo, long eventDate, String eventColor, String eventIcon, boolean isEnabledNotifications) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventInfo = eventInfo;
        this.eventDate = eventDate;
        this.eventColor = eventColor;
        this.eventIcon = eventIcon;
        this.isEnabledNotifications = isEnabledNotifications;
    }

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INFO = "info";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_ICON = "icon";
    public static final String COLUMN_NOTIFICATIONS = "notifications";


    private long eventId;
    private String eventName;
    private String eventInfo;
    private long eventDate; //miliseconds
    private String eventColor;
    private String eventIcon;
    private boolean isEnabledNotifications;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_INFO + " TEXT,"
                    + COLUMN_DATE + " BIGINT,"
                    + COLUMN_COLOR + " TEXT, "
                    + COLUMN_ICON + " TEXT, "
                    + COLUMN_NOTIFICATIONS + " BOOLEAN, "
                    + ")";

    public long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventInfo() {
        return eventInfo;
    }
    public long getEventDate() {
        return eventDate;

    }

    public String getEventColor() {
        return eventColor;
    }

    public String getEventIcon() {
        return eventIcon;
    }

    public boolean isEnabledNotifications() {
        return isEnabledNotifications;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId &&
                eventDate == event.eventDate &&
                isEnabledNotifications == event.isEnabledNotifications &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(eventInfo, event.eventInfo) &&
                Objects.equals(eventColor, event.eventColor) &&
                Objects.equals(eventIcon, event.eventIcon);
    }

    @Override
    public int hashCode() {

        return Objects.hash(eventId, eventName, eventInfo, eventDate, eventColor, eventIcon, isEnabledNotifications);
    }*/
}
