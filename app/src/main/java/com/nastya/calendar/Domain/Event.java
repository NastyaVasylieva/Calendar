package com.nastya.calendar.Domain;

import com.nastya.calendar.Styles.LessonIcons;
import com.nastya.calendar.Styles.Theme;

import java.util.Objects;

public class Event {
    private int eventId;
    private String eventName;
    private String eventInfo;
    private long eventDate; //miliseconds
    private Theme eventColor;
    private LessonIcons eventIcon;
    private boolean isEnabledNotifications;

    public int getEventId() {
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

    public Theme getEventColor() {
        return eventColor;
    }

    public LessonIcons getEventIcon() {
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
